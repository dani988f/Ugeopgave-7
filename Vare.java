package supermarked.data;

public class Vare {

    private String ean;
    private String navn;
    private double pris;

    public Vare(String ean, String navn, double pris) {
        this.ean = ean;
        this.navn = navn;
        this.pris = pris;
    }

    public String getEan() {
        return ean;
    }

    public String getNavn() {
        return navn;
    }

    public double getPris() {
        return pris;
    }
}