package jsanca.download.internal.execution;

import java.util.Objects;

/**
 * Execution context for a running download.
 *
 * <p>This object groups runtime collaborators required by a {@code DownloadStrategy}
 * without polluting its method signature with multiple parameters.</p>
 *
 * <p>It currently contains coordination primitives such as {@link PauseCoordinator},
 * but it can be extended in the future to include additional runtime concerns
 * (e.g. event emitters, progress reporters, configuration, etc.).</p>
 */
public final class DownloadExecutionContext {

    private final PauseCoordinator pauseCoordinator;

    /**
     * Creates a new execution context.
     *
     * @param pauseCoordinator coordinator responsible for handling pause/resume semantics
     */
    public DownloadExecutionContext(final PauseCoordinator pauseCoordinator) {
        this.pauseCoordinator = Objects.requireNonNull(pauseCoordinator, "pauseCoordinator cannot be null");
    }

    /**
     * Returns the {@link PauseCoordinator} associated with this execution.
     *
     * @return pause coordinator
     */
    public PauseCoordinator pauseCoordinator() {
        return this.pauseCoordinator;
    }
}
