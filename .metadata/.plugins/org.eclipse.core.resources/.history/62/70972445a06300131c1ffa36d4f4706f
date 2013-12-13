package controller;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.sun.xml.internal.xsom.impl.scd.Iterators.Map;

import dao.DaoDecubito;
import dao.DaoPontosDecubito;
import dao.DaoRestricao;

import model.Decubito;
import model.Ponto;
import model.PontosDecubito;
import model.Restricao;

public class SugereDecubito {
	
	private HashMap<String, List<Ponto>> pontosDecubito = new HashMap<String, List<Ponto>>();
	private List<Restricao> restricoes;
	List<Decubito> decubitos;
	
	public SugereDecubito(){
		List<Ponto> p = DaoPontosDecubito.INSTANCE.findDecubito("decubitoDorsal");
		pontosDecubito.put("decubitoDorsal", p);
		pontosDecubito.put("decubitoVentral", 
				DaoPontosDecubito.INSTANCE.findDecubito("decubitoVentral"));
		pontosDecubito.put("decubitoLateralDir", 
				DaoPontosDecubito.INSTANCE.findDecubito("decubitoLateralDir"));
		pontosDecubito.put("decubitoLateralEsq", 
				DaoPontosDecubito.INSTANCE.findDecubito("decubitoLateralEsq"));
		
		restricoes = DaoRestricao.INSTANCE.listRestricoes();
		decubitos = DaoDecubito.INSTANCE.listDecubitos();
	}
	
	public String sugereDecubito(){
		String resposta = "";
		Collections.sort(decubitos);
		
		for (Decubito decubito : decubitos) {
			if (!possuiTrava(decubito.getNome())){
				resposta = decubito.getNome();
				break;
			}
		}
		
		if(resposta.equals(resposta)){
			resposta = decubitos.get(0).getNome();
		}
		return resposta;
	}
	
	private boolean possuiTrava(String decubito){
		List<Ponto> pontos =  pontosDecubito.get(decubito);
		for (Ponto ponto : pontos) {
			if(restricoes.contains(ponto.getNome())){
				return false;
			}
		}
		return true;
	}
	
}
