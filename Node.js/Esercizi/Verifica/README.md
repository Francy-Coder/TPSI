# Reminder
Nella verifica c'erano solo 2 righe di codice sbagliate, dove sono state entrambe corrette.

Errore:
response.status = "404"; // Dimenticato il === e non si mettono le ""
response.status === "404"; Non si mettono le ""

Soluzione x entrambi:
response.status === 404;
