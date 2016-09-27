/**
 *
 * This example shows how works the associate array in javascript
 *
 */

// first example
var yearsOldByNameArray = new Array();

yearsOldByNameArray ['Jsanca']    = 33;
yearsOldByNameArray ['Everomle']  = 30;
yearsOldByNameArray ['MayuRoman'] = 31;
yearsOldByNameArray ['Robertos']  = 34;


var divToRender = document.getElementById("peopleAge");

// if exists
if (divToRender) {

    // for more details, see: http://www.w3schools.com/js/js_loop_for.asp
    var yearsOldByNameKey;
    var yearsOldByNameValue;

    for (yearsOldByNameKey in yearsOldByNameArray) {

        yearsOldByNameValue = yearsOldByNameArray[yearsOldByNameKey];

        // this render something like Jsanca is 33 years old <br/>
        divToRender.innerHTML += yearsOldByNameKey + " is " + yearsOldByNameValue + " years old <br/>";
    }
}

// second example
var yearsOldByNameObject = {

    Jsanca: '33',
    Everomle: '30',
    MayuRoman: '31',
    Robertos: '34'
};

var divToRender2 = document.getElementById("peopleAge2");

// if exists
if (divToRender2) {

    // for more details, see: http://www.w3schools.com/js/js_loop_for.asp
    var yearsOldByNameKey;
    var yearsOldByNameValue;

    for (yearsOldByNameKey in yearsOldByNameObject) {

        yearsOldByNameValue = yearsOldByNameObject[yearsOldByNameKey];

        // this render something like Jsanca is 33 years old <br/>
        divToRender2.innerHTML += yearsOldByNameKey + " is " + yearsOldByNameValue + " years old <br/>";
    }
}


alert (yearsOldByNameArray instanceof Array);
alert (yearsOldByNameObject instanceof Array);
alert (yearsOldByNameObject instanceof Object);