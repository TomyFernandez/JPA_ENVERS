package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.ArrayList;

import entidades.*;



public class Main {
    public static void main(String[] args) {
       
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
      

        try {
            entityManager.getTransaction().begin(); //inicia la transaccion
            
             //Crea una factura
             Factura factura1 = Factura.builder()
             .numero(12)
             .total(5555)
             .build();
         //Crea un domicilio
         Domicilio domicilio1 = Domicilio.builder()
             .nombreCalle("Falsa")
             .numeroCalle(123)
             .build();
         //Crea un cliente
         Cliente cliente1 = Cliente.builder()
             .nombre("Pablo")
             .apellido("Munoz")
             .dni(454545)
             .build();
         //Establezco la relacion entre el cliente y el domicilio creados
         cliente1.setDomicilio(domicilio1);
         //Establezco la relacion entre el cliente y la factura creada
         factura1.setCliente(cliente1);
         
         //Crea varias categorias y varios articulos

         Categoria perecederos = Categoria.builder()
             .denominacion("perecederos")
             .build();
         Categoria lacteos = Categoria.builder()
             .denominacion("lacteos")
             .build();
         Articulo carne = Articulo.builder()
             .denominacion("es carne")
             .cantidad(2)
             .precio(400)
             .build();
         Articulo yougurt = Articulo.builder()
             .denominacion("firme")
             .cantidad(50)
             .precio(70)
             .build();

            
         
         //Establezco la relacion bidireccional en cada articulo y categoria creada
         
         carne.getCategorias().add(perecederos);
         perecederos.getArticulos().add(carne);

         yougurt.getCategorias().add(lacteos);
         perecederos.getArticulos().add(yougurt);  //como se ve en esta linea y en la de abajo como la relacion es ManyToMany un articulo puede estar en varias categorias y viceversa 
         lacteos.getArticulos().add(yougurt);    

         //Creo un detalle factura haciendo uso del constructor vacio que proporciona Lombok
         DetalleFactura detallefactura1 = DetalleFactura.builder()
             .build();
         //aca seteo las cosas en el detalle
         detallefactura1.setArticulo(yougurt);
         detallefactura1.setCantidad(2);
         detallefactura1.setSubtotal(140);

         //establezco la relacion entre yougurt y detalle factura
         yougurt.getArticulosDetalleFactura().add(detallefactura1);
         //establezco la relacion bidireccional entre detallefactura y factura
         factura1.getDetalleFactura().add(detallefactura1);
         detallefactura1.setFactura(factura1);


        //hago lo mismo que arriba pero con otro detalle factura EN LA MISMA FACTURA
         DetalleFactura detalleFactura2 = DetalleFactura.builder()  
            .build();
        
        detalleFactura2.setArticulo(carne);
        detalleFactura2.setCantidad(3);
        detalleFactura2.setSubtotal(1200);

        carne.getArticulosDetalleFactura().add(detalleFactura2);
        factura1.getDetalleFactura().add(detalleFactura2);
        detalleFactura2.setFactura(factura1);

        factura1.setTotal(1340);
        
        entityManager.persist(factura1);


            entityManager.flush();
            entityManager.getTransaction().commit();
            
            
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
            System.out.println("No se pudo grabar la clase Cliente");
           
        }

        
        // Cerrar el EntityManager y el EntityManagerFactory
        entityManager.close();
        entityManagerFactory.close();
    }
}
