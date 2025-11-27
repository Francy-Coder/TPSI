# **Esercizi Async JS: Promise, Async/Await e Fetch API**
# **BLOCCO 3: Composizione e decisioni**

## **Esercizio 13: setTimeout + fetch**

Simula caricamento:

* aspetta 2 secondi
* poi fetch utente ID 2
* stampa `"Caricamento completato: [nome]"`

Puoi usare:

```js
function wait(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}
```

---

## **Esercizio 14: Loop con fetch sequenziali**

Fetch utenti ID 1–3 in un loop, sequenzialmente.
Stampa per ognuno: `"[ID]: [nome]"`

---

## **Esercizio 15: Mini-progetto — Dashboard meteo**

Fetch meteo per:

* Milano
* Roma
* Napoli

Per ogni città crea un `<div>` con:

* temperatura
* velocità vento

**API base:**
[https://api.open-meteo.com/v1/forecast?latitude=[LAT]&longitude=[LON]&current_weather=true](https://api.open-meteo.com/v1/forecast?latitude=[LAT]&longitude=[LON]&current_weather=true)

Esempio output:

```
Milano: 12°C, vento 5 km/h
Roma: 15°C, vento 8 km/h
Napoli: 17°C, vento 10 km/h
```

---

# **Debugging tips**

* Usa sempre `console.log()`
* Controlla Network (F12)
* Se vedi `"[object Promise]"`, hai dimenticato un `await`