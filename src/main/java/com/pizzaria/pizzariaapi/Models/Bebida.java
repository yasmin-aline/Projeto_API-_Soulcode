package com.pizzaria.pizzariaapi.Models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Bebida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private BigDecimal preco;

    @OneToOne(mappedBy = "bebida")
    private BebidaPedida bebidaPedida;

    @ManyToMany(mappedBy = "bebidas")
    private Set<Pedido> pedidos = new HashSet<>();

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public BebidaPedida getBebidaPedida() {
        return bebidaPedida;
    }

    public void setBebidaPedida(BebidaPedida bebidaPedida) {
        this.bebidaPedida = bebidaPedida;
    }

    @Entity
    public static class BebidaPedida {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "bebida_id", referencedColumnName = "id")
        private Bebida bebida;

        @Column
        private int quantidade;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Bebida getBebida() {
            return bebida;
        }

        public void setBebida(Bebida bebida) {
            this.bebida = bebida;
        }

        public int getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(int quantidade) {
            this.quantidade = quantidade;
        }
    }
}