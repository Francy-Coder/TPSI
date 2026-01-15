const http = require("http")

const PORT = 1025;
const HOSTNAME = "localhost";

// Per creare un server su node, possiamo utilizzare:
const server = http.createServer((req, res) => {
    res.end("Ciao, benvenuto");
}) //request = richiesta che arriva dal client || response = risposta che mando al client

server.listen(PORT, HOSTNAME, () => {
    console.log("servizio ONLINE" + HOSTNAME + ":" + PORT); //Se non esegue, restituisce un errore (EADDRRINUSE)
}) //Riferimento al server creato precedentemente



/*Terminale
cntrl + J <-- Apre terminale
cntrl + C <-- Ferma esecuzione terminale

* Sul Server:
node server.js

* Sul Client:
curl localhost:1025 <-- Client URL, un programmino che permette di eseguire chiamate di rete livello x (dipende dal protocollo), la default di curl è GET

*/
