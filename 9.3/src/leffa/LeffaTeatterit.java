package leffa;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class LeffaTeatterit {
    public ArrayList<Teatteri> teatteri_array;
    public ArrayList<String> naytos_array;
    private static LeffaTeatterit lt = null;
    
    private LeffaTeatterit(String content) {
        Parser(content);
    }
    static public LeffaTeatterit getInstance() {
        if (lt == null)
            lt = new LeffaTeatterit("http://www.finnkino.fi/xml/TheatreAreas/");
        return lt;
    }
    private void Parser(String content) {
        teatteri_array = new ArrayList<>();
        
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            try {
                Document doc = builder.parse(content);
                NodeList theatreList = doc.getElementsByTagName("TheatreArea");
                for (int i = 1; i<theatreList.getLength();i++) {
                    Node t = theatreList.item(i);
                    if (t.getNodeType()==Node.ELEMENT_NODE) {
                        Element theatre = (Element) t;
                        NodeList nameList = theatre.getChildNodes();
                        
                        Node n = nameList.item(1);
                        Node n1 = nameList.item(3);
                            
                        if(n.getNodeType()==Node.ELEMENT_NODE) {
                            Element id = (Element) n;
                            Element name = (Element) n1;

                            teatteri_array.add(new Teatteri(name.getTextContent(), id.getTextContent()));
                        }
                    }
                }
            } catch (SAXException | IOException ex) {
                Logger.getLogger(LeffaTeatterit.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(LeffaTeatterit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Listaa(String content) {
        naytos_array = new ArrayList<>();
        
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            try {
                Document doc = builder.parse(content);
                NodeList showList = doc.getElementsByTagName("Title");
                for (int i = 0; i<showList.getLength();i++) {
                    Node n = showList.item(i);
                    
                        if(n.getNodeType()==Node.ELEMENT_NODE) {
                            Element naytos = (Element) n;
                            //System.out.println(naytos.getTextContent());
                            naytos_array.add(naytos.getTextContent());
                        }
                    }
                System.out.println(naytos_array);
            } catch (SAXException | IOException ex) {
                Logger.getLogger(LeffaTeatterit.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(LeffaTeatterit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}