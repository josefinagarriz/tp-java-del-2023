package logica.interfaces;

public interface Durable {

    double getDuracion();
    void avanzar(double seg);
    void retroceder(double seg);
    void detener();
    void reanudar();

}
