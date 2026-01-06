(function() {
    const container = document.getElementById('game-container');
    const damageButton = document.getElementById('damage-button');
    const inputField = document.getElementById('entity-id');
    const numberOfButtons = Math.floor(Math.random() * 10) + 1;
    const entityPoints = {};
    const resurrectionDelay = 2000;

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

    inputField.addEventListener('input', () => {
        const id = inputField.value;
        damageButton.innerText = `Togli LP a ${id}`;
    });

    const applyDamage = (id, damage) => {
        if (entityPoints[id] !== undefined) {
            entityPoints[id] -= damage;
            const button = document.getElementById(id);
            button.innerText = `LP: ${entityPoints[id]}`;

            if (entityPoints[id] <= 0) {
                button.disabled = true; 
                setTimeout(() => {
                    resurrectEntity(id);
                }, resurrectionDelay); 
            }
        }
    };

    const resurrectEntity = (id) => {
        const lp = Math.floor(Math.random() * 10) + 1;
        entityPoints[id] = lp;
        const button = document.getElementById(id);
        button.disabled = false; 
        button.innerText = `LP: ${lp}`;
    };

    damageButton.addEventListener('click', () => {
        const id = inputField.value;
        applyDamage(id, 1);
    });

    damageButton.addEventListener('contextmenu', (e) => {
        e.preventDefault(); 
        const id = inputField.value;
        applyDamage(id, 2); 
    });
})();
