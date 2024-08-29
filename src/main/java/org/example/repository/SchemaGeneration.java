package org.example.repository;

import org.example.model.Empresa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class SchemaGeneration {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();
        List<Empresa> list = em.createQuery("from Empresa", Empresa.class).getResultList();
        System.out.println(list);
        em.close();
        emf.close();
    }
}
