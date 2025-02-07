public class Coordinata {
    private double latitudine;
    private double longitudine;

    public Coordinata(double latitudine, double longitudine) { //throws IllegalAccessException
        setLatitudine(latitudine);
        setLongitudine(longitudine);
        // IAE è eccezione NON controllata
        /*if (latitudine > 90 || latitudine < -90)
            throw new IllegalAccessException("Valore di latiduine non valido: " + latitudine);

        if (longitudine < -180.0 || longitudine > 180.0)
            throw new IllegalAccessException("Valore di longitudine non valido: " + latitudine);*/
    }

    //Definire metodo sposta

    public double getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(double latitudine) {
        this.latitudine = latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(double longitudine) {
        this.longitudine = longitudine;
    }

    @Override
    public String toString() {
        return null;
    }
}
