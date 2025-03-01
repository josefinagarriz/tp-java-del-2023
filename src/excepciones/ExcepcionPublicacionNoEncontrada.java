package excepciones;

public class ExcepcionPublicacionNoEncontrada extends RuntimeException {
    public ExcepcionPublicacionNoEncontrada(String message) {
        super(message);
    }
}
