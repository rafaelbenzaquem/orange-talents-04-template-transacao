package br.com.zup.academy.benzaquem.transacao.transacao;

import br.com.zup.academy.benzaquem.transacao.cartao.Cartao;
import br.com.zup.academy.benzaquem.transacao.cartao.CartaoRepository;
import br.com.zup.academy.benzaquem.transacao.estabelecimento.Estabelecimento;
import br.com.zup.academy.benzaquem.transacao.estabelecimento.EstabelecimentoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class TransacaoControllerTest {


    @Autowired
    private MockMvc mockMvc;

    private String responseBodyGetSuccess;

    @Test
    void buscaUltimas10TransacoesSucessoRetorna200() throws Exception {
        popularBancoH2();
        String numeroCartao = "1234-1234-1234-4949";
        URI uri = new URI("/transacoes/" + numeroCartao + "/cartoes");

        mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(responseBodyGetSuccess));
    }

    @Test
    void buscaComCartaoInexistenteRetorna404() throws Exception {
        String numeroCartao = "1234-1234-1234-0000";
        URI uri = new URI("/transacoes/" + numeroCartao + "/cartoes");

        mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()));
    }


    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;
    @Autowired
    private TransacaoRepository transacaoRepository;


    @Transactional
    void popularBancoH2() {
        Cartao cartao = new Cartao("1234-1234-1234-4949", "rafael.neto@zup.com.br");
        Estabelecimento estabelecimento1 = new Estabelecimento(null, "Atacadão", "Santarém-Pa", "Avenida Engenheiro Fernando Guilhon, nº S/N, Amparo");
        Estabelecimento estabelecimento2 = new Estabelecimento(null, "Avante Atacadista", "Santarém-Pa", "Tv. Assis de Vasconcelos, N° 1-165 - Aldeia");
        Estabelecimento estabelecimento3 = new Estabelecimento(null, "Assaí Atacadista", "Santarém-Pa", "Avenida Engenheiro Fernando Guilhon, nº S/N - Amparo");
        UUID uuid = UUID.randomUUID();
        Transacao transacao0 = new Transacao(UUID.randomUUID().toString(), 200.00, estabelecimento3, cartao,
                LocalDate.parse("25/08/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        Transacao transacao1 = new Transacao(UUID.randomUUID().toString(), 500.00, estabelecimento1, cartao,
                LocalDate.parse("26/09/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        Transacao transacao2 = new Transacao(UUID.randomUUID().toString(), 600.00, estabelecimento2, cartao,
                LocalDate.parse("27/09/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        Transacao transacao3 = new Transacao(UUID.randomUUID().toString(), 1200.00, estabelecimento3, cartao,
                LocalDate.parse("28/09/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        Transacao transacao4 = new Transacao(UUID.randomUUID().toString(), 10.00, estabelecimento2, cartao,
                LocalDate.parse("03/10/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        Transacao transacao5 = new Transacao(UUID.randomUUID().toString(), 1100.00, estabelecimento2, cartao,
                LocalDate.parse("15/10/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        Transacao transacao6 = new Transacao(UUID.randomUUID().toString(), 40.00, estabelecimento3, cartao,
                LocalDate.parse("20/11/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        Transacao transacao7 = new Transacao(UUID.randomUUID().toString(), 30.00, estabelecimento3, cartao,
                LocalDate.parse("22/12/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        Transacao transacao8 = new Transacao(UUID.randomUUID().toString(), 10.00, estabelecimento3, cartao,
                LocalDate.parse("26/12/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        Transacao transacao9 = new Transacao(UUID.randomUUID().toString(), 120.00, estabelecimento2, cartao,
                LocalDate.parse("01/01/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        Transacao transacao10 = new Transacao(UUID.randomUUID().toString(), 1100.00, estabelecimento2, cartao,
                LocalDate.parse("02/02/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        List<Transacao> transacoes = Lists.list(transacao1, transacao2, transacao3, transacao4, transacao5, transacao6,
                transacao7, transacao8, transacao9, transacao10);
        cartaoRepository.save(cartao);
        estabelecimentoRepository.save(estabelecimento1);
        estabelecimentoRepository.save(estabelecimento2);
        estabelecimentoRepository.save(estabelecimento3);
        transacaoRepository.save(transacao0);
        transacaoRepository.saveAll(transacoes);

        try {
            responseBodyGetSuccess = new ObjectMapper().writeValueAsString(transacoes);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
