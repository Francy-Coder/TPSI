(function() {
    const generators = [
        { id: 'g1', symbol: '🔥', speed: 2000, capacity: 20, current: 0, interval: null },
        { id: 'g2', symbol: '⚡', speed: 3000, capacity: 20, current: 0, interval: null },
        { id: 'g3', symbol: '💎', speed: 4000, capacity: 20, current: 0, interval: null },
        { id: 'g4', symbol: '🌾', speed: 5000, capacity: 20, current: 0, interval: null }
    ];

    const container = document.getElementById('generator-container');

    // Funzione per creare i bottoni dei generatori
    const createButtons = () => {
        generators.forEach(generator => {
            const button = document.createElement('button');
            button.id = generator.id;
            button.innerText = `${generator.symbol} ${generator.current}/${generator.capacity}`;

            button.addEventListener('click', () => collectResources(generator.id));

            container.appendChild(button);
            startProducing(generator); // Inizia la produzione automatica
        });
    };

    // Funzione per far partire la produzione
    const startProducing = (generator) => {
        generator.interval = setInterval(() => {
            if (generator.current < generator.capacity) {
                generator.current++;
                updateButtonText(generator);
            } else {
                stopProducing(generator);
            }
        }, generator.speed);
    };

    // Funzione per fermare la produzione
    const stopProducing = (generator) => {
        clearInterval(generator.interval);
        generator.interval = null;
    };

    // Funzione per raccogliere le risorse
    const collectResources = (id) => {
        const generator = generators.find(gen => gen.id === id);
        if (generator) {
            generator.current = 0; // Reset delle risorse
            updateButtonText(generator);
            startProducing(generator); // Riavvia la produzione
        }
    };

    // Funzione per aggiornare il testo del bottone
    const updateButtonText = (generator) => {
        const button = document.getElementById(generator.id);
        button.innerText = `${generator.symbol} ${generator.current}/${generator.capacity}`;
    };

    // Inizializza i bottoni
    createButtons();
})();
