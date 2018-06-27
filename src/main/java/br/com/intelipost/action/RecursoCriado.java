package br.com.intelipost.action;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;
/**
 * Classe de quando instanciada e encapsulado em um 
 * AplicationPublish irá invocar um listener para 
 * execultar instrução. Padrão de projeto command
 * @author willi
 *
 */
public class RecursoCriado extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private HttpServletResponse response;


	public RecursoCriado(Object source, Long id, HttpServletResponse response) {
		super(source);
		this.id = id;
		this.response = response;
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public HttpServletResponse getResponse() {
		return response;
	}


	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	
	
}
