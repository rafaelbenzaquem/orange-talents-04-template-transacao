package br.com.zup.academy.benzaquem.transacao.transacao;

import br.com.zup.academy.benzaquem.transacao.cartao.Cartao;
import br.com.zup.academy.benzaquem.transacao.estabelecimento.Estabelecimento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransacaoListResponse {

    private String id;
    private BigDecimal valor;
    private EstabelecimentoListResponse estabelecimento;
    private CartaoListResponse cartao;
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate efetivadaEm;

    @Deprecated
    private TransacaoListResponse() {
    }

    public TransacaoListResponse(Transacao transacao) {
        this.id = transacao.getId();
        this.valor = new BigDecimal(transacao.getValor());
        this.estabelecimento = new EstabelecimentoListResponse(transacao.getEstabelecimento());
        this.cartao = new CartaoListResponse(transacao.getCartao());
        this.efetivadaEm = transacao.getEfetivadaEm();
    }

    public String getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public EstabelecimentoListResponse getEstabelecimento() {
        return estabelecimento;
    }

    public CartaoListResponse getCartao() {
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

    static class EstabelecimentoListResponse {
        private String nome;
        private String cidade;
        private String endereco;

        @Deprecated
        public EstabelecimentoListResponse() {
        }

        public EstabelecimentoListResponse(Estabelecimento estabelecimento) {
            this.nome = estabelecimento.getNome();
            this.cidade = estabelecimento.getCidade();
            this.endereco = estabelecimento.getEndereco();
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

    }

    static class CartaoListResponse {
        private String id;
        private String email;

        @Deprecated
        public CartaoListResponse() {
        }

        public CartaoListResponse(Cartao cartao) {
            this.id = cartao.getId();
            this.email = cartao.getEmail();
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

    }
}
