package com.github.jsanca.scheduler;

/**
 * Generic exception for the scheduler
 * @author jsanca
 */
public class SchedulerException extends RuntimeException {

    public SchedulerException() {
    }

    public SchedulerException(String message) {
        super(message);
    }

    public SchedulerException(String message, Throwable cause) {
        super(message, cause);
    }

    public SchedulerException(Throwable cause) {
        super(cause);
    }
}
