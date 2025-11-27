# **Esercizi Async JS: Promise, Async/Await e Fetch API**

## **Informazioni Generali**

Questa serie di 15 esercizi è progettata per costruire competenza progressiva nell'uso di:

* Promise API (.then(), .catch())
* async/await
* Fetch API
* Gestione errori asincroni
* Manipolazione dati da API esterne (REST)

**Prerequisiti:**

* JavaScript vanilla
* funzioni come argomenti
* Event loop (concetti base)
* Richieste HTTP (teoria)

**Strumenti necessari:**

* Google Chrome
* Editor di codice
* Connessione internet (per le API pubbliche)

---

# **BLOCCO 1: Promise API**

## **Esercizio 1: Il primo fetch**

**Task:**
Fai una richiesta GET all’API JSONPlaceholder per ottenere l’utente con ID 1. Stampa in console l’intero oggetto. Usa `.then()` e `.catch()`.

**API:**
[https://jsonplaceholder.typicode.com/users/1](https://jsonplaceholder.typicode.com/users/1)

**Output atteso:**

```json
{
  "id": 1,
  "name": "Leanne Graham",
  "username": "Bret",
  "email": "Sincere@april.biz",
  ...
}
```

**Hint:**
`fetch()` restituisce una Promise. Serve `.then()` e poi `.json()`.

---

## **Esercizio 2: Gestire l'errore**

**Task:**
Fai fetch a un URL errato:
[https://jsonplaceholder.typicode.com/usersXXX/1](https://jsonplaceholder.typicode.com/usersXXX/1)

Gestisci l’errore con `.catch()`.
Stampa: `"Errore nella richiesta: [messaggio]"`

**Hint:**
`.catch()` cattura errori di rete, non i 404 (che sono risposte valide).

---

## **Esercizio 3: Chaining con trasformazione**

**Task:**
Fetch a:
[https://pokeapi.co/api/v2/pokemon/pikachu](https://pokeapi.co/api/v2/pokemon/pikachu)

Usa più `.then()` per:

* Parsare JSON
* Estrarre nome e peso
* Stampare `"Nome: [nome], Peso: [peso]"`

**Output atteso:**
`Nome: pikachu, Peso: 60`

---

## **Esercizio 4: Status code e response.ok**

**Task:**
Fetch a:
[https://jsonplaceholder.typicode.com/users/999999](https://jsonplaceholder.typicode.com/users/999999)

Se `response.ok === true`, stampa i dati.
Se `false`, stampa `"Errore HTTP: [status]"`.

**Output atteso:**
`Errore HTTP: 404`

---

## **Esercizio 5: Dati meteo (coordinate)**

**Task:**
Fetch meteo Milano:
[https://api.open-meteo.com/v1/forecast?latitude=45.46&longitude=9.19&current_weather=true](https://api.open-meteo.com/v1/forecast?latitude=45.46&longitude=9.19&current_weather=true)

Stampa: `"Temperatura attuale a Milano: [temperatura]°C"`

---

## **Esercizio 6: Multiple then per analisi**

**Task:**
Fetch paesi da:
[https://restcountries.com/v3.1/name/italy](https://restcountries.com/v3.1/name/italy)

Estrai:

* capitale → `capital[0]`
* popolazione → `population`

Stampa: `"Capitale: Rome, Popolazione: 59554023"`

---

# **BLOCCO 2: async/await**

## **Esercizio 7: Primo async/await**

Riscrivi esercizio 1 usando `async/await` in una funzione `getUser()` che ritorni i dati.

---

## **Esercizio 8: Try/catch per errori**

Riscrivi esercizio 2 usando `async/await` e gestisci l’errore con `try/catch`.

---

## **Esercizio 9: Multiple await sequenziali**

Crea una funzione che:

1. Fa fetch dell’utente ID 1
2. Fa fetch dei post dell’utente
3. Stampa: `"L’utente [nome] ha scritto [numero] post"`

---

## **Esercizio 10: L’errore del await dimenticato**

Codice dato (errato):

```js
async function getPokemon() {
  let response = fetch('https://pokeapi.co/api/v2/pokemon/ditto');
  let data = response.json();
  console.log(data.name);
}

getPokemon();
```

Osserva l’errore e correggi usando `await`.

---

## **Esercizio 11: Response.ok con async/await**

Riscrivi esercizio 4 con async/await, controllando `response.ok`.

---

## **Esercizio 12: Funzione async e chiamata corretta**

Crea la funzione:
`getCountryPopulation(countryName)`

Fetch a:
[https://restcountries.com/v3.1/name/${countryName}](https://restcountries.com/v3.1/name/${countryName})

Restituisci la popolazione.
Chiama la funzione con `"germany"` e stampa:

`Popolazione Germania: [numero]`

---

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