package br.com.zup.academy.benzaquem.transacao.transacao;

import br.com.zup.academy.benzaquem.transacao.cartao.Cartao;
import br.com.zup.academy.benzaquem.transacao.estabelecimento.Estabelecimento;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransacaoResponse {

    private String id;
    private BigDecimal valor;
    private EstabelecimentoResponse estabelecimento;
    private CartaoResponse cartao;
    private LocalDate efetivadaEm;

    @Deprecated
    private TransacaoResponse() {
    }

    public TransacaoResponse(String id, BigDecimal valor, EstabelecimentoResponse estabelecimento, CartaoResponse cartao, LocalDate efetivadaEm) {
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

    public EstabelecimentoResponse getEstabelecimento() {
        return estabelecimento;
    }

    public CartaoResponse getCartao() {
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
                ", " + estabelecimento +
                ", " + cartao +
                ", efetivadaEm=" + efetivadaEm +
                '}';
    }

    public Transacao toModel() {
        return new Transacao(id, valor.doubleValue(), estabelecimento.toModel(), cartao.toModel(), efetivadaEm);
    }

    static class EstabelecimentoResponse {
        private String nome;
        private String cidade;
        private String endereco;

        @Deprecated
        public EstabelecimentoResponse() {
        }

        public EstabelecimentoResponse(String nome, String cidade, String endereco) {
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
            return "EstabelecimentoResponse{" +
                    "nome='" + nome + '\'' +
                    ", cidade='" + cidade + '\'' +
                    ", endereco='" + endereco + '\'' +
                    '}';
        }

        public Estabelecimento toModel() {
            return new Estabelecimento(null, nome, cidade, endereco);
        }
    }

    static class CartaoResponse {
        private String id;
        private String email;

        public CartaoResponse() {
        }

        public CartaoResponse(String id, String email) {
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
            return "CartaoResponse{" +
                    "id='" + id + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }

        public Cartao toModel() {
            return new Cartao(id, email);
        }
    }
}
