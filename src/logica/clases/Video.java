package logica.clases;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import logica.enums.*;

public class Video extends Publicacion implements logica.interfaces.Durable, logica.interfaces.Filtrable{

    private double duracionSegundos;
    private int resolucion;
    private int cantCuadros;
    private Filtro filtro;

    public Video (double dS, int res, int cantC, Filtro fil, String c, String des, LocalDate f, LocalTime h, int likes, List<String> et, int com)
    {
        super (c, des, f, h,  likes, et, com);
        duracionSegundos= dS;
        resolucion= res;
        cantCuadros= cantC;
        filtro=fil;
    }

    //getters
    public int getResolucion() {return resolucion;}
    public int getCantCuadros() {return cantCuadros;}
    public double getDuracionSegundos() {return duracionSegundos;}
    public Filtro getFiltro() {return filtro;}

    @Override
    public double getDuracion()
    {
        return duracionSegundos;
    }

    @Override
    public void avanzar(double seg)
    {
        System.out.println("Avanzando "+seg+" en el video "+getCodigo());
    }

    @Override
    public void retroceder(double seg)
    {
        System.out.println("Retrocediendo "+seg+" en el video "+ getCodigo());
    }

    @Override
    public void detener() {
        System.out.println("Deteniendo el video "+ getCodigo());
    }

    @Override
    public void reanudar() {
        System.out.println("Reanudar el video "+ getCodigo());
    }

    @Override
    public void aplicarFiltro(Filtro f)
    {
        filtro=f;
        System.out.println("Aplicando filtro " + f +" en el video "+ getCodigo());
    }

    @Override
    public void sacarFiltro()
    {
        System.out.println("Sacando filtro en el video "+ getCodigo());
        filtro=null;
    }


    @Override
    public String toString()
    {
        return  "   -Video " + super.toString() +
                "\n   Duración: " + LocalTime.ofSecondOfDay((int)duracionSegundos) +
                "\n   Resolucion: " + resolucion +
                "\n   Cantidad de Cuadros: " + cantCuadros;}

}
