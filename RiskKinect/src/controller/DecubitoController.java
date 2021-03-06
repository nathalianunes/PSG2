package controller;

import communication.Update;

public class DecubitoController {
	private Decubito decubitoAtual;
	private Decubito decubitoPassado;
	private Decubito ultimoVentralDorsal;
	static long startTime;
	static long endTime;
	private Update update;
	
	public DecubitoController(){
		decubitoAtual = null;
		decubitoPassado = null;
		ultimoVentralDorsal = null;
		startTime = System.nanoTime();
		update = new Update();
	}
	
	public void defineDecubito(float ombroDirZ, float ombroEsqZ){
//		if (decubitoAtual!= null && decubitoPassado != null)
//		System.out.println("Atual: "+decubitoAtual+" Passado: "+decubitoPassado+" ultimo: "+ultimoVentralDorsal);
		
		//se estiver de frente ou costas
		if (Math.abs(ombroDirZ-ombroEsqZ) < 100.0){
			if (decubitoAtual == null){
				decubitoAtual = Decubito.DORSAL;
				ultimoVentralDorsal = Decubito.DORSAL;
//				System.out.println("Frente");
			}
			else{
				if(decubitoPassado == null){
//					System.out.println("Frente");
					decubitoPassado = decubitoAtual;
					decubitoAtual = Decubito.DORSAL;
					ultimoVentralDorsal = Decubito.DORSAL;
				}
				else{
					if(decubitoPassado.equals(Decubito.DORSAL)){
//						System.out.println("Frente");
						//decubitoPassado = decubitoAtual;
						decubitoAtual = Decubito.DORSAL;
						ultimoVentralDorsal = Decubito.DORSAL;
					}
					else{
						if(decubitoPassado.equals(Decubito.VENTRAL)){
//							System.out.println("Costas");
							//decubitoPassado = decubitoAtual;
							decubitoAtual = Decubito.VENTRAL;
							ultimoVentralDorsal = Decubito.VENTRAL;
						}
						else{
							if (ultimoVentralDorsal.equals(Decubito.VENTRAL)){
//								System.out.println("Frente");
								decubitoPassado = Decubito.DORSAL;
								decubitoAtual = Decubito.DORSAL;
								ultimoVentralDorsal = Decubito.DORSAL;
							}
							else{
//								System.out.println("Costas");
								decubitoPassado = Decubito.VENTRAL;
								decubitoAtual = Decubito.VENTRAL;
								ultimoVentralDorsal = Decubito.VENTRAL;
							}
						}
					}
				}
			}
			
		}
		else{//lateral
			if (ombroDirZ < ombroEsqZ){
//				System.out.println("Lateral Direito");
				if (decubitoAtual != null){
					//if (!decubitoAtual.equals(Decubito.LATERAL_DIR)){
						decubitoPassado = decubitoAtual;
						decubitoAtual = Decubito.LATERAL_DIR;
					//}
				}
				else{
					decubitoPassado = decubitoAtual;
					decubitoAtual = Decubito.LATERAL_DIR;
				}
			}
			else{
//				System.out.println("Lateral Esquerdo");
				if (decubitoAtual != null){
					//if (!decubitoAtual.equals(Decubito.LATERAL_ESQ)){
						decubitoPassado = decubitoAtual;
						decubitoAtual = Decubito.LATERAL_ESQ;
					//}
				}
				else{
					decubitoPassado = decubitoAtual;
					decubitoAtual = Decubito.LATERAL_ESQ;
				}
			}
		}
		
	}
	
	public void updateDecubito(float[] vetorPontos){
		float occiptalX = vetorPontos[0];
		float occiptalY = vetorPontos[1];
		
		float ombroDirX = vetorPontos[9];
		float ombroDirY = vetorPontos[10];
		float ombroDirZ = vetorPontos[11];
		float ombroEsqX = vetorPontos[12];
		float ombroEsqY = vetorPontos[13];
		float ombroEsqZ = vetorPontos[14];
		
		defineDecubito(ombroDirZ, ombroEsqZ);
		
		if (decubitoAtual != null && decubitoPassado !=null){
			if (!decubitoAtual.equals(decubitoPassado)){
				endTime = System.nanoTime();
				double difference = (endTime - startTime)/1e6;
				System.out.println("tempo: "+difference);
				update.updateDecubito(decubitoPassado.getNome(), difference);
				startTime = System.nanoTime();
			}
		}
	}
}
