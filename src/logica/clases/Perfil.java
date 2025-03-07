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

    public void crearAlbum(String nom, LocalDate fecha, LocalTime hora, int o) {
        if (nom == null || nom.isEmpty()) {
            throw new IllegalArgumentException("El nombre del álbum no puede estar vacío.");
        }

        Album nuevoAlbum = new Album(nom, fecha, hora, o);
        albumes.add(nuevoAlbum);
    }

    public void crearSubAlbumEnAlbum(Album albumPrincipal, String nom, LocalDate fecha, LocalTime hora, int o) {
        if (albumPrincipal == null || nom == null || nom.isEmpty()) {
            throw new IllegalArgumentException("El álbum o el nombre del subálbum no pueden ser null o vacíos.");
        }
        if (!albumes.contains(albumPrincipal)) {
            throw new IllegalArgumentException("El álbum principal no pertenece a este perfil.");
        }

        albumPrincipal.crearSubAlbum(nom, fecha, hora, o);
    }

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

    public void agregarPublicacionEnSubAlbum(Publicacion p, Album albumPrincipal, Album subAlbum) {
        if (p == null || albumPrincipal == null || subAlbum == null) {
            throw new IllegalArgumentException("La publicación, el álbum o el subálbum no pueden ser null.");
        }
        if (!albumes.contains(albumPrincipal)) {
            throw new IllegalArgumentException("El álbum principal no pertenece a este perfil.");
        }

        boolean agregado = albumPrincipal.agregarPublicacionEnSubAlbum(p, subAlbum);
        if (!agregado) {
            throw new IllegalArgumentException("El subálbum no pertenece al álbum proporcionado.");
        }
    }

    public void eliminarPublicacionEnSubAlbum(Publicacion p, Album albumPrincipal, Album subAlbum) {
        if (p == null || albumPrincipal == null || subAlbum == null) {
            throw new IllegalArgumentException("La publicación, el álbum o el subálbum no pueden ser null.");
        }
        if (!albumes.contains(albumPrincipal)) {
            throw new IllegalArgumentException("El álbum principal no pertenece a este perfil.");
        }

        boolean eliminado = albumPrincipal.eliminarPublicacionEnSubAlbum(p, subAlbum);
        if (!eliminado) {
            throw new IllegalArgumentException("El subálbum no pertenece al álbum proporcionado o la publicación no existe.");
        }
    }

    public void copiarPublicacionEntreAlbumes(Publicacion p, Album origen, Album destino) {
        if (p == null || origen == null || destino == null) {
            throw new IllegalArgumentException("Publicación, álbum de origen o álbum de destino no pueden ser null.");
        }
        if (!albumes.contains(origen) && !buscarAlbumEnSubAlbumes(albumes, origen)) {
            throw new IllegalArgumentException("El álbum de origen no pertenece a este perfil.");
        }
        if (!albumes.contains(destino) && !buscarAlbumEnSubAlbumes(albumes, destino)) {
            throw new IllegalArgumentException("El álbum de destino no pertenece a este perfil.");
        }
        destino.agregarPublicacion(p);
    }

    public void moverPublicacionEntreAlbumes(Publicacion p, Album origen, Album destino) {
        copiarPublicacionEntreAlbumes(p, origen, destino); // Primero la copia
        origen.eliminarPublicacion(p); // Luego la elimina del álbum original
    }

    // Método auxiliar para buscar un álbum en los subálbumes recursivamente
    private boolean buscarAlbumEnSubAlbumes(List<Album> listaAlbumes, Album albumBuscado) {
        for (Album album : listaAlbumes) {
            if (album == albumBuscado || buscarAlbumEnSubAlbumes(album.getSubAlbumes(), albumBuscado)) {
                return true;
            }
        }
        return false;
    }
}
