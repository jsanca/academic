package monentrevue.reader;

import monentrevue.bean.Interview;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Test for TabLiInterviewReader
 *
 * @author jsanca
 */
@RunWith(JUnit4.class)
public class TabLiInterviewReaderTest {

    @Test
    public void readTest() throws IOException {

        final String file = "entrevue-test1.q";
        InputStream stream = null;
        InterviewReader interviewReader = null;
        Interview interview = null;

        stream =
                this.getClass()
                        .getClassLoader().
                        getResourceAsStream(file);
        try {

            interviewReader =
                    new TabLiInterviewReader();

            interview =
                    interviewReader.read(new InputStreamReader(stream));

            org.junit.Assert.assertNotNull(interview);
            org.junit.Assert.assertTrue(interview.getQuestions().size() == 4);

            org.junit.Assert.assertTrue("interview1".equals(interview.getNameId()));
            org.junit.Assert.assertTrue("A test interview".equals(interview.getTitle()));
            org.junit.Assert.assertTrue("A test interview for unit test".equals(interview.getNote()));

            org.junit.Assert.assertTrue("1".equals(interview.getQuestions().get(0).getNameId()));
            org.junit.Assert.assertTrue("Question 1".equals(interview.getQuestions().get(0).getText()));
            org.junit.Assert.assertTrue("beginner".equals(interview.getQuestions().get(0).getType()));
            org.junit.Assert.assertTrue("An really easy question".equals(interview.getQuestions().get(0).getNote()));

            org.junit.Assert.assertTrue("2".equals(interview.getQuestions().get(1).getNameId()));
            org.junit.Assert.assertTrue("Question 2".equals(interview.getQuestions().get(1).getText()));
            org.junit.Assert.assertTrue("beginner".equals(interview.getQuestions().get(1).getType()));
            org.junit.Assert.assertTrue("Another really easy question".equals(interview.getQuestions().get(1).getNote()));

            org.junit.Assert.assertTrue("3".equals(interview.getQuestions().get(2).getNameId()));
            org.junit.Assert.assertTrue("Question 3".equals(interview.getQuestions().get(2).getText()));
            org.junit.Assert.assertTrue("expert".equals(interview.getQuestions().get(2).getType()));
            org.junit.Assert.assertTrue("Hardest question".equals(interview.getQuestions().get(2).getNote()));


            org.junit.Assert.assertTrue("4".equals(interview.getQuestions().get(3).getNameId()));
            org.junit.Assert.assertTrue("Just a question".equals(interview.getQuestions().get(3).getText()));


        } finally {

            if (null != stream) {

                stream.close();
            }
        }

    } // readTest.
} // E:O:F:TabLiInterviewReaderTest.
