package com.example.proyectoIntegrador.controller;

import com.example.proyectoIntegrador.domain.Odontologo;
import com.example.proyectoIntegrador.domain.Turno;
import com.example.proyectoIntegrador.service.OdontologoService;
import com.example.proyectoIntegrador.service.PacienteService;
import com.example.proyectoIntegrador.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;
    private OdontologoService odontologoService;
    private PacienteService pacienteService;

    @Autowired
    public TurnoController(TurnoService turnoService, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoService = turnoService;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno){
        ResponseEntity<Turno> respuesta;
        //trabajo
        if (pacienteService.buscarPaciente(turno.getPaciente().getId())!=null &&
        odontologoService.buscarOdontologo(turno.getOdontologo().getId())!=null){
            respuesta=ResponseEntity.ok(turnoService.guardarTurno(turno));
        }
        else{
            respuesta=ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return respuesta;
    }

    @GetMapping("/{id}")
    public Turno buscarTurno(@PathVariable int id){
        return turnoService.buscarTurno(id);
    }

    @GetMapping
    public List<Turno> listTurnoS() {
        return turnoService.buscarTodosTurno();
    }
    @PutMapping
    public Turno actualizarTurno(@RequestBody Turno turno){

        return turnoService.actualizarTurno(turno);
    }
    @DeleteMapping("/{id}")
    public void eliminarTurno(@PathVariable int id){

        turnoService.eliminarTurno(id);
    }

}
