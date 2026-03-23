package jsanca.download.internal.execution;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Cooperative cancellation token for download execution.
 *
 * <p>This token allows one component to request cancellation while another
 * component periodically checks whether cancellation has been requested.
 * It is intentionally lightweight and thread-safe.
 * @author jsanca & elo
 */
public final class CancellationToken {

    private final AtomicBoolean cancelled = new AtomicBoolean(false);

    /**
     * Marks this token as cancelled.
     *
     * <p>This operation is idempotent. Calling it multiple times has the same
     * effect as calling it once.
     */
    public void cancel() {
        this.cancelled.set(true);
    }

    /**
     * Returns whether cancellation has been requested.
     *
     * @return {@code true} if cancellation was requested; {@code false} otherwise
     */
    public boolean isCancelled() {
        return this.cancelled.get();
    }
}
