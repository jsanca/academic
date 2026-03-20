package jsanca.download.internal.execution;

import jsanca.download.api.model.DownloadConfig;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class DownloadExecutorsTest {

    @Test
    void shouldExecuteTasksUsingVirtualThreads() throws InterruptedException {

        final DownloadExecutor executor = DownloadExecutors.createVirtualThreadExecutor(
                DownloadConfig.defaultConfig()
        );

        final int tasks = 10;
        final CountDownLatch latch = new CountDownLatch(tasks);
        final List<String> threadNames = Collections.synchronizedList(new ArrayList<>());

        for (int i = 0; i < tasks; i++) {
            executor.execute(() -> {
                threadNames.add(Thread.currentThread().toString());
                latch.countDown();
            });
        }

        final boolean completed = latch.await(5, TimeUnit.SECONDS);

        assertTrue(completed, "Tasks did not complete in time");
        assertEquals(tasks, threadNames.size());

        // Verify virtual threads (name usually contains "VirtualThread")
        assertTrue(threadNames.stream().anyMatch(name -> name.contains("VirtualThread")),
                "Expected at least one virtual thread");
    }

    @Test
    void shouldRespectConcurrencyLimit() throws InterruptedException {

        final int maxConcurrent = 2;
        final DownloadExecutor executor = DownloadExecutors.createVirtualThreadExecutor(
                new DownloadConfig(maxConcurrent)
        );

        final int tasks = 6;
        final CountDownLatch latch = new CountDownLatch(tasks);
        final List<Long> executionTimes = Collections.synchronizedList(new ArrayList<>());

        for (int i = 0; i < tasks; i++) {
            executor.execute(() -> {
                try {
                    executionTimes.add(System.currentTimeMillis());
                    Thread.sleep(200);
                } catch (InterruptedException ignored) {
                } finally {
                    latch.countDown();
                }
            });
        }

        final boolean completed = latch.await(5, TimeUnit.SECONDS);

        assertTrue(completed, "Tasks did not complete in time");

        // With concurrency=2 and sleep=200ms, total time should be at least ~600ms
        final long duration = executionTimes.get(executionTimes.size() - 1) - executionTimes.get(0);
        assertTrue(duration >= 400, "Concurrency limit not respected");
    }

    @Test
    void shouldThrowOnNullTask() {

        final DownloadExecutor executor = DownloadExecutors.createVirtualThreadExecutor(
                DownloadConfig.defaultConfig()
        );

        assertThrows(NullPointerException.class, () -> executor.execute(null));
    }
}
