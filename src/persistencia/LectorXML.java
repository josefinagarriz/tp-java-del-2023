package persistencia;

import logica.clases.*;
import excepciones.*;
import logica.enums.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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
                // Verificar si la descripción está presente
                String des = elemento.getElementsByTagName("descripcion") != null &&
                        elemento.getElementsByTagName("descripcion").getLength() > 0
                        ? elemento.getElementsByTagName("descripcion").item(0).getTextContent()
                        : null;
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
                // Verificar si el filtro está presente
                String filtroStr = elemento.getElementsByTagName("filtro") != null &&
                        elemento.getElementsByTagName("filtro").getLength() > 0
                        ? elemento.getElementsByTagName("filtro").item(0).getTextContent()
                        : null;
                Filtro filtro = (filtroStr == null || filtroStr.isEmpty()) ? null : Filtro.valueOf(filtroStr);
                Imagen i = new Imagen(res, an, al, filtro, c, des, f, h, likes, etiquetas, com);
                validarImagen(i);  // Validación
                p.agregarPublicacion(i);
            }

            // Leer y agregar Videos
            NodeList nListVideos = doc.getElementsByTagName("Video");
            for (int temp = 0; temp < nListVideos.getLength(); temp++) {
                Element elemento = (Element) nListVideos.item(temp);
                String c = elemento.getElementsByTagName("codigo").item(0).getTextContent();
                // Verificar si la descripción está presente
                String des = elemento.getElementsByTagName("descripcion") != null &&
                        elemento.getElementsByTagName("descripcion").getLength() > 0
                        ? elemento.getElementsByTagName("descripcion").item(0).getTextContent()
                        : null;
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
                String filtroStr = elemento.getElementsByTagName("filtro") != null &&
                        elemento.getElementsByTagName("filtro").getLength() > 0
                        ? elemento.getElementsByTagName("filtro").item(0).getTextContent()
                        : null;
                Filtro filtro = (filtroStr == null || filtroStr.isEmpty()) ? null : Filtro.valueOf(filtroStr);
                Video v  = new Video(ds, res, cantc, filtro, c, des, f, h, likes, etiquetas, com);
                validarVideo(v);  // Validación
                p.agregarPublicacion(v);
            }

            // Leer y agregar Audios
            NodeList nListAudios = doc.getElementsByTagName("Audio");
            for (int temp = 0; temp < nListAudios.getLength(); temp++) {
                Element elemento = (Element) nListAudios.item(temp);
                String c = elemento.getElementsByTagName("codigo").item(0).getTextContent();
                // Verificar si la descripción está presente
                String des = elemento.getElementsByTagName("descripcion") != null &&
                        elemento.getElementsByTagName("descripcion").getLength() > 0
                        ? elemento.getElementsByTagName("descripcion").item(0).getTextContent()
                        : null;
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
        if (i.getCodigo() == null || i.getCodigo().isEmpty()) {
            throw new ExcepcionArchivoInvalido("El código de la imagen no puede estar vacío.");
        }
        if (i.getFecha() == null) {
            throw new ExcepcionArchivoInvalido("La fecha no puede ser nula.");
        }
        if (i.getHora() == null) {
            throw new ExcepcionArchivoInvalido("La hora no puede ser nula.");
        }
        if (i.getCantLikes() < 0) {
            throw new ExcepcionArchivoInvalido("La cantidad de likes debe ser un número positivo ó 0.");
        }
        if (i.getCantComentarios() < 0) {
            throw new ExcepcionArchivoInvalido("La cantidad de comentarios debe ser un número positivo ó 0.");
        }
        if (i.getResolucion() <= 0) {
            throw new ExcepcionArchivoInvalido("La resolución debe ser un número positivo.");
        }
        if (i.getAlto() <= 0) {
            throw new ExcepcionArchivoInvalido("El alto debe ser un número positivo.");
        }
        if (i.getAncho() <= 0) {
            throw new ExcepcionArchivoInvalido("El ancho debe ser un número positivo.");
        }
        if (i.getEtiquetas() != null) {
            for (String etiqueta : i.getEtiquetas()) {
                if (etiqueta == null || etiqueta.trim().isEmpty()) {
                    throw new ExcepcionArchivoInvalido("Las etiquetas no pueden contener valores vacíos.");
                }
            }
        }
        // Validar descripción (puede ser nula o vacía). No es necesario hacer nada si está vacía o nula, ya que se permite.
        // Validar etiquetas (si no hay etiquetas, la lista debería estar vacía)
        if (i.getEtiquetas() != null) {
            for (String etiqueta : i.getEtiquetas()) {
                if (etiqueta == null || etiqueta.trim().isEmpty()) {
                    throw new ExcepcionArchivoInvalido("Las etiquetas no pueden estar vacías.");
                }
            }
        }

        // Validar filtro (puede ser nulo)
        if (i.getFiltro() != null) {
            try {
                Filtro.valueOf(i.getFiltro().toString()); // Si el filtro es válido, no hace nada.
            } catch (IllegalArgumentException e) {
                throw new ExcepcionArchivoInvalido("El filtro " + i.getFiltro() + " no es válido.");
            }
        }
    }

    private void validarVideo(Video v) throws ExcepcionArchivoInvalido
    {
        if (v.getCodigo() == null || v.getCodigo().isEmpty()) {
            throw new ExcepcionArchivoInvalido("El código del video no puede estar vacío.");
        }
        if (v.getFecha() == null) {
            throw new ExcepcionArchivoInvalido("La fecha no puede ser nula.");
        }
        if (v.getHora() == null) {
            throw new ExcepcionArchivoInvalido("La hora no puede ser nula.");
        }
        if (v.getCantLikes() < 0) {
            throw new ExcepcionArchivoInvalido("La cantidad de likes debe ser un número positivo ó 0.");
        }
        if (v.getCantComentarios() < 0) {
            throw new ExcepcionArchivoInvalido("La cantidad de comentarios debe ser un número positivo ó 0.");
        }
        if (v.getDuracionSegundos() <= 0) {
            throw new ExcepcionArchivoInvalido("La duración debe ser un número positivo.");
        }
        if (v.getResolucion() <= 0) {
            throw new ExcepcionArchivoInvalido("La resolución debe ser un número positivo.");
        }
        if (v.getCantCuadros() <= 0) {
            throw new ExcepcionArchivoInvalido("La cantidad de cuadros debe ser un número positivo.");
        }
        // Validar descripción (puede ser nula o vacía). No es necesario hacer nada si está vacía o nula, ya que se permite.
        // Validar etiquetas (si no hay etiquetas, la lista debería estar vacía)
        if (v.getEtiquetas() != null) {
            for (String etiqueta : v.getEtiquetas()) {
                if (etiqueta == null || etiqueta.trim().isEmpty()) {
                    throw new ExcepcionArchivoInvalido("Las etiquetas no pueden estar vacías.");
                }
            }
        }

        // Validar filtro (puede ser nulo)
        if (v.getFiltro() != null) {
            try {
                Filtro.valueOf(v.getFiltro().toString()); // Si el filtro es válido, no hace nada.
            } catch (IllegalArgumentException e) {
                throw new ExcepcionArchivoInvalido("El filtro " + v.getFiltro() + " no es válido.");
            }
        }
    }

    private void validarAudio(Audio a) throws ExcepcionArchivoInvalido
    {
        if (a.getCodigo() == null || a.getCodigo().isEmpty()) {
            throw new ExcepcionArchivoInvalido("El código del audio no puede estar vacío.");
        }
        if (a.getFecha() == null) {
            throw new ExcepcionArchivoInvalido("La fecha no puede ser nula.");
        }
        if (a.getHora() == null) {
            throw new ExcepcionArchivoInvalido("La hora no puede ser nula.");
        }
        if (a.getCantLikes() < 0) {
            throw new ExcepcionArchivoInvalido("La cantidad de likes debe ser un número positivo ó 0.");
        }
        if (a.getCantComentarios() < 0) {
            throw new ExcepcionArchivoInvalido("La cantidad de comentarios debe ser un número positivo ó 0.");
        }
        if (a.getDuracionSegundos() <= 0) {
            throw new ExcepcionArchivoInvalido("La duración debe ser un número positivo.");
        }
        if (a.getVelocidadBits() <= 0) {
            throw new ExcepcionArchivoInvalido("La velocidad por bit debe ser un número positivo.");
        }
        // Validar descripción (puede ser nula o vacía). No es necesario hacer nada si está vacía o nula, ya que se permite.
        // Validar etiquetas (si no hay etiquetas, la lista debería estar vacía)
        if (a.getEtiquetas() != null) {
            for (String etiqueta : a.getEtiquetas()) {
                if (etiqueta == null || etiqueta.trim().isEmpty()) {
                    throw new ExcepcionArchivoInvalido("Las etiquetas no pueden estar vacías.");
                }
            }
        }
    }

}
