package logica.clases;

import java.util.*;
import java.time.*;

public class Album {

    private String nombre;
    private LocalDate fechaCreacion;
    private LocalTime horaCreacion;
    private List<Publicacion> publicaciones = new ArrayList<>();
    private List<Album> subAlbumes = new ArrayList<>();
    private int orden; // 1= por publicación de la publicaión (ascendente), 2= por publicación de la publicación (descendente), 3= por titulo de publicación (a-z), 4= por titulo de publicación (z-a), 5= por publicación en album (ascendente), 6= por publicación en album (descendente)

    public Album(String nom, LocalDate fecha, LocalTime hora, int o)
    {
        nombre=nom;
        fechaCreacion=fecha;
        horaCreacion=hora;
        orden=o;
    }


    //getters


    //setters


    //calculos
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







    //gestionar album

    /*

    HACER EXEPCIONES NUEVAS

    */
    public void agregarPublicacion(Publicacion p)
    {
        if (p == null)
            throw new IllegalArgumentException("No se encuentra la publicación");
        else
            if(!publicaciones.contains((p))) //evita duplicados
                publicaciones.add(p);
            else
                throw new IllegalArgumentException("No se puede tener publicaciones duplicadas en unmismo album");
    }

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

    public void crearSubalbum(Album subalbum)
    {
        subAlbumes.add(subalbum);
    }

    //CREAR SUBALBUM, ELIMINAR SUBALBUM, ELIMINAR TODO EL ABUM, CREAR EL ALBUM






    @Override
    public String toString()
    {
        return "Likes totales: "+calcularAcumLikes()+"\n Fecha de creación: "+ fechaCreacion.toString() +"\n Hora de creación: "+ horaCreacion.toString()+ "\n";
    }
}
/*
private String nombre;
    private LocalDate fechaCreacion;
    private LocalTime horaCreacion;
    private List<Publicacion> publicaciones = new ArrayList<>();
    private List<Album> subAlbumes = new ArrayList<>();
    private int orden;
 */



/*
 public int calcularCantAudios(Set<Publicacion> audiosUnicos)
    {
        int cantAudios= 0;
        //contar audios en el album actual evitando duplicados
        for (Publicacion p : publicaciones)
        {
            if (p instanceof Audio && audiosUnicos.add(p))
                cantAudios++;
        }
        //recursividad para contar audios en los subalbumes
        for (Album a : subAlbumes)
            cantAudios+= a.calcularCantAudios(audiosUnicos);
        return cantAudios;
    }

    public int calcularCantImagenes(Set<Publicacion> imagenesUnicas)
    {
        int cantImagenes= 0;
        //contar imagenes en el album actual evitando duplicados
        for (Publicacion p : publicaciones)
        {
            if (p instanceof Imagen && imagenesUnicas.add(p))
                cantImagenes++;
        }
        //recursividad para contar imagenes en los subalbumes
        for (Album a : subAlbumes)
            cantImagenes+= a.calcularCantImagenes(imagenesUnicas);
        return cantImagenes;
    }

    public int calcularCantVideos(Set<Publicacion> videosUnicos)
    {
        int cantVideos= 0;
        //contar videos en el album actual evitando duplicados
        for (Publicacion p : publicaciones)
        {
            if (p instanceof Video && videosUnicos.add(p))
                cantVideos++;
        }
        //recursividad para contar videos en los subalbumes
        for (Album a : subAlbumes)
            cantVideos+= a.calcularCantVideos(videosUnicos);
        return cantVideos;
    }
 */