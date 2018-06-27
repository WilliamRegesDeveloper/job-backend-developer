package br.com.intelipost.event;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpHeaders;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.intelipost.action.RecursoCriado;
/**
 * Classe que executa evento assim que uma instancia de RecursoCriado
 * estiver encapsulado em tempo de execução em AplicationPublish
 * @author willi
 *
 */
@Component
public class RecursoCriadoEvent implements ApplicationListener<RecursoCriado> {

	@Override
	public void onApplicationEvent(RecursoCriado arg0) {

		HttpServletResponse response = arg0.getResponse();
		Long id = arg0.getId();

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(id).toUri();
		
		response.setHeader(HttpHeaders.LOCATION, uri.toASCIIString());
	}

}
