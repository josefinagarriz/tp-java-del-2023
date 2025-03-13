package logica.interfaces;

import logica.enums.*;

public interface Filtrable {
    void aplicarFiltro(Filtro fil);
    void sacarFiltro();
}
