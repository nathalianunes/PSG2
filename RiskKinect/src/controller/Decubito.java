package controller;

public enum Decubito {
	VENTRAL ("decubitoVentral"), 
	DORSAL ("decubitoDorsal"), 
	LATERAL_DIR ("decubitoLateralDir"),
	LATERAL_ESQ ("decubitoLateralEsq");
	
	private String nome;
	
	private Decubito(String nome){
		this.nome = nome;
	}
	public String getNome(){
		return this.nome;
	}
}
