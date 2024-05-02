package com.pizzaria.pizzariaapi.Models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class PizzaPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPizzaPedida;

    @ManyToOne
    @JoinColumn(name = "idPedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "idPizza")
    private Pizza pizza;

    @ManyToOne
    @JoinColumn(name = "idTamanho")
    private Tamanho tamanho;

    @Entity
    public static class Pizza {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idPizza;

        private String nome;
        private double precoBase;
        private boolean personalizada;

        public Long getIdPizza() {
            return idPizza;
        }

        public void setIdPizza(Long idPizza) {
            this.idPizza = idPizza;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public double getPrecoBase() {
            return precoBase;
        }

        public void setPrecoBase(double precoBase) {
            this.precoBase = precoBase;
        }

        public boolean isPersonalizada() {
            return personalizada;
        }

        public void setPersonalizada(boolean personalizada) {
            this.personalizada = personalizada;
        }
    }

    @Entity
    public static class Tamanho {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idTamanho;

        private String nome;
        private double desconto;

        public Long getIdTamanho() {
            return idTamanho;
        }

        public void setIdTamanho(Long idTamanho) {
            this.idTamanho = idTamanho;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public double getDesconto() {
            return desconto;
        }

        public void setDesconto(double desconto) {
            this.desconto = desconto;
        }
    }

    @Entity
    public static class PizzaPedidoHasIngrediente {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        // Referência para PizzaPedido
        @ManyToOne
        @JoinColumn(name = "idPizzaPedida")
        private PizzaPedido pizzaPedido;

        // Referência para Ingrediente
        @ManyToOne
        @JoinColumn(name = "idIngrediente")
        private Ingrediente ingrediente;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public PizzaPedido getPizzaPedido() {
            return pizzaPedido;
        }

        public void setPizzaPedido(PizzaPedido pizzaPedido) {
            this.pizzaPedido = pizzaPedido;
        }

        public Ingrediente getIngrediente() {
            return ingrediente;
        }

        public void setIngrediente(Ingrediente ingrediente) {
            this.ingrediente = ingrediente;
        }

        @Entity
        public static class Ingrediente {

            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Long id;

            private String nome;
            private double preco;

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

            public double getPreco() {
                return preco;
            }

            public void setPreco(double preco) {
                this.preco = preco;
            }
        }
    }

    public Long getIdPizzaPedida() {
        return idPizzaPedida;
    }

    public void setIdPizzaPedida(Long idPizzaPedida) {
        this.idPizzaPedida = idPizzaPedida;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}