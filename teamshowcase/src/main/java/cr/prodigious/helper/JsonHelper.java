package cr.prodigious.helper;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;

/**
 * Json Helper to marshall and unmarshall
 *
 * @author jsanca
 */
public class JsonHelper implements Serializable {

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Coverts the object to a json representation and write the json into the writer
     * @param writer
     * @param object
     * @throws IOException
     */
    public void write (final Writer writer, final Object object) throws IOException {

        this.mapper.writeValue(writer, object);
    } // write.

    /**
     * Converts an json from the reader to an object; needs the class in order to
     * determine which is the expected class
     * @param reader
     * @param aClass
     * @param <T>
     * @return T
     * @throws IOException
     */
    public <T> T read (final Reader reader, Class<? extends T> aClass) throws IOException {

        return this.mapper.readValue(reader, aClass);
    } // read.

    /**
     * Converts an json from a file to an object; needs the class in order to
     * determine which is the expected class
     * @param file
     * @param aClass
     * @param <T>
     * @return T
     */
    public <T> T read(final File file, final Class<T> aClass) throws IOException {

        return this.mapper.readValue(file, aClass);
    } // read

    /**
     * Converts an json from an input stream to an object; needs the class in order to
     * determine which is the expected class
     *
     * @param inputStream
     * @param aClass
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T> T read(final InputStream inputStream, final Class<T> aClass) throws IOException {

        return this.mapper.readValue(inputStream, aClass);
    } // read
} // E:O:F:JsonHelper.
