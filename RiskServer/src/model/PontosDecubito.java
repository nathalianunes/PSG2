package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PontosDecubito {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String decubito;
	private String ponto;
	
	public PontosDecubito(String decubito, String ponto) {
		super();
		this.decubito = decubito;
		this.ponto = ponto;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDecubito() {
		return decubito;
	}

	public void setDecubito(String decubito) {
		this.decubito = decubito;
	}

	public String getPonto() {
		return ponto;
	}

	public void setPonto(String ponto) {
		this.ponto = ponto;
	}
	
}
