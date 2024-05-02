package com.pizzaria.pizzariaapi.Controllers;

import com.pizzaria.pizzariaapi.Models.Pedido;
import com.pizzaria.pizzariaapi.Repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    // CREATE
    @PostMapping("/criar-pedido")
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
        try {
            pedido.setDataHora(LocalDateTime.now()); // Definindo a data e hora do pedido para o momento atual
            Pedido novoPedido = pedidoRepository.save(pedido);
            return new ResponseEntity<>(novoPedido, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ
    @GetMapping("/listar-pedidos")
    public ResponseEntity<List<Pedido>> listarPedidos() {
        try {
            List<Pedido> pedidos = pedidoRepository.findAll();
            if (pedidos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(pedidos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ by ID
    @GetMapping("/pedido/{id}")
    public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable("id") long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if (pedido.isPresent()) {
            return new ResponseEntity<>(pedido.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // CREATE Fornada para um Pedido específico
    @PostMapping("/pedido/{id}/criar-fornada")
    public ResponseEntity<Pedido> criarFornadaParaPedido(@PathVariable("id") long id, @RequestBody Pedido.Fornada fornada) {
        Optional<Pedido> pedidoData = pedidoRepository.findById(id);
        if (pedidoData.isPresent()) {
            Pedido pedido = pedidoData.get();
            pedido.setFornada(fornada);
            pedidoRepository.save(pedido);
            return new ResponseEntity<>(pedido, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE Fornada para um Pedido específico
    @DeleteMapping("/pedido/{id}/deletar-fornada")
    public ResponseEntity<Pedido> deletarFornadaParaPedido(@PathVariable("id") long id) {
        Optional<Pedido> pedidoData = pedidoRepository.findById(id);
        if (pedidoData.isPresent()) {
            Pedido pedido = pedidoData.get();
            pedido.setFornada(null);
            pedidoRepository.save(pedido);
            return new ResponseEntity<>(pedido, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE Pedido
    @DeleteMapping("/pedido/{id}")
    public ResponseEntity<HttpStatus> deletarPedido(@PathVariable("id") long id) {
        try {
            pedidoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}