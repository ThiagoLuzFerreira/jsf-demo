package org.example.service;

import org.example.model.Empresa;
import org.example.repository.EmpresaDAO;
import org.example.util.Transcional;

import javax.inject.Inject;
import java.io.Serializable;

public class CadastroEmpresaService implements Serializable {

    @Inject
    private EmpresaDAO empresaDAO;

    @Transcional
    public void salvar(Empresa empresa){
        empresaDAO.guardar(empresa);
    }

    @Transcional
    public void excluir(Empresa empresa){
        empresaDAO.remover(empresa);
    }
}
