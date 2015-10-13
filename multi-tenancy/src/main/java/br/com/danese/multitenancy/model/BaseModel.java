package br.com.danese.multitenancy.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

@MappedSuperclass
@FilterDefs({
		@FilterDef(name = "filtroEmpresa", parameters = { @ParamDef(name = "paramEmpresa", type = "string") }) })
@Filters({ 
		@Filter(name = "filtroEmpresa", condition = "empresa = :paramEmpresa") })
public class BaseModel implements Serializable {

	private static final long serialVersionUID = 7888378619981559302L;
	
	@Column(name = "empresa", nullable = true, insertable = true, updatable = true, length=8)
	private String empresa;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@PrePersist
	public void onPersist() {
		if (empresa == null) {
			empresa = SelecionadorDeEmpresa.empresa();
		}
	}

	@PostLoad
	@PreUpdate
	@PreRemove
	public void onEntityModification() throws Exception {
		if (!(SelecionadorDeEmpresa.empresa().equals(empresa))) {
			throw new RuntimeException("Tentativa de acesso negada.");
		}
	}

	public String getEmpresa() {
		return empresa;
	}
	
}