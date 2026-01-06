(function() {
    const container = document.getElementById('game-container');
    const numberOfButtons = Math.floor(Math.random() * 10) + 1;
    const entityPoints = {};

    for (let i = 1; i <= numberOfButtons; i++) {
        const lp = Math.floor(Math.random() * 10) + 1;
        const buttonId = `b${i}`;
        
        const button = document.createElement('button');
        button.id = buttonId;
        button.innerText = `LP: ${lp}`;
        
        entityPoints[buttonId] = lp;

        container.appendChild(button);
    }

    window.getLifePoints = function(id) {
        return entityPoints[id] || null;
    };
})();
