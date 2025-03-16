package logica.clases;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import logica.enums.*;

public class Imagen extends Publicacion implements logica.interfaces.Filtrable{

    private int resolucion;
    private int ancho;
    private int alto;
    private Filtro filtro;

    public Imagen (int res, int an, int al, Filtro fil, String c, String des, LocalDate f, LocalTime h, int likes, List<String> et, int com)
    {
        super (c, des, f, h, likes, et, com);
        resolucion= res;
        ancho= an;
        alto= al;
        filtro=fil;
    }

    public Imagen (int res, int an, int al, Filtro fil, String c, String des, LocalDate f, LocalTime h, List<String> et)
    {
        super (c, des, f, h, et);
        resolucion= res;
        ancho= an;
        alto= al;
        filtro=fil;
    }

    //getter
    public int getResolucion() {return resolucion;}
    public int getAncho() {return ancho;}
    public int getAlto() {return alto;}
    public Filtro getFiltro() {return filtro;}

    @Override
    public void aplicarFiltro(Filtro f)
    {
        filtro=f;
        System.out.println("Aplicando filtro " + f +" en la imágen "+ getCodigo());
    }

    @Override
    public void sacarFiltro()
    {
        System.out.println("Sacando filtro en la imágen "+ getCodigo());
        filtro=null;
    }


    @Override
    public String toString()
    {
        return  "   -Imagen " + super.toString() +
                "\n   Resolucion: " + resolucion +
                "\n   Alto y ancho: " + alto + "x" + ancho;}
}
