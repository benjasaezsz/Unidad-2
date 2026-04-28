package cl.patrones.taller.u2.tienda.adapter;

import cl.patrones.taller.u2.catalogo.domain.Categoria;
import cl.patrones.taller.u2.catalogo.service.CategoriaService;
import cl.patrones.taller.u2.tienda.menu.ItemMenu;
import cl.patrones.taller.u2.tienda.menu.util.Slugger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * PATRÓN ADAPTER
 * Adapta objetos Categoria a la interfaz ItemMenu
 */
public class CategoriaMenuAdapter implements ItemMenu {

    private final Categoria categoria;
    private final CategoriaService categoriaService;

    public CategoriaMenuAdapter(Categoria categoria, CategoriaService categoriaService) {
        this.categoria = categoria;
        this.categoriaService = categoriaService;
    }

    @Override
    public String getTexto() {
        return categoria.getNombre();
    }

    @Override
    public String getSlug() {
        return Slugger.toSlug(categoria.getNombre());
    }

    @Override
    public String getEnlace() {
        return "/categoria/" + categoria.getId() + "/" + getSlug();
    }

    @Override
    public boolean tieneHijos() {
        // Buscar si esta categoría tiene hijos
        List<Categoria> todasCategorias = categoriaService.getCategorias();
        return todasCategorias.stream()
                .anyMatch(c -> c.getPadre() != null &&
                        c.getPadre().getId() != null &&
                        c.getPadre().getId().equals(categoria.getId()));
    }

    @Override
    public List<? extends ItemMenu> getHijos() {
        // Obtener todas las categorías hijas
        List<Categoria> todasCategorias = categoriaService.getCategorias();

        return todasCategorias.stream()
                .filter(c -> c.getPadre() != null &&
                        c.getPadre().getId() != null &&
                        c.getPadre().getId().equals(categoria.getId()))
                .map(c -> new CategoriaMenuAdapter(c, categoriaService))
                .collect(Collectors.toList());
    }
}
