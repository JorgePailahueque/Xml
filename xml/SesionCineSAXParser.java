/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;
import java.util.Vector; 
import javax.xml.parsers.SAXParserFactory; 
import javax.xml.parsers.ParserConfigurationException; 
import javax.xml.parsers.SAXParser;
import org.xml.sax.Attributes; 
import org.xml.sax.SAXException; 
import org.xml.sax.helpers.DefaultHandler;
/**
 *
 * @author diego
 */
public class SesionCineSAXParser extends DefaultHandler  {
    private Vector sesionesPeliculas; 
    private Vector sesionesStrPelicula;
    private String codigo, titulo, director, actores; 
    private String textoSesion;
    private boolean esTextoSesion = false;
    
    public SesionCineSAXParser(String url) { 
        SAXParserFactory factory = SAXParserFactory.newInstance(); 
        // factory.setNamespaceAware(true); // activar namespaces try 
    try {
        SAXParser saxParser = factory.newSAXParser();
        saxParser.parse(url, this); 
    } catch (Throwable t) { 
        t.printStackTrace(); 
    }
    }
    
    public void startElement(String namespaceURI, String lName, String qName,Attributes attrs) 
            throws SAXException {
    if (qName.equals("CarteleraCine")) { 
        this.sesionesPeliculas = new Vector(); 
        
    } else if (qName.equals("Pelicula")) { 
    this.sesionesStrPelicula = new Vector(); 
    this.codigo = attrs.getValue("codigo"); 
    this.titulo = attrs.getValue("titulo"); 
    this.director = attrs.getValue("director"); 
    this.actores = attrs.getValue("actores"); 
    
    } else if (qName.equals("Sesion")) {
    this.esTextoSesion = true; this.textoSesion = "";
    }
    }
    
    public void characters(char[] ch, int start, int length) { 
        if (this.esTextoSesion == true) {
            this.textoSesion += (new String(ch, start, length)).trim(); 
        }
        }
        
    public void endElement(String namespaceURI, String sName, String qName) 
            throws SAXException { 
        if (qName.equals("Pelicula")) { 
        Pelicula pelicula = new Pelicula(this.codigo, this.titulo, this.director, this.actores);
        this.sesionesPeliculas.add(new SesionesPelicula(pelicula,
        this.sesionesStrPelicula)); 
        }else if (qName.equals("Sesion")) { 
        this.esTextoSesion = false; this.sesionesStrPelicula.add(this.textoSesion);
}

}
    
    public Vector getSesionesPeliculas() { 
        return this.sesionesPeliculas; 
    }
    }
    

