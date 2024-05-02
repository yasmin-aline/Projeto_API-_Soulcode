package com.pizzaria.pizzariaapi.Repositories;

import com.pizzaria.pizzariaapi.Models.Bebida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BebidaRepository extends JpaRepository<Bebida, Long> {
}