package logica.clases;

import java.util.*;
import java.time.*;

public class Album {

    private String nombre;
    private LocalDate fechaCreacion;
    private LocalTime horaCreacion;
    private List<Publicacion> publicaciones = new ArrayList<>();
    private List<Album> subAlbumes = new ArrayList<>();
    private int orden; // 1= por publicación de la publicaión (ascendente), 2= por publicación de la publicación (descendente), 3= por cantidad de likes (ascendente), 4= por cantidad de likes (descendente)

    public Album(String nom, LocalDate fecha, LocalTime hora, int o)
    {
        nombre=nom;
        fechaCreacion=fecha;
        horaCreacion=hora;
        orden=o;
    }


    //getters
    public List<Album> getSubAlbumes() {return subAlbumes;}
    public String getNombre() {return nombre;}
    public List<Publicacion> getPublicaciones() {return publicaciones;}
    public LocalDate getFechaCreacion() {return fechaCreacion;}
    public LocalTime getHoraCreacion() {return horaCreacion;}
    public int getOrden() {return orden;}

    //setters
    public void setOrden(int o)
    {
        orden=o;
        // Ordenamos la lista según el criterio de "orden"
        switch (orden) {
            case 1 -> publicaciones.sort(Comparator.comparing(Publicacion::getFecha));
            case 2 -> publicaciones.sort(Comparator.comparing(Publicacion::getFecha).reversed());
            case 3 -> publicaciones.sort(Comparator.comparingInt(Publicacion::getCantLikes));
            case 4 -> publicaciones.sort(Comparator.comparingInt(Publicacion::getCantLikes).reversed());
        }
    }


    //calculos y contar

    public int contarCantPublicaciones() {return publicaciones.size();}
    public int contarCantSubAlbumes() {return subAlbumes.size();}

    public int calcularAcumLikes()
    {
        int acumLikes=0;
        for(Publicacion p: publicaciones)
            acumLikes += p.getCantLikes();
        return acumLikes;
    }

    public int calcularTotalPublicaciones()
    {
        int cantPublicaciones=0;
        for (Publicacion p: publicaciones)
            cantPublicaciones++;
        return cantPublicaciones;
    }

    public int calcularCantAudios()
    {
        int cantAudios= 0;
        for (Publicacion p : publicaciones)
        {
            if (p instanceof Audio)
                cantAudios++;
        }
        return cantAudios;
    }

    public int calcularCantImagenes()
    {
        int cantImagenes= 0;
        for (Publicacion p : publicaciones)
        {
            if (p instanceof Imagen)
                cantImagenes++;
        }
        return cantImagenes;
    }

    public int calcularCantVideos()
    {
        int cantVideos= 0;
        for (Publicacion p : publicaciones)
        {
            if (p instanceof Video)
                cantVideos++;
        }
        return cantVideos;
    }


    //Gestionar publicaciones

    /*

    HACER EXEPCIONES NUEVAS

    */
    public void agregarPublicacion(Publicacion p) {
        if (p == null)
            throw new IllegalArgumentException("La publicación no puede ser null.");
        else
        if(publicaciones.contains(p))
            throw new IllegalArgumentException("La publicación ya está en el álbum.");
        else{
        // Definir cómo comparar las publicaciones según "orden"
        Comparator<Publicacion> comparator;

        switch (orden) {
            case 1 -> comparator = Comparator
                    .comparing(Publicacion::getFecha)
                    .thenComparing(Publicacion::getHora); // Fecha ascendente, si hay empate compara la hora

            case 2 -> comparator = Comparator
                    .comparing(Publicacion::getFecha)
                    .thenComparing(Publicacion::getHora)
                    .reversed(); // Fecha descendente, si hay empate compara la hora
            case 3 -> comparator = Comparator.comparingInt(Publicacion::getCantLikes); // Likes ascendente
            case 4 -> comparator = Comparator.comparingInt(Publicacion::getCantLikes).reversed(); // Likes descendente
            default -> throw new IllegalArgumentException("Orden no válido: " + orden);
        }
        // Buscar la posición correcta en la lista ordenada
        int index = 0;
        while (index < publicaciones.size() && comparator.compare(publicaciones.get(index), p) < 0) {
            index++;
        }
        // Insertar en la posición correcta
        publicaciones.add(index, p);
    }}

    /*

    HACER EXEPCIONES NUEVAS

    */
    public void eliminarPublicacion(Publicacion p)
    {
        if (p == null)
            throw new IllegalArgumentException("No se encuentra la publicación");
        else
            if (publicaciones.contains(p)) //verificar si la publicación está en la lista
                publicaciones.remove(p);
            else
                throw new IllegalArgumentException("No se encontró la publicación");
    }

    public boolean agregarPublicacionEnSubAlbum(Publicacion p, Album subAlbum) {
        if (subAlbumes.contains(subAlbum)) {
            subAlbum.agregarPublicacion(p);
            return true;
        }
        for (Album sub : subAlbumes) {
            if (sub.agregarPublicacionEnSubAlbum(p, subAlbum)) {
                return true;
            }
        }
        return false;
    }

    public boolean eliminarPublicacionEnSubAlbum(Publicacion p, Album subAlbum) {
        if (subAlbumes.contains(subAlbum)) {
            subAlbum.eliminarPublicacion(p);
            return true;
        }
        for (Album sub : subAlbumes) {
            if (sub.eliminarPublicacionEnSubAlbum(p, subAlbum)) {
                return true;
            }
        }
        return false;
    }

    public boolean copiarPublicacionEnSubAlbum(Publicacion p, Album destino) {
        if (p == null || destino == null) {
            throw new IllegalArgumentException("La publicación o el álbum de destino no pueden ser null.");
        }
        if (this == destino || buscarSubAlbumAuxiliar(destino)) {
            destino.agregarPublicacion(p);
            return true;
        }
        return false;
    }

    public boolean moverPublicacionEnSubAlbum(Publicacion p, Album destino) {
        if (copiarPublicacionEnSubAlbum(p, destino)) {
            eliminarPublicacion(p);
            return true;
        }
        return false;
    }

    // Método auxiliar para buscar un álbum en los subálbumes recursivamente
    private boolean buscarSubAlbumAuxiliar(Album albumBuscado) {
        for (Album sub : subAlbumes) {
            if (sub == albumBuscado || sub.buscarSubAlbumAuxiliar(albumBuscado)) {
                return true;
            }
        }
        return false;
    }


    //Gestionar albumes

    public void eliminarTodoDelAlbum()
    {
        publicaciones.clear(); // Eliminamos todas las publicaciones del álbum pero no las eliminamos del perfil
        for (Album subAlbum: new ArrayList<>(subAlbumes)) // Usamos una copia para evitar problemas al iterar
            subAlbum.eliminarTodoDelAlbum(); // Llamado recursivo para limpiar los subálbumes
        subAlbumes.clear(); //vaciamos la lista de subálbumes
    }

    /*

        HACER EXEPCIONES NUEVAS

        */
    public void eliminarSubAlbum(Album subAlbum)
    {
        if (subAlbum == null) {
            throw new IllegalArgumentException("El subálbum no puede ser null.");
        }
        else
        if (subAlbumes.contains(subAlbum)) //verificar si el subalbum está en la lista
        {
            // Limpiar todas las publicaciones y subálbumes dentro de este subálbum
            subAlbum.eliminarTodoDelAlbum();
            // Eliminar el subálbum de la lista
            subAlbumes.remove(subAlbum);
        }
        else
            throw new IllegalArgumentException("No se encontró el subalbum");

    }

    public boolean buscarSubAlbum(Album subAlbum) {
        if (subAlbumes.contains(subAlbum)) {
            return true;
        }
        // Búsqueda en los subálbumes recursivamente
        for (Album sub : subAlbumes) {
            if (sub.buscarSubAlbum(subAlbum)) {
                return true;
            }
        }
        return false;
    }

    public void crearSubAlbum(String nom, int o) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del subálbum no puede estar vacío.");
        }

        Album nuevoSubAlbum = new Album(nom, LocalDate.now(), LocalTime.now(), o);
        subAlbumes.add(nuevoSubAlbum);
    }

    public boolean crearSubAlbumEnSubAlbum(Album subAlbumPadre, String nom, int o) {
        if (subAlbumes.contains(subAlbumPadre)) {
            subAlbumPadre.crearSubAlbum(nom, o);
            return true;
        }
        for (Album sub : subAlbumes) {
            if (sub.crearSubAlbumEnSubAlbum(subAlbumPadre, nom, o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString()
    {
        return  "-Album " + nombre +
                "\nLikes totales: " + calcularAcumLikes() +
                "\nCantidad de imagenes: " + calcularCantImagenes() +
                "\nCantidad de videos: " + calcularCantVideos() +
                "\nCantidad de audios: " + calcularCantAudios() +
                "\nFecha de creación: " + fechaCreacion.toString() +
                "\nOrden: " + orden;}

    public String mostrarInteriorAlbum()
    {
        StringBuilder sb1 = new StringBuilder();
        for (Album a : subAlbumes)
            sb1.append(a.toString()).append("\n");

        StringBuilder sb2 = new StringBuilder();
        for (Publicacion p : publicaciones)
            sb2.append(p.toString()).append("\n");

        return sb1.toString() + sb2.toString();
    }

}


