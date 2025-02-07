public class Statistiche {
    private float[] datiStatistiche;

    public Statistiche(int numDati) {
        this.datiStatistiche = new float[numDati];
    }

    public float calcola() {
        float somma = 0;
        for (float dato : datiStatistiche) {
            somma += dato;
        }
        return somma / datiStatistiche.length;
    }

    public float[] getDatiStatistiche() {
        return datiStatistiche;
    }

    public void setDatiStatistiche(float[] datiStatistiche) {
        this.datiStatistiche = datiStatistiche;
    }
}
