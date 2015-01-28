package cr.prodigious.dao;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.Serializable;

/**
 * Data Base Helper plus Spring support.
 *
 * @author jsanca
 */
public class SpringDataBaseHelper implements InitializingBean, DisposableBean, ApplicationContextAware, Serializable {

    private DataBaseHelper dataBaseHelper = null;

    private ApplicationContext applicationContext = null;

    private String dataBaseName = "dataBase";

    public void setDataBaseName(String dataBaseName) {

        this.dataBaseName = dataBaseName;
    }

    @Override
    public void destroy() throws Exception {

        if (null == this.dataBaseHelper) {

            if (null != applicationContext) {

                this.dataBaseHelper =
                        (DataBaseHelper)this.applicationContext.getBean(this.dataBaseName);
            }
        }

        if (null != this.dataBaseHelper) {

            this.dataBaseHelper.close();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        if (null != applicationContext) {

            this.dataBaseHelper =
                    (DataBaseHelper)this.applicationContext.getBean(this.dataBaseName);
        }

        if (null != this.dataBaseHelper) {

            this.dataBaseHelper.init();
        }
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.applicationContext =
                applicationContext;
    }
} // E:O:F:SpringDataBaseHelper.
