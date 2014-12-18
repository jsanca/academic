package cr.prodigious;

import com.fasterxml.jackson.databind.ObjectMapper;
import cr.prodigious.bean.cases.Case;
import cr.prodigious.bean.cases.CasesBean;
import cr.prodigious.bean.cases.ChallengeText;
import cr.prodigious.bean.cases.Chart;
import cr.prodigious.bean.cases.Keytool;
import cr.prodigious.bean.cases.Result;
import cr.prodigious.bean.cases.WhatWeDoText;
import cr.prodigious.bean.team.Category;
import cr.prodigious.bean.team.CategoryOp;
import cr.prodigious.bean.team.Person;
import cr.prodigious.bean.team.TeamBean;
import cr.prodigious.bean.work.Work;
import cr.prodigious.bean.work.WorkBean;
import cr.prodigious.dao.DataBase;
import cr.prodigious.dao.DataBaseHelper;
import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class DataBaseTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DataBaseTest(String testName)
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( DataBaseTest.class );
    }

    private DataBaseHelper createDB () {

        DataBaseHelper dataBaseHelper =
                new DataBaseHelper();

        String dbPath = "./db4o-path";
        File file = new File(dbPath);

        if (!file.exists()) {

            file.mkdirs();
        }

        dataBaseHelper.setDataBasePath(dbPath + "/prodigious");
        dataBaseHelper.init();

        return dataBaseHelper;
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {

        DataBaseHelper dataBaseHelper =
                this.createDB();

        try {
            CasesBean casesBean = this.getCasesBean();

            if (dataBaseHelper.insert(casesBean)) {

                CasesBean casesBeanTwo =
                        dataBaseHelper.selectById
                                (1L, CasesBean.class);

                this.isEqual(casesBean, casesBeanTwo);
            } else {

                fail("Couldn't insert");
            }
        } finally {

            dataBaseHelper.close();
        }


    }

    private void isEqual (CasesBean casesBean1, CasesBean casesBean2) {

        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer1 = new StringWriter();
        StringWriter writer2 = new StringWriter();

        try {

            mapper.writeValue(writer1, casesBean1);
            mapper.writeValue(writer2, casesBean2);

            Assert.assertEquals(
                    writer1.toString(),
                    writer2.toString());
        } catch (IOException e) {

            fail(e.getMessage());
            e.printStackTrace();
        }
    }

    private CasesBean getCasesBean() {

        CasesBean casesBean =
                new CasesBean();

        casesBean.setId(1L);

        List<Case> caseList =
                new ArrayList<Case>();

            Case aCase = new Case();

            aCase.setBrand("Angel Soft");
            aCase.setDescription("Banner");
            aCase.setImage("angel.jpg");
            aCase.setInputText("First campaign done for Georgia Pacific from a 300x250 comp directly with Art Director Franklin Bachrach");

            List<ChallengeText> challengeTextList =
                    new ArrayList<ChallengeText>();

                ChallengeText challengeText =
                        new ChallengeText();

                challengeText.setText("It was the first time that the feedback was provided by Account leadership.");

                challengeTextList.add(challengeText);

            aCase.setChallengeText(challengeTextList);

            List<WhatWeDoText> whatWeDoTextList =
                    new ArrayList<WhatWeDoText>();

                WhatWeDoText whatWeDoText = new WhatWeDoText();
                whatWeDoText.setText("The team had the freedom to create and suggest his ideas, always following the direction from client and the result was excellent. Client approved the work with minor round of edits and loved our work.");

            aCase.setWhatWeDoText(whatWeDoTextList);

            List<Result> results =
                    new ArrayList<Result>();

                Result result =
                        new Result();

                result.setUrl("images/cases-slide/one/slide1.jpg");

                results.add(result);

            aCase.setResults(results);

            aCase.setExcecutionTime("20");

        List<Chart> chartList =
                new ArrayList<Chart>();

            Chart chart = new Chart();

            chart.setPorcentage("20");
            chart.setSkill("ANIMATION");

            chartList.add(chart);

            chart = new Chart();

            chart.setPorcentage("60");
            chart.setSkill("OPTIMIZATION");

            chart = new Chart();

            chart.setPorcentage("20");
            chart.setSkill("VECTORING");

            chartList.add(chart);

        aCase.setCharts(chartList);


        List<Keytool> keyToolList =
                new ArrayList<Keytool>();

            Keytool keytool =
                    new Keytool();

            keytool.setName("OPTIMIZATION");

            keyToolList.add(keytool);

        aCase.setKeytools(keyToolList);

            caseList.add(aCase);

        casesBean.setCases(caseList);

       return casesBean;
    }


}
