# **Esercizi Async JS: Promise, Async/Await e Fetch API**
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