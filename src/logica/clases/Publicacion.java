package logica.clases;

import java.util.*;
import java.time.*;

public class Publicacion {

    private String codigo;
    private String descripcion;
    private LocalDate fecha;
    private LocalTime hora;
    private int cantLikes;
    private List<String> etiquetas = new ArrayList<>();
    private int cantComentarios;

    public Publicacion (String c,String des, LocalDate f, LocalTime h, int likes, List<String>et, int com)
    {
        codigo=c;
        descripcion=des;
        fecha=f;
        hora=h;
        cantLikes=likes;
        etiquetas = (et != null) ? new ArrayList<>(et) : new ArrayList<>(); // Copia la lista o crea una nueva vacía si es null
        cantComentarios=com;
    }


    //getters

    public int getCantLikes() {return cantLikes;}
    public String getCodigo() {return codigo;}
    public LocalDate getFecha() {return fecha;}
    public LocalTime getHora() {return hora;}
    public int getCantComentarios() {return cantComentarios;}

    @Override
    public String toString()
    {return codigo +
            "\n   Descripción: " + descripcion +
            "\n   Fecha de p: " + fecha.toString() +
            "\n   Hora de p: " + hora.toString() +
            "\n   Likes: " + cantLikes +
            "\n   Comentarios: " + cantComentarios +
            "\n   Etiquetas: " + etiquetas;}
}
