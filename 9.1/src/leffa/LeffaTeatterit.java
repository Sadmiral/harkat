package leffa;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ListView;
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
    
    public void Listaa(String content, String hNaytos, Date alku, Date loppu) {
        naytos_array = new ArrayList<>();

        SimpleDateFormat timev = new SimpleDateFormat("HH:mm"); //15:30
        
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            try {
                Document doc = builder.parse(content);
                NodeList showList = doc.getElementsByTagName("Title");
                NodeList alkuList = doc.getElementsByTagName("dttmShowStart");
                NodeList päätyList = doc.getElementsByTagName("dttmShowEnd");
                
                //Etsitään jokainen haluttu childNode for loopilla
                for (int i = 0; i<showList.getLength();i++) {
                    Node n = showList.item(i);
                    Node a = alkuList.item(i);
                    Node p = päätyList.item(i);
                    
                    if(n.getNodeType()==Node.ELEMENT_NODE) {
                        Element naytos = (Element) n;
                        Element aloitus = (Element) a;
                        Element päättymis = (Element) p;
                        
                        //Muutetaan saatu string haluttuun muotoon, jotta voidaan verrata kellonaikoja
                        String alkuS = aloitus.getTextContent().substring(11,16);
                        String loppuS = päättymis.getTextContent().substring(11,16);
                        
                        //Sitten parsetaan stringistä Date halutulla formatilla (HH:mm)
                        Date alkuv = timev.parse(alkuS);
                        Date loppuv = timev.parse(loppuS);
                        if (alku.before(alkuv) && loppu.after(loppuv) && hNaytos == null) {
                            naytos_array.add(naytos.getTextContent());
                        }
                        else if (alku.before(alkuv) && loppu.after(loppuv) && hNaytos != null) {
                            naytos_array.add(naytos.getTextContent());
                            naytos_array.add(alkuS);
                            naytos_array.add(loppuS);
                        }
                    }
                }
            } catch (SAXException | IOException | ParseException ex) {
                Logger.getLogger(LeffaTeatterit.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(LeffaTeatterit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}