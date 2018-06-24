package br.com.intelipost.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import br.com.intelipost.security.conf.SegurancaConfigApi;
/**
 * Filtro que define configuração no HEADER da resposta
 * quando o metodo OPTIONS é requisitado pelo JavaScript dos browsers
 * para poder liberar o uso de outros metodos e headers
 * 
 * Define o acesso de qualquer origem de acesso a essa API
 * @author willi
 *
 */

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class FilterCors implements Filter {

	@Autowired
	private SegurancaConfigApi confApi;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse resp = (HttpServletResponse) arg1;

		resp.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
		resp.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, confApi.getOrigemPermitida());

		if ("OPTIONS".equals(req.getMethod())
				&& req.getHeader(HttpHeaders.ORIGIN).equals(confApi.getOrigemPermitida())) {

			resp.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, DELETE, OPTIONS");
			resp.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "Authorization, Content-Type, Accept");
			resp.setHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "3600");

			resp.setStatus(HttpServletResponse.SC_OK);
		} else {
			arg2.doFilter(req, resp);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
