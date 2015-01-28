package cr.prodigious.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import cr.prodigious.bean.cases.CasesBean;
import cr.prodigious.bean.team.TeamBean;
import cr.prodigious.bean.work.WorkBean;
import cr.prodigious.helper.JsonHelper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.text.MessageFormat;
import java.util.Date;

/**
 * This tool creates an backup and also retrieves xml objects
 * @author jsanca
 */
public class BackupDAOHelper implements Serializable {

    private String backupBaseDirectory = "./";

    private JsonHelper jsonHelper = null;

    public void setJsonHelper(JsonHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }


    protected void storeBackup(final Object object, final String fileName) throws Exception {

        final File file = new File(backupBaseDirectory,
                MessageFormat.format(fileName,
                        String.valueOf(new Date().getTime())));

        Writer writer = null;

        try {

            writer = new FileWriter(file);
            this.jsonHelper.write(writer, object);
            //writer.flush();
        } finally {

            if (null != writer) {

                try {

                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                writer = null;
            }
        }
    } // storeBackup.

    public void setBackupBaseDirectory(String backupBaseDirectory) {

        this.backupBaseDirectory = backupBaseDirectory;
    }

    public void storeBackup(final WorkBean workBean) {

        try {

            this.storeBackup(workBean, MessageFormat.format("work{0}.backup",
                    String.valueOf(new Date().getTime())));
        } catch (Exception e) {

                e.printStackTrace();
        }

    } // storeBackup.

    public void storeBackup(final CasesBean casesBean) {

        try {

            this.storeBackup(casesBean, MessageFormat.format("cases{0}.backup",
                    String.valueOf(new Date().getTime())));
        } catch (Exception e) {

            e.printStackTrace();
        }
    } // storeBackup.

    public void storeBackup(final TeamBean teamBean) {

        try {

            this.storeBackup(teamBean, MessageFormat.format("team{0}.backup",
                    String.valueOf(new Date().getTime())));
        } catch (Exception e) {

            e.printStackTrace();
        }
    } // storeBackup.

    protected <T> T getBeanFromBackupFile(final String fileName, Class<T> aClass) {

        T t = null;
        final File file = new File(this.backupBaseDirectory, fileName);

        if (file.exists()) {

            try {

                t =
                    this.jsonHelper.read
                            (file, aClass);
            } catch (IOException e) {

                t = null;
            }
        }

        return t;
    } // getBeanFromBackupFile.

    public WorkBean getWorkBeanFromBackupFile(final String fileName) {

        return this.getBeanFromBackupFile(fileName, WorkBean.class);
    } // getWorkBeanFromBackupFile.

    public CasesBean getCasesBeanFromBackupFile(final String fileName) {

        return this.getBeanFromBackupFile(fileName, CasesBean.class);
    } // getCasesBeanFromBackupFile.

    public TeamBean getTeamBeanFromBackupFile(final String fileName) {

        return this.getBeanFromBackupFile(fileName, TeamBean.class);
    } // getTeamBeanFromBackupFile.


} // E:O:F:BackupDAOHelper.
