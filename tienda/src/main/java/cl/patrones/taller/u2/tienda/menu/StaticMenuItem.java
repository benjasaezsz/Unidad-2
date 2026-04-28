package cl.patrones.taller.u2.tienda.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de ItemMenu para enlaces estáticos
 */
public class StaticMenuItem implements ItemMenu {

    private final String texto;
    private final String enlace;

    public StaticMenuItem(String texto, String enlace) {
        this.texto = texto;
        this.enlace = enlace;
    }

    @Override
    public String getTexto() {
        return texto;
    }

    @Override
    public String getSlug() {
        // Los enlaces estáticos no necesitan slug
        return "";
    }

    @Override
    public String getEnlace() {
        return enlace;
    }

    @Override
    public boolean tieneHijos() {
        return false;
    }

    @Override
    public List<? extends ItemMenu> getHijos() {
        return new ArrayList<>();
    }
}
