package logica.clases;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Video extends Publicacion implements logica.interfaces.Durable, logica.interfaces.Filtrable{

    private double duracionSegundos;
    private int resolucion;
    private int cantCuadros;

    public Video (double dS, int res, int cantC, String c, String des, LocalDate f, LocalTime h, int likes, List<String> et, int com)
    {
        super (c, des, f, h,  likes, et, com);
        duracionSegundos= dS;
        resolucion= res;
        cantCuadros= cantC;
    }

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
    public void aplicarFiltro(String filtro) {
        System.out.println("Aplicando filtro " + filtro +" en el video "+ getCodigo());
    }

    @Override
    public void sacarFiltro() {
        System.out.println("Sacando filtro en el video "+ getCodigo());
    }

}
