package pubsub;

/**
 * Representa um evento publicado no sistema, contendo o topico e o conteudo textual.
 */
public class Mensagem {

	private String topico;
	private String conteudo;

	public Mensagem() {}

	public Mensagem(String topico, String conteudo) {
		this.topico = topico;
		this.conteudo = conteudo;
	}

	public String getTopico() {
		return topico;
	}

	public void setTopico(String topico) {
		this.topico = topico;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
}
