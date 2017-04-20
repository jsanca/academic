package annotations;


@PublisherConfig (isStatic =  true)
public class Publisher {

    @FieldPublisherConfig (isStatic = true)
    private boolean staticField;

    @PublisherConfig (isStatic = true)
    public boolean isStatic () {
        return true;
    }
}
