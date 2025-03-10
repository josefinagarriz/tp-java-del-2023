package logica;

import logica.clases.*;
import persistencia.*;
import excepciones.*;
import interfazGrafica.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Perfil p = Persistencia.DeserializarPerfil();

        if (p == null) {
            // Cargar datos desde el archivo XML si no hay datos serializados
            p = new Perfil("JosefinaGa", LocalDate.of(2021, 5, 2));
            LectorXML lector = new LectorXML();
            try {
                lector.cargarDatos("src/persistencia/datos_perfil.xml", p);
                System.out.println("Datos cargados desde XML correctamente.");
            } catch (ExcepcionArchivoInvalido e) {
                System.err.println("Error: " + e.getMessage());
                return; // Termina el programa si hay un error
            }
        } else {
            System.out.println("Datos deserializados correctamente.");
        }

        // Mostrar los datos cargados



        // Interfaz grafica
        PaginaPrincipal ventana = new PaginaPrincipal(p);
        ventana.setVisible(true);



        //Reportes
        Reportes reportes = new Reportes();
        Reportes.generarReportePublicacionesPorTipo(p.getPublicaciones());
        Reportes.generarReporteAlbumes(p.getAlbumes(), LocalDate.of(2021, 5, 2), LocalDate.of(2024, 12, 31));

        // Agregar un shutdown hook para serializar datos al cerrar la aplicación
        Perfil finalP = p;
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            boolean result = Persistencia.SerializarPerfil(finalP);
            if (result) {
                System.out.println("Datos serializados correctamente.");
            }
        }));


    }
}