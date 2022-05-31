/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.ejb;

import ec.edu.ups.modelo.Producto;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author Adrian
 */
@Stateless
public class ProductoFacade extends AbstractFacade<Producto>{
     @PersistenceContext()
    private EntityManager em;
    
    public ProductoFacade() {
        super(Producto.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
