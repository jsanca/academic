package monentrevue.service.impl;

import monentrevue.bean.Interview;
import monentrevue.dao.InterviewDAO;
import monentrevue.service.InterviewDriveService;
import monentrevue.service.InterviewServiceException;
import monentrevue.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Default implementation.
 * Date: 9/3/14
 * Time: 3:03 PM
 * @author jsanca
 */
public class InterviewDriveServiceImpl implements InterviewDriveService {

    private File baseDirectory;

    private InterviewDAO interviewDAO;

    public void setBaseDirectory(File baseDirectory) {

        this.baseDirectory = baseDirectory;
    }

    public void setInterviewDAO(InterviewDAO interviewDAO) {

        this.interviewDAO = interviewDAO;
    }

    /**
     * Take the File (probably uploaded) and saved in on a directory
     *
     * @param fileName    String
     * @param inputStream InputStream
     * @return Interview
     */
    @Override
    public Interview saveInterviewFile(final String fileName,
                                       final InputStream inputStream) {

        Interview interview = null;

        if (null != this.baseDirectory) {

            try {

                FileUtil.store(new File(this.baseDirectory, fileName), inputStream);
            } catch (IOException e) {

                throw new InterviewServiceException(e);
            }
        }

        return interview;
    } // saveInterviewFile.

    /**
     * Get all the interviews, if populate is true will returns the interview objects completed with all the
     * questions and info, otherwise only the necessary info to be displayed in a header or list.
     *
     * @return List of Interview objects
     */
    @Override
    public List<Interview> getAll() {

        List<Interview> interviewList = null;

        interviewList =
                this.interviewDAO.getAll(false);

        return interviewList;
    } // getAll.

    /**
     * Get a interview index by interviewNameId
     *
     * @param interviewNameId String
     * @return Interview
     */
    @Override
    public Interview get(String interviewNameId) {

        Interview interview = null;

        interview =
                this.interviewDAO.get(interviewNameId);

        return interview;
    } // get


} // E:O:F:InterviewDriveServiceImpl.
