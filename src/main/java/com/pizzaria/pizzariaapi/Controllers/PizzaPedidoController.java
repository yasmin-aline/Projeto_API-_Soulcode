package com.pizzaria.pizzariaapi.controllers;

import com.pizzaria.pizzariaapi.Models.Pedido;
import com.pizzaria.pizzariaapi.Models.PizzaPedido;
import com.pizzaria.pizzariaapi.Repositories.IngredienteRepository;
import com.pizzaria.pizzariaapi.Repositories.PizzaPedidoRepository;
import com.pizzaria.pizzariaapi.Repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;


import java.util.List;
import java.util.Optional;

@Controller
public class PizzaPedidoController {

    @Autowired
    private PizzaPedidoRepository pizzaPedidoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private IngredienteRepository ingredienteRepository;

    // CREATE
    @PostMapping("/pedido/{idPedido}/adicionar-pizza")
    public ResponseEntity<PizzaPedido> adicionarPizzaAoPedido(@PathVariable("idPedido") Long idPedido, @RequestBody PizzaPedido pizzaPedido) {
        try {
            // Encontrar o pedido associado
            Optional<Pedido> pedidoData = pedidoRepository.findById(idPedido);
            if (pedidoData.isPresent()) {
                Pedido pedido = pedidoData.get();
                pizzaPedido.setPedido(pedido);
                PizzaPedido novaPizzaPedido = pizzaPedidoRepository.save(pizzaPedido);
                return new ResponseEntity<>(novaPizzaPedido, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ
    @GetMapping("/pedido/{idPedido}/pizzas")
    public ResponseEntity<List<PizzaPedido>> listarPizzasDoPedido(@PathVariable("idPedido") Long idPedido) {
        try {
            Optional<Pedido> pedidoData = pedidoRepository.findById(idPedido);
            if (pedidoData.isPresent()) {
                List<PizzaPedido> pizzas = pizzaPedidoRepository.findByPedidoId(idPedido);
                if (pizzas.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(pizzas, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ by ID
    @GetMapping("/pedido/{idPedido}/pizza/{idPizzaPedido}")
    public ResponseEntity<PizzaPedido> buscarPizzaDoPedidoPorId(@PathVariable("idPedido") Long idPedido, @PathVariable("idPizzaPedido") Long idPizzaPedido) {
        try {
            Optional<PizzaPedido> pizzaPedido = pizzaPedidoRepository.findById(idPizzaPedido);
            if (pizzaPedido.isPresent()) {
                // Verificar se a pizza pertence ao pedido específico
                if (pizzaPedido.get().getPedido().getId().equals(idPedido)) {
                    return new ResponseEntity<>(pizzaPedido.get(), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE
    @DeleteMapping("/pedido/{idPedido}/pizza/{idPizzaPedido}")
    public ResponseEntity<HttpStatus> removerPizzaDoPedido(@PathVariable("idPedido") Long idPedido, @PathVariable("idPizzaPedido") Long idPizzaPedido) {
        try {
            Optional<PizzaPedido> pizzaPedidoData = pizzaPedidoRepository.findById(idPizzaPedido);
            if (pizzaPedidoData.isPresent()) {
                // Verificar se a pizza pertence ao pedido específico
                if (pizzaPedidoData.get().getPedido().getId().equals(idPedido)) {
                    pizzaPedidoRepository.deleteById(idPizzaPedido);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // CREATE Ingrediente
    @PostMapping("/ingredientes")
    public ResponseEntity<PizzaPedido.PizzaPedidoHasIngrediente.Ingrediente> criarIngrediente(@RequestBody PizzaPedido.PizzaPedidoHasIngrediente.Ingrediente ingrediente) {
        try {
            PizzaPedido.PizzaPedidoHasIngrediente.Ingrediente novoIngrediente = ingredienteRepository.save(ingrediente);
            return new ResponseEntity<>(novoIngrediente, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ Ingredientes
    @GetMapping("/ingredientes")
    public ResponseEntity<List<PizzaPedido.PizzaPedidoHasIngrediente.Ingrediente>> listarIngredientes() {
        try {
            List<PizzaPedido.PizzaPedidoHasIngrediente.Ingrediente> ingredientes = ingredienteRepository.findAll();
            if (ingredientes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(ingredientes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}