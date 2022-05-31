/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.beans;

import ec.edu.ups.ejb.BodegaFacade;
import ec.edu.ups.ejb.ProductoFacade;
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
public class ProductoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private ProductoFacade productoFacade;
    private int id;
    private String nombreProducto;
    private int stock;
    private double precio;
    private Bodega bodega;
    private List<Producto> list = new ArrayList<>();
    private String nombreBodega;
    @EJB
    private BodegaFacade BodegaFacade;
    

    @PostConstruct
    public void init() {
        list = productoFacade.findAll();
    }

    public String add(String bodega) {
        Bodega b = obtenerBodegaporNombre(nombreBodega);
        System.out.println(b.toString());
        productoFacade.create(new Producto(id, nombreProducto, stock, precio, b));
        list = productoFacade.findAll();//llamo al findall para que se me actualice la lista
        return null;
    }

    public Bodega obtenerBodegaporNombre(String nombreBodega) {
        List<Bodega> lista = new ArrayList<>();
        lista = BodegaFacade.findAll();

        for (Bodega b : lista) {
            if (b.getNombre().equals(nombreBodega)) {
                System.out.println("nombre bodega " + b.getNombre());
                return b;

            } else {
                System.out.println("sfdsdfsd" + b.toString());
            }
        }
        System.out.println("==== " + nombreBodega);
        return null;

    }

    public String delete(Producto p) {
        productoFacade.remove(p);
        list = productoFacade.findAll();//llamo al findall para que se me actualice la lista
        return null;
    }

    public String edit(Producto p) {
        p.setEditable(true); //habilita la caja
        return null;
    }

    public String save(Producto p) {
        productoFacade.edit(p);
        list = productoFacade.findAll(); //actualizo la lista
        p.setEditable(false); //desabilita la caja
        return null;
    }

    public ProductoFacade getProductoFacade() {
        return productoFacade;
    }

    public void setProductoFacade(ProductoFacade productoFacade) {
        this.productoFacade = productoFacade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public BodegaFacade getBodegaFacade() {
        return BodegaFacade;
    }

    public void setBodegaFacade(BodegaFacade BodegaFacade) {
        this.BodegaFacade = BodegaFacade;
    }

    public int getStock() {
        return stock;
    }

    public String getNombreBodega() {
        return nombreBodega;
    }

    public void setNombreBodega(String nombreBodega) {
        this.nombreBodega = nombreBodega;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Producto[] getList() { //este metodo tambien se lo modifica
        return list.toArray(new Producto[0]);// Lo que necesita el JSF dentro del table es un
        //arreglo no una lista por lo que convierto de lista a arreglo
    }

    public void setList(List<Producto> list) {
        this.list = list;
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

}
