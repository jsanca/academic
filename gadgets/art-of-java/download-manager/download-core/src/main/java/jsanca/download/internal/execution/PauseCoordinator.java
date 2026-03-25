
package jsanca.download.internal.execution;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Coordinates cooperative pause and resume requests for active downloads.
 *
 * <p>This coordinator keeps pause state outside of {@code DownloadTask} and
 * allows a running download to block at safe checkpoints until execution is
 * resumed.
 * @author jsanca & elo
 */
public final class PauseCoordinator {

    private static final long PAUSE_POLL_MILLIS = 250L;

    private final ConcurrentMap<String, PauseState> states;

    public PauseCoordinator() {
        this.states = new ConcurrentHashMap<>();
    }

    /**
     * Requests pause for the given download.
     *
     * @param downloadId the download identifier
     */
    public void requestPause(final String downloadId) {
        final PauseState state = this.states.computeIfAbsent(
                Objects.requireNonNull(downloadId, "downloadId must not be null"),
                ignored -> new PauseState()
        );
        state.requestPause();
    }

    /**
     * Requests resume for the given download and wakes any waiting thread.
     *
     * @param downloadId the download identifier
     */
    public void requestResume(final String downloadId) {
        final PauseState state = this.states.get(Objects.requireNonNull(downloadId, "downloadId must not be null"));
        if (state != null) {
            state.requestResume();
        }
    }

    /**
     * Returns whether pause has been requested for the given download.
     *
     * @param downloadId the download identifier
     * @return {@code true} if pause has been requested; {@code false} otherwise
     */
    public boolean isPauseRequested(final String downloadId) {
        final PauseState state = this.states.get(Objects.requireNonNull(downloadId, "downloadId must not be null"));
        return state != null && state.isPauseRequested();
    }

    /**
     * Waits while pause is requested for the given download.
     *
     * <p>This method performs cooperative waiting and periodically re-checks the
     * supplied cancellation token so a paused download can still terminate when
     * cancellation is requested.
     *
     * @param downloadId the download identifier
     * @param cancellationToken the cancellation token associated with the running download
     * @param pauseListener monitor callbacks to handle the pause and resume
     * @throws InterruptedException if the waiting thread is interrupted
     */
    public void awaitIfPaused(final String downloadId,
                              final CancellationToken cancellationToken,
                              final PauseListener pauseListener) throws InterruptedException {

        Objects.requireNonNull(downloadId, "downloadId must not be null");
        Objects.requireNonNull(cancellationToken, "cancellationToken must not be null");
        Objects.requireNonNull(pauseListener, "pauseListener must not be null");

        final PauseState state = this.states.get(downloadId);
        if (state == null) {
            return;
        }

        state.awaitIfPaused(cancellationToken, pauseListener);
    }

    /**
     * Removes any pause coordination state for the given download.
     *
     * @param downloadId the download identifier
     */
    public void clear(final String downloadId) {
        final PauseState state = this.states.remove(Objects.requireNonNull(downloadId, "downloadId must not be null"));
        if (state != null) {
            state.requestResume();
        }
    }

    private static final class PauseState {

        private final ReentrantLock lock;
        private final Condition resumed;
        private boolean pauseRequested;

        private PauseState() {
            this.lock = new ReentrantLock();
            this.resumed = this.lock.newCondition();
            this.pauseRequested = false;
        }

        private void requestPause() {
            this.lock.lock();
            try {
                this.pauseRequested = true;
            } finally {
                this.lock.unlock();
            }
        }

        private void requestResume() {
            this.lock.lock();
            try {
                this.pauseRequested = false;
                this.resumed.signalAll();
            } finally {
                this.lock.unlock();
            }
        }

        private boolean isPauseRequested() {
            this.lock.lock();
            try {
                return this.pauseRequested;
            } finally {
                this.lock.unlock();
            }
        }

        private void awaitIfPaused(final CancellationToken cancellationToken,
                                   final PauseListener pauseListener) throws InterruptedException {

            boolean enteredPausedWait = false;
            boolean resumedNormally = false;
            boolean pauseNotified = false;

            this.lock.lock();
            try {
                while (this.pauseRequested) {
                    if (!enteredPausedWait) {
                        enteredPausedWait = true;
                    }

                    pauseNotified = emitPauseIfNeeded(pauseListener, pauseNotified);

                    if (cancellationToken.isCancelled()) {
                        return;
                    }

                    this.resumed.await(PAUSE_POLL_MILLIS, TimeUnit.MILLISECONDS);
                }

                if (enteredPausedWait) {
                    resumedNormally = true;
                }
            } finally {
                this.lock.unlock();
            }

            if (enteredPausedWait && resumedNormally) {
                pauseListener.onResumed();
            }
        }

        private boolean emitPauseIfNeeded(final PauseListener pauseListener,
                                          final boolean pauseNotified) {
            if (!pauseNotified) {

                this.lock.unlock();
                try {
                    pauseListener.onPaused();
                } finally {
                    this.lock.lock();
                }
                return true;
            }
            return pauseNotified;
        }
    }
}
