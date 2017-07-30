package bdfx;

public class Bottle {
    private final String nimi;
    private final double koko, hinta;
    
    public Bottle(String n, double k, double h) {
        nimi = n;
        koko = k;
        hinta = h;
    }
    public String getName() {
        return nimi;
    }
    public double getKoko() {
        return koko;
    }
    public double getHinta() {
        return hinta;
    }
}
