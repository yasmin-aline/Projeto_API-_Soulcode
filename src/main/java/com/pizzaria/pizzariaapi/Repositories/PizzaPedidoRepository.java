package com.pizzaria.pizzariaapi.Repositories;

import com.pizzaria.pizzariaapi.Models.PizzaPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzaPedidoRepository extends JpaRepository<PizzaPedido, Long> {
    List<PizzaPedido> findByPedidoId(Long idPedido);
}