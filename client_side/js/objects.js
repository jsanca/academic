// how to create right objects

this.name = "Evelyn";

// how to create a Doggy
function Doggy(name, birthdate, color, breed) {

    this.name = name;
    this.birthdate = birthdate;
    this.color = color;
    this.breed = breed;
   /*
    this.bark = function () {

        alert (this.name + 'rau rau');
    }*/
} // Doggy.

Doggy.prototype.bark =  function () {

    alert (this.name + ' rau rau rau');
}

var puppy = new Doggy("puppy", "01/01/2002", "white", "french poodle");
var tita = new Doggy("tita", "01/01/2005", "white", "french poodle");

//alert (puppy instanceof Doggy);
//alert (tita instanceof Doggy);

puppy.bark();
tita.bark();

alert((puppy.bark == tita.bark));


alert("Doggy.constructor is " + puppy.constructor );


alert(name);