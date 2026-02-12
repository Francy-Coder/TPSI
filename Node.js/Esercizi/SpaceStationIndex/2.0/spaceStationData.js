const BASE_URL = "http://localhost:3000";

async function getPanels() {
    const response = await fetch(BASE_URL + "/station/status");

    if (response.ok) {
        const json = await response.json()
        // console.log(json);
        const panels = json.power.solar.panels
        const totalPanels = panels.length
        // console.log(panels);

        //Trova solo quelli operativi
        let activePanels = 0;
        for (const panel of panels) {
            if (panel.status === "nominal") {
                activePanels++;
            }
        }

        const percentage = (activePanels / totalPanels) * 100;

        console.log(json);
        console.log(panels);

        return {
            totalPanels: totalPanels,
            operationalPanels: activePanels,
            percentage: percentage
        }
    }else{
        console.log("Errore HTTP:" + response.status);
    }
}

async function printResults() {
    const results = await getPanels();
    //console.clear();
    console.log(results);
}

printResults();

async function getDegradedModules() {
    const response = await fetch(BASE_URL + "/station/modules");
    if (!response.ok) return;

    const data = await response.json();
    const degradedModules = [];

    for (const module of data.modules) {
        if (module.systems && module.systems.subsystems) {
            const degradedSystemNames = [];
            
            for (const sub of module.systems.subsystems) {
                if (sub.status !== "nominal") {
                    degradedSystemNames.push(sub.name);
                }
            }

            if (degradedSystemNames.length > 0) {
                degradedModules.push({
                    moduleId: module.id,
                    degradedSystemNames: degradedSystemNames
                });
            }
        }
    }
    return degradedModules;
}

async function getLabConsumption() {
    const response = await fetch(BASE_URL + "/station/modules");
    if (!response.ok) return;

    const data = await response.json();
    let totalPower = 0;
    let totalCooling = 0;
    let activeExperimentsCount = 0;

    for (const module of data.modules) {
        if (module.type === "laboratory" && module.experiments) {
            for (const exp of module.experiments) {
                if (exp.status === "active") {
                    totalPower += exp.resourceConsumption.power;
                    totalCooling += exp.resourceConsumption.cooling;
                    activeExperimentsCount++;
                }
            }
        }
    }

async function checkEnergyEmergency() {
    const statusRes = await fetch(BASE_URL + "/station/status");
    const modulesRes = await fetch(BASE_URL + "/station/modules");
    
    if (!statusRes.ok || !modulesRes.ok) return;

    const statusData = await statusRes.json();
    const modulesData = await modulesRes.json();

    const reserves = statusData.power.reserves;

    if (reserves < 95) {
        const activeExps = [];
        
        for (const module of modulesData.modules) {
            if (module.experiments) {
                for (const exp of module.experiments) {
                    if (exp.status === "active") {
                        activeExps.push({
                            experimentId: exp.id,
                            name: exp.name,
                            powerConsumption: exp.resourceConsumption.power
                        });
                    }
                }
            }
        }
        return activeExps;
    } else {
        return { message: "Energia sufficiente, nessuna azione necessaria" };
    }
}
}
