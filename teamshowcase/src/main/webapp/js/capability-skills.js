

/**
 * Update a Tree with the capability skills.
 * @param divId
 * @param capabilitySkillListJSON
 */
function updateCapabilitySkillsTree(divId, capabilitySkillListJSON) {

    var skillTree =
        document.getElementById(divId);

    // check if contains any child
    if (skillTree.hasChildNodes()) {

        removeAllChildNodes(skillTree);
    }

    var skillsUl =
        document.createElement("ul");

    capabilitySkillListJSON.forEach(
        function(capabilitySkill) {

            var skillLi =
                document.createElement("li");

            var capabilitySpan =
                document.createElement("span");

            capabilitySpan.innerText = capabilitySkill.name;

            skillLi.appendChild(capabilitySpan);
            skillsUl.appendChild(skillLi);

            loadSkills(capabilitySkill.skills, skillLi);
        }
    );

    skillTree.appendChild(skillsUl);
} // updateCapabilitySkillsTree.

function loadSkills(skills, skillLi) {

    var skillCategoryUl =
        document.createElement("ul");

    if (skills) {

        skills.forEach(

            function(skill) {

                var skillCategoryLi =
                    document.createElement("li");

                var skillCategorySpan =
                    document.createElement("span");

                skillCategorySpan.innerText =
                    skill.category.name;

                skillCategoryLi.appendChild(skillCategorySpan);

                loadSkillSubCategories(skill.subcategories, skillCategoryLi);

                skillCategoryUl.appendChild(skillCategoryLi);
            }
        );

        skillLi.appendChild(skillCategoryUl);
    }
} // loadSkills.

function loadSkillSubCategories(skillSubCategories, skillCategoryLi) {

    var skillSubCategoryUl =
        document.createElement("ul");

    skillSubCategories.forEach(

        function(skillSubCategory) {

            var skillSubCategoryLi =
                document.createElement("li");

            var skillSubCategorySpan =
                document.createElement("span");

            skillSubCategorySpan.innerText =
                skillSubCategory.name;

            skillSubCategoryLi.appendChild(skillSubCategorySpan);

            skillSubCategoryUl.appendChild(skillSubCategoryLi);
        }
    );

    skillCategoryLi.appendChild(skillSubCategoryUl);
} // loadSkillSubCategories.


/**
 * Creates a div with the skill sub categories.
 * @param skill
 * @returns {HTMLElement}
 */
function createSubCategorySkillDiv (skill) {

    var categoryName =
        skill.category.name;

    var categorySkillNameId =
        removeSpaces(categoryName);

    var div =
        document.createElement("div");

    div.style.display = "none"; // hide by default
    div.id = categorySkillNameId;  // the id is the name without spaces

    var h2 =
        document.createElement("h2");

    h2.innerText =
        categoryName;

    div.appendChild(h2);

    div.appendChild
        (createSubCategoryKillTable(skill.subcategories, categoryName));

    return div;
} // createSubCategoryTable.


/**
 * Creates the actual table with the sub cat titles and number inputs
 * @param subCategoriesSkill
 * @param categoryName
 * @returns {HTMLElement}
 */
function createSubCategoryKillTable(subCategoriesSkill, categoryName) {

    var table =
        document.createElement("table");

    if (subCategoriesSkill) {

        subCategoriesSkill.forEach(

            function (subCategorySkill) {

               var tr =
                   document.createElement("tr");

               var tdTitle =
                   document.createElement("td");

                tdTitle.innerText =
                    subCategorySkill.name;

                var tdInput =
                    document.createElement("td");

                var input =
                    document.createElement("input");

                input.type = "number";
                input.min  = 0;
                input.max  = 100;
                // an example of name SKILL-MOTION-2D ANIMATION
                input.name = "SKILL-" + categoryName + "-" + subCategorySkill.name;

                tdInput.appendChild(input);

                tr.appendChild(tdTitle);
                tr.appendChild(tdInput);

                table.appendChild(tr);
            }
        );
    }

    return table;
} // createSubCategoryKillTable.

