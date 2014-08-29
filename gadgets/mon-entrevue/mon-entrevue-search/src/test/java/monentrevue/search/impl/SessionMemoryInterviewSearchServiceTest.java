package monentrevue.search.impl;

import monentrevue.bean.Interview;
import monentrevue.bean.Question;
import monentrevue.dao.InterviewDAO;
import monentrevue.dao.directory.DirectoryInterviewDAOImpl;
import monentrevue.reader.TabLiInterviewReader;
import monentrevue.search.InterviewSearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * SessionMemoryInterviewSearchService test
 * Date: 8/29/14
 * Time: 4:48 PM
 * @author jsanca
 */
@RunWith(JUnit4.class)
public class SessionMemoryInterviewSearchServiceTest {

    @Test
    public void searchTest() throws IOException {

        final InterviewDAO interviewDAO =
                this.createInterviewDAO ();

        final Interview interview =
                interviewDAO.get("entrevue-test-search.q");

        final InterviewSearchService searchService =
                new SessionMemoryInterviewSearchService();

        searchService.indexInterview(interview);

        List<Question> questions =
                searchService.search("futbol");

        org.junit.Assert.assertNotNull(questions);
        org.junit.Assert.assertTrue(questions.size() == 1);
        org.junit.Assert.assertEquals("1", questions.get(0).getNameId());

        questions =
                searchService.search("rum");

        org.junit.Assert.assertNotNull(questions);
        org.junit.Assert.assertTrue(questions.size() == 1);
        org.junit.Assert.assertEquals("4", questions.get(0).getNameId());

        questions =
                searchService.search("best");

        org.junit.Assert.assertNotNull(questions);
        org.junit.Assert.assertTrue(questions.size() == 3);


    }

    private InterviewDAO createInterviewDAO() {

        final DirectoryInterviewDAOImpl directoryInterviewDAO =
                new DirectoryInterviewDAOImpl();

        directoryInterviewDAO.setBaseDirectory(new File("."));
        directoryInterviewDAO.setExtensions(new String[] {".q"});
        directoryInterviewDAO.setInterviewReader(new TabLiInterviewReader());

        return directoryInterviewDAO;
    }
} // E:O:F:SessionMemoryInterviewSearchServiceTest.
