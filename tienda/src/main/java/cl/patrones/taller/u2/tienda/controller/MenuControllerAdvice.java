package cl.patrones.taller.u2.tienda.controller;

import cl.patrones.taller.u2.catalogo.domain.Categoria;
import cl.patrones.taller.u2.catalogo.service.CategoriaService;
import cl.patrones.taller.u2.tienda.adapter.CategoriaMenuAdapter;
import cl.patrones.taller.u2.tienda.menu.ItemMenu;
import cl.patrones.taller.u2.tienda.menu.MenuItemConHijos;
import cl.patrones.taller.u2.tienda.menu.StaticMenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class MenuControllerAdvice {

	@Autowired
	private CategoriaService categoriaService;

	@ModelAttribute("menu")
	public List<ItemMenu> menu() {
		List<ItemMenu> menu = new ArrayList<>();

		// 1. Inicio
		menu.add(new StaticMenuItem("Inicio", "/"));

		// 2. Categorías (con hijos)
		MenuItemConHijos categoriasMenu = new MenuItemConHijos("Categorias", "/categoria");

		// 2.1 Obtener solo categorías raíz (sin padre): Lámparas y Sillas
		List<Categoria> categorias = categoriaService.getCategorias();
		List<Categoria> categoriasRaiz = new ArrayList<>();

		for (Categoria categoria : categorias) {
			if (categoria.getPadre() == null) {
				categoriasRaiz.add(categoria);
			}
		}

		// 2.2 Agregar categorías raíz como hijos de "Categorías"
		for (Categoria categoria : categoriasRaiz) {
			categoriasMenu.agregarHijo(new CategoriaMenuAdapter(categoria, categoriaService));
		}

		menu.add(categoriasMenu);

		// 3. Ubicación
		menu.add(new StaticMenuItem("Ubicacion", "/ubicacion"));

		// 4. Contacto
		menu.add(new StaticMenuItem("Contacto", "/contacto"));

		return menu;
	}
}

