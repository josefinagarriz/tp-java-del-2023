package logica.clases;

import java.util.*;
import java.time.*;

public class Publicacion {

    private String titulo;
    private String descripcion;
    private LocalDate fecha;
    private LocalTime hora;
    private int cantLikes;
    private List<String> etiquetas = new ArrayList<>();
    private int cantComentarios;
    private Map<String,Perfil> comentarios = new HashMap<>();

    public Publicacion (String tit,String des, LocalDate f, LocalTime h)
    {
        titulo=tit;
        descripcion=des;
        fecha=f;
        hora=h;
        cantLikes=0;
        cantComentarios=0;
    }


    //getters

    public int getCantLikes() {return cantLikes;}
    public String getTitulo() {return titulo;}
    public LocalDate getFecha() {return fecha;}
    public LocalTime getHora() {return hora;}
}
