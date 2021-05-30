package br.com.zup.academy.benzaquem.transacao.transacao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
public class TransacaoListener {

    @PersistenceContext
    private EntityManager entityManager;

    private Logger logger = LoggerFactory.getLogger(TransacaoListener.class);

    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    @Transactional
    public void ouvir(TransacaoLegadoResponse transacaoLegadoResponse) {
        entityManager.merge(transacaoLegadoResponse.toModel());
        logger.info("Transacao {} salva", transacaoLegadoResponse.getId());
    }
}
