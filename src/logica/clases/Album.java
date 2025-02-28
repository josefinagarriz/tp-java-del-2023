package logica.clases;

import java.util.*;
import java.time.*;

public class Album {

    private String nombre;
    private int acumLikes;
    private LocalDate fechaCreacion;
    private LocalTime horaCreacion;
    private int cantPublicaciones;
    private List<Album> subAlbumes = new ArrayList<>();

    public Album(String nom, LocalDate fecha, LocalTime hora)
    {
        nombre=nom;
        acumLikes=0;
        fechaCreacion=fecha;
        horaCreacion=hora;
        cantPublicaciones=0;
    }
}
