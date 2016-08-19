
package xml;


public class Xml {

    public static void main(String[] args) {
        SesionCineSAXParser s = new SesionCineSAXParser("file:///C:/Users/diego/Documents/NetBeansProjects/Xml/xml.txt");
        s.startElement("Cartelera","Pelicula", null, null);
    }
    
}
