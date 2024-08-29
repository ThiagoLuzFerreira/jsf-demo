package org.example.repository;

import org.example.model.Empresa;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

public class EmpresaDAO implements Serializable {

    @Inject
    private EntityManager entityManager;

    public EmpresaDAO() {}

    public EmpresaDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Empresa porId(Long id){
        return entityManager.find(Empresa.class, id);
    }

    public List<Empresa> pesquisar(String nome){
        TypedQuery<Empresa> query = entityManager.createQuery("from Empresa where nomeFantasia like :nomeFantasia", Empresa.class);
        query.setParameter("nomeFantasia", nome + "%");
        return query.getResultList();
    }

    public List<Empresa> todas(){
        return  entityManager.createQuery("from Empresa", Empresa.class).getResultList();
    }

    public Empresa guardar(Empresa empresa){
        return entityManager.merge(empresa);
    }

    public void remover(Empresa empresa){
        empresa = porId(empresa.getId());
        entityManager.remove(empresa);
    }
}
