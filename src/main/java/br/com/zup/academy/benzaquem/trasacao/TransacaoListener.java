package br.com.zup.academy.benzaquem.trasacao;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransacaoListener {
    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    public void ouvir(TransacaoResponse transacaoResponse) {
        System.out.println(transacaoResponse);
    }
}
