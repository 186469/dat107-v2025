package no.hvl.dat107;

import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class VitnemalDAO {

    private EntityManagerFactory emf;

    public VitnemalDAO() {
        emf = Persistence.createEntityManagerFactory("vitnemalPU");
    }
    
    /* --------------------------------------------------------------------- */

    public Vitnemal hentVitnemalForStudent(int studnr) {
        
        EntityManager em = emf.createEntityManager();
        try {
        	
        	return em.find(Vitnemal.class, studnr);
        	
        } finally {
            em.close();
        }
    }

    /* --------------------------------------------------------------------- */

    public /*TODO*/void hentKarakterForStudentIEmne(int studnr, String emnekode) {
        
        EntityManager em = emf.createEntityManager();
        
        try {
        	
        	String q = "select k from Karakter as k" 
        			+ " where k.vitnemal.studnr = :studnr"
        			+ "and k.emnekode = : emnekode";
        	
        } finally {
            em.close();
        }
    }
    
    /* --------------------------------------------------------------------- */

    public /*TODO*/void registrerKarakterForStudent(/*TODO*/) {
        
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try {
        	tx.begin();
        	
        	/*TODO*/
        	
        	tx.commit();
        	
        } finally {
            em.close();
        }
    }
    
    /* --------------------------------------------------------------------- */

    public List<Karakter> hentKarakterlisteForFerdige(String emnekode) {
        
        EntityManager em = emf.createEntityManager();
        
        try {
        	
        	/* 
        	   	Finne liste av DAT107-karakterer for studenter som er
				ferdig (har sluttdato). Forventer kun denne:
				(1, DAT107, '2022-05-30', 'A', 123456)
				
        	  I SQL kan det se slik ut:
        	  		SELECT k.* 
					FROM karakter AS k 
					NATURAL JOIN vitnemal AS v
					WHERE v.studieslutt IS NOT NULL
					AND k.emnekode LIKE 'DAT107';
        	 */
        	
        	String jpqlQuery = """
        			select k 
        			from Karakter as k, 
        			k.vitnemal as v 
        			where v.studieslutt is not null
        			and k.emnekode like :emnekode""";
        	
			TypedQuery<Karakter> query = em.createQuery(jpqlQuery, Karakter.class);
			query.setParameter("emnekode", emnekode);

			return query.getResultList();
        	
        } finally {
            em.close();
        }
    }
    

}

