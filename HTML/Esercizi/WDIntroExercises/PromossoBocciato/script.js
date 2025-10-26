function verificaVoto() {
  const voto = Number(document.getElementById("voto").value);
  document.getElementById("risultato").textContent =
    voto >= 6 ? "Promosso!" : "Bocciato 😢";
}
