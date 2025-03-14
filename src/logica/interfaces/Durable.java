package logica.interfaces;

public interface Durable {

    double getDuracion();
    void avanzar(double seg); //adelantar x seg
    void retroceder(double seg); //retroceder x seg
    void pausar(); //detiene publicacionAvanzando
    void despausar(); //activa publicacionAvanzando
    void reanudar();// vuelve al comienzo
    void publicacionAvanzando(); //va avanzando de a uno el tiempoActual

}
