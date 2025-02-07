public class RaidSimulator {
    private Avatar[] avatars;
    private PostazioneRDA postazione;

    public RaidSimulator(Avatar[] avatars, PostazioneRDA postazione) {
        this.avatars = avatars;
        this.postazione = postazione;
    }

    public void eseguiRaid() {
        for (int i = 0; i < 10; i++) {
            for (Avatar avatar : avatars) {
                try {
                    avatar.attacca(postazione);
                    System.out.println(avatar.getNome() + " ha attaccato con successo!");
                } catch (AttaccoFallitoException | ArmaMalfunzionanteException e) {
                    System.out.println(e.getMessage());
                } catch (DifesaInsufficienteException e) {
                    System.out.println(e.getMessage());
                    postazione.setPosizione(new Coordinata(Math.random() * 180 - 90, Math.random() * 360 - 180));
                    postazione.setDifesa((int) (Math.random() * 100 + 50));
                } catch (UnobtaniumEsauritoException e) {
                    System.out.println(e.getMessage());
                    postazione.setPosizione(new Coordinata(Math.random() * 180 - 90, Math.random() * 360 - 180));
                    postazione.setUnobtaniumRimanente((int) (Math.random() * 11 + 1));
                }
            }
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        Avatar[] avatars = {
            new Avatar("Jake", "Lancia", 30),
            new Avatar("Neytiri", "Arco", 25),
            new Avatar("Tsu'tey", "Ascia", 20)
        };
        PostazioneRDA postazione = new PostazioneRDA(new Coordinata(0, 0), 100, 10);
        RaidSimulator simulator = new RaidSimulator(avatars, postazione);
        simulator.eseguiRaid();
    }
}
