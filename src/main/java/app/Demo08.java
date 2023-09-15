
package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;
import model.Proveedor;

public class Demo08 {
	// Objetivo: Listado de datos de Usuario segun el tipo de usuario(filtro)
	
	public static void main(String[] args) {
		// llamar a la conexión
			
		EntityManagerFactory fabrica = 
				Persistence.createEntityManagerFactory("jpa_sesion01");
		
		// crear un manejador de las entidades
		
		EntityManager em = fabrica.createEntityManager();
		String usa = "admin@ciberfarma.com";
		String pass = "super";
		
		// select * from tb_usuarios where u.idtipo = ?
		String jpql = "select u from Usuario u where u.usr_usua= :xtip and u.cla_usua = :clave ";
		List<Usuario> lstUsuarios = em.createQuery(jpql,Usuario.class).setParameter("xtip", usa).setParameter("clave",pass).getResultList();

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

