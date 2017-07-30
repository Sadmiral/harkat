package leffa;


import java.io.IOException;
import java.util.ArrayList;
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
                            //System.out.println(name.getTextContent());
                            //System.out.println(id.getTextContent());
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
}