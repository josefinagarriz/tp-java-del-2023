package logica;

import logica.clases.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class Reportes {

    // Método para generar y mostrar el listado de publicaciones agrupadas por tipo
    public static void generarReportePublicacionesPorTipo(List<Publicacion> publicaciones) {
        List<Publicacion> audios = new ArrayList<>();
        List<Publicacion> imagenes = new ArrayList<>();
        List<Publicacion> videos = new ArrayList<>();

        // Separar publicaciones por tipo
        for (Publicacion p : publicaciones) {
            if (p instanceof Audio) {
                audios.add(p);
            } else if (p instanceof Imagen) {
                imagenes.add(p);
            } else if (p instanceof Video) {
                videos.add(p);
            }
        }

        // Ordenar por cantidad de "me gusta" de forma descendente
        Comparator<Publicacion> comparadorLikes = Comparator.comparingInt(Publicacion::getCantLikes).reversed();
        audios.sort(comparadorLikes);
        imagenes.sort(comparadorLikes);
        videos.sort(comparadorLikes);

        // Mostrar reporte en pantalla
        System.out.println("\n🔹 Reporte de Publicaciones por Tipo 🔹");
        mostrarGrupoPublicaciones("Audios", audios);
        mostrarGrupoPublicaciones("Imágenes", imagenes);
        mostrarGrupoPublicaciones("Videos", videos);

        // Guardar en archivo
        guardarReportePublicaciones("reporte_publicaciones.txt", audios, imagenes, videos);
    }

    private static void mostrarGrupoPublicaciones(String tipo, List<Publicacion> lista) {
        int totalLikes = lista.stream().mapToInt(Publicacion::getCantLikes).sum();
        double promedioLikes = lista.isEmpty() ? 0 : (double) totalLikes / lista.size();

        System.out.println("\n📂 Tipo: " + tipo);
        System.out.println("Cantidad de publicaciones: " + lista.size());
        System.out.println("Promedio de 'me gusta': " + promedioLikes);
        for (Publicacion p : lista) {
            System.out.println(" - " + p.getTitulo() + " (" + p.getCantLikes() + " likes)");
        }
    }

    private static void guardarReportePublicaciones(String archivo, List<Publicacion> audios, List<Publicacion> imagenes, List<Publicacion> videos) {
        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write("🔹 Reporte de Publicaciones por Tipo 🔹\n");
            escribirGrupoEnArchivo(writer, "Audios", audios);
            escribirGrupoEnArchivo(writer, "Imágenes", imagenes);
            escribirGrupoEnArchivo(writer, "Videos", videos);
            System.out.println("\n✅ Reporte guardado en: " + archivo);
        } catch (IOException e) {
            System.out.println("❌ Error al guardar el archivo: " + e.getMessage());
        }
    }

    private static void escribirGrupoEnArchivo(FileWriter writer, String tipo, List<Publicacion> lista) throws IOException {
        int totalLikes = lista.stream().mapToInt(Publicacion::getCantLikes).sum();
        double promedioLikes = lista.isEmpty() ? 0 : (double) totalLikes / lista.size();

        writer.write("\n📂 Tipo: " + tipo + "\n");
        writer.write("Cantidad de publicaciones: " + lista.size() + "\n");
        writer.write("Promedio de 'me gusta': " + promedioLikes + "\n");
        for (Publicacion p : lista) {
            writer.write(" - " + p.getTitulo() + " (" + p.getCantLikes() + " likes)\n");
        }
    }

    // Método para generar y mostrar el listado de álbumes alfabéticamente con publicaciones en un rango de fechas
    public static void generarReporteAlbumes(List<Album> albumes, LocalDate inicio, LocalDate fin) {
        albumes.sort(Comparator.comparing(Album::getNombre)); // Ordenar alfabéticamente por nombre

        System.out.println("\n🔹 Reporte de Álbumes en Rango de Fechas 🔹");
        try (FileWriter writer = new FileWriter("reporte_albumes.txt")) {
            writer.write("🔹 Reporte de Álbumes en Rango de Fechas 🔹\n");

            for (Album album : albumes) {
                List<Publicacion> publicacionesFiltradas = new ArrayList<>();
                int totalComentarios = 0;

                for (Publicacion p : album.getPublicaciones()) {
                    if (!p.getFecha().isBefore(inicio) && !p.getFecha().isAfter(fin)) {
                        publicacionesFiltradas.add(p);
                        totalComentarios += p.getCantComentarios();
                    }
                }

                System.out.println("\n📂 Álbum: " + album.getNombre());
                System.out.println("Cantidad de publicaciones en el rango: " + publicacionesFiltradas.size());
                System.out.println("Total de comentarios: " + totalComentarios);

                writer.write("\n📂 Álbum: " + album.getNombre() + "\n");
                writer.write("Cantidad de publicaciones en el rango: " + publicacionesFiltradas.size() + "\n");
                writer.write("Total de comentarios: " + totalComentarios + "\n");
            }

            System.out.println("\n✅ Reporte de álbumes guardado en 'reporte_albumes.txt'");
        } catch (IOException e) {
            System.out.println("❌ Error al guardar el archivo: " + e.getMessage());
        }
    }
}
