package monentrevue.service;

import monentrevue.bean.Interview;

import java.io.InputStream;

/**
 * To Handle Drive operations for interview
 *
 * Date: 9/3/14
 * Time: 2:32 PM
 * @author jsanca
 */
public interface InterviewDriveService extends InterviewService {


    /***
     * Take the File (probably uploaded) and saved in on a directory
     * @param fileName String
     * @param inputStream  InputStream
     * @return  Interview
     */
    public Interview saveInterviewFile (String fileName, InputStream inputStream);

} // E:O:F:InterviewService.
