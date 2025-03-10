package persistencia;

import logica.clases.*;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Persistencia {

    public static boolean SerializarPerfil(Perfil p) {
        try (FileOutputStream file = new FileOutputStream("Perfil.dat");
             ObjectOutputStream objectOut = new ObjectOutputStream(file)) {
            objectOut.writeObject(p);
        } catch (IOException ex) {
            System.out.println("Se ha presentado un error al serializar el perfil: " + ex.getMessage());
            return false;
        }
        return true;
    }

    public static Perfil DeserializarPerfil() {
        Perfil p = null;
        try (FileInputStream file = new FileInputStream("Perfil.dat");
             ObjectInputStream objectIn = new ObjectInputStream(file)) {
            p = (Perfil) objectIn.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Se ha presentado un error al deserializar el perfil: " + ex.getMessage());
        }
        return p;
    }

}
