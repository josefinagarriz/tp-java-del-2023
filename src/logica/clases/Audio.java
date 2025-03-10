package logica.clases;

import java.time.LocalDate;
import java.time.LocalTime;

public class Audio extends Publicacion implements logica.interfaces.Durable{

    private double duracionSegundos;
    private int velocidadBits;

    public Audio (double dS, int vB, String tit, String des, LocalDate f, LocalTime h)
    {
        super (tit, des, f, h);
        duracionSegundos= dS;
        velocidadBits= vB;
    }

    @Override
    public double getDuracion()
    {
        return duracionSegundos;
    }

    @Override
    public void avanzar(double seg)
    {
        System.out.println("Avanzando "+seg+" en el audio "+ getCodigo());
    }

    @Override
    public void retroceder(double seg)
    {
        System.out.println("Retrocediendo "+seg+" en el audio "+ getCodigo());
    }

    @Override
    public void detener() {
        System.out.println("Deteniendo el audio "+ getCodigo());
    }

    @Override
    public void reanudar() {
        System.out.println("Reanudar el audio "+ getCodigo());
    }
}
