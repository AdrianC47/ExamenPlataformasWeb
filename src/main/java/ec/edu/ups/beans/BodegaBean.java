/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.beans;

import ec.edu.ups.ejb.BodegaFacade;
import ec.edu.ups.modelo.Bodega;
import ec.edu.ups.modelo.Producto;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adrian
 */
@Named
@SessionScoped
public class BodegaBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private BodegaFacade bodegaFacade;
    private List<Bodega> list = new ArrayList<>();
    private int id;
    private String nombre;
    private String ciudad;
    private List<Producto> listaProductos;

    @PostConstruct
    public void init() {//este metodo init se va a ejecutar despues 
        list = bodegaFacade.findAll();//de que se ha creado o visualizado el JSF o el bean
    }

    public String add() {
        bodegaFacade.create(new Bodega(id, nombre, ciudad, listaProductos));
        list = bodegaFacade.findAll();//llamo al findall para que se me actualice la lista
        return null;
    }

    public String add2() {
        bodegaFacade.create(new Bodega(id, nombre, ciudad));
        list = bodegaFacade.findAll();//llamo al findall para que se me actualice la lista
        return null;
    }

    public String delete(Bodega p) {
        bodegaFacade.remove(p);
        list = bodegaFacade.findAll();//llamo al findall para que se me actualice la lista
        return null;
    }

    public String edit(Bodega p) {
        p.setEditable(true); //habilita la caja
        return null;
    }

    public String save(Bodega p) {
        bodegaFacade.edit(p);
        list = bodegaFacade.findAll(); //actualizo la lista
        p.setEditable(false); //desabilita la caja
        return null;
    }

    public List<String> bodegaNombre() {
        List<String> listaNombres = new ArrayList<>();
        String n;
        for (Bodega s : list) {
            n = s.getNombre();
            listaNombres.add(n);
        }
        return listaNombres;
    }

    public BodegaFacade getBodegaFacade() {
        return bodegaFacade;
    }

    public void setBodegaFacade(BodegaFacade bodegaFacade) {
        this.bodegaFacade = bodegaFacade;
    }

    public Bodega[] getList() {
        return list.toArray(new Bodega[0]);
    }

    public void setList(List<Bodega> list) {
        this.list = list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

}
