/**
 * Esercizio: Space Station API 2
 * - Link: https://docs.google.com/document/d/1X7fgZ6R_79Tq9YJw0z91PTuJXOzw6uzOvrxkEDxJ5hc/edit?tab=t.0
 * - Note: Insicuro
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

async function executePendingCommands() {
    const response = await fetch("http://localhost:3000/commands");
    const data = await response.json();
    
    let executed = 0;
    let failed = 0;
    const results = [];

    for (const cmd of data.queue) {
        if (cmd.status === "pending") {
            const execRes = await fetch(`http://localhost:3000/commands/${cmd.id}/execute`, {
                method: "PUT"
            });
            const execData = await execRes.json();

            if (execData.success) {
                executed++;
                results.push(execData);
            } else {
                failed++;
            }
        }
    }

    return { executed, failed, results };
}

async function emergencyStopHighestPower() {
    const response = await fetch("http://localhost:3000/experiments");
    const data = await response.json();

    const target = data.experiments
        .filter(e => e.status === "active")
        .reduce((prev, current) => (parseFloat(prev.power) > parseFloat(current.power)) ? prev : current);

    if (!target) return { message: "No active experiments found" };

    const postRes = await fetch("http://localhost:3000/commands", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            action: "emergency_stop",
            experimentId: target.id,
            reason: "Excessive power consumption"
        })
    });
    const postData = await postRes.json();

    return { 
        action: postData.success ? "executed" : "failed", 
        experimentId: target.id, 
        powerSaved: target.power 
    };
}

async function dynamicPowerManagement() {
    const response = await fetch("http://localhost:3000/experiments");
    const data = await response.json();
    
    const { budget, used } = data.powerStatus;
    const threshold = budget * 0.8;
    let finalResult = {};

    if (used > threshold) {
        const target = data.experiments
            .filter(e => e.status === "active" && e.priority === "medium")
            .sort((a, b) => a.power - b.power)[0];

        if (target) {
            finalResult = await createAndExecute(target.id, "shutdown");
        }
    } else {
        const target = data.experiments.find(e => e.status === "standby");
        if (target) {
            finalResult = await createAndExecute(target.id, "activate");
        }
    }

    return finalResult;
}

async function createAndExecute(experimentId, action) {
    const postRes = await fetch("http://localhost:3000/commands", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ action, experimentId })
    });
    const postData = await postRes.json();

    const execRes = await fetch(`http://localhost:3000/commands/${postData.command.id}/execute`, {
        method: "PUT"
    });
    const execData = await execRes.json();

    return {
        action: action,
        experimentId: experimentId,
        newPowerUsed: execData.newPowerUsed
    };
}

async function run() {
    console.log("--- TASK 1 ---");
    console.log(await powerCalculation());

    console.log("--- TASK 2 ---");
    console.log(await shutdownAllLowExperiments());

    console.log("--- TASK 3 ---");
    console.log(await executePendingCommands());

    console.log("--- TASK 4 ---");
    console.log(await emergencyStopHighestPower());

    console.log("--- TASK 5 ---");
    console.log(await dynamicPowerManagement());
}
