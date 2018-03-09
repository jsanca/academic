package com.github.jsanca.scheduler;

import java.io.Serializable;

/**
 * Encapsulates an user interface
 * @author jsanca
 */
public interface UserInterface extends Serializable {

    /**
     * Shows a message to the user.
     * @param message String
     */
    void message (String message);

} // E:O:F:UserInterface.
