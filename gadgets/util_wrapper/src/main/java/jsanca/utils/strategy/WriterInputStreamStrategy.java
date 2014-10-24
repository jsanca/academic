package jsanca.utils.strategy;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

/**
 * This strategy receives an writer and writes everything what is read on the input stream.
 *
 * Closing the writer is optional, by default the writer will be keep open it
 *
 * @author jsanca
 */
public class WriterInputStreamStrategy implements InputStreamStrategy {

    private Writer writer = null;

    private final static int BUFFER_SIZE = 516;

    private int bufferSize = BUFFER_SIZE;

    private int bufferCount = 0;

    private boolean closeIt = false;


    /**
     * Constructor
     *
     * @param writer
     */
    public WriterInputStreamStrategy(final Writer writer) {

        this(writer, false);
    }

    /**
     * Constructor
     *
     * @param writer
     * @param needToCloseTheWriter true if you want to close the writer at the same time that the input stream is closed, set it to true
     */
    public WriterInputStreamStrategy(final Writer writer, final boolean needToCloseTheWriter) {

        this.writer = writer;
        this.closeIt = needToCloseTheWriter;
    }

    public WriterInputStreamStrategy setBufferSize(final int bufferSize) {

        this.bufferSize = bufferSize;
        return this;
    }

    public WriterInputStreamStrategy setCloseIt(final boolean closeIt) {

        this.closeIt = closeIt;
        return this;
    }

    /**
     * Called when a read method is invoked
     *
     * @param aByte
     */
    @Override
    public void onReadMethod(final int aByte) {

        try {

            this.writer.write(aByte);
            this.checkFlush(1);
        } catch (IOException e) {
            // quiet
        }
    }

    /**
     * Called when a read method is invoked
     *
     * @param bytes
     */
    @Override
    public void onReadMethod(final byte[] bytes) {

        try {

            this.writer.write(new String(bytes));
            this.checkFlush(bytes.length);
        } catch (IOException e) {
            // quiet
        }
    }

    /**
     * Called when a read method is invoked
     *
     * @param bytes
     * @param off
     * @param len
     */
    @Override
    public void onReadMethod(byte[] bytes, int off, int len) {

        final int toIndex = (off + len < bytes.length)?
                // off + len: bytes.length - 1;
                off + len: bytes.length;
        String text = null;

        try {

            text =
                    new String(Arrays.copyOfRange(bytes, off, toIndex));
            this.writer.write(text);
            this.checkFlush(text.length());
        } catch (Exception e) {
            // quiet
        }
    }

    /**
     * Called when close method is invoked
     */
    @Override
    public void onClose() {

        if (this.closeIt) {

            try {
                this.writer.close();
            } catch (IOException e) {

                // quiet.
            }
        }
    }

    private void checkFlush (final int numBytes) throws IOException {

        this.bufferCount += numBytes;

        if (this.bufferCount >= this.bufferSize) {

            this.writer.flush();
            this.bufferCount = 0;
        }
    }
}  // E:O:F:WriterInputStreamStrategy.
