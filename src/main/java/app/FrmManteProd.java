package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Categoria;
import model.Producto;
import model.Proveedor;
import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class FrmManteProd extends JFrame {

	private JPanel contentPane;
	
	private JTextArea txtSalida;
	private JTextField txtCódigo;
	JComboBox cboCategorias;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	private JTextField txtPrecio;
	private JComboBox cboProveedores;
	private JLabel lblProveedor;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteProd frame = new FrmManteProd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmManteProd() {
		setTitle("Mantenimiento de Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnNewButton.setBounds(324, 29, 89, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 414, 143);
		contentPane.add(scrollPane);
		
		txtSalida = new JTextArea();
		scrollPane.setViewportView(txtSalida);
		
		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(177, 322, 89, 23);
		contentPane.add(btnListado);
		
		txtCódigo = new JTextField();
		txtCódigo.setBounds(122, 11, 86, 20);
		contentPane.add(txtCódigo);
		txtCódigo.setColumns(10);
		
		JLabel lblCodigo = new JLabel("Id. Producto :");
		lblCodigo.setBounds(10, 14, 102, 14);
		contentPane.add(lblCodigo);
		
		cboCategorias = new JComboBox();
		cboCategorias.setBounds(122, 70, 102, 22);
		contentPane.add(cboCategorias);
		
		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 74, 102, 14);
		contentPane.add(lblCategora);
		
		JLabel lblNomProducto = new JLabel("Nom. Producto :");
		lblNomProducto.setBounds(10, 45, 102, 14);
		contentPane.add(lblNomProducto);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(122, 42, 144, 20);
		contentPane.add(txtDescripcion);
		
		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(10, 115, 102, 14);
		contentPane.add(lblStock);
		
		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(122, 112, 77, 20);
		contentPane.add(txtStock);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(10, 143, 102, 14);
		contentPane.add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(122, 140, 77, 20);
		contentPane.add(txtPrecio);
		
		cboProveedores = new JComboBox();
		cboProveedores.setBounds(280, 138, 130, 22);
		contentPane.add(cboProveedores);
		
		lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBounds(281, 115, 102, 14);
		contentPane.add(lblProveedor);
		
		llenaCombo();
	}

	void llenaCombo() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();
		
		//select * from tb_usuarios -->List
		String jpql = "select c from Categoria c";
		List<Categoria> lstCategorias = em.createQuery(jpql,Categoria.class).
						getResultList();
		
		//Imprimir listado
		cboCategorias.addItem("Seleccione...");
		for (Categoria c : lstCategorias) {
			cboCategorias.addItem(c.getDescripcion());
			
		}
			
		// select * from tb_proveedores -->List
		String jpql2 = "select p from Proveedor p";
		List<Proveedor> lstProveedores = em.createQuery(jpql2,Proveedor.class).
					getResultList();
			
		//Imprimir listado
		cboProveedores.addItem("Seleccione...");
		for (Proveedor p : lstProveedores) {
			cboProveedores.addItem(p.getNombre_rs());
			}
	}
	
	void listado() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();
		
		//select * from tb_usuarios -->List
		String jpql = "select p from Producto p";
		List<Producto> lstProducto = em.createQuery(jpql,Producto.class).
						getResultList();
		
		//Imprimir listado
		for (Producto p : lstProducto) {
			imprimir("Codigo....." + p.getId_prod());
			imprimir("Descripcion....." + p.getDesc_prod());
			imprimir("Codigo....." + p.getObjCategoria().getDescripcion());
			imprimir("Codigo....." + p.getObjProveedor().getNombre_rs());
			imprimir("---------------------------------");
			
		}
		em.close();
	}
	
	void imprimir(String texto) {
		txtSalida.append(texto + "\n");
	}
	
	void registrar() {
		// registro de un nuevo Producto usando valores fijos
			EntityManagerFactory fabrica = 
					Persistence.createEntityManagerFactory("jpa_sesion01");
			// crear un manejador de las entidades
			EntityManager manager = fabrica.createEntityManager();
			
			// objeto a grabar
			Producto pro = new Producto();
			pro.setId_prod(txtCódigo.getText());
			pro.setDesc_prod(txtDescripcion.getText());
			pro.setStk_prod(Integer.parseInt(txtStock.getText()));
			pro.setPre_prod(Double.parseDouble(txtPrecio.getText()));
			pro.setIdcategoria(cboCategorias.getSelectedIndex());
			pro.setIdproveedor(cboProveedores.getSelectedIndex());
			pro.setEst_prod(1);
			// si queremos registrar, actualizar o eliminar -> transa..
			try {
				manager.getTransaction().begin();
				manager.persist(pro);
				manager.getTransaction().commit();
				aviso("Registro OK!!");
			}
			catch (Exception e) {
				aviso("Error: " + e.getCause().getMessage());
			}
			manager.close();
			}

	void aviso(String mensaje) {
		JOptionPane.showMessageDialog(this,  mensaje, "Aviso", JOptionPane.INFORMATION_MESSAGE);
	}
	}
