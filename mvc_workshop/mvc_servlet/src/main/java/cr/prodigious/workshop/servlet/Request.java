package cr.prodigious.workshop.servlet;

import java.io.Serializable;

/**
 * Custom Wrapper for the request
 *
 * Date: 4/2/14
 * Time: 4:30 PM
 * @author jsanca
 */
public interface Request extends Serializable {

    /**
     * Get the request path
     * @return
     */
    String getPath();

    /**
     * Get App Content
     * @return
     */
    Context getContext();

    /**
     * Set a value to the request
     * @param key  String
     * @param value value
     */
    void set(String key, Object value);
} // E:O:F:Request
