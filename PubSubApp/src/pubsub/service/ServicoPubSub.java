package pubsub.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import pubsub.Mensagem;
import pubsub.subscriber.Assinante;

/**
 * Orquestra o fluxo entre publicadores e assinantes, mantendo filas e registros.
 */
public class ServicoPubSub {

	// Mapa de topicos para conjunto de assinantes, evitando duplicidades.
	private final Map<String, Set<Assinante>> mapaDeAssinantesPorTopico = new HashMap<String, Set<Assinante>>();

	// Fila central onde os eventos aguardam distribuicao.
	private final Queue<Mensagem> filaDeMensagens = new LinkedList<Mensagem>();

	/**
	 * Insere uma nova mensagem na fila aguardando distribuicao.
	 */
	public void adicionarMensagemNaFila(Mensagem mensagem) {
		filaDeMensagens.add(mensagem);
	}

	/**
	 * Registra um assinante para um determinado topico.
	 */
	public void registrarAssinante(String topico, Assinante assinante) {
		Set<Assinante> assinantes = mapaDeAssinantesPorTopico.get(topico);

		if (assinantes == null) {
			assinantes = new HashSet<Assinante>();
		}

		assinantes.add(assinante);
		mapaDeAssinantesPorTopico.put(topico, assinantes);
	}

	/**
	 * Remove um assinante previamente registrado para um topico.
	 */
	public void removerAssinante(String topico, Assinante assinante) {
		Set<Assinante> assinantes = mapaDeAssinantesPorTopico.get(topico);

		if (assinantes != null) {
			assinantes.remove(assinante);
			mapaDeAssinantesPorTopico.put(topico, assinantes);
		}
	}

	/**
	 * Entrega cada mensagem pendente para todos os assinantes do respectivo topico.
	 */
	public void distribuirMensagens() {
		if (filaDeMensagens.isEmpty()) {
			System.out.println("Nao ha mensagens de publicadores para exibir");
			return;
		}

		while (!filaDeMensagens.isEmpty()) {
			Mensagem mensagem = filaDeMensagens.remove();
			String topico = mensagem.getTopico();

			Set<Assinante> assinantesDoTopico = mapaDeAssinantesPorTopico.get(topico);

			if (assinantesDoTopico == null || assinantesDoTopico.isEmpty()) {
				continue;
			}

			for (Assinante assinante : assinantesDoTopico) {
				List<Mensagem> mensagensDoAssinante = assinante.getMensagensDoAssinante();
				mensagensDoAssinante.add(mensagem);
				assinante.setMensagensDoAssinante(mensagensDoAssinante);
			}
		}
	}

	/**
	 * Entrega apenas mensagens de um topico especifico para um assinante.
	 */
	public void entregarMensagensPorTopico(String topico, Assinante assinante) {
		if (filaDeMensagens.isEmpty()) {
			System.out.println("Nao ha mensagens de publicadores para exibir");
			return;
		}

		while (!filaDeMensagens.isEmpty()) {
			Mensagem mensagem = filaDeMensagens.remove();

			if (mensagem.getTopico().equalsIgnoreCase(topico)) {

				Set<Assinante> assinantesDoTopico = mapaDeAssinantesPorTopico.get(topico);

				if (assinantesDoTopico == null) {
					continue;
				}

				for (Assinante assinanteDoTopico : assinantesDoTopico) {
					if (assinanteDoTopico.equals(assinante)) {
						List<Mensagem> mensagensDoAssinante = assinante.getMensagensDoAssinante();
						mensagensDoAssinante.add(mensagem);
						assinante.setMensagensDoAssinante(mensagensDoAssinante);
					}
				}
			}
		}
	}

}
