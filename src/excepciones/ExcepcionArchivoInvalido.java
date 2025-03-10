package excepciones;

public class ExcepcionArchivoInvalido extends RuntimeException {
    public ExcepcionArchivoInvalido(String mensaje){super("Problema: "+mensaje);}
}
