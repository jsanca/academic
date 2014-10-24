package jsanca.utils;

import jsanca.utils.strategy.InputStreamStrategy;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Wrapper of a InputStream, it receives a Strategy that will handles the background operation.
 * @author jsanca
 */
public class InputStreamWrapper extends FilterInputStream {

    private InputStreamStrategy inputStreamStrategy;

    /**
     * Creates a <code>FilterInputStream</code>
     * by assigning the  argument <code>in</code>
     * to the field <code>this.in</code> so as
     * to remember it for later use.
     *
     * @param in the underlying input stream, or <code>null</code> if
     *           this instance is to be created without an underlying stream.
     */
    protected InputStreamWrapper(final InputStream in, final InputStreamStrategy inputStreamStrategy) {

        super(in);
        this.inputStreamStrategy = inputStreamStrategy;
    } // InputStreamWrapper.

    @Override
    public int read() throws IOException {

        int aByte = super.read();

        this.inputStreamStrategy.onReadMethod(aByte);

        return aByte;
    } // read.

    @Override
    public int read(byte[] b) throws IOException {

        int numBytes =
                super.read(b);

        this.inputStreamStrategy.onReadMethod(b);

        return numBytes;
    } // read.

    @Override
    public int read(byte[] b, int off, int len) throws IOException {

        int numBytes =
                super.read(b, off, len);

        this.inputStreamStrategy.onReadMethod(b, off, numBytes);

        return numBytes;
    }

    /**
     * Closes this input stream and releases any system resources
     * associated with the stream.
     * This
     * method simply performs <code>in.close()</code>.
     *
     * @throws java.io.IOException if an I/O error occurs.
     * @see java.io.FilterInputStream#in
     */
    @Override
    public void close() throws IOException {

        this.inputStreamStrategy.onClose();
        super.close();
    }
} // E:O:F:InputStreamWrapper.
