package cr.prodigious.dao;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Data Base Helper plus Spring support.
 *
 * @author jsanca
 */
public class SpringDataBaseHelper extends DataBaseHelper implements InitializingBean, DisposableBean {


    @Override
    public void destroy() throws Exception {


        this.close();
    }

    @Override
    public void afterPropertiesSet() throws Exception {


        this.init();
    }
} // E:O:F:SpringDataBaseHelper.
