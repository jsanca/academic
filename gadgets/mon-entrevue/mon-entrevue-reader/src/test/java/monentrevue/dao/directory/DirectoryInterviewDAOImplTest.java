package monentrevue.dao.directory;

import monentrevue.bean.Choice;
import monentrevue.bean.Interview;
import monentrevue.bean.Question;
import monentrevue.dao.DataAccessException;
import monentrevue.reader.TabLiInterviewReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Directory Interview DAO test
 * Date: 8/29/14
 * Time: 11:25 AM
 * @author jsanca
 */
@RunWith(JUnit4.class)
public class DirectoryInterviewDAOImplTest {


    private Interview createInterviewScore () {

        final Interview interview =
                new Interview();

        interview.setNameId("interview2.q");
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

        return interview;
    }

    @Test
    public void crudTest() throws IOException {

        final Interview interview =
            this.createInterviewScore();

        final DirectoryInterviewDAOImpl interviewDAO =
                new DirectoryInterviewDAOImpl();

        interviewDAO.setBaseDirectory(new File(".")); // just here
        interviewDAO.setExtensions(new String[] {".q"});
        interviewDAO.setInterviewReader(new TabLiInterviewReader());

        final String interviewNameId =
                interviewDAO.save(interview);

        org.junit.Assert.assertNotNull(interviewNameId);
        org.junit.Assert.assertEquals(interviewNameId, interview.getNameId());

        final String interviewNameIdRepeated =
                interviewDAO.save(interview);

        org.junit.Assert.assertNotNull(interviewNameIdRepeated);
        org.junit.Assert.assertNotEquals(interviewNameId, interviewNameIdRepeated);

        try {

            interviewDAO.get("ksdskdjks.q");
            org.junit.Assert.fail("Interview does not exists should throw an exception");
        } catch (DataAccessException e) {

            // ok
        }

        final Interview interview1 =
                interviewDAO.get(interviewNameId);

        org.junit.Assert.assertNotNull(interview1);
        org.junit.Assert.assertEquals(interview.getTitle(), interview1.getTitle());

        interview1.setTitle("a new title");

        final boolean updated =
                interviewDAO.update(interviewNameId, interview1);

        org.junit.Assert.assertTrue(updated);

        org.junit.Assert.assertNotEquals(interview.getTitle(), interview1.getTitle());
        org.junit.Assert.assertEquals(interview.getNote(), interview1.getNote());
        org.junit.Assert.assertEquals("a new title", interview1.getTitle());

        try {

            interviewDAO.delete("non-existing-interview");
            org.junit.Assert.fail("Can not delete an unexisting Interview should throw an exception");
        } catch (DataAccessException e) {

            // ok
        }

        final boolean deleted1 =
            interviewDAO.delete(interviewNameId);

        org.junit.Assert.assertTrue(deleted1);

        final boolean deleted2 =
                interviewDAO.delete(interviewNameIdRepeated);

        org.junit.Assert.assertTrue(deleted2);

        try {

            interviewDAO.get(interviewNameId);
            org.junit.Assert.fail("Interview does not exists should throw an exception");
        } catch (DataAccessException e) {

            // ok
        }

        try {

            interviewDAO.get(interviewNameIdRepeated);
            org.junit.Assert.fail("Interview does not exists should throw an exception");
        } catch (DataAccessException e) {

            // ok
        }
    } // crudTest.
} // E:O:F:DirectoryInterviewDAOImplTest.
