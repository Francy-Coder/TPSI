const URL = "https://jsonplaceholder.typicode.com/users/xxxxxxxxxxx"

let resElem = document.getElementById("res")

//NO ASYNC/AWAIT
/*fetch(URL).then(response => {
    console.log(response);
    
    //response.ok === response.status===200
    if(response.ok){ 
        //parse body (.json() è async)
        response.json().then(data => {
            console.log(data);
            //console.log(typeof data);
            resElem.textContent = JSON.stringify(data);
            for (key in data){
                console.log(data[key]);
            }
        });
    }else{
        //tutto male
        console.log("ERRORe: ", response.status);
    }
})
*/

fetch(URL)
    .then(response => {
        if (response.ok) {
            response.json()
        } else {
            throw new Error("ERRORE HTTP: " + response.status);
        }
    })
    .then(data => console.log(data)) //.then() --> restituisce una promessa
    .catch(error => console.log(error)) //uso catch per prevenire gli errori di rete
