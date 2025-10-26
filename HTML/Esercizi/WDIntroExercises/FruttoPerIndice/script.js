const frutti = ["Mela", "Banana", "Pera"];

function mostraFrutto() {
  const i = Number(document.getElementById("indice").value);
  const output = document.getElementById("risultato");

  if (i >= 0 && i < frutti.length) {
    output.textContent = "Frutto: " + frutti[i];
  } else {
    output.textContent = "Indice non valido!";
  }
}
