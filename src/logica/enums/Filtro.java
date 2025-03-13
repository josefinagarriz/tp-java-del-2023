package logica.enums;

public enum Filtro {
    BLANCO_Y_NEGRO,
    SEPIA,
    BRILLO,
    CONTRASTE,
    SATURACION,
    DESENFOQUE,
    NEGATIVO;

    @Override
    public String toString() {
        return name().toLowerCase().replace("_", " ");
    }
}
