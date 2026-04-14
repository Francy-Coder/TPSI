BASE_URL = "http://localhost:3000";

async function mostraTuttiMinerali(){
    const response = await fetch(BASE_URL + "/minerali");

    if(response.ok){
        const minerali = await response.json();
        console.log(minerali);

        let totale = minerali.length;

        let mineraliCritici = 0;
        for(let i = 0; i < minerali.length; i++){
            if(minerali[i].tonnellate < 10){
                mineraliCritici++;
            }
        }

        console.log("Minerali totali: " + totale + ", di cui " + mineraliCritici + " con scorte critiche");
    }else{
        console.log("Errore: " + response.status);
    }
}

async function cercaMineralePerId(){
    let response = await fetch(BASE_URL + "/minerali/999");

    if(response.status = "404"){
        response = await fetch(BASE_URL + "/minerali/5");
    }

    if(response.ok){
        const minerali = await response.json();
        console.log("Nome: " + minerali.nome + ", Tonnelatte: " + minerali.tonnellate);
    }else{
        console.log("Errore: " + response.status);
    }
}

async function aggiungiNuovoMinerale(){
    const response = await fetch(BASE_URL + "/minerali", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            nome: "Tungsteno",
            categoria: "metalli",
            tonnellate: 25,
            creditiPerTonnellata: 420,
            zona: "est"
        })
    });

    if(response.ok){
        const minerali = await response.json();
        console.log(minerali);
    }
}

async function contaMineraliCategoria(){
    let tipo = "cristalli";
    let response = await fetch(BASE_URL + "/minerali/categoria/" + tipo);

    if(response.status === "404"){
        tipo = "metalli";
        response = await fetch(BASE_URL + "/minerali/categoria/" + tipo);
    }
    
    if(response.ok){
        const minerali = await response.json();
        let trovati = minerali.length;

        console.log("Minerali di categoria " + tipo + ": " + trovati);
    }else{
        console.log("Errore: " + response.status);
    }
}

async function calcolaValoreTotale(){
    const response = await fetch(BASE_URL + "/minerali");
    
    if(response.ok){
        const minerali = await response.json();

        let valoreTotale = 0;
        let piuPrezioso = minerali[0];
        
        for(let i = 0; i < minerali.length; i++){
            let valore = minerali[i].tonnellate * minerali[i].creditiPerTonnellata;
            valoreTotale += valore;

            if(valore > piuPrezioso.tonnellate * piuPrezioso.creditiPerTonnellata){
                piuPrezioso = minerali[i];
            }
        }

        console.log("Valore totale inventario: " + valoreTotale + " crediti");
        console.log("Minerale più prezioso: " + piuPrezioso.nome + " con valore " + piuPrezioso.tonnellate * piuPrezioso.creditiPerTonnellata + " crediti");
    }else{
        console.log("Errore: " + response.status);
    }
}

async function trovaMineraliCritici(){
    const response = await fetch(BASE_URL + "/minerali");

    if(response.ok){
        const minerali = await response.json();
        let mineraliCritici = "";

        for(let i = 0; i < minerali.length; i++){
            if(minerali[i].zona === "cratere" && minerali[i].tonnellate < 10){
                if(mineraliCritici !== ""){
                    mineraliCritici += ", "
                }
                mineraliCritici += minerali[i].nome;
            }
        }

        console.log("Minerali critici nella zona cratere: " + mineraliCritici);
    }else{
        console.log("Errore: " + response.status);
    }
}

async function richiamoFunzioni(){
    await mostraTuttiMinerali();
    await cercaMineralePerId();
    await aggiungiNuovoMinerale();
    await contaMineraliCategoria();
    await calcolaValoreTotale();
    await trovaMineraliCritici();
}

richiamoFunzioni();