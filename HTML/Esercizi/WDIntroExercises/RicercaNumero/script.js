const numeri = [2, 4, 6, 8, 10];

function cercaNumero() {
  const n = Number(document.getElementById("numero").value);
  document.getElementById("risultato").textContent =
    numeri.includes(n) ? "Numero trovato!" : "Numero non presente.";
}
