package logica.clases;

import java.time.LocalDate;
import java.time.LocalTime;

public class Audio extends Publicacion implements logica.interfaces.Durable{

    private int duracionMinutos;
    private int duracionSegundos;
    private int velocidadBits;

    public Audio (int dM, int dS, int vB, String tit, String des, LocalDate f, LocalTime h)
    {
        super (tit, des, f, h);
        duracionMinutos= dM;
        duracionSegundos= dS;
        velocidadBits= vB;
    }
}
