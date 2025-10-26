const nomi = ["Luca", "Giovanni", "Serena", "Anna"];

function trovaNomePiuLungo() {
  let nomeLungo = nomi[0];
  for (let nome of nomi) {
    if (nome.length > nomeLungo.length) nomeLungo = nome;
  }
  document.getElementById("risultato").textContent =
    "Il nome più lungo è: " + nomeLungo;
}
