package pubsub.publisher;

import pubsub.Mensagem;
import pubsub.service.ServicoPubSub;

/**
 * Contrato para qualquer publicador que deseje enviar mensagens ao servico Pub/Sub.
 */
public interface Publicador {

	/**
	 * Envia uma nova mensagem para o servico central, onde ficara disponivel aos assinantes.
	 */
	void publicar(Mensagem mensagem, ServicoPubSub servicoPubSub);

}
