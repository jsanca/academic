package cr.prodigious.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.List;

/**
 * Represents the Manager info
 * @author jsanca
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "order",
        "positions"
})
public class CapabilityBean implements Serializable {

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("order")
    private Integer order = null;

    @JsonProperty("positions")
    private List<PositionBean> positions;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("order")
    public Integer getOrder() {
        return order;
    }

    @JsonProperty("order")
    public void setOrder(Integer order) {
        this.order = order;
    }

    @JsonProperty("positions")
    public List<PositionBean> getPositions() {
        return positions;
    }

    @JsonProperty("positions")
    public void setPositions(List<PositionBean> positions) {
        this.positions = positions;
    }
}  // E:O:F:ManagerBean.
