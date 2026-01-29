/* Il prof ha modificato il codice, controllare il suo repo*/

const http = require("http")

const PORT = 1025;
const HOSTNAME = "localhost";

let users = [
    {username:"UNAME1", age:17},
    {username:"UNAME2", age:18},
    {username:"UNAME3", age:19}
]

// Per creare un server su node, possiamo utilizzare:
const server = http.createServer((req, res) => {

    if(req.url === "/"){
        res.statusCode = 200; // OK
        res.setHeader("Content-Type", "text/plain");
        res.end("Ciao, benvenuto"); //Manda la risposta

    } if (req.url === "/users" && req.method === "GET") {
        res.statusCode = 200; 
        res.setHeader("Content-Type", "application/json");
        res.end(JSON.stringify(users));

    } else if (req.url === "/users" && req.method === "POST") {
        res.statusCode = 405; //Methond Not Allowed
        res.end();
    } else {
        res.statusCode = 404; //Not Found
        res.setHeader("Content-Type", "application/json");
        res.end(JSON.stringify({error:"not found"}));
    }

}) //request = richiesta che arriva dal client || response = risposta che mando al client

server.listen(PORT, HOSTNAME, () => {
    console.log("servizio ONLINE" + HOSTNAME + ":" + PORT); //Se non esegue, restituisce un errore (EADDRRINUSE)
}) //Riferimento al server creato precedentemente

/*Terminale
cntrl + J <-- Apre terminale | Creo 2 terminali bash, dove chiamo il primo "server" e il secondo "client"
cntrl + C <-- Ferma esecuzione terminale

* Sul Server:
node server.js
node --watch server.js

* Sul Client:
curl localhost:1025 <-- Client URL, un programmino che permette di eseguire chiamate di rete livello x (dipende dal protocollo), la default di curl è GET
*/
