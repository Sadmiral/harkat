package javafx;

import java.io.*;

/**
 *
 * @author Sadmiral
 */
public class ReadAndWriteIO {
    
    public ReadAndWriteIO(File s) {
        File file = s;
    }
    
    public void writeFile(String i, File o) throws FileNotFoundException, IOException {        
        
        try (BufferedWriter out = new BufferedWriter(new FileWriter(o))) {
            out.write(i);
        }
    }

    public String readFile(File filepath) throws FileNotFoundException, IOException {
        String rivi, r = "";
        
        try (BufferedReader in = new BufferedReader(new FileReader(filepath))) {
            while ((rivi = in.readLine()) != null) {
                r = r + rivi + "\n";
            }
        }
        return r;
    }
}
