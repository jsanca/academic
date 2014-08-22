package monentrevue.serializer;

import monentrevue.bean.InterviewScore;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * Reader from an input stream an interview object
 * Date: 8/21/14
 * Time: 9:03 PM
 * @author jsanca
 */
public class InterviewScoreReader implements Serializable {

    public InterviewScore read (final InputStream inputStream) {

        InterviewScore interviewScore = null;
        ObjectInputStream objectInputStream = null;

        try {

            objectInputStream =
                    new ObjectInputStream(inputStream);

            interviewScore =
                    (InterviewScore)objectInputStream.readObject();
        } catch (IOException e) {

            // todo: throw an exception.
        } catch (ClassNotFoundException e) {

            // todo: throw an exception.
        }

        return interviewScore;
    } // write.

} // E:O:F:InterviewReader.
