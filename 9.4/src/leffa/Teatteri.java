package leffa;


public class Teatteri {
    private final String nimi, id;
    private final int numero;
    
    public Teatteri(String n, String num) {
        nimi = n;
        numero = Integer.valueOf(num);
        id = num;
    }
    public String getNimi() {
        return nimi;
    }
    public String getID() {
        return id;
    }
    public int getNumero() {
        return numero;
    }
}
