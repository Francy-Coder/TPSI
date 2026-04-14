# Reminder
Nella verifica c'erano solo 2 righe di codice sbagliate, dove sono state entrambe corrette in questo repo.

- Errore: <br>
response.status = "404"; // Dimenticato il === e non si mettono le "" <br>
response.status === "404"; // Non si mettono le ""

- Soluzione x Entrambi: <br>
response.status === 404;
