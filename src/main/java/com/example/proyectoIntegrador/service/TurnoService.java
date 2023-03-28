package com.example.proyectoIntegrador.service;

import com.example.proyectoIntegrador.dao.IDao;
import com.example.proyectoIntegrador.dao.TurnoDAOLista;
import com.example.proyectoIntegrador.domain.Turno;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService {
    private IDao<Turno> turnoIDao= new TurnoDAOLista();

    public Turno buscarTurno(int id){
        return turnoIDao.buscar(id);
    }
    public void eliminarTurno(int id){
         turnoIDao.eliminar(id);
    }
    public Turno actualizarTurno(Turno turno){
        return turnoIDao.actualizar(turno);
    }
    public Turno guardarTurno(Turno turno){
        return turnoIDao.guardar(turno);
    }

    public List<Turno> buscarTodosTurno(){
        return turnoIDao.buscarTodos();
    }
}
