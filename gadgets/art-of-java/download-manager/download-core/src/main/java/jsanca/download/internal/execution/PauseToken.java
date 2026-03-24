package jsanca.download.internal.execution;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Clears the pause request and allows execution to continue.
 *
 * <p>This operation is idempotent. Calling it multiple times has the same
 * effect as calling it once.
 * </p>
 *
 * @author jsanca & elo
 */
public final class PauseToken {

    private final AtomicBoolean pause = new AtomicBoolean(false);

    /**
     * Marks this token as paused.
     *
     * <p>This operation is idempotent. Calling it multiple times has the same
     * effect as calling it once.
     */
    public void pause() {
        this.pause.set(true);
    }

    /**
     * Marks this token as resumed.
     *
     * <p>This operation is idempotent. Calling it multiple times has the same
     * effect as calling it once.
     */
    public void resume() {
        this.pause.set(false);
    }

    /**
     * Returns whether pause has been requested.
     *
     * @return {@code true} if pause was requested; {@code false} otherwise
     */
    public boolean isPauseRequested() {
        return this.pause.get();
    }
}
