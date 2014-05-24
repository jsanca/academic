package helianthus.core.util;

import org.apache.commons.beanutils.PropertyUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Reflection Utils
 *
 * Date: 5/12/14
 * Time: 8:13 PM
 *
 * @author jsanca
 */
public class BeanUtils implements Serializable {

    private static Logger logger =
            Logger.getLogger(BeanUtils.class.getName());

    public static BeanUtils INSTANCE =
            new BeanUtils();

    public Map<String, String> describeBean (final Object bean) {

        Map<String, String> describeMap = null;

        try {

            describeMap =
                    org.apache.commons.beanutils.BeanUtils.describe(bean);
        } catch (Exception  e) {

            describeMap = Collections.EMPTY_MAP;
        }

        return describeMap;
    } // describeBean

    /**
     * Get a property bean as a collection
     *
     * @param bean Object
     * @param propertyName String
     * @return Collection
     */
    public Collection getPropertyCollectionBean (final Object bean, final String propertyName) {

        Collection collection = null;

        try {

            collection =
                    (Collection)PropertyUtils.getProperty(bean, propertyName);
        } catch (Exception  e) {

             collection = null;
        }

        return collection;
    } // getPropertyCollectionBean.

    /**
     * Get a property bean as an object
     *
     * @param bean Object
     * @param propertyName String
     * @return Object
     */
    public Object getPropertyBean (final Object bean, final String propertyName) {

        Object o = null;

        try {

            o =
               PropertyUtils.getProperty(bean, propertyName);
        } catch (Exception  e) {

            o = null;
        }

        return o;
    } // getPropertyCollectionBean.

} // E:O:F:BeanUtils.
