const express = require('express');
const fs = require('fs');
const path = require('path');

const app = express();
app.use(express.json());
const PORT = 3000;

let clienti = [
    { id: 1, nome: 'Han Solo', specie: 'umano', credito: 3000 },
    { id: 2, nome: 'Greedo', specie: 'Rodian', credito: 300 },
    { id: 3, nome: 'Lando Calrissian', specie: 'umano', credito: 2000 }
];

let bevande = [
    { id: 1, nome: 'Corellian Ale', gradazione: 5, prezzo: 10 },
    { id: 2, nome: 'Jawa Juice', gradazione: 2, prezzo: 5 },
    { id: 3, nome: 'Spotchka', gradazione: 20, prezzo: 15 }
];

let ordini = [];

let taglie = [
    { id: 1, clientId: 2, motivazione: 'Debito non pagato', ricompensa: 800, attiva: true }
];

let missioni = [
    { id: 1, codice: 'AURORA-1', descrizione: 'Recupero piani della Morte Nera', pianeta: 'Scarif', rischio: 'alto', clearance: 3, agente: 'Cassian Andor' },
    { id: 2, codice: 'NEBULA-4', descrizione: 'Sorveglianza porto di Mos Eisley', pianeta: 'Tatooine', rischio: 'basso', clearance: 1, agente: 'Fulcrum' },
    { id: 3, codice: 'ECLIPSE-7', descrizione: 'Sabotaggio generatori imperiali', pianeta: 'Lothal', rischio: 'alto', clearance: 2, agente: 'Hera Syndulla' },
    { id: 4, codice: 'PHANTOM-2', descrizione: 'Estrazione agente sotto copertura', pianeta: 'Coruscant', rischio: 'critico', clearance: 3, agente: 'Sconosciuto' }
];

const tessere = [
    { codice: 'HAN-001', ruolo: 'cliente', clearance: 1 },
    { codice: 'CHEWIE-02', ruolo: 'cliente', clearance: 1 },
    { codice: 'BOBA-007', ruolo: 'cacciatore', clearance: 2 },
    { codice: 'BOSSK-008', ruolo: 'cacciatore', clearance: 1 },
    { codice: 'FULCRUM-3', ruolo: 'ribelle', clearance: 2 },
    { codice: 'CASSIAN-9', ruolo: 'ribelle', clearance: 3 },
    { codice: 'ADMIN-000', ruolo: 'admin', clearance: 3 }
];

app.use((req, res, next) => {
    if (req.path.startsWith('/bevande')) {
        return next();
    }

    const tesseraCode = req.headers['x-tessera'];
    if (!tesseraCode) {
        return res.status(401).json({ errore: 'Tessera mancante. Nessuno entra senza tesserino.' });
    }
    const tessera = tessere.find(t => t.codice === tesseraCode);
    if (!tessera) {
        return res.status(403).json({ errore: 'Tessera non riconosciuta. Fuori dai piedi.' });
    }
    req.tessera = tessera;
    next();
});

const soloClienteEAdmin = (req, res, next) => {
    const ruolo = req.tessera.ruolo;
    if (ruolo === 'cliente' || ruolo === 'admin') return next();
    res.status(403).json({ errore: 'Accesso negato. Solo clienti o admin.' });
};

const soloCacciatoreEAdmin = (req, res, next) => {
    const ruolo = req.tessera.ruolo;
    if (ruolo === 'cacciatore' || ruolo === 'admin') return next();
    res.status(403).json({ errore: 'Accesso negato. Solo cacciatori o admin.' });
};

const soloAdmin = (req, res, next) => {
    if (req.tessera.ruolo === 'admin') return next();
    res.status(403).json({ errore: 'Accesso negato. Solo admin.' });
};

const soloRibelleEAdmin = (req, res, next) => {
    const ruolo = req.tessera.ruolo;
    if (ruolo === 'ribelle' || ruolo === 'admin') return next();
    res.status(403).json({ errore: 'Accesso negato. Solo ribelli o admin.' });
};

app.get('/clienti', (req, res) => {
    res.json(clienti);
});

app.get('/clienti/:id', (req, res) => {
    const id = parseInt(req.params.id);
    const cliente = clienti.find(c => c.id === id);
    if (!cliente) return res.status(404).json({ errore: 'Cliente non trovato' });
    res.json(cliente);
});

app.post('/clienti', (req, res) => {
    const { nome, specie, credito } = req.body;
    if (!nome || !specie || credito === undefined || credito < 0) {
        return res.status(400).json({ errore: 'Dati cliente non validi' });
    }
    if (clienti.some(c => c.nome === nome)) {
        return res.status(409).json({ errore: 'Cliente già esistente' });
    }
    const nuovoId = clienti.length ? Math.max(...clienti.map(c => c.id)) + 1 : 1;
    const nuovo = { id: nuovoId, nome, specie, credito };
    clienti.push(nuovo);
    res.status(201).json(nuovo);
});

app.put('/clienti/:id', (req, res) => {
    const id = parseInt(req.params.id);
    const cliente = clienti.find(c => c.id === id);
    if (!cliente) return res.status(404).json({ errore: 'Cliente non trovato' });
    const { nome, specie, credito } = req.body;
    if (nome) cliente.nome = nome;
    if (specie) cliente.specie = specie;
    if (credito !== undefined) cliente.credito = credito;
    res.json(cliente);
});

app.delete('/clienti/:id', soloAdmin, (req, res) => {
    const id = parseInt(req.params.id);
    const index = clienti.findIndex(c => c.id === id);
    if (index === -1) return res.status(404).json({ errore: 'Cliente non trovato' });
    clienti.splice(index, 1);
    res.status(204).send();
});

app.get('/ordini', (req, res) => {
    res.json(ordini);
});

app.post('/ordini', soloClienteEAdmin, (req, res) => {
    const { clientId, bevandaId, quantita } = req.body;
    if (!clientId || !bevandaId || !quantita || quantita <= 0) {
        return res.status(400).json({ errore: 'Dati ordine non validi' });
    }
    const cliente = clienti.find(c => c.id === clientId);
    if (!cliente) return res.status(404).json({ errore: 'Cliente non trovato' });
    const bevanda = bevande.find(b => b.id === bevandaId);
    if (!bevanda) return res.status(404).json({ errore: 'Bevanda non trovata' });

    let prezzoUnitario = bevanda.prezzo;
    if (bevanda.gradazione > 15) {
        prezzoUnitario *= 1.15;
    }
    const costoTotale = prezzoUnitario * quantita;

    if (cliente.credito < costoTotale) {
        return res.status(400).json({ errore: 'Credito insufficiente' });
    }

    cliente.credito -= costoTotale;

    const nuovoOrdine = {
        id: ordini.length + 1,
        cliente: cliente.nome,
        bevandaId,
        quantita,
        costoTotale
    };
    ordini.push(nuovoOrdine);

    let taglieChiuse = 0;
    const taglieDaChiudere = taglie.filter(t => t.clientId === clientId && t.attiva === true);
    taglieDaChiudere.forEach(t => {
        t.attiva = false;
        taglieChiuse++;
    });

    res.status(201).json({
        ...nuovoOrdine,
        taglia_riscossa: taglieChiuse
    });
});

app.get('/taglie', soloCacciatoreEAdmin, (req, res) => {
    res.json(taglie);
});

app.post('/taglie', soloCacciatoreEAdmin, (req, res) => {
    const { clientId, motivazione, ricompensa } = req.body;
    if (!clientId || !motivazione || !ricompensa) {
        return res.status(400).json({ errore: 'Dati taglia non validi' });
    }
    const cliente = clienti.find(c => c.id === clientId);
    if (!cliente) return res.status(404).json({ errore: 'Cliente inesistente' });

    let ricompensaEffettiva = ricompensa;
    if (cliente.credito < 500) {
        ricompensaEffettiva = ricompensa * 1.2;
    }
    const nuovaTaglia = {
        id: taglie.length + 1,
        clientId,
        motivazione,
        ricompensa: ricompensaEffettiva,
        attiva: true
    };
    taglie.push(nuovaTaglia);
    res.status(201).json(nuovaTaglia);
});

app.patch('/taglie/:id/chiudi', soloAdmin, (req, res) => {
    const id = parseInt(req.params.id);
    const taglia = taglie.find(t => t.id === id);
    if (!taglia) return res.status(404).json({ errore: 'Taglia non trovata' });
    taglia.attiva = false;
    res.json({ message: 'Taglia chiusa', taglia });
});

const middlewareGradazione = (req, res, next) => {
    const gradHeader = req.headers['x-gradazione-max'];
    if (gradHeader !== undefined) {
        const parsed = parseInt(gradHeader);
        req.gradazioneMax = isNaN(parsed) ? null : parsed;
    } else {
        req.gradazioneMax = null;
    }
    next();
};

app.get('/bevande', middlewareGradazione, (req, res) => {
    if (req.gradazioneMax === null) {
        return res.json(bevande);
    }
    const filtrate = bevande.filter(b => b.gradazione <= req.gradazioneMax);
    res.json(filtrate);
});

const middlewareRuolo = (req, res, next) => {
    const ruoloHeader = req.headers['x-ruolo'];
    req.ruolo = ruoloHeader || 'ospite';
    next();
};

app.get('/clienti/:id/ordini', middlewareRuolo, (req, res) => {
    const id = parseInt(req.params.id);
    if (isNaN(id)) return res.status(400).json({ errore: 'ID non valido' });
    const cliente = clienti.find(c => c.id === id);
    if (!cliente) return res.status(404).json({ errore: 'Cliente non trovato' });

    const ordiniCliente = ordini.filter(o => o.cliente === cliente.nome);
    if (req.ruolo === 'admin') {
        return res.json(ordiniCliente);
    } else {
        const ordiniSenzaCosto = ordiniCliente.map(({ costoTotale, ...resto }) => resto);
        return res.json(ordiniSenzaCosto);
    }
});

app.get('/clienti/:id/riepilogo', (req, res) => {
    const id = parseInt(req.params.id);
    if (isNaN(id)) return res.status(400).json({ errore: 'ID non valido' });
    const cliente = clienti.find(c => c.id === id);
    if (!cliente) return res.status(404).json({ errore: 'Cliente non trovato' });

    const ordiniCliente = ordini.filter(o => o.cliente === cliente.nome);
    const numeroOrdini = ordiniCliente.length;
    const totaleSpeso = ordiniCliente.reduce((sum, o) => sum + o.costoTotale, 0);

    let bevandaPreferita = null;
    if (ordiniCliente.length > 0) {
        const aggregato = {};
        for (const ord of ordiniCliente) {
            aggregato[ord.bevandaId] = (aggregato[ord.bevandaId] || 0) + ord.quantita;
        }
        let maxQta = 0;
        let bestId = null;
        for (const [idBev, qta] of Object.entries(aggregato)) {
            if (qta > maxQta) {
                maxQta = qta;
                bestId = parseInt(idBev);
            }
        }
        const bestBevanda = bevande.find(b => b.id === bestId);
        bevandaPreferita = bestBevanda ? bestBevanda.nome : null;
    }

    const taglieAttive = taglie.filter(t => t.clientId === id && t.attiva === true).length;

    res.json({
        cliente: cliente.nome,
        credito_attuale: cliente.credito,
        numero_ordini: numeroOrdini,
        totale_speso: totaleSpeso,
        bevanda_preferita: bevandaPreferita,
        taglie_attive: taglieAttive
    });
});

const checkRuoloMissioni = (req, res, next) => {
    if (req.tessera.ruolo === 'ribelle' || req.tessera.ruolo === 'admin') {
        return next();
    }
    res.status(403).json({ errore: 'Accesso negato. Solo ribelli o admin.' });
};

const soloGetMissioni = (req, res, next) => {
    if (req.method !== 'GET') {
        return res.status(405).json({ errore: 'Metodo non consentito. Le missioni non si toccano.' });
    }
    next();
};

const filtraMissioni = (req, res, next) => {
    const clearanceUtente = req.tessera.clearance;
    req.missioniVisibili = missioni.filter(m => m.clearance <= clearanceUtente);
    next();
};

app.use('/missioni', checkRuoloMissioni);
app.use('/missioni', soloGetMissioni);
app.use('/missioni', filtraMissioni);

app.get('/missioni', (req, res) => {
    const clearance = req.tessera.clearance;
    if (clearance === 0) {
        return res.status(403).json({ errore: 'Clearance insufficiente. Non sai niente.' });
    }
    let risultato = req.missioniVisibili;
    if (clearance === 1 || clearance === 2) {
        risultato = risultato.map(m => ({ ...m, agente: '[CLASSIFICATO]' }));
    }
    res.json(risultato);
});

app.get('/missioni/:id', (req, res) => {
    const id = parseInt(req.params.id);
    const clearance = req.tessera.clearance;
    if (clearance === 0) {
        return res.status(403).json({ errore: 'Clearance insufficiente.' });
    }

    const missioneTrovata = req.missioniVisibili.find(m => m.id === id);
    if (!missioneTrovata) {
        const esisteOriginale = missioni.some(m => m.id === id);
        if (esisteOriginale) {
            return res.status(403).json({ errore: 'Clearance insufficiente per questa missione.' });
        } else {
            return res.status(404).json({ errore: 'Missione non trovata.' });
        }
    }

    let risultato = { ...missioneTrovata };
    if (clearance === 1 || clearance === 2) {
        risultato.agente = '[CLASSIFICATO]';
    }
    res.json(risultato);
});

app.listen(PORT, () => {
    console.log(`Cantina server in ascolto su http://localhost:${PORT}`);
});
