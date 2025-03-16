package logica.clases;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import excepciones.ExcepcionValorInvalido;
import logica.enums.*;

public class Video extends Publicacion implements logica.interfaces.Durable, logica.interfaces.Filtrable{

    private double duracionSegundos;
    private double tiempoActual;
    private int resolucion;
    private int cantCuadros;
    private Filtro filtro;

    public Video (double dS, int res, int cantC, Filtro fil, String c, String des, LocalDate f, LocalTime h, int likes, List<String> et, int com)
    {
        super (c, des, f, h,  likes, et, com);
        duracionSegundos= dS;
        tiempoActual = 0;
        resolucion= res;
        cantCuadros= cantC;
        filtro=fil;
    }

    public Video (double dS, int res, int cantC, Filtro fil, String c, String des, LocalDate f, LocalTime h, List<String> et)
    {
        super (c, des, f, h, et);
        duracionSegundos= dS;
        tiempoActual = 0;
        resolucion= res;
        cantCuadros= cantC;
        filtro=fil;
    }

    //getters
    public int getResolucion() {return resolucion;}
    public int getCantCuadros() {return cantCuadros;}
    public Filtro getFiltro() {return filtro;}
    public double getTiempoActual() {return tiempoActual;}

    @Override
    public double getDuracion()
    {
        return duracionSegundos;
    }

    @Override
    public void avanzar(double seg)
    {
        if (seg<0)
            throw new ExcepcionValorInvalido("No se puede avanzar un número negativo de segundos.");
        else
        {
            tiempoActual+=seg;
            if(tiempoActual>duracionSegundos)
                tiempoActual=duracionSegundos; // Llega al final
            System.out.println("Avanzando "+seg+" en el video "+getCodigo());
        }

    }

    @Override
    public void retroceder(double seg)
    {
        if (seg<0)
            throw new ExcepcionValorInvalido("No se puede retroceder un número negativo de segundos.");
        else
        {
            tiempoActual-=seg;
            if(tiempoActual<0)
                tiempoActual=0; // Llega al inicio
            System.out.println("Retrocediendo "+seg+" en el video "+getCodigo());
        }
    }

    @Override
    public void pausar() {
        System.out.println("Pausando el video "+ getCodigo());
    }

    @Override
    public void despausar() {
        System.out.println("Desausando el video "+ getCodigo());
    }

    @Override
    public void reanudar() {
        tiempoActual=0;
        System.out.println("Reanudar el video "+ getCodigo());
    }

    @Override
    public void publicacionAvanzando() {if (tiempoActual<duracionSegundos) tiempoActual++;}


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
