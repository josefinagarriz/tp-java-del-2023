package persistencia;

import logica.clases.*;
import excepciones.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.File;
import java.util.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LectorXML {

    public void cargarDatos(String rutaArchivo, Perfil p) throws ExcepcionArchivoInvalido
    {
        try
        {
            File archivo = new File(rutaArchivo);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivo);
            doc.getDocumentElement().normalize();

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

            // Leer y agregar Imagenes
            NodeList nListImagenes = doc.getElementsByTagName("Imagen");
            for (int temp = 0; temp < nListImagenes.getLength(); temp++) {
                Element elemento = (Element) nListImagenes.item(temp);
                String c = elemento.getElementsByTagName("codigo").item(0).getTextContent();
                String des = elemento.getElementsByTagName("descripcion").item(0).getTextContent();
                LocalDate f = LocalDate.parse(elemento.getElementsByTagName("fecha").item(0).getTextContent(), dateFormatter);
                LocalTime h = LocalTime.parse(elemento.getElementsByTagName("hora").item(0).getTextContent(), timeFormatter);
                int likes = Integer.parseInt(elemento.getElementsByTagName("cantLikes").item(0).getTextContent());
                //cargar etiquetas en una lista
                List<String> etiquetas = new ArrayList<>();
                NodeList etiquetasList = elemento.getElementsByTagName("etiqueta");
                for (int i = 0; i < etiquetasList.getLength(); i++) {
                    etiquetas.add(etiquetasList.item(i).getTextContent());
                }
                int com = Integer.parseInt(elemento.getElementsByTagName("cantComentarios").item(0).getTextContent());
                int res = Integer.parseInt(elemento.getElementsByTagName("resolucion").item(0).getTextContent());
                int an = Integer.parseInt(elemento.getElementsByTagName("ancho").item(0).getTextContent());
                int al = Integer.parseInt(elemento.getElementsByTagName("alto").item(0).getTextContent());
                Imagen i = new Imagen(res, an, al, c, des, f, h, likes, etiquetas, com);
                validarImagen(i);  // Validación
                p.agregarPublicacion(i);
            }

            // Leer y agregar Videos
            NodeList nListVideos = doc.getElementsByTagName("Video");
            for (int temp = 0; temp < nListVideos.getLength(); temp++) {
                Element elemento = (Element) nListVideos.item(temp);
                String c = elemento.getElementsByTagName("codigo").item(0).getTextContent();
                String des = elemento.getElementsByTagName("descripcion").item(0).getTextContent();
                LocalDate f = LocalDate.parse(elemento.getElementsByTagName("fecha").item(0).getTextContent(), dateFormatter);
                LocalTime h = LocalTime.parse(elemento.getElementsByTagName("hora").item(0).getTextContent(), timeFormatter);
                int likes = Integer.parseInt(elemento.getElementsByTagName("cantLikes").item(0).getTextContent());
                //cargar etiquetas en una lista
                List<String> etiquetas = new ArrayList<>();
                NodeList etiquetasList = elemento.getElementsByTagName("etiqueta");
                for (int i = 0; i < etiquetasList.getLength(); i++) {
                    etiquetas.add(etiquetasList.item(i).getTextContent());
                }
                int com = Integer.parseInt(elemento.getElementsByTagName("cantComentarios").item(0).getTextContent());
                double ds = Double.parseDouble(elemento.getElementsByTagName("duracionSegundos").item(0).getTextContent());
                int res = Integer.parseInt(elemento.getElementsByTagName("resolucion").item(0).getTextContent());
                int cantc = Integer.parseInt(elemento.getElementsByTagName("cantCuadros").item(0).getTextContent());
                Video v  = new Video(ds, res, cantc, c, des, f, h, likes, etiquetas, com);
                validarVideo(v);  // Validación
                p.agregarPublicacion(v);
            }

            // Leer y agregar Audios
            NodeList nListAudios = doc.getElementsByTagName("Audio");
            for (int temp = 0; temp < nListAudios.getLength(); temp++) {
                Element elemento = (Element) nListAudios.item(temp);
                String c = elemento.getElementsByTagName("codigo").item(0).getTextContent();
                String des = elemento.getElementsByTagName("descripcion").item(0).getTextContent();
                LocalDate f = LocalDate.parse(elemento.getElementsByTagName("fecha").item(0).getTextContent(), dateFormatter);
                LocalTime h = LocalTime.parse(elemento.getElementsByTagName("hora").item(0).getTextContent(), timeFormatter);
                int likes = Integer.parseInt(elemento.getElementsByTagName("cantLikes").item(0).getTextContent());
                //cargar etiquetas en una lista
                List<String> etiquetas = new ArrayList<>();
                NodeList etiquetasList = elemento.getElementsByTagName("etiqueta");
                for (int i = 0; i < etiquetasList.getLength(); i++) {
                    etiquetas.add(etiquetasList.item(i).getTextContent());
                }
                int com = Integer.parseInt(elemento.getElementsByTagName("cantComentarios").item(0).getTextContent());
                double ds = Double.parseDouble(elemento.getElementsByTagName("duracionSegundos").item(0).getTextContent());
                int vb = Integer.parseInt(elemento.getElementsByTagName("velocidadBits").item(0).getTextContent());
                Audio a  = new Audio(ds, vb, c, des, f, h, likes, etiquetas, com);
                validarAudio(a);  // Validación
                p.agregarPublicacion(a);
            }
        }
        catch (Exception e){
            throw new ExcepcionArchivoInvalido("Error al cargar archivo. " + e.getMessage());}
    }


    private void validarImagen(Imagen i) throws ExcepcionArchivoInvalido
    {
        //f
    }

    private void validarVideo(Video v) throws ExcepcionArchivoInvalido
    {
        //f
    }

    private void validarAudio(Audio a) throws ExcepcionArchivoInvalido
    {
        //f
    }

}
