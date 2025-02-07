public class Coordinata {
    private double latitudine;
    private double longitudine;

    public Coordinata(double latitudine, double longitudine) {
        if (latitudine < -90.0 || latitudine > 90.0) {
            throw new IllegalArgumentException("Latitudine non valida: " + latitudine);
        }
        if (longitudine < -180.0 || longitudine > 180.0) {
            throw new IllegalArgumentException("Longitudine non valida: " + longitudine);
        }
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }

    public double getLatitudine() {
        return latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }

    public void setLatitudine(double latitudine) {
        if (latitudine < -90.0 || latitudine > 90.0) {
            throw new IllegalArgumentException("Latitudine non valida: " + latitudine);
        }
        this.latitudine = latitudine;
    }

    public void setLongitudine(double longitudine) {
        if (longitudine < -180.0 || longitudine > 180.0) {
            throw new IllegalArgumentException("Longitudine non valida: " + longitudine);
        }
        this.longitudine = longitudine;
    }
}