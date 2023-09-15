
package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo05 {
	// Objetivo: Listado de todos los usuarios
	public static void main(String[] args) {
		// llamar a la conexión
			
		EntityManagerFactory fabrica = 
				Persistence.createEntityManagerFactory("jpa_sesion01");
		// crear un manejador de las entidades
		
		EntityManager em= fabrica.createEntityManager();
		
		// select * from tb_usuarios --->
		String jpql = "select u from Usuario u";
		List<Usuario> lstUsuarios = em.createQuery(jpql,Usuario.class).
						getResultList();
		
		// Imprimir el listado
		for(Usuario u : lstUsuarios) {
			System.out.println("Codigo...:" + u.getCod_usua());
			System.out.println("Nombre...:" + u.getNom_usua() + " " + u.getApe_usua());
			System.out.println("Tipo...:" + u.getIdtipo());
			System.out.println("--------------------------------------"); 
			
		}
		
		
		
		em.close();
		
		
	}
}
