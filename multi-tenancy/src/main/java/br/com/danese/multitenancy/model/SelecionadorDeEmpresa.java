package br.com.danese.multitenancy.model;

import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

import br.com.danese.multitenancy.util.Tenant;

public class SelecionadorDeEmpresa {
	
	@Produces
	public static String empresa() {
		return getTenant().getId();
	}
	
	private static String getBasePath() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestScheme() + "://"
				+ FacesContext.getCurrentInstance().getExternalContext().getRequestServerName()
				+ ":"
				+ FacesContext.getCurrentInstance().getExternalContext().getRequestServerPort()
				+ FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
	}
	
	private static Tenant getTenant() {
		String requestURL = getBasePath().toString();
		String tenantId = requestURL.substring(7).replaceAll("\\..+", "");
		return new Tenant(tenantId);
	}
	
}
