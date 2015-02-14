

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

