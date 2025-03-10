package logica.clases;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Audio extends Publicacion implements logica.interfaces.Durable{

    private double duracionSegundos;
    private int velocidadBits;

    public Audio (double dS, int vB, String c, String des, LocalDate f, LocalTime h, int likes, List<String> et, int com)
    {
        super (c, des, f, h, likes, et,  com);
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
