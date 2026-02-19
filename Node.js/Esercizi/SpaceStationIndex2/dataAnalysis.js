/**
 * Esercizio: Space Station API 2
 * - Link: https://docs.google.com/document/d/1X7fgZ6R_79Tq9YJw0z91PTuJXOzw6uzOvrxkEDxJ5hc/edit?tab=t.0
 * - Note: Non Completo
 */

async function powerCalculation() {
    const response = await fetch("http://localhost:3000/experiments");
    const data = await response.json();

    const experiments = data.experiments;
    const availablePower = data.powerStatus.available;

    let activeCount = 0;
    let standbyCount = 0;
    let standbyExperiments = [];

    for (const exp of experiments) {
        if (exp.status === "active"){
            activeCount++;
        }

        if (exp.status === "standby") {
            standbyCount++;
            standbyExperiments.push(exp);
        }
    }

    const priorityPowerMap = { high: 4.5, medium: 3.0, low: 2.0 };

    let canActivateMore = false;
    for (const exp of standbyExperiments) {
        const requiredPower = priorityPowerMap[exp.priority] ?? 2.0;
        if (availablePower >= requiredPower) {
            canActivateMore = true;
            break;
        }
    }

    return { activeCount, standbyCount, canActivateMore };
}

async function shutdownAllLowExperiments(){
    const response = await fetch("http://localhost:3000/experiments");
    const data = await response.json();

    const experiments = data.experiments;
    let commandsFromServer = [];

    for (const exp of experiments) {
        if (exp.status === "active" && exp.priority === "low") {
            //fare la post a POST /commands
            const postResponse = await fetch("http://localhost:3000/commands", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    action: "shutdown",
                    experimentId: exp.id,
                    reason: "obsoleto"
                })
            });

            const postData = await postResponse.json();
            if (postData.success) {
                commandsFromServer.push({
                    experimentId: exp.id,
                    commandId: postData.command.id
                });
            }
        }
    }

    return commandsFromServer;
}

async function run() {
    const task1Result = await powerCalculation();
    console.log("TASK1 RESULT:", task1Result);

    const task2Result = await shutdownAllLowExperiments();
    console.log("TASK2 RESULT:", task2Result);
}

run();

