package pubsub.subscriber;

import java.util.ArrayList;
import java.util.List;
import pubsub.Mensagem;
import pubsub.service.ServicoPubSub;

/**
 * Base para qualquer assinante: mantem as mensagens recebidas e define operacoes obrigatorias.
 */
public abstract class Assinante {

	// Armazena todas as mensagens ja recebidas por este assinante.
	private List<Mensagem> mensagensDoAssinante = new ArrayList<Mensagem>();

	public List<Mensagem> getMensagensDoAssinante() {
		return mensagensDoAssinante;
	}

	public void setMensagensDoAssinante(List<Mensagem> mensagensDoAssinante) {
		this.mensagensDoAssinante = mensagensDoAssinante;
	}

	/**
	 * Solicita a assinatura do topico informado junto ao servico Pub/Sub.
	 */
	public abstract void assinarTopico(String topico, ServicoPubSub servicoPubSub);

	/**
	 * Cancela a assinatura do topico informado, deixando de receber novas mensagens.
	 */
	public abstract void cancelarInscricao(String topico, ServicoPubSub servicoPubSub);

	/**
	 * Busca mensagens pendentes apenas do topico indicado, sob demanda.
	 */
	public abstract void buscarMensagensDoTopico(String topico, ServicoPubSub servicoPubSub);

	/**
	 * Mostra todas as mensagens ja armazenadas para este assinante.
	 */
	public void imprimirMensagens() {
		for (Mensagem mensagem : mensagensDoAssinante) {
			System.out.println("Topico -> " + mensagem.getTopico() + " : " + mensagem.getConteudo());
		}
	}
}
