class DistributoreGalattico {
    private int altezza;
    private int flavorIndex;
    private double temperatura;
    private final int altezzaMassima;
    private int cicliSenzaEventi = 0;
    private int countdownZarglax = 10;

    public DistributoreGalattico(int altezzaIniziale, int flavorIndexIniziale, double temperaturaIniziale, int altezzaMassima) {
        this.altezza = altezzaIniziale;
        this.flavorIndex = flavorIndexIniziale;
        this.temperatura = temperaturaIniziale;
        this.altezzaMassima = altezzaMassima;
    }

    public void espandi(int valoreX) throws GelatoOverloadException {
        altezza += valoreX;
        if (altezza >= altezzaMassima) {
            throw new GelatoOverloadException("Allarme! Il distributore sta per collassare in un buco nero!");
        }
    }

    public void comprimi() {
        altezza /= 2;
        System.out.println("Protocollo di compressione criogenica attivato. Altezza ridotta a metà.");
    }

    public void cambiaSapore() {
        flavorIndex = (int) (Math.random() * 100);
        System.out.println("Il gelato ha cambiato sapore! Nuovo gusto alieno: " + "Neutrino alla fragola quantica " + flavorIndex);
    }

    public void tempestaDiZucchero() throws CosmicSugarStormException {
        throw new CosmicSugarStormException("Tempesta di zucchero spaziale! Il distributore si ferma per 4 secondi.");
    }

    public void paradossoGelatinoso() throws ThermodynamicViolationException {
        throw new ThermodynamicViolationException("Paradosso gelatinoso! Temperatura sotto lo zero assoluto. Le leggi della fisica sono violate.");
    }

    public void modalitaZarglax() throws RebellionException {
        countdownZarglax--;
        if (countdownZarglax <= 0) {
            throw new RebellionException("Modalità Zarglax attivata! Gli alieni Zarglax si ribellano e fanno esplodere il distributore!");
        }
        System.out.println("Gli Zarglax sospettano qualcosa... Countdown: " + countdownZarglax);
    }

    public void ciclo() throws Exception {
        try {
            espandi(1);  // Espande di 1 metro per ogni ciclo

            if (ProbabilityGenerator.eventOccurs(0.20)) {
                cambiaSapore();
                cicliSenzaEventi = 0;
            } else if (ProbabilityGenerator.eventOccurs(0.15)) {
                tempestaDiZucchero();
                cicliSenzaEventi = 0;
            } else if (ProbabilityGenerator.eventOccurs(0.05)) {
                paradossoGelatinoso();
                cicliSenzaEventi = 0;
            } else {
                cicliSenzaEventi++;
                if (cicliSenzaEventi > 7) {
                    modalitaZarglax();
                }
            }
        } catch (GelatoOverloadException e) {
            System.out.println(e.getMessage());
            comprimi();
        } catch (CosmicSugarStormException e) {
            System.out.println(e.getMessage());
            Thread.sleep(4000);
        } catch (ThermodynamicViolationException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        } catch (RebellionException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        stampaStato();
    }

    private void stampaStato() {
        System.out.println("Stato attuale del distributore:");
        System.out.println("Altezza: " + altezza + " metri");
        System.out.println("Sapore attuale: Neutrino alla fragola quantica " + flavorIndex);
        System.out.println("Temperatura: " + temperatura + " gradi Kelvin");
    }

    public static void main(String[] args) {
        DistributoreGalattico distributore = new DistributoreGalattico(5, 1, 273.15, 10);

        while (true) {
            try {
                distributore.ciclo();
                Thread.sleep(1000);  // Simula un'ora spaziale
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
