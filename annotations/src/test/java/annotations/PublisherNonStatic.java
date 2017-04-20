package annotations;


@PublisherConfig (isStatic =  false)
public class PublisherNonStatic {

    @FieldPublisherConfig (isStatic = false)
    private boolean staticField;

    @PublisherConfig (isStatic = false)
    public boolean isStatic () {
        return false;
    }
}
