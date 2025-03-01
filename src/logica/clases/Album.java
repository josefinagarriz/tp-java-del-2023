package logica.clases;

import java.util.*;
import java.time.*;

public class Album {

    private String nombre;
    private int acumLikes;
    private LocalDate fechaCreacion;
    private LocalTime horaCreacion;
    private int cantPublicaciones;
    private List<Publicacion> publicaciones = new ArrayList<>();
    private List<Album> subAlbumes = new ArrayList<>();

    public Album(String nom, LocalDate fecha, LocalTime hora)
    {
        nombre=nom;
        acumLikes=0;
        fechaCreacion=fecha;
        horaCreacion=hora;
        cantPublicaciones=0;
    }


    //getters
    public int getAcumLikes()
    {
        return acumLikes;
    }

    public int getCantPublicaciones()
    {
        return cantPublicaciones;
    }

    public int getCantAudios(Set<Publicacion> audiosUnicos)
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
            cantAudios+= a.getCantAudios(audiosUnicos);
        return cantAudios;
    }

    public int getCantImagenes(Set<Publicacion> imagenesUnicas)
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
            cantImagenes+= a.getCantImagenes(imagenesUnicas);
        return cantImagenes;
    }

    public int getCantVideos(Set<Publicacion> videosUnicos)
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
            cantVideos+= a.getCantVideos(videosUnicos);
        return cantVideos;
    }


    //setters
    public void setAcumLikes(int cantLikes)
    {
        acumLikes+= cantLikes;
    }

    public void setCantPublicaciones()
    {
        cantPublicaciones++;
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
}
