package com.example.proyectoIntegrador.controller;

import com.example.proyectoIntegrador.domain.Odontologo;
import com.example.proyectoIntegrador.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//metodos http
@RequestMapping("/odontologos")//ruta
public class OdontologoController {
    private OdontologoService odontologoService;
    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping("/{id}")
    public Odontologo buscarOdontologo(@PathVariable int id){
        return odontologoService.buscarOdontologo(id);
    }

    @PostMapping
    public ResponseEntity <Odontologo> crearOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));

    }//tarea

    @GetMapping
    public List<Odontologo> listarodontologo(){
        return odontologoService.buscarOdontologos();
    }
    @PutMapping
    public Odontologo actualizarOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.actualizarOdontologo(odontologo);
    }
    @DeleteMapping("/{id}")
    public void eliminarOdontologo(@PathVariable int id){

        odontologoService.eliminarOdontologo(id);
    }






}
