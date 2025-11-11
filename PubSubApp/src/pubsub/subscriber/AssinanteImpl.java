package pubsub.subscriber;

import pubsub.service.ServicoPubSub;

/**
 * Implementacao concreta que apenas delega as operacoes ao servico Pub/Sub.
 */
public class AssinanteImpl extends Assinante {

	/**
	 * Registra este assinante em um topico do servico.
	 */
	@Override
	public void assinarTopico(String topico, ServicoPubSub servicoPubSub) {
		servicoPubSub.registrarAssinante(topico, this);
	}

	/**
	 * Remove este assinante de um topico especifico.
	 */
	@Override
	public void cancelarInscricao(String topico, ServicoPubSub servicoPubSub) {
		servicoPubSub.removerAssinante(topico, this);
	}

	/**
	 * Solicita mensagens pendentes somente do topico informado.
	 */
	@Override
	public void buscarMensagensDoTopico(String topico, ServicoPubSub servicoPubSub) {
		servicoPubSub.entregarMensagensPorTopico(topico, this);
	}

}
