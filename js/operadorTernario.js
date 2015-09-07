/**
 * Muestra como usar el operador ternario, cuya sintaxis es la siguiente:
 *
 * (condicion booleana)? retorno cuando es verdadero: retorno cuando es falso;
 *
 * por ejemplo:
 *
 *
 * var esMiCumpleanos = (dia == 4 && mes == 10)? "Si es mi cumple": "Hoy no es su cumple trabaje";
 **/


// por ejemplo si tenemos la siguiente condicion.

var esMiCumpleanos;
var dia = 4;
var mes = 10;

if (dia == 4 && mes == 10) {

    esMiCumpleanos = "Yupiiii es mi cumple";
} else {

    esMiCumpleanos = "Ahhh no es mi cumple, trabaje!!!!";
}


// lo anterior es equivalente a.....

esMiCumpleanos = (dia == 4 && mes == 10)? "Yupiiii es mi cumple" : "Ahhh no es mi cumple, trabaje!!!!";

// Ambas funcionan exactamente igual, solo que la segunda es una version reducida.