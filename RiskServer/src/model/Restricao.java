package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Restricao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String ponto;
	
	public Restricao(String ponto) {
		super();
		this.ponto = ponto;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPonto() {
		return ponto;
	}

	public void setPonto(String ponto) {
		this.ponto = ponto;
	}
	
}
