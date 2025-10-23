//Vai a prendere dal DOM quell'emenento tramite id
let res = document.getElementById("res")
let input = document.getElementById("in")
let inBtn = document.getElementById("inBtn")
//console.log("CIAO")

/*
res.textContent = "Ciao"
res.textContent += " Mondo"
*/

inBtn.addEventListener("click", getInputAndAdd)

//Funzioni
function getInputAndAdd(){
    let inVal = Number(input.value)

    //Guardia
    if(isNaN(inVal)){
        res.textContent = "Eh no dai.."
        return
    }

    console.log(typeof inVal);
    console.log((inVal + 5));
    console.log(typeof(inVal + 5));

    res.textContent = inVal + ": " + (inVal + 5)
}

//Array - Eterogenei e Dinamici
let array = [1,2,3,4,5,6,7]
array.push(123) //Spingo in coda, aggiunto uno o più elementi alla fine di un array
console.log(array)
let elem = array.pop() //Rimuove e resituisce l'ultimo elemento
array.pop()
console.log(array)
