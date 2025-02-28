package logica.clases;

import java.time.LocalDate;
import java.time.LocalTime;

public class Imagen extends Publicacion implements logica.interfaces.Filtrable{

    private int resolucion;
    private int ancho;
    private int alto;

    public Imagen (int res, int an, int al, String tit, String des, LocalDate f, LocalTime h)
    {
        super (tit, des, f, h);
        resolucion= res;
        ancho= an;
        alto= al;
    }
}
