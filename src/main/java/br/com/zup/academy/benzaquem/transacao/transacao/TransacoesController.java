package br.com.zup.academy.benzaquem.transacao.transacao;

import br.com.zup.academy.benzaquem.transacao.cartao.Cartao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacoesController {

    @PersistenceContext
    private EntityManager em;

    @GetMapping("/{idCartao}/cartoes")
    public ResponseEntity<?> listarTransacoes(@PathVariable String idCartao) {
        Cartao cartao = em.find(Cartao.class, idCartao);
        if (cartao == null)
            return ResponseEntity.notFound().build();
        Query q = em.createQuery("select t from Transacao t where t.cartao.id =:idCartao order by t.efetivadaEm desc");
        q.setParameter("idCartao", idCartao);
        List<?> list = q.setMaxResults(10).getResultList();
        return ResponseEntity.ok(list);
    }
}
