package helianthus.core.util.springframework;

import helianthus.core.util.InputStreamUtils;
import helianthus.core.util.OperationMappingHelper;
import helianthus.core.util.ParseQueryConfigurationUtil;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.io.InputStream;

/**
 * Spring factory bean to parse and create the query configuration helper
 *
 * Date: 5/20/14
 * Time: 7:14 PM
 * @author jsanca
 */
public class SpringQueryConfigurationFactoryBean implements FactoryBean<OperationMappingHelper>,
        InitializingBean {

    private OperationMappingHelper operationMappingHelper = null;

    private String queryConfigPath = null;

    private ParseQueryConfigurationUtil parseQueryConfigurationUtil =
            new ParseQueryConfigurationUtil();

    @Override
    public void afterPropertiesSet() throws Exception {

        InputStream inputStream = null;

        try {

            inputStream = InputStreamUtils.getInputStream
                    (this.queryConfigPath);

            this.operationMappingHelper =
                    this.parseQueryConfigurationUtil.parse
                            (inputStream);
        } finally {

            if (null != inputStream) {

                inputStream.close();
            }
        }
    }

    @Override
    public OperationMappingHelper getObject() throws Exception {

        return this.operationMappingHelper;
    }

    @Override
    public Class<?> getObjectType() {
        return OperationMappingHelper.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setQueryConfigPath(String queryConfigPath) {

        this.queryConfigPath = queryConfigPath;
    }
} // E:O:F:SpringQueryConfigurationFactoryBean.
