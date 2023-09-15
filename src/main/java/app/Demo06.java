
package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Producto;
import model.Proveedor;

public class Demo06 {
	// Objetivo: Listado de todos los usuarios
	public static void main(String[] args) {
		// llamar a la conexión
			
		EntityManagerFactory fabrica = 
				Persistence.createEntityManagerFactory("jpa_sesion01");
		// crear un manejador de las entidades
		
		EntityManager em = fabrica.createEntityManager();
		
		// select * from tb_usuarios --->
		String jpql = "select p from Producto p";
		List<Producto> lstProducto = em.createQuery(jpql,Producto.class).
						getResultList();
		
		// Imprimir el listado
		for(Producto u : lstProducto) {
			System.out.println("Codigo...:" + u.getId_prod());
			System.out.println("Descripcion...:" + u.getDesc_prod());
			System.out.println("Stock...:" + u.getStk_prod());
			System.out.println("Precio...:" + u.getPre_prod());
			System.out.println("Categoria...:" + u.getObjCategoria().getDescripcion());
			System.out.println("Estado...:" + u.getEst_prod());
			System.out.println("Proveedor...:" + u.getObjProveedor().getNombre_rs());
			System.out.println("--------------------------------------"); 
			
		}
		
		
		
		em.close();
		
		
	}
}
