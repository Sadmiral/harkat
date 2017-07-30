package bdfx;

import java.io.*;

/**
 *
 * @author Sadmiral
 */
public class ReadAndWriteIO {
    
    public ReadAndWriteIO(String s) {
        String file = s;
    }
    
    public void writeFile(Bottle i) throws FileNotFoundException, IOException {        
        
        try (BufferedWriter out = new BufferedWriter(new FileWriter("kuitti.txt"))) {
            out.write(i.getNimi() + " " + i.getKoko() + " " + i.getHinta() + "€");
        }
    }
}

