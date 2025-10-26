const numeri = [3, 7, 10, 5];

function sommaArray() {
  let somma = 0;
  for (let n of numeri) {
    somma += n;
  }
  document.getElementById("risultato").textContent = "Somma: " + somma;
}
