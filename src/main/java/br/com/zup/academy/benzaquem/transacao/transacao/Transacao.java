package br.com.zup.academy.benzaquem.transacao.transacao;

import br.com.zup.academy.benzaquem.transacao.cartao.Cartao;
import br.com.zup.academy.benzaquem.transacao.estabelecimento.Estabelecimento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Transacao {

    @Id
    private String id;
    private Double valor;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Estabelecimento estabelecimento;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Cartao cartao;
    private LocalDate efetivadaEm;

    @Deprecated
    public Transacao() {
    }

    public Transacao(String id, Double valor, Estabelecimento estabelecimento, Cartao cartao, LocalDate efetivadaEm) {
        this.id = id;
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.cartao = cartao;
        this.efetivadaEm = efetivadaEm;
    }

    public String getId() {
        return id;
    }

    public Double getValor() {
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
}
