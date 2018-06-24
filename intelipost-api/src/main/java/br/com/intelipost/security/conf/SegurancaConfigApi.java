package br.com.intelipost.security.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 * Classe que define novas entradas de parametros configuraveis 
 * dentro do arquivo application.properties
 * @author willi
 *
 */
@Component
@ConfigurationProperties(prefix="custom.security")
public class SegurancaConfigApi {

	
//    private String origemPermitida = "http://localhost:8000";
	
	private String origemPermitida = "*"; //todos podem acessar
	
	private final Seguranca seguranca = new Seguranca();
	
	
	public Seguranca getSeguranca() {
		return seguranca;
	}
	
	public String getOrigemPermitida() {
		return origemPermitida;
	}
	
	public void setOrigemPermitida(String origemPermitida) {
		this.origemPermitida = origemPermitida;
	}
	
	public static class Seguranca{
		
		private boolean enableHttps;
		
		public boolean isEnableHttps() {
			return enableHttps;
		}
		
		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}
		
		
	}
}
