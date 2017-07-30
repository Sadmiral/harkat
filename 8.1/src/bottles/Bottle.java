package bottles;

public class Bottle {
    public String nimi, merkki;
    public double koko, hinta;
    
    public Bottle(String n, double k, double h) {
        nimi = n;
        koko = k;
        hinta = h;
    }

    public String getNimi() {
        return(nimi);
    }

    public double getKoko() {
        return(koko);
    }
    
    public double getHinta() {
        return(hinta);
    }
}

