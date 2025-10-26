const voti = [8, 6, 5, 9, 7];

function calcolaMedia() {
  let somma = 0;
  for (let v of voti) somma += v;
  const media = somma / voti.length;
  const esito = media >= 6 ? "Promosso" : "Bocciato";
  document.getElementById("risultato").textContent =
    "Media: " + media.toFixed(2) + " → " + esito;
}
