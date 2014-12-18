package cr.prodigious.bean;

import java.io.Serializable;

/**
 * Init Delegate
 * @author jsanca
 */
public interface InitDelegate extends Serializable {

    public  void init();

    public  void close();

} // E:O:F:InitDelegate.
