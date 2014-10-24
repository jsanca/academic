package jsanca.utils.strategy;

import java.io.Serializable;

/**
 * Defines an input stream strategy that will runs on background.
 *
 * @author jsanca
 */
public interface InputStreamStrategy extends Serializable {

    /**
     * Called when a read method is invoked
     * @param aByte
     */
    void onReadMethod(int aByte);

    /**
     * Called when a read method is invoked
     * @param bytes
     */
    void onReadMethod(byte[] bytes);


    /**
     * Called when a read method is invoked
     * @param b
     * @param off
     * @param numBytes
     */
    void onReadMethod(byte[] b, int off, int numBytes);

    /**
     * Called when close method is invoked
     */
    void onClose();
} // E:O:F:InputStreamStrategy.
