package com.example.proyectoIntegrador.service;

import com.example.proyectoIntegrador.dao.OdontologoDAOH2;
import com.example.proyectoIntegrador.domain.Odontologo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service//INTERMEDIARIO ENTREVISTAS BUSCA LOS METODOS DEL DAO
public class OdontologoService {
    private OdontologoDAOH2 odontologoDAOH2= new OdontologoDAOH2();

    public Odontologo buscarOdontologo(int id){
        return odontologoDAOH2.buscar(id);
    }
    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoDAOH2.guardar(odontologo);
    }
    public Odontologo actualizarOdontologo(Odontologo odontologo){
        return odontologoDAOH2.actualizar(odontologo);
    }
    public void eliminarOdontologo(int id){
        odontologoDAOH2.eliminar(id);//este es diferente por el void
    }

    public List<Odontologo> buscarOdontologos(){
        return odontologoDAOH2.buscarTodos();
    }//sin parametro pues nos interesan todos y asi lo dejamos en el dao

}
