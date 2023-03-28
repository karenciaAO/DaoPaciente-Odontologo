package com.example.proyectoIntegrador.controller;

import com.example.proyectoIntegrador.domain.Paciente;
import com.example.proyectoIntegrador.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public List<Paciente> buscarTodosPacientes(){

        return pacienteService.buscarTodosPacientes();
    }
    @PostMapping
    public Paciente registrarPaciente(@RequestBody Paciente paciente){
        return pacienteService.guardarPaciente(paciente);
    }

    @PutMapping
    public Paciente actualizarPaciente(@RequestBody Paciente paciente){
        return pacienteService.actualizarPaciente(paciente);
    }
    @GetMapping("/{email}")
    public Paciente buscarXEmail(@PathVariable String email){

        return pacienteService.buscarXEmail(email);
    }
    @DeleteMapping("/{id}")
    public void eliminarPaciente(@PathVariable int id){
        pacienteService.eliminarPaciente(id);
    }


}
