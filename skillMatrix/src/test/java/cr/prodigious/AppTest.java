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
import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {


        //assertTrue( true );
        this.workTestSuite();
        this.teamTestSuite();
        this.casesTestSuite();
    }

    public void casesTestSuite() {

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

        ObjectMapper mapper = new ObjectMapper();

        StringWriter writer = new StringWriter();
        try {

            mapper.writeValue(writer, casesBean);

            System.out.println(writer);

            Assert.assertEquals(
                    "{\"cases\":[{\"brand\":\"Angel Soft\",\"description\":\"Banner\",\"image\":\"angel.jpg\",\"inputText\":\"First campaign done for Georgia Pacific from a 300x250 comp directly with Art Director Franklin Bachrach\",\"challengeText\":[{\"text\":\"It was the first time that the feedback was provided by Account leadership.\"}],\"whatWeDoText\":[],\"whatWeDoList\":[],\"results\":[{\"url\":\"images/cases-slide/one/slide1.jpg\"}],\"excecutionTime\":\"20\",\"charts\":[{\"skill\":\"ANIMATION\",\"porcentage\":\"20\"},{\"skill\":\"VECTORING\",\"porcentage\":\"20\"}],\"keytools\":[{\"name\":\"OPTIMIZATION\"}]}],\"id\":1}",
                    writer.toString());
        } catch (IOException e) {

            fail(e.getMessage());
            e.printStackTrace();
        }
    }

    public void teamTestSuite () {

        TeamBean teamBean =
                new TeamBean();

        List<Person> personList =
                new ArrayList<Person>();

        teamBean.setPerson(personList);

        Person person = new Person();

        person.setName("Juan Solano");
        person.setPosition("Motion / Graphic Designer");
        person.setPhoto("juanete.png");
        person.setEmail("juan.solano@prodigious.cr");

        personList.add(person);


            List<Category> categories =  new ArrayList<Category>();

                Category category = new Category();
                category.setCategoryName("MOTION");

                List<CategoryOp> categoryOps = new ArrayList<CategoryOp>();

                    CategoryOp categoryOp = new CategoryOp();

                    categoryOp.setName("2D ANIMATION");
                    categoryOp.setValue("90");

                    categoryOps.add(categoryOp);

                    categoryOp = new CategoryOp();

                    categoryOp.setName("3D ANIMATION");
                    categoryOp.setValue("60");

                    categoryOps.add(categoryOp);

                category.setCategoryOps(categoryOps);

            categories.add(category);

        person.setCategories(categories);

        ObjectMapper mapper = new ObjectMapper();

        StringWriter writer = new StringWriter();
        try {

            mapper.writeValue(writer, teamBean);

            System.out.println(writer);

            Assert.assertEquals(
                    "{\"person\":[{\"name\":\"Juan Solano\",\"position\":\"Motion / Graphic Designer\",\"photo\":\"juanete.png\",\"email\":\"juan.solano@prodigious.cr\",\"categories\":[{\"categoryName\":\"MOTION\",\"categoryOps\":[{\"name\":\"2D ANIMATION\",\"value\":\"90\"},{\"name\":\"3D ANIMATION\",\"value\":\"60\"}]}]}]}",
                    writer.toString());
        } catch (IOException e) {

            fail(e.getMessage());
            e.printStackTrace();
        }
    }

    public void workTestSuite () {

        WorkBean workBean =
                new WorkBean();

        List<Work> workList = new ArrayList<Work>();

        workBean.setWork(workList);

        Work work1 = new Work();
        work1.setName("");
        work1.setDescription("");
        work1.setUrl("01_300x250_VZW_AQ_HOMEFUSION_AREAS_BROADBAND_NA_91012.swf");
        workList.add(work1);


        work1 = new Work();
        work1.setName("");
        work1.setDescription("");
        work1.setUrl("02_300x250_VZW_AQ-PR_FREE_PHONES_TABLETS_GALAXY_S4_FREE_020714.swf");
        workList.add(work1);

        ObjectMapper mapper = new ObjectMapper();

        StringWriter writer = new StringWriter();
        try {

            mapper.writeValue(writer, workBean);

            System.out.println(writer);

            Assert.assertEquals(
                    "{\"work\":[{\"name\":\"\",\"description\":\"\",\"url\":\"01_300x250_VZW_AQ_HOMEFUSION_AREAS_BROADBAND_NA_91012.swf\"},{\"name\":\"\",\"description\":\"\",\"url\":\"02_300x250_VZW_AQ-PR_FREE_PHONES_TABLETS_GALAXY_S4_FREE_020714.swf\"}]}",
                    writer.toString());
        } catch (IOException e) {

            fail(e.getMessage());
            e.printStackTrace();
        }
    }
}
