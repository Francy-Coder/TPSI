const BASE_URL="http://localhost:3000";

async function getSolarPanels(){
    let response = await fetch(BASE_URL + "/station/status");

    if(response.ok){
        let json = await response.json();
        let panels = json.power.solar.panels; //Panelli solari presenti
        let panelsCount = panels.length; // "panels" è un array

        let activePanels = 0; //Panelli solari operativi
        for (const panel of panels){
            if(panel.status === "nominal"){
                activePanels++;
            }
        }

        const percetageActive = (activePanels / panelsCount) * 100; //Percentuale dei panelli solari operativi

        console.log(json);
        console.log(panels);

        //Creo un OBJ JSON e lo restituisco
        return{
            totalPanels: panelsCount,
            opearionalPanels: activePanels,
            percetage: percetageActive
        }
    }else{
        console.log("Errore HTTP:" + response.status);
    }
}

//Non bellisima ma utile...
async function printResults(){
    let results = await getSolarPanels();

    console.log(results);
}

getSolarPanels();