package cr.prodigious.helper;

import cr.prodigious.bean.team.Category;
import cr.prodigious.bean.team.CategoryOp;
import cr.prodigious.bean.team.Person;
import cr.prodigious.bean.team.TeamBean;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Populates the person
 *
 * @author jsanca
 *
 */
public class PersonPopulatorHelper implements Serializable {

    public static Person populate (final String name,
                                     final String position,
                                     final String imageLocal,
                                     final String email,
                                     final String regionproject,
                                     final HttpServletRequest request) {

        final Person person = new Person();

        person.setName(name);
        person.setPosition(position);
        person.setPhoto(imageLocal);
        person.setEmail(email);
        person.setRegionProject(regionproject);

        populateSkills(person, request);

        return person;
    } // populate

    private static void populateSkills (final Person person,
                                        final HttpServletRequest request) {

        final Enumeration parameterNames =
                request.getParameterNames();

        String parameterName  = null;
        String parameterValue = null;
        Category category     = null;
        final Map<String, Category> categoryMap =
                new HashMap<>();
        String [] categoryArray = null;

        while (parameterNames.hasMoreElements()) {

            parameterName =
                    parameterNames.nextElement().toString();

            parameterValue =
                    request.getParameter(parameterName);

            // SKILL-Category Name-Category Option
            if (parameterName.startsWith("SKILL-")) {

                categoryArray =
                        parameterName.split("-");

                if (3 == categoryArray.length) {

                    checkIfCategoryExists(categoryMap, categoryArray[1]);

                    category = categoryMap.get(categoryArray[1]);
                    populateCategoryOp(parameterValue, category, categoryArray[2]);
                }
            }
        }

        setCategories (person, categoryMap);
    } // populateSkills

    private static void setCategories(Person person, Map<String, Category> categoryMap) {

        person.setCategories(new ArrayList<Category>());

        for (Category category: categoryMap.values()) {

            if ((null != category.getCategoryOps())
                    && (category.getCategoryOps().size() > 0)) {

                person.getCategories().add(category);
            }
        }
    } // setCategories.

    private static void checkIfCategoryExists(
            final Map<String, Category> categoryMap, final String key) {

        Category category = null;

        if (!categoryMap.containsKey(key)) {

            category = new Category();
            category.setCategoryName(key);
            categoryMap.put(key, category);
        }
    } // checkIfCategoryExists.

    private static void populateCategoryOp(final String parameterValue,
                                           final Category category,
                                           final String name) {

        CategoryOp categoryOp = null;

        if (isValidValue (parameterValue)) {

            categoryOp = new CategoryOp();
            categoryOp.setName(name);
            categoryOp.setValue(parameterValue);

            if (null == category.getCategoryOps()) {

                category.setCategoryOps(new ArrayList<CategoryOp>());
            }

            category.getCategoryOps().add(categoryOp);
        }
    } // populateCategoryOp.

    private static boolean isValidValue(final String parameterValue) {

        return (null != parameterValue) && (parameterValue.trim().length() > 0) &&
                (!"0".equalsIgnoreCase(parameterValue));
    } // isValidValue.

} // E:O:F:TeamPopulatorHelper.
