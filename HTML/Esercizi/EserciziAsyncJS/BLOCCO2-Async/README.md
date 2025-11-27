# **Esercizi Async JS: Promise, Async/Await e Fetch API**
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