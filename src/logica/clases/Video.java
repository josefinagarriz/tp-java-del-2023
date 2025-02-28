package logica.clases;

import java.time.LocalDate;
import java.time.LocalTime;

public class Video extends Publicacion implements logica.interfaces.Durable, logica.interfaces.Filtrable{

    private int duracionMinutos;
    private int duracionSegundos;
    private int resolucion;
    private int cantCuadros;

    public Video (int dM, int dS, int res, int cantC, String tit, String des, LocalDate f, LocalTime h)
    {
        super (tit, des, f, h);
        duracionMinutos= dM;
        duracionSegundos= dS;
        resolucion= res;
        cantCuadros= cantC;
    }
}
