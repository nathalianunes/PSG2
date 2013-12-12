package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Ponto;


public enum DaoPonto {
	INSTANCE;
	
	public List<Ponto> listPontos() {
		EntityManager em = EMFService.get().createEntityManager();
	    // Read the existing entries
	    Query q = em.createQuery("select m from Ponto m");
	    List<Ponto> pontos = q.getResultList();
	    return pontos;
	}
	
	public boolean add(Ponto ponto) {
		synchronized (this) {
		      EntityManager em = EMFService.get().createEntityManager();
		      em.persist(ponto);
		      em.close();
		      return true;
		    }
	}
	
	public boolean update(String nome, double tempo){
		long id = this.findId(nome);
		if (id == 0)
			return false;
		System.out.println("Tempo: "+tempo+" ID: "+id);
		EntityManager em = EMFService.get().createEntityManager();
		try {
		      Ponto ponto = em.find(Ponto.class, id);
		      ponto.setTempo(tempo);
		      em.merge(ponto);
		    } catch(Exception e){
		    	return false;
		    }finally {
		      em.close();
		    }
		    return true;
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
	
	public Long findId(String nome) {
		EntityManager em = EMFService.get().createEntityManager();
	    Query q = em
	        .createQuery("select t from Ponto t where t.nome = :nome");
	    q.setParameter("nome", nome);
	    List<Ponto> pontos = q.getResultList();
	    if(pontos.size()>0){
	    	return pontos.get(0).getId();
	    }
	    return 0L;
	}
	
	public boolean remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
	    try {
	      Ponto ponto = em.find(Ponto.class, id);
	      em.remove(ponto);
	    } catch(Exception e){
	    	return false;
	    }finally {
	      em.close();
	    }
	    return true;
	}
}
