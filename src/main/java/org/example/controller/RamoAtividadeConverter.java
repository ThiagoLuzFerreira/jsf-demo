package org.example.controller;

import org.example.model.RamoAtividade;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.util.List;

public class RamoAtividadeConverter implements Converter {

    private List<RamoAtividade> listaRamoAtividade;

    public RamoAtividadeConverter(List<RamoAtividade> listaRamoAtividade) {
        this.listaRamoAtividade = listaRamoAtividade;
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if(s == null) return null;
        Long id = Long.valueOf(s);
        for (RamoAtividade ramoAtividade : listaRamoAtividade){
            if (id.equals(ramoAtividade.getId())){
                return ramoAtividade;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if(o == null) return null;
        RamoAtividade ramoAtividade = (RamoAtividade) o;
        return ramoAtividade.getId().toString();
    }
}
