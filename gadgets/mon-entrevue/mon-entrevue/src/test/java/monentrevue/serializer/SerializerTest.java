package monentrevue.serializer;

import monentrevue.bean.Choice;
import monentrevue.bean.Interview;
import monentrevue.bean.InterviewScore;
import monentrevue.bean.Interviewed;
import monentrevue.bean.Interviewer;
import monentrevue.bean.Question;
import monentrevue.bean.QuestionScore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Writer/Read test
 *
 * Date: 8/21/14
 * Time: 9:13 PM
 * @author jsanca
 */
@RunWith(JUnit4.class)
public class SerializerTest {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT =
            new SimpleDateFormat("dd/MM/yyyy");

    private static final String DEFAULT_DATE = "01/01/2014";


    static {

        SIMPLE_DATE_FORMAT.setLenient(false);
    }

    private Date createDefaultDate () {

        Date date = null;

        try {

            date =
                SIMPLE_DATE_FORMAT.parse(DEFAULT_DATE);
        } catch (ParseException e) {

            e.printStackTrace();
        }

        return date;
    }

    private InterviewScore createInterviewScore () {


        final Interview interview =
                new Interview();

        interview.setNameId("interview1.q");
        interview.setNote("Test interview");
        interview.setTitle("Title interview");

        final List<Question> questions =
                new ArrayList<Question>();

        final Question question1 =
                new Question();

        question1.setNameId("A");
        question1.setNote("Two choices");
        question1.setText("This is a great question");
        question1.setType("Futbol_Soccer");


        final List<Choice> choices =
                new ArrayList<Choice>();

        final Choice choice1 =
                new Choice();

        choice1.setNote("Note choice 1");
        choice1.setSuggestedScore(0);
        choice1.setText("Choice 1");
        choice1.setValid(false);

        choices.add(choice1);

        final Choice choice2 =
                new Choice();

        choice2.setNote("Note choice 2");
        choice2.setSuggestedScore(0);
        choice2.setText("Choice 2");
        choice2.setValid(false);

        choices.add(choice2);

        final Choice choice3 =
                new Choice();

        choice3.setNote("Note choice 3");
        choice3.setSuggestedScore(4);
        choice3.setText("Choice 3");
        choice3.setValid(true);

        choices.add(choice3);


        final Choice choice4 =
                new Choice();

        choice4.setNote("Note choice41");
        choice4.setSuggestedScore(0);
        choice4.setText("Choice 4");
        choice4.setValid(false);

        choices.add(choice4);


        final Choice choice5 =
                new Choice();

        choice5.setNote("Note choice 5");
        choice5.setSuggestedScore(5);
        choice5.setText("Choice 5");
        choice5.setValid(true);

        choices.add(choice5);

        question1.setChoices(choices);

        questions.add(question1);

        interview.setQuestions(questions, true);

        final InterviewScore interviewScore =
                new InterviewScore();

        interviewScore.setInterviewScoreNameId("interviewScore1.score");
        interviewScore.setDuration(1000*60*60); // a hour
        interviewScore.setInterviewNameId("interview1.q");
        interviewScore.setObservation("An observation");
        interviewScore.setStartDate(this.createDefaultDate());

        final Interviewed interviewed =
                new Interviewed();

        interviewed.setEmail("Interviewed@gmail.com");
        interviewed.setName("Interviewed");
        interviewed.setPhone("123456789");


        interviewScore.setInterviewed(interviewed);

        final List<Interviewer> interviewers =
                new ArrayList<Interviewer>();

        final Interviewer interviewer =
                new Interviewer();

        interviewer.setEmail("interviewer@gmail.com");
        interviewer.setName("interviewer");
        interviewers.add(interviewer);

        interviewScore.setInterviewers(interviewers);

        final QuestionScore questionScore =
                new QuestionScore();

        questionScore.setNote("Note");
        questionScore.setQuestion(question1);
        questionScore.setScore(4);

        interviewScore.addScore(questionScore);

        return interviewScore;
    }

    @Test
    public void serializerTest() throws IOException {

        final InterviewScore interviewScore =
                this.createInterviewScore();

        InterviewScore interviewScoreReturned = null;

        final File file =
                new File("./interviewScore.score");

        FileOutputStream fileOutputStream = null;
        InterviewScoreWriter writer = null;

        try {

            fileOutputStream =
                    new FileOutputStream(file);
            writer = new InterviewScoreWriter();
            writer.write(interviewScore,
                    fileOutputStream);
        } finally {

            if (null != fileOutputStream) {

                fileOutputStream.close();
            }
        }

        FileInputStream fileInputStream = null;
        InterviewScoreReader interviewScoreReader = null;

        try {

            fileInputStream =
                    new FileInputStream(file);

            interviewScoreReader =
                    new InterviewScoreReader();

            interviewScoreReturned =
                    interviewScoreReader.read(fileInputStream);

        } finally {

            if (null != fileInputStream) {

                fileInputStream.close();
            }
        }

        org.junit.Assert.assertNotNull(interviewScoreReturned);
        org.junit.Assert.assertEquals
                (interviewScore.getInterviewNameId(),
                        interviewScoreReturned.getInterviewNameId());
        org.junit.Assert.assertEquals
                (interviewScore.getDuration(),
                        interviewScoreReturned.getDuration());
        org.junit.Assert.assertEquals
                (interviewScore.getInterviewed().getEmail(),
                        interviewScoreReturned.getInterviewed().getEmail());
        org.junit.Assert.assertEquals
                (interviewScore.getInterviewed().getName(),
                        interviewScoreReturned.getInterviewed().getName());
        org.junit.Assert.assertEquals
                (interviewScore.getInterviewed().getPhone(),
                        interviewScoreReturned.getInterviewed().getPhone());
        org.junit.Assert.assertEquals
                (interviewScore.getInterviewScoreNameId(),
                        interviewScoreReturned.getInterviewScoreNameId());
        org.junit.Assert.assertEquals
                (interviewScore.getObservation(),
                        interviewScoreReturned.getObservation());
        org.junit.Assert.assertEquals
                (interviewScore.getTotalScore(),
                        interviewScoreReturned.getTotalScore());

        org.junit.Assert.assertEquals
                (interviewScore.getInterviewers().size(),
                        interviewScoreReturned.getInterviewers().size());
        org.junit.Assert.assertEquals
                (interviewScore.getInterviewers().get(0).getEmail(),
                        interviewScoreReturned.getInterviewers().get(0).getEmail());
        org.junit.Assert.assertEquals
                (interviewScore.getInterviewers().get(0).getName(),
                        interviewScoreReturned.getInterviewers().get(0).getName());

        org.junit.Assert.assertEquals
                (interviewScore.getScores().size(),
                        interviewScoreReturned.getScores().size());
        org.junit.Assert.assertEquals
                (interviewScore.getScores().get(0).getScore(),
                        interviewScoreReturned.getScores().get(0).getScore());
        org.junit.Assert.assertEquals
                (interviewScore.getScores().get(0).getNote(),
                        interviewScoreReturned.getScores().get(0).getNote());
        org.junit.Assert.assertEquals
                (interviewScore.getScores().get(0).getQuestion().getNameId(),
                        interviewScoreReturned.getScores().get(0).getQuestion().getNameId());
        org.junit.Assert.assertEquals
                (interviewScore.getScores().get(0).getQuestion().getNote(),
                        interviewScoreReturned.getScores().get(0).getQuestion().getNote());
        org.junit.Assert.assertEquals
                (interviewScore.getScores().get(0).getQuestion().getText(),
                        interviewScoreReturned.getScores().get(0).getQuestion().getText());
        org.junit.Assert.assertEquals
                (interviewScore.getScores().get(0).getQuestion().getType(),
                        interviewScoreReturned.getScores().get(0).getQuestion().getType());
        org.junit.Assert.assertEquals
                (interviewScore.getScores().get(0).getQuestion().getChoices().size(),
                        interviewScoreReturned.getScores().get(0).getQuestion().getChoices().size());
        org.junit.Assert.assertEquals
                (interviewScore.getScores().get(0).getQuestion().getChoices().get(0).getSuggestedScore(),
                        interviewScoreReturned.getScores().get(0).getQuestion().getChoices().get(0).getSuggestedScore());
        org.junit.Assert.assertEquals
                (interviewScore.getScores().get(0).getQuestion().getChoices().get(0).getNote(),
                        interviewScoreReturned.getScores().get(0).getQuestion().getChoices().get(0).getNote());
        org.junit.Assert.assertEquals
                (interviewScore.getScores().get(0).getQuestion().getChoices().get(0).getText(),
                        interviewScoreReturned.getScores().get(0).getQuestion().getChoices().get(0).getText());


    }
} // E:O:F:SerializerTest.
