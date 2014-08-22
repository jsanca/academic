package tagliparser.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Represent an item of a list and can contains a list inside as well (for the child nodes)
 *
 * Date: 8/21/14
 * Time: 4:51 PM
 * @author jsanca
 */
public class Item<T> implements Serializable {

    private T value;
    private List<Item<T>> list;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public List<Item<T>> getList() {
        return list;
    }

    public void setList(List<Item<T>> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Item");
        sb.append("{value=").append(value);
        sb.append(", list size=").append((null != list)?list.size():0);
        sb.append('}');
        return sb.toString();
    }
} // E:O:F:Item.
