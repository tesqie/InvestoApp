package investoapp.Securities;

/**
 *
 * @author tesqie
 */
public class Securities {
    private final String Tag;
    private final String operator;
    private final Double value;

    public Securities(String Tag, String operator, Double value) {
        this.Tag = Tag;
        this.operator = operator;
        this.value = value;
    }

    public String getTag() {
        return Tag;
    }

    public String getOperator() {
        return operator;
    }

    public Double getValue() {
        return value;
    }

    
    
    
    
    
}
