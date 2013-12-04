package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Ponto;
import model.PontosDecubito;

public enum DaoPontosDecubito {
INSTANCE;
	
	public List<PontosDecubito> listAll() {
		EntityManager em = EMFService.get().createEntityManager();
	    // Read the existing entries
	    Query q = em.createQuery("select m from PontosDecubito m");
	    List<PontosDecubito> pontosDecubitos = q.getResultList();
	    return pontosDecubitos;
	}
	
	public List<PontosDecubito> listPontosDecubito(String decubito) {
		EntityManager em = EMFService.get().createEntityManager();
	    // Read the existing entries
	    Query q = em.createQuery("select m from PontosDecubito m where m.decubito = :decubito");
	    List<PontosDecubito> pontosDecubitos = q.getResultList();
	    return pontosDecubitos;
	}
	
	public boolean add(PontosDecubito pontosDecubito) {
		synchronized (this) {
		      EntityManager em = EMFService.get().createEntityManager();
		      em.persist(pontosDecubito);
		      em.close();
		      return true;
		    }
	}
	
	public List<Ponto> findDecubito(String decubito) {
		EntityManager em = EMFService.get().createEntityManager();
	    Query q = em
	        .createQuery("select t from PontosDecubito t where t.decubito = :decubito");
	    q.setParameter("decubito", decubito);
	    List<Ponto> pontos = q.getResultList();
	    return pontos;
	}
	
	public Ponto find(String nome) {
		EntityManager em = EMFService.get().createEntityManager();
	    Query q = em
	        .createQuery("select t from Ponto t where t.nome = :nome");
	    q.setParameter("nome", nome);
	    List<Ponto> pontos = q.getResultList();
	    if(pontos.size()>0){
	    	return pontos.get(0);
	    }
	    return null;
	}
	
	public boolean remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
	    try {
	      PontosDecubito pontosDecubito = em.find(PontosDecubito.class, id);
	      em.remove(pontosDecubito);
	    } catch(Exception e){
	    	return false;
	    }finally {
	      em.close();
	    }
	    return true;
	}
}
