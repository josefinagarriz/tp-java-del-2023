package logica.clases;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Imagen extends Publicacion implements logica.interfaces.Filtrable{

    private int resolucion;
    private int ancho;
    private int alto;

    public Imagen (int res, int an, int al, String c, String des, LocalDate f, LocalTime h, int likes, List<String> et, int com)
    {
        super (c, des, f, h, likes, et, com);
        resolucion= res;
        ancho= an;
        alto= al;
    }

    //getter
    public int getResolucion() {return resolucion;}
    public int getAncho() {return ancho;}
    public int getAlto() {return alto;}

    @Override
    public void aplicarFiltro(String filtro) {
        System.out.println("Aplicando filtro " + filtro +" en la imágen "+ getCodigo());
    }

    @Override
    public void sacarFiltro() {
        System.out.println("Sacando filtro en la imágen "+ getCodigo());
    }


    @Override
    public String toString()
    {
        return  "   -Imagen " + super.toString() +
                "\n   Resolucion: " + resolucion +
                "\n   Alto y ancho: " + alto + "x" + ancho;}
}
