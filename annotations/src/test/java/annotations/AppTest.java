package annotations;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static annotations.AnnotationUtils.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }


    public void testFullStaticApp() throws NoSuchMethodException {
        assertTrue( isBeanAnnotatedBy(Publisher.class, PublisherConfig.class)  );
        PublisherConfig publisherConfig =
                getBeanAnnotation(Publisher.class, PublisherConfig.class);
        assertNotNull(publisherConfig);
        assertTrue( publisherConfig.isStatic() );

        assertTrue( isMethodAnnotatedBy(Publisher.class.getMethod("isStatic"), PublisherConfig.class)  );
        PublisherConfig publisherConfigMethod =
                getMethodAnnotation(Publisher.class.getMethod("isStatic"), PublisherConfig.class);
        assertNotNull(publisherConfigMethod);
        assertTrue( publisherConfigMethod.isStatic() );


        assertTrue( isFieldAnnotatedBy("staticField", Publisher.class, FieldPublisherConfig.class)  );
        FieldPublisherConfig publisherConfigField =
                getAttributeAnnotation("staticField", Publisher.class, FieldPublisherConfig.class);
        assertNotNull(publisherConfigField);
        assertTrue( publisherConfigField.isStatic() );
    }

    public void testNonStaticApp() throws NoSuchMethodException {
        assertTrue( isBeanAnnotatedBy(PublisherNonStatic.class, PublisherConfig.class)  );
        PublisherConfig publisherConfig =
                getBeanAnnotation(PublisherNonStatic.class, PublisherConfig.class);
        assertNotNull(publisherConfig);
        assertTrue( !publisherConfig.isStatic() );

        assertTrue( isMethodAnnotatedBy(PublisherNonStatic.class.getMethod("isStatic"), PublisherConfig.class)  );
        PublisherConfig publisherConfigMethod =
                getMethodAnnotation(PublisherNonStatic.class.getMethod("isStatic"), PublisherConfig.class);
        assertNotNull(publisherConfigMethod);
        assertTrue( !publisherConfigMethod.isStatic() );


        assertTrue( isFieldAnnotatedBy("staticField", PublisherNonStatic.class, FieldPublisherConfig.class)  );
        FieldPublisherConfig publisherConfigField =
                getAttributeAnnotation("staticField", PublisherNonStatic.class, FieldPublisherConfig.class);
        assertNotNull(publisherConfigField);
        assertTrue( !publisherConfigField.isStatic() );
    }

    public void testNonAnnotatedApp() throws NoSuchMethodException {
        assertTrue( !isBeanAnnotatedBy(PublisherNonAnnotated.class, PublisherConfig.class)  );
        PublisherConfig publisherConfig =
                getBeanAnnotation(PublisherNonAnnotated.class, PublisherConfig.class);
        assertNull(publisherConfig);

        assertTrue( !isMethodAnnotatedBy(PublisherNonAnnotated.class.getMethod("isStatic"), PublisherConfig.class)  );
        PublisherConfig publisherConfigMethod =
                getMethodAnnotation(PublisherNonAnnotated.class.getMethod("isStatic"), PublisherConfig.class);
        assertNull(publisherConfigMethod);


        assertTrue( !isFieldAnnotatedBy("staticField", PublisherNonAnnotated.class, FieldPublisherConfig.class)  );
        FieldPublisherConfig publisherConfigField =
                getAttributeAnnotation("staticField", PublisherNonAnnotated.class, FieldPublisherConfig.class);
        assertNull(publisherConfigField);
    }
}
