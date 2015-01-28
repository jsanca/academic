package cr.prodigious.helper;

import cr.prodigious.bean.cases.Case;
import cr.prodigious.bean.cases.ChallengeText;
import cr.prodigious.bean.cases.Chart;
import cr.prodigious.bean.cases.Keytool;
import cr.prodigious.bean.cases.Result;
import cr.prodigious.bean.cases.WhatWeDoText;
import cr.prodigious.bean.team.Category;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Populates the Case
 *
 * @author jsanca
 */
public class CasePopulatorHelper  implements Serializable {

    public static Case populate(final String brand, final String description,
                                final String image, final String inputText,
                                final String excecutionTime, final HttpServletRequest request) {

        final Case aCase = new Case();

        aCase.setBrand(brand);
        aCase.setDescription(description);
        aCase.setImage(image);
        aCase.setInputText(inputText);
        aCase.setExcecutionTime(excecutionTime);

        populateMultiOptions (aCase, request);

        return aCase;
    } // populate

    private static void populateMultiOptions(final Case aCase, final HttpServletRequest request) {

        final Enumeration parameterNames =
                request.getParameterNames();

        String parameterName  = null;
        String parameterValue = null;

        while (parameterNames.hasMoreElements()) {

            parameterName =
                    (String)parameterNames.nextElement();

            if (null != parameterName) {

                parameterValue =
                        request.getParameter(parameterName);

                if (null != parameterValue) {

                    if (parameterName.startsWith("whatwedo")) {

                        populateWhatWeDo(aCase, parameterValue);
                    }

                    if (parameterName.startsWith("skillporcentage")) {

                        populateSkills(aCase, parameterValue);
                    }

                    if (parameterName.startsWith("results")) {

                        populateResults(aCase, parameterValue);
                    }

                    if (parameterName.startsWith("challenge")) {

                        populateChallenge(aCase, parameterValue);
                    }

                    if (parameterName.startsWith("keytool")) {

                        populateKeytool(aCase, parameterValue);
                    }
                }
            }
        }
    } // populateMultiOptions.

    private static void populateKeytool(final Case aCase, final String parameterValue) {

        Keytool keytool = null;

        if (null == aCase.getKeytools()) {

            aCase.setKeytools(new ArrayList<Keytool>());
        }

        keytool =
                new Keytool();

        keytool.setName(parameterValue);

        aCase.getKeytools().add(keytool);
    }

    private static void populateChallenge(final Case aCase, final String parameterValue) {

        ChallengeText challengeText = null;

        if (null == aCase.getChallengeText()) {

            aCase.setChallengeText(new ArrayList<ChallengeText>());
        }

        challengeText =
                new ChallengeText();

        challengeText.setText(parameterValue);

        aCase.getChallengeText().add(challengeText);
    }

    private static void populateResults(final Case aCase, final String parameterValue) {

        Result result = null;

        if (null == aCase.getResults()) {

            aCase.setWhatWeDoText(new ArrayList<WhatWeDoText>());
        }

        result =
                new Result();

        result.setUrl(parameterValue);

        aCase.getResults().add(result);
    }

    private static void populateSkills(final Case aCase, final String parameterValue) {

        Chart chart = null;
        String [] array = null;

        if (null == aCase.getCharts()) {

            aCase.setCharts(new ArrayList<Chart>());
        }

        chart =
                new Chart();

        /// JAVA --- 100
        array = parameterValue.split("---");

        if (2 == array.length) {

            chart.setSkill(array[0]);
            chart.setPorcentage(array[1]);

            aCase.getCharts().add(chart);
        }
    }

    private static void populateWhatWeDo(final Case aCase, final String parameterValue) {

        WhatWeDoText whatWeDoText = null;

        if (null == aCase.getWhatWeDoText()) {

             aCase.setWhatWeDoText(new ArrayList<WhatWeDoText>());
        }

        whatWeDoText =
                new WhatWeDoText();

        whatWeDoText.setText(parameterValue);

        aCase.getWhatWeDoText().add(whatWeDoText);
    }


} // E:O:F:CasePopulatorHelper.
