package pubsub.publisher;

import pubsub.Mensagem;
import pubsub.service.ServicoPubSub;

/**
 * Implementa o publicador padrao, que apenas delega ao servico Pub/Sub.
 */
public class PublicadorImpl implements Publicador {

	/**
	 * Adiciona a mensagem a fila central para posterior distribuicao.
	 */
	@Override
	public void publicar(Mensagem mensagem, ServicoPubSub servicoPubSub) {
		servicoPubSub.adicionarMensagemNaFila(mensagem);
	}

}
