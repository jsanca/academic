package monentrevue.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Interview score
 *
 * Date: 8/21/14
 * Time: 8:23 PM
 * @author jsanca
 */
public class InterviewScore implements Serializable {

    private String interviewNameId;

    private String interviewScoreNameId;

    private String observation;

    private Interviewed interviewed;

    private List<Interviewer> interviewers;

    private Date startDate;

    private long duration;

    private List<QuestionScore> scores;

    public void addScore (QuestionScore questionScore) {

        if (null == this.scores) {

            this.scores =
                    new ArrayList<QuestionScore>();
        }

        this.scores.add(questionScore);
    }

    public String getInterviewNameId() {
        return interviewNameId;
    }

    public void setInterviewNameId(String interviewNameId) {
        this.interviewNameId = interviewNameId;
    }

    public String getInterviewScoreNameId() {
        return interviewScoreNameId;
    }

    public void setInterviewScoreNameId(String interviewScoreNameId) {
        this.interviewScoreNameId = interviewScoreNameId;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Interviewed getInterviewed() {
        return interviewed;
    }

    public void setInterviewed(Interviewed interviewed) {
        this.interviewed = interviewed;
    }

    public List<Interviewer> getInterviewers() {
        return interviewers;
    }

    public void setInterviewers(List<Interviewer> interviewers) {
        this.interviewers = interviewers;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public List<QuestionScore> getScores() {
        return scores;
    }

    public void setScores(List<QuestionScore> scores) {
        this.scores = scores;
    }

    public int getTotalScore () {

        int totalScore = 0;

        if (null != this.scores) {

            for(QuestionScore questionScore : this.scores) {

                totalScore +=
                        questionScore.getScore();
            }
        }

        return totalScore;
    }

} // E:O:F:InterviewScore.
