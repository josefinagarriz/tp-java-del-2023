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
    private List<Perfil> Seguidores = new ArrayList<>();
    private List<Perfil> Seguidos = new ArrayList<>();
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
}
