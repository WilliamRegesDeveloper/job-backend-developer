package br.com.intelipost.exception;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Classe responsável por tratar exceçoes lançadas pela requisição e envando em
 * json para o cliente da requisição
 * 
 * @author willi
 *
 */
@ControllerAdvice
public class ServicoExceptionHandle extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource source;
	

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<Object> handlerUsernameNotFoundException(UsernameNotFoundException ex, WebRequest req) {

		String msgUsuario = source.getMessage("mensagem.usuario-inexistente-inativo", null,
				LocaleContextHolder.getLocale());
		String mensagemDesenv = ex.getCause() != null ? ex.getCause().toString() : ex.toString();

		List<Erro> erros = Arrays.asList(new Erro(msgUsuario, mensagemDesenv));

		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, req);
	}

	
	public static class Erro {
		private String mensagemUsuario;
		private String mensagemDesenvol;

		public Erro(String mensagemUsuario, String mensagemDesenvol) {
			super();
			this.mensagemUsuario = mensagemUsuario;
			this.mensagemDesenvol = mensagemDesenvol;
		}

		public String getMensagemUsuario() {
			return mensagemUsuario;
		}

		public void setMensagemUsuario(String mensagemUsuario) {
			this.mensagemUsuario = mensagemUsuario;
		}

		public String getMensagemDesenvol() {
			return mensagemDesenvol;
		}

		public void setMensagemDesenvol(String mensagemDesenvol) {
			this.mensagemDesenvol = mensagemDesenvol;
		}

	}
}
