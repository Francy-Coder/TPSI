function calcolaSomma() {
  const a = Number(document.getElementById("num1").value);
  const b = Number(document.getElementById("num2").value);
  document.getElementById("risultato").textContent = "Somma: " + (a + b);
}
