package spring.tutorial;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;

/**
 * Created by jsanchez8 on 10/7/14.
 */
public class MapCollectionExample {

    private Map<String, Integer> map = null;

    private Properties properties = null;

    private Set<Object> set;

    private List<Object> list;

    public Map<String, Integer> getMap() {
        return map;
    }

    public void setMap(Map<String, Integer> map) {
        this.map = map;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Set<Object> getSet() {
        return set;
    }

    public void setSet(Set<Object> set) {
        this.set = set;
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MapCollectionExample{");
        sb.append("map=").append(map);
        sb.append(", properties=").append(properties);
        sb.append(", set=").append(set);
        sb.append(", list=").append(list);
        sb.append('}');
        return sb.toString();
    }
}
