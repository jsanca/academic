package cr.prodigious.helper;

import cr.prodigious.bean.cases.Case;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

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



        return aCase;
    } // populate
} // E:O:F:CasePopulatorHelper.
