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

    public Publicacion (String c,String des, LocalDate f, LocalTime h)
    {
        codigo=c;
        descripcion=des;
        fecha=f;
        hora=h;
        cantLikes=0;
        cantComentarios=0;
    }


    //getters

    public int getCantLikes() {return cantLikes;}
    public String getCodigo() {return codigo;}
    public LocalDate getFecha() {return fecha;}
    public LocalTime getHora() {return hora;}
    public int getCantComentarios() {return cantComentarios;}
}
