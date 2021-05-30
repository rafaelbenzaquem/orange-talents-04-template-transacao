package br.com.zup.academy.benzaquem.transacao.transacao;

import br.com.zup.academy.benzaquem.transacao.cartao.Cartao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transacoes")
public class TransacoesController {

    @PersistenceContext
    private EntityManager em;

    private Logger logger = LoggerFactory.getLogger(TransacoesController.class);

    @GetMapping("/{idCartao}/cartoes")
    public ResponseEntity<?> listarTransacoes(@PathVariable String idCartao) {
        var cartao = em.find(Cartao.class, idCartao);
        if (cartao == null) {
            logger.warn("Cartao {} não foi encontrado", idCartao);
            return ResponseEntity.notFound().build();
        }
        var q = em.createQuery("select t from Transacao t where t.cartao.id =:idCartao order by t.efetivadaEm desc");
        q.setParameter("idCartao", idCartao);
        var transacoes = (List<Transacao>) q.setMaxResults(10).getResultList();
        logger.info("As ultimas {} transações foram retornadas", transacoes.size());
        return ResponseEntity.ok(transacoes.stream().map(TransacaoListResponse::new).collect(Collectors.toList()));
    }
}
