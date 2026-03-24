package jsanca.download.api.manager;

import jsanca.download.api.model.DownloadTaskSnapshot;
import jsanca.download.internal.model.DownloadTask;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Internal registry of active and known download tasks.
 *
 * <p>This component provides lookup and lifecycle management for download tasks,
 * primarily keyed by download identifier. It acts as the bridge between public
 * control operations (such as cancel) and the internal mutable runtime state
 * stored in {@link DownloadTask} instances.
 * @author jsanca & elo
 */
public final class DownloadRegistry {

    private final Map<String, DownloadTask> tasks = new ConcurrentHashMap<>();

    /**
     * Registers the provided task.
     *
     * @param task the task to register
     * @throws NullPointerException if {@code task} is {@code null}
     */
    public void register(final DownloadTask task) {
        Objects.requireNonNull(task, "task must not be null");
        this.tasks.put(task.info().downloadId(), task);
    }

    /**
     * Finds a task by download identifier.
     *
     * @param downloadId the download identifier
     * @return an optional containing the task when present
     * @throws NullPointerException if {@code downloadId} is {@code null}
     */
    public Optional<DownloadTask> findTask(final String downloadId) {
        Objects.requireNonNull(downloadId, "downloadId must not be null");
        return Optional.ofNullable(this.tasks.get(downloadId));
    }

    /**
     * Finds a snapshot by download identifier.
     *
     * @param downloadId the download identifier
     * @return an optional containing the task snapshot when present
     * @throws NullPointerException if {@code downloadId} is {@code null}
     */
    public Optional<DownloadTaskSnapshot> findSnapshot(final String downloadId) {
        return findTask(downloadId).map(DownloadTask::snapshot);
    }

    /**
     * Returns whether a task with the given identifier is registered.
     *
     * @param downloadId the download identifier
     * @return {@code true} if the task is present; {@code false} otherwise
     * @throws NullPointerException if {@code downloadId} is {@code null}
     */
    public boolean contains(final String downloadId) {
        Objects.requireNonNull(downloadId, "downloadId must not be null");
        return this.tasks.containsKey(downloadId);
    }

    /**
     * Removes a task from the registry.
     *
     * @param downloadId the download identifier
     * @return the removed task, if it existed
     * @throws NullPointerException if {@code downloadId} is {@code null}
     */
    public Optional<DownloadTask> remove(final String downloadId) {
        Objects.requireNonNull(downloadId, "downloadId must not be null");
        return Optional.ofNullable(this.tasks.remove(downloadId));
    }

    /**
     * Returns an immutable snapshot map of the current registry state.
     *
     * <p>The returned map is detached from the internal registry and contains
     * immutable task snapshots keyed by download identifier.
     *
     * @return immutable map of download snapshots
     */
    public Map<String, DownloadTaskSnapshot> snapshotAll() {
        final Map<String, DownloadTaskSnapshot> snapshots = new LinkedHashMap<>();
        for (Map.Entry<String, DownloadTask> entry : this.tasks.entrySet()) {
            snapshots.put(entry.getKey(), entry.getValue().snapshot());
        }
        return Collections.unmodifiableMap(snapshots);
    }

    /**
     * Returns the number of registered tasks.
     *
     * @return registry size
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns whether the registry is empty.
     *
     * @return {@code true} if empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Clears the registry.
     */
    public void clear() {
        this.tasks.clear();
    }
}
