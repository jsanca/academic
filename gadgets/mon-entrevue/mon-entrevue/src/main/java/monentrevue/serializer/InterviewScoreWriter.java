package monentrevue.serializer;

import monentrevue.bean.InterviewScore;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * Write into an output stream an interview object
 * Date: 8/21/14
 * Time: 9:03 PM
 * @author jsanca
 */
public class InterviewScoreWriter implements Serializable {

    public void write (final InterviewScore interview,
                       final OutputStream outputStream) {

        ObjectOutputStream objectOutputStream = null;

        try {

            objectOutputStream =
                    new ObjectOutputStream(outputStream);

            objectOutputStream.writeObject(interview);

            objectOutputStream.flush();
        } catch (IOException e) {

            // todo: throw an exception.
        }
    } // write.

} // E:O:F:InterviewWriter.
