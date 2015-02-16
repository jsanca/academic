package cr.prodigious.helper;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * MD5 impl
 * @author jsanca
 */
public class MD5StringEncoderImpl implements StringEncoder {

    private static final String UTF_8 = "UTF-8";

    /**
     * Encode a string
     *
     * @param string unencoded string
     * @return String encoded string
     */
    @Override
    public String encode(String string) {

        String encodeString = null;

        try {

            byte [] utf8Array =
                    string.getBytes(UTF_8);

            byte [] encodedArray =
                    DigestUtils.md5(utf8Array);

            encodeString =
                    new String(encodedArray, UTF_8);
        } catch (UnsupportedEncodingException e) {

            encodeString =
                    string;
        }

        return encodeString;
    } // encode.
} // E:O:F:MD5StringEncoderImpl.
