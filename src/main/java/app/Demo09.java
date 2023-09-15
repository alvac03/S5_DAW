
package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import model.Usuario;
import model.Proveedor;

public class Demo09 {
	// Objetivo: Listado de datos de Usuario segun el tipo de usuario(filtro)
	
	public static void main(String[] args) {
		// llamar a la conexión
			
		EntityManagerFactory fabrica = 
				Persistence.createEntityManagerFactory("jpa_sesion01");
		
		// crear un manejador de las entidades
		
		EntityManager manejador = fabrica.createEntityManager();
		
		
		// select * from tb_usuarios where u.idtipo = ?
		String usuario = JOptionPane.showInputDialog("Ingrese Usuario: ");
		String pass = JOptionPane.showInputDialog("Ingrese Contraseña: ");
		
		String jpql = "select u from Usuario u where u.usr_usua= :xtip and u.cla_usua = :clave ";
		try {
		
		Usuario u = manejador.createQuery(jpql,Usuario.class).
				setParameter("xtip", usuario).
				setParameter("clave",pass).
				getSingleResult();
		
		System.out.println("Codigo...:" + u.getCod_usua());
		System.out.println("Nombre...:" + u.getNom_usua() + " " + u.getApe_usua());
		System.out.println("Tipo...:" + u.getIdtipo());
		System.out.println("--------------------------------------"); 
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Usuario o clave incorrecta");
		}
		manejador.close();
		// Imprimir el listado
	
		}
	}

