package br.com.zup.academy.benzaquem.trasacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransacaoResponse {

    private String id;
    private BigDecimal valor;
    private Estabelecimento estabelecimento;
    private Cartao cartao;
    private LocalDate efetivadaEm;

    @Deprecated
    private TransacaoResponse(){}

    public TransacaoResponse(String id, BigDecimal valor, Estabelecimento estabelecimento, Cartao cartao, LocalDate efetivadaEm) {
        this.id = id;
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.cartao = cartao;
        this.efetivadaEm = efetivadaEm;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public LocalDate getEfetivadaEm() {
        return efetivadaEm;
    }

    @Override
    public String toString() {
        return "TransacaoResponse{" +
                "id='" + id + '\'' +
                ", valor=" + valor +
                ", estabelecimento=" + estabelecimento +
                ", cartao=" + cartao +
                ", efetivadaEm=" + efetivadaEm +
                '}';
    }

    static class Estabelecimento{
        private String nome;
        private String cidade;
        private String endereco;

        @Deprecated
        public Estabelecimento() {
        }

        public Estabelecimento(String nome, String cidade, String endereco) {
            this.nome = nome;
            this.cidade = cidade;
            this.endereco = endereco;
        }

        public String getNome() {
            return nome;
        }

        public String getCidade() {
            return cidade;
        }

        public String getEndereco() {
            return endereco;
        }

        @Override
        public String toString() {
            return "Estabelecimento{" +
                    "nome='" + nome + '\'' +
                    ", cidade='" + cidade + '\'' +
                    ", endereco='" + endereco + '\'' +
                    '}';
        }
    }

    static class Cartao{
        private String id;
        private String email;

        public Cartao() {
        }

        public Cartao(String id, String email) {
            this.id = id;
            this.email = email;
        }

        public String getId() {
            return id;
        }

        public String getEmail() {
            return email;
        }

        @Override
        public String toString() {
            return "Cartao{" +
                    "id='" + id + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
    }
}
