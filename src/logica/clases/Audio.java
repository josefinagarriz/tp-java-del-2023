package logica.clases;

import excepciones.ExcepcionValorInvalido;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Audio extends Publicacion implements logica.interfaces.Durable{

    private double duracionSegundos;
    private double tiempoActual;
    private int velocidadBits;

    public Audio (double dS, int vB, String c, String des, LocalDate f, LocalTime h, int likes, List<String> et, int com)
    {
        super (c, des, f, h, likes, et,  com);
        duracionSegundos= dS;
        tiempoActual = 0;
        velocidadBits= vB;
    }

    public Audio (double dS, int vB, String c, String des, LocalDate f, LocalTime h, List<String> et)
    {
        super (c, des, f, h, et);
        duracionSegundos= dS;
        tiempoActual = 0;
        velocidadBits= vB;
    }


    //getters
    public int getVelocidadBits() {return velocidadBits;}
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
            System.out.println("Retrocediendo "+seg+" en el audio "+getCodigo());
        }
    }

    @Override
    public void pausar() {
        System.out.println("Pausando el audio "+ getCodigo());
    }

    @Override
    public void despausar() {
        System.out.println("Desausando el audio "+ getCodigo());
    }

    @Override
    public void reanudar() {
        tiempoActual=0;
        System.out.println("Reanudar el audio "+ getCodigo());
    }

    @Override
    public void publicacionAvanzando() {if (tiempoActual<duracionSegundos) tiempoActual++;}

    @Override
    public String toString()
    {
        return  "   -Audio " + super.toString() +
                "\n   Duración: " + LocalTime.ofSecondOfDay((int)duracionSegundos) +
                "\n   Velocidad por bits: " + velocidadBits;}
}
