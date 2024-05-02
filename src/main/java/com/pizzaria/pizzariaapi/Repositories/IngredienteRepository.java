package com.pizzaria.pizzariaapi.Repositories;
import com.pizzaria.pizzariaapi.Models.PizzaPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRepository extends JpaRepository<PizzaPedido.PizzaPedidoHasIngrediente.Ingrediente, Long> {
}