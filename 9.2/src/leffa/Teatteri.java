package leffa;


public class Teatteri {
    private final String nimi, numero;
    
    public Teatteri(String n, String num) {
        nimi = n;
        numero = num;
    }
    public String getNimi() {
        return nimi;
    }
    public String getNumero() {
        return numero;
    }
}
