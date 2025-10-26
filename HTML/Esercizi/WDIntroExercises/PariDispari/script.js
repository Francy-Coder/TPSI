function controllaPari() {
  const n = Number(document.getElementById("numero").value);
  document.getElementById("risultato").textContent =
    n % 2 === 0 ? "Il numero è pari" : "Il numero è dispari";
}
