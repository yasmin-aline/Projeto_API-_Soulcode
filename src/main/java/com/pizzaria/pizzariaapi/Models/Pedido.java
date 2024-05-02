package com.pizzaria.pizzariaapi.Models;
import com.pizzaria.pizzariaapi.Models.Cliente;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime dataHora;

    @ManyToOne
    private Cliente cliente;

    @Embedded
    private Fornada fornada;

    @ManyToMany
    @JoinTable(
            name = "pedido_bebida",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "bebida_id"))
    private Set<com.pizzaria.pizzariaapi.Models.Bebida> bebidas = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Fornada getFornada() {
        return fornada;
    }

    public void setFornada(Fornada fornada) {
        this.fornada = fornada;
    }

    @Embeddable
    public static class Fornada {

        @Column
        private Long idFornada;

        @Column
        private int numFornada;

        @Column
        private int qtdPizzas;

        public Long getIdFornada() {
            return idFornada;
        }

        public void setIdFornada(Long idFornada) {
            this.idFornada = idFornada;
        }

        public int getNumFornada() {
            return numFornada;
        }

        public void setNumFornada(int numFornada) {
            this.numFornada = numFornada;
        }

        public int getQtdPizzas() {
            return qtdPizzas;
        }

        public void setQtdPizzas(int qtdPizzas) {
            this.qtdPizzas = qtdPizzas;
        }
    }
}