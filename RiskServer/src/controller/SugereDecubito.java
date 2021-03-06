package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.sun.org.apache.xml.internal.security.keys.content.RetrievalMethod;
import com.sun.xml.internal.xsom.impl.scd.Iterators.Map;

import dao.DaoDecubito;
import dao.DaoPontosDecubito;
import dao.DaoRestricao;

import model.Decubito;
import model.Ponto;
import model.PontosDecubito;
import model.Restricao;

public class SugereDecubito {
	
	private HashMap<String, List<String>> pontosDecubito = new HashMap<String, List<String>>();
	private List<Restricao> restricoes;
	private List<String> rest = new ArrayList<String>();
	private List<Decubito> decubitos;
	private DaoDecubito daoDecubito = DaoDecubito.INSTANCE;
	private DaoRestricao daoRest = DaoRestricao.INSTANCE;
	private DaoPontosDecubito daoPontosDecubito = DaoPontosDecubito.INSTANCE;
	
	private HashMap<String, String> resp = new HashMap<String, String>();
	
	public SugereDecubito(){
		resp.put("decubitoDorsal", "Dec�bito Dorsal");
		resp.put("decubitoVentral", "Dec�bito Ventral");
		resp.put("decubitoLateralDir", "Dec�bito Lateral Direito");
		resp.put("decubitoLateralEsq", "Dec�bito Lateral Esquerdo");
		
		pontosDecubito.put("decubitoDorsal", 
				daoPontosDecubito.findDecubito("decubitoDorsal"));
		pontosDecubito.put("decubitoVentral", 
				DaoPontosDecubito.INSTANCE.findDecubito("decubitoVentral"));
		pontosDecubito.put("decubitoLateralDir", 
				DaoPontosDecubito.INSTANCE.findDecubito("decubitoLateralDir"));
		pontosDecubito.put("decubitoLateralEsq", 
				DaoPontosDecubito.INSTANCE.findDecubito("decubitoLateralEsq"));
		
		restricoes = daoRest.listRestricoes();
		for (Restricao restricao : restricoes) {
			rest.add(restricao.getPonto());
		}
		
		decubitos = daoDecubito.listDecubitos();
		
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
		return resp.get(resposta);
	}
	
	private boolean possuiTrava(String decubito){
		if (restricoes.isEmpty())
			return false;
		List<String> pontos =  pontosDecubito.get(decubito);
		for (String ponto : pontos) {
			if(restricoes.contains(ponto)){
				return true;
			}
		}
		return false;
	}
	
}
