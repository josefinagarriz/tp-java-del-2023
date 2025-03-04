package logica.clases;

import java.util.*;
import java.time.*;

public class Perfil {

    private static int cont=0;
    private int cod;
    private String nombreUsuario;
    private LocalDate ingreso;
    private int cantSeguidores;
    private int cantSeguidos;
    private int cantPublicaciones;
    private List<Publicacion> publicaciones = new ArrayList<>();
    private int cantAlbumes;
    private List<Album> albumes = new ArrayList<>();

    public Perfil (String nomU, LocalDate ing)
    {
        cod=++cont;
        nombreUsuario=nomU;
        ingreso=ing;
        cantSeguidores=0;
        cantSeguidos=0;
        cantPublicaciones=0;
        cantAlbumes=0;
    }

    //Gestionar Albumes
     /*

    HACER EXEPCIONES NUEVAS

    */
    public void borrarAlbum(Album a)
    {
        if(a ==null)
            throw new IllegalArgumentException("El album no puede ser null");
        else
              if (albumes.contains(a)) //verificar si elalbum está en la lista
              {
                  // Limpiar todas las publicaciones y subálbumes dentro de este álbum
                  a.eliminarTodoDelAlbum();
                  // Eliminar el álbum de la lista del perfil
                  albumes.remove(a);
              }
              else
                   throw new IllegalArgumentException("No se encontró el album");

    }

    public void borrarSubAlbum(Album subAlbum)
    {
        if (subAlbum == null) {
            throw new IllegalArgumentException("El subálbum no puede ser null.");
        }
        else {
            // Buscar en los álbumes del perfil cuál contiene el subálbum
            boolean bandera = false;
            int i = 0;
            // Recorremos la lista de álbumes del perfil para encontrar el álbum que contiene el subálbum
            while (i < albumes.size() && !bandera) {
                Album album = albumes.get(i); //obtener un álbum específico de la lista
                if (album.buscarSubAlbum(subAlbum)) {
                    album.eliminarSubAlbum(subAlbum);
                    bandera = true;
                }
                i++;
            }
            if (!bandera) {
                throw new IllegalArgumentException("El subálbum no se encontró en ningún álbum del perfil.");
            }
        }
    }

    public void agregarPublicacionEnAlbum(Publicacion p, Album album) {
        if (p == null || album == null) {
            throw new IllegalArgumentException("La publicación o el álbum no pueden ser null.");
        }
        else
        if (!albumes.contains(album)) {
            throw new IllegalArgumentException("El álbum no pertenece a este perfil.");
        }
        else
             album.agregarPublicacion(p); // Llama al método de Album
    }

    public void eliminarrPublicacionEnAlbum(Publicacion p, Album album) {
        if (p == null || album == null) {
            throw new IllegalArgumentException("La publicación o el álbum no pueden ser null.");
        }
        else
        if (!albumes.contains(album)) {
            throw new IllegalArgumentException("El álbum no pertenece a este perfil.");
        }
        else
            album.eliminarPublicacion(p); // Llama al método de Album
    }
}
