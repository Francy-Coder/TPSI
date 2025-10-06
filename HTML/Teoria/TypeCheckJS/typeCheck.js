// variabili, costanti, tipi

//login console

console.log("Hello") //per conettersi alla console

//vari
let x = 7; //aggiunta di una variabile con un valore
let y = "ciao"

console.log("x: " + typeof x) //typeof = operatore di JS che preso un nome della sua variabile, restistuisce il suo tipo
console.log("y: " + typeof y)

y = 4.19
console.log("y: " + typeof y)

let f;
let g = null; //null = object

let arr = [1, 2, 3, "ciao", false, true, []]
console.log("Arr: " + typeof arr)

console.log(typeof true);
console.log(typeof undefined);
console.log(typeof (4 + 5));
console.log(typeof (4 + "5"));
console.log(typeof 1 / -5); //errore, mancano le parentesi: NaN
console.log(typeof (1 / -5));

let l = 1 / 0;
console.log("1 / 0: " + typeof l);
console.log("NaN: " + typeof NaN);

// ma perchè è true -> numero e non l'uno -> boolean?
console.log(true + 1); //true = 1, quindi il risultato è 2
console.log(false + 1); //false = 0, quindi il risultato è 1 
console.log(typeof (true + 1)); 

console.log(5 * "44"); //Conversione implicita: se vede che puo trasformare la stringa come un numero, restituisce il risultato matematico. 
console.log(5 * "dd");      // Invece se vede la stringa come una stringa, fa l'operazione ma restituisce NaN

console.log(1 + 2 + '3' + 4); //1 + 2 + stringa 3 + 4 --> 3 + stringa 3 + 4 --> risultato: 334

let m; // m = undefined
console.log("m*m: " + m * m);

//Strings
let str = "nbuganza@salesianisesto.it"

console.log(str);

console.log(str[8]); //Restistuisce il carattere che si trova nel ottava posizione iniziando da 0
console.log(str.charAt(8)); // ^ Stesso risultato, ma è preferibile usare questo

console.log(str.indexOf('@'));  //Restituisce la posizione del primo carattere 
console.log(str.replace("n", "X")); //Sostituisce la prima n con la X
console.log(str.replaceAll("n", "X")); // ^ Stostiuisce tutti i caratteri 
console.log(str.includes("@")); // Esiste il carattere? Restituisce boolean
console.log("str.slice: " + str.slice(1,8)); //Restituisce i caratteri dalla posizione 1 alla posizione 8
console.log("str.slice: " + str.substring(1,8));

//Concatenare le Stringhe
let a = "ciao"
let b = " a tutti"

    //tecnica 1 - normale
console.log(a + b);

    //tecnica 2 - da usare solo in node.js - TEMPLATE STRING
console.log(`${a}${b}`); // Alt+96 oppure Alt Gr + ' --> risultato: `

//Confronti
if ("0"){ //true / false | 0 = false / 1 = true | full String = true / empty String = false
    console.log("THEN");
}else{
    console.log("ELSE")
}

if (5 != "5"){
    console.log("THEN");
}else{
    console.log("ELSE")
}

//Ciclo: While, Do-While, For
while(true){

}

for(let i = 0; i<4; i++){

}