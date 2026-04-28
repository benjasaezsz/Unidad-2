package cl.patrones.taller.u2.tienda.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de ItemMenu que puede contener elementos hijos
 */
public class MenuItemConHijos implements ItemMenu {

    private final String texto;
    private final String enlace;
    private final List<ItemMenu> hijos;

    public MenuItemConHijos(String texto, String enlace) {
        this.texto = texto;
        this.enlace = enlace;
        this.hijos = new ArrayList<>();
    }

    public void agregarHijo(ItemMenu hijo) {
        this.hijos.add(hijo);
    }

    @Override
    public String getTexto() {
        return texto;
    }

    @Override
    public String getSlug() {
        return "";
    }

    @Override
    public String getEnlace() {
        return enlace;
    }

    @Override
    public boolean tieneHijos() {
        return !hijos.isEmpty();
    }

    @Override
    public List<? extends ItemMenu> getHijos() {
        return hijos;
    }
}
