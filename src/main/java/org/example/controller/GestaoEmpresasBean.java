package org.example.controller;

import org.example.model.Empresa;
import org.example.model.RamoAtividade;
import org.example.model.TipoEmpresa;
import org.example.repository.EmpresaDAO;
import org.example.repository.RamoAtividadeDAO;
import org.example.service.CadastroEmpresaService;
import org.example.util.FacesMessages;
import org.primefaces.context.RequestContext;

import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {

    @Inject
    private EmpresaDAO empresaDAO;
    @Inject
    private RamoAtividadeDAO ramoAtividadeDAO;
    @Inject
    private CadastroEmpresaService empresaService;
    @Inject
    private FacesMessages messages;
    private List<Empresa> listaEmpresas;
    private String termoPesquisa;
    private Converter ramoAtividadeConverter;
    private Empresa empresa;

    public void prepararNovaEmpresa() {
        empresa = new Empresa();
    }

    public void prepararEdicao(){
        ramoAtividadeConverter = new RamoAtividadeConverter(Arrays.asList(empresa.getRamoAtividade()));
    }

    public void salvar() {
        empresaService.salvar(empresa);

        atualizarRegistros();

        messages.info("Empresa salva com sucesso!");

        RequestContext.getCurrentInstance().update(Arrays.asList("frm:empresasDataTable", "frm:messages"));
    }

    public void excluir(){
        empresaService.excluir(empresa);
        empresa = null;
        atualizarRegistros();
        messages.info("Empresa excluida com sucesso");
    }

    public void todasEmpresas(){
        listaEmpresas = empresaDAO.todas();
    }

    public List<RamoAtividade> completarRamoAtividades(String termo){
        List<RamoAtividade> listaRamoAtividades = ramoAtividadeDAO.pesquisar(termo);
        ramoAtividadeConverter = new RamoAtividadeConverter(listaRamoAtividades);
        return listaRamoAtividades;
    }
    public void pesquisar(){
        listaEmpresas = empresaDAO.pesquisar(termoPesquisa);
        if (listaEmpresas.isEmpty()){
            messages.info("Sua consulta nao retornou registros");
        }
    }

    private boolean jaHouvePesquisa() {
        return termoPesquisa != null && !"".equals(termoPesquisa);
    }

    private void atualizarRegistros() {
        if (jaHouvePesquisa()) {
            pesquisar();
        } else {
            todasEmpresas();
        }
    }

    public List<Empresa> getListaEmpresas() {
        return listaEmpresas;
    }

    public String getTermoPesquisa() {
        return termoPesquisa;
    }

    public void setTermoPesquisa(String termoPesquisa) {
        this.termoPesquisa = termoPesquisa;
    }

    public TipoEmpresa[] getTiposEmpresa(){
        return TipoEmpresa.values();
    }

    public Converter getRamoAtividadeConverter() {
        return ramoAtividadeConverter;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public boolean isEmpresaSelecioanda(){
        return empresa != null && empresa.getId() != null;
    }


}
