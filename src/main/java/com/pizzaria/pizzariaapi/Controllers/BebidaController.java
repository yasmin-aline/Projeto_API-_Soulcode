package com.pizzaria.pizzariaapi.Controllers;

import com.pizzaria.pizzariaapi.Models.Bebida;
import com.pizzaria.pizzariaapi.Repositories.BebidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class BebidaController {

    @Autowired
    private BebidaRepository bebidaRepository;

    // CREATE Bebida
    @PostMapping("/bebidas")
    public ResponseEntity<Bebida> criarBebida(@RequestBody Bebida bebida) {
        try {
            Bebida novaBebida = bebidaRepository.save(bebida);
            return new ResponseEntity<>(novaBebida, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ Bebidas
    @GetMapping("/bebidas")
    public ResponseEntity<List<Bebida>> listarBebidas() {
        try {
            List<Bebida> bebidas = bebidaRepository.findAll();
            if (bebidas.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(bebidas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ Bebida por ID
    @GetMapping("/bebidas/{id}")
    public ResponseEntity<Bebida> buscarBebidaPorId(@PathVariable("id") Long id) {
        Optional<Bebida> bebida = bebidaRepository.findById(id);
        return bebida.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // DELETE Bebida
    @DeleteMapping("/bebidas/{id}")
    public ResponseEntity<HttpStatus> deletarBebida(@PathVariable("id") Long id) {
        try {
            bebidaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // UPDATE Bebida
    @PutMapping("/bebidas/{id}")
    public ResponseEntity<Bebida> atualizarBebida(@PathVariable("id") Long id, @RequestBody Bebida bebida) {
        Optional<Bebida> bebidaData = bebidaRepository.findById(id);

        if (bebidaData.isPresent()) {
            Bebida _bebida = bebidaData.get();
            _bebida.setNome(bebida.getNome());
            _bebida.setPreco(bebida.getPreco());
            return new ResponseEntity<>(bebidaRepository.save(_bebida), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // READ BebidaPedida por ID
    @GetMapping("/bebidas/{id}/bebida-pedida")
    public ResponseEntity<Bebida.BebidaPedida> buscarBebidaPedidaPorId(@PathVariable("id") Long id) {
        Optional<Bebida> bebidaData = bebidaRepository.findById(id);
        return (ResponseEntity<Bebida.BebidaPedida>) bebidaData.map(bebida -> {
            if (bebida.getBebidaPedida() != null) {
                return new ResponseEntity<>(bebida.getBebidaPedida(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // DELETE BebidaPedida por ID
    @DeleteMapping("/bebidas/{id}/bebida-pedida")
    public ResponseEntity<HttpStatus> deletarBebidaPedida(@PathVariable("id") Long id) {
        Optional<Bebida> bebidaData = bebidaRepository.findById(id);

        if (bebidaData.isPresent() && bebidaData.get().getBebidaPedida() != null) {
            bebidaData.get().setBebidaPedida(null);
            bebidaRepository.save(bebidaData.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}