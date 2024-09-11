package org.example;

import org.example.audit.Revision;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;  // Importación necesaria
import java.util.List;       // Importación necesaria

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("example-unit");
        EntityManager em = emf.createEntityManager();
        System.out.println("En marcha con Hibernate Envers");

        try {
            // Iniciar una nueva transacción
            em.getTransaction().begin();

            // Crear y persistir entidades
            Domicilio domicilio = Domicilio.builder()
                    .nombreCalle("Av. Siempre Viva")
                    .numero(742)
                    .build();

            Cliente cliente = Cliente.builder()
                    .nombre("Martin")
                    .apellido("Gómez")
                    .dni(12345678)
                    .domicilio(domicilio)
                    .build();

            Articulo articulo1 = Articulo.builder()
                    .cantidad(10)
                    .denominacion("Laptop")
                    .precio(1500)
                    .build();

            Articulo articulo2 = Articulo.builder()
                    .cantidad(5)
                    .denominacion("Teclado")
                    .precio(50)
                    .build();

            DetalleFactura detalle1 = DetalleFactura.builder()
                    .cantidad(1)
                    .subtotal(1500)
                    .articulo(articulo1)
                    .build();

            DetalleFactura detalle2 = DetalleFactura.builder()
                    .cantidad(2)
                    .subtotal(100)
                    .articulo(articulo2)
                    .build();

            // Inicialización de la lista al construir el objeto Factura
            Factura factura = Factura.builder()
                    .fecha("2024-09-09")
                    .numero(1)
                    .total(1600)
                    .cliente(cliente)
                    .detalles(new ArrayList<>())  // Asegurar la inicialización de la lista
                    .build();

            // Agregar detalles a la factura
            factura.getDetalles().add(detalle1);
            factura.getDetalles().add(detalle2);

            // Persistir la factura
            em.persist(factura);

            // Commit de la transacción para persistir la factura
            em.getTransaction().commit();
            System.out.println("Factura persistida: " + factura);

            // Actualización de la factura para que Envers audite el cambio
            em.getTransaction().begin();
            factura.setTotal(1700);  // Modificar el total de la factura
            em.getTransaction().commit();
            System.out.println("Factura actualizada: " + factura);

            // Eliminar la factura para que Envers audite la eliminación
            em.getTransaction().begin();
            em.remove(factura);
            em.getTransaction().commit();
            System.out.println("Factura eliminada: " + factura);

            // Recuperar las revisiones usando Hibernate Envers
            AuditReader auditReader = AuditReaderFactory.get(em);
            List<Number> revisiones = auditReader.getRevisions(Factura.class, factura.getId());

            System.out.println("Revisiones de la Factura:");
            for (Number revision : revisiones) {
                Factura facturaRevisionada = auditReader.find(Factura.class, factura.getId(), revision);
                System.out.println("Factura en la revisión " + revision + ": " + facturaRevisionada);
            }

        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error: " + e.getMessage());
        } finally {
            // Cerrar EntityManager y EntityManagerFactory
            em.close();
            emf.close();
        }
    }
}
