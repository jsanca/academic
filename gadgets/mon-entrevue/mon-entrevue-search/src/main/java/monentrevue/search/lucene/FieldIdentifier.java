package monentrevue.search.lucene;

import org.apache.lucene.document.Field;

/**
 * Defines a stored but not indexed field.
 * Date: 8/29/14
 * Time: 4:12 PM
 * @author jsanca
 */
public class FieldIdentifier extends org.apache.lucene.document.Field {

    /**
     * Constructor.
     *
     * @param fieldName name field
     * @param fieldValue value field
     */
    public FieldIdentifier(final String fieldName, final String fieldValue) {

        super(fieldName, fieldValue, Field.Store.YES, Field.Index.NO);
    } // FieldIdentifier
} // E:O:F:FieldIdentifier
