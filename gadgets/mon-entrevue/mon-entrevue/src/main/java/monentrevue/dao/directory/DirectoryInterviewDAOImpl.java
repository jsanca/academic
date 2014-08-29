package monentrevue.dao.directory;

import monentrevue.bean.Choice;
import monentrevue.bean.Interview;
import monentrevue.bean.Question;
import monentrevue.dao.DataAccessException;
import monentrevue.dao.InterviewDAO;
import monentrevue.reader.InterviewReader;
import monentrevue.util.NameUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation based on a directory persistence.
 * It basically stored everything in a directory in .q files.
 * Date: 8/27/14
 * Time: 10:35 AM
 * @author jsanca
 */
public class DirectoryInterviewDAOImpl implements InterviewDAO {

    private File baseDirectory;
    private String [] extensions;
    private FilenameFilter filenameFilter;
    private InterviewReader interviewReader;

    public void setInterviewReader(InterviewReader interviewReader) {

        this.interviewReader = interviewReader;
    }

    public void setExtensions(String [] extensions) {

        this.extensions = extensions;
    }

    public FilenameFilter getFilenameFilter () {

        if (null == this.filenameFilter) {

            if (null != this.extensions) {

                this.filenameFilter =
                        new ExtensionListFilenameFilter
                                (this.extensions);
            }
        }

        return this.filenameFilter;
    } // getFilenameFilter.


    public void setBaseDirectory(File baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    /**
     * Get all the interviews, if populate is true will returns the interview objects completed with all the
     * questions and info, otherwise only the necessary info to be displayed in a header or list.
     *
     * @param populate boolean true to populate the whole interview object
     * @return List of Interview objects
     */
    @Override
    public List<Interview> getAll(final boolean populate) {

        List<Interview> interviewList =
                Collections.EMPTY_LIST;
        String [] interviewFiles = null;
        Interview interview = null;

        if (null != this.baseDirectory) {

            if (this.baseDirectory.isDirectory()) {

                if (null != this.getFilenameFilter()) {

                    interviewFiles =
                        this.baseDirectory.list
                                (this.getFilenameFilter());
                } else {

                    interviewFiles =
                            this.baseDirectory.list();
                }

                interviewList =
                        new ArrayList<Interview>();

                for(String interviewFile : interviewFiles) {

                    if (populate) {

                        interviewList.add(this.get(interviewFile));
                    } else {

                        interview =
                                new Interview();

                        interview.setNameId(interviewFile);

                        interviewList.add(interview);
                    }
                }
            }
        }

        return interviewList;
    } // getAll.

    /**
     * Get a interview index by interviewNameId
     *
     * @param interviewNameId String
     * @return Interview
     */
    @Override
    public Interview get(final String interviewNameId) {

        File file = null;
        Interview interview = null;
        InputStream inputStream = null;

        if (null != this.baseDirectory) {

            file =
              new File
                (this.baseDirectory, interviewNameId);

            if (file.exists()) {

                try {

                    inputStream =
                            new FileInputStream(file);

                    interview = this.interviewReader.read
                            (new InputStreamReader(inputStream));
                } catch (FileNotFoundException e) {

                    throw new DataAccessException(e);
                } finally {

                    if (null != inputStream) {

                        try {

                            inputStream.close();
                        } catch (IOException e) {

                            // quiet.
                        }
                    }
                }
            } else {

                throw new DataAccessException("The Interview does not exists");
            }
        }

        return interview;
    } // get

    /**
     * Save an interview
     *
     * @param interview Interview
     * @return String
     */
    @Override
    public String save(final Interview interview) {

        String interviewNameId =
                interview.getNameId();
        File interviewFile = null;

        if (null != this.baseDirectory) {

            interviewFile =
                    new File(this.baseDirectory, interviewNameId);

            if (interviewFile.exists()) {

                interviewNameId =
                        this.getFileName(interviewNameId);
                interview.setNameId
                        (interviewNameId);
                interviewFile =
                        new File(interview.getNameId());
            }

            saveFile(interview, interviewNameId, interviewFile);
        }

        return interviewNameId;
    } // save.

    private String getFileName(final String interviewNameId) {

        return NameUtil.createDateName(interviewNameId, ".q");
    } // getFileName.

    private synchronized void saveFile(final Interview interview,
                                       final String interviewNameId,
                                       final File interviewFile) {

        FileWriter fileWriter = null;

        try {

            fileWriter =
                    new FileWriter(interviewFile);

            fileWriter.write("id: "); fileWriter.write(interviewNameId); fileWriter.write("\n");

            if (null != interview.getTitle()) {

                fileWriter.write("title: "); fileWriter.write(interview.getTitle()); fileWriter.write("\n");
            }

            if (null != interview.getNote()) {

                fileWriter.write("note: "); fileWriter.write(interview.getNote()); fileWriter.write("\n");
            }

            if (null != interview.getQuestions())
            for(Question question : interview.getQuestions()) {

                if (null != question.getNameId()) {

                    fileWriter.write(question.getNameId());
                    fileWriter.write(" | ");
                }

                fileWriter.write(question.getText());
                fileWriter.write(" | ");
                fileWriter.write(question.getType());

                if (null != question.getNote()) {

                    fileWriter.write(" | ");
                    fileWriter.write(question.getNote());
                }

                fileWriter.write("\n");

                for(Choice choice : question.getChoices()) {

                    fileWriter.write("\t");
                    fileWriter.write(choice.getText());

                    fileWriter.write("|");
                    fileWriter.write (choice.getSuggestedScore());

                    if (null != choice.getNote()) {

                        fileWriter.write("|");
                        fileWriter.write (choice.getNote());
                    }

                    fileWriter.write("\n");
                }
            }
        } catch (IOException e) {

            throw new DataAccessException(e);
        } finally {

            if (null != fileWriter) {


                try {

                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException e) {

                    // Quiet.
                }
            }
        }
    } // saveFile.

    /**
     * Update an existing interview
     *
     * @param interviewNameId String
     * @param interview       String
     * @return boolean
     */
    @Override
    public boolean update(final String interviewNameId,
                          final Interview interview) {

        boolean update = false;
        File  interviewFile = null;

        if (null != this.baseDirectory) {

            interviewFile =
                    new File(this.baseDirectory, interviewNameId);

            if (interviewFile.exists()) {

                saveFile(interview, interviewNameId, interviewFile);
                update = true;
            }
        }

        return update;
    } // update.

    /**
     * Delete an existing interview
     *
     * @param interviewNameId String
     * @return boolean
     */
    @Override
    public synchronized boolean delete(final String interviewNameId) {

        File file = null;
        Interview interview = null;
        InputStream inputStream = null;
        boolean delete = false;

        if (null != this.baseDirectory) {

            file =
                    new File
                            (this.baseDirectory, interviewNameId);

            if (file.exists()) {

                delete =
                    file.delete();
            } else {

                throw new DataAccessException("Interview does not exists");
            }
        }

        return delete;
    } // delete.

} // E:O:F:DirectoryInterviewDAOImpl.
