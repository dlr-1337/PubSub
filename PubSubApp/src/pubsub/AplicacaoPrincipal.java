package pubsub;

import pubsub.Mensagem;
import pubsub.publisher.Publicador;
import pubsub.publisher.PublicadorImpl;
import pubsub.service.ServicoPubSub;
import pubsub.subscriber.Assinante;
import pubsub.subscriber.AssinanteImpl;

/**
 * Demonstra o uso basico do mecanismo Pub/Sub com dois topicos e diferentes assinantes.
 */
public class AplicacaoPrincipal {

	public static void main(String[] args) {

		// Instancia publicadores, assinantes e o servico central.
		Publicador publicadorJava = new PublicadorImpl();
		Publicador publicadorPython = new PublicadorImpl();

		Assinante assinanteJava = new AssinanteImpl();
		Assinante assinanteTodosTopicos = new AssinanteImpl();
		Assinante assinantePython = new AssinanteImpl();

		ServicoPubSub servicoPubSub = new ServicoPubSub();

		// Cria mensagens e envia ao servico Pub/Sub.
		Mensagem mensagemJava1 = new Mensagem("Java", "Conceitos fundamentais de Java");
		Mensagem mensagemJava2 = new Mensagem("Java", "Spring MVC: injecao de dependencias e AOP");
		Mensagem mensagemJava3 = new Mensagem("Java", "JPA e Hibernate");

		publicadorJava.publicar(mensagemJava1, servicoPubSub);
		publicadorJava.publicar(mensagemJava2, servicoPubSub);
		publicadorJava.publicar(mensagemJava3, servicoPubSub);

		Mensagem mensagemPython1 = new Mensagem("Python", "Linguagem poderosa e simples");
		Mensagem mensagemPython2 = new Mensagem("Python", "Mensagem avancada sobre Python");

		publicadorPython.publicar(mensagemPython1, servicoPubSub);
		publicadorPython.publicar(mensagemPython2, servicoPubSub);

		// Registra cada assinante nos topicos desejados.
		assinanteJava.assinarTopico("Java", servicoPubSub); // Assina apenas Java
		assinantePython.assinarTopico("Python", servicoPubSub); // Assina apenas Python
		assinanteTodosTopicos.assinarTopico("Java", servicoPubSub); // Assina ambos
		assinanteTodosTopicos.assinarTopico("Python", servicoPubSub);

		// Exemplo de cancelamento de assinatura.
		// assinantePython.cancelarInscricao("Python", servicoPubSub);

		// Distribui todas as mensagens atualmente na fila para os assinantes.
		servicoPubSub.distribuirMensagens();

		// Exibe as mensagens recebidas por cada assinante.
		System.out.println("Mensagens do assinante Java:");
		assinanteJava.imprimirMensagens();

		System.out.println("\nMensagens do assinante Python:");
		assinantePython.imprimirMensagens();

		System.out.println("\nMensagens do assinante de todos os topicos:");
		assinanteTodosTopicos.imprimirMensagens();

		// Apos distribuir, a fila fica vazia; vamos publicar mais conteudo.
		System.out.println("\nPublicando mais 2 mensagens de Java...");
		Mensagem mensagemJava4 = new Mensagem("Java", "JSP e Servlets");
		Mensagem mensagemJava5 = new Mensagem("Java", "Framework Struts");

		publicadorJava.publicar(mensagemJava4, servicoPubSub);
		publicadorJava.publicar(mensagemJava5, servicoPubSub);

		assinanteJava.buscarMensagensDoTopico("Java", servicoPubSub);
		System.out.println("\nMensagens do assinante Java agora sao:");
		assinanteJava.imprimirMensagens();
	}
}
