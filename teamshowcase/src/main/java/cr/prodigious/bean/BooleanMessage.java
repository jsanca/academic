package cr.prodigious.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

/**
 * Simple Wrapper for a boolean
 * @author jsanca
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "value"
})
public class BooleanMessage implements Serializable {

    @JsonProperty("value")
    private boolean value;

    public BooleanMessage() {

    }

    public BooleanMessage(boolean value) {
        this.value = value;
    }

    @JsonProperty("value")
    public boolean isValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(boolean value) {
        this.value = value;
    }
} // E:O:F:BooleanMessage.
