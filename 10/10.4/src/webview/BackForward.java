package webview;

import java.util.ArrayList;

public class BackForward {
    public ArrayList<String> osoitteet = new ArrayList<>();
    private static BackForward bf = null;
    int itr;
    
    private BackForward(String o) {
        osoitteet.add(o);
        itr = 0;
    }
    static public BackForward getInstance() {
        if (bf == null)
            bf = new BackForward("http://www.google.com");
        return bf;
    }
}