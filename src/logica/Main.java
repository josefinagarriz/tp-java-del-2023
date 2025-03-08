package logica;

import logica.clases.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
       Perfil p = new Perfil("JosefinaGa", LocalDate.now());


        //Reportes
        Reportes reportes = new Reportes();
        Reportes.generarReportePublicacionesPorTipo(p.getPublicaciones());
        Reportes.generarReporteAlbumes(p.getAlbumes(), LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31));
    }
}