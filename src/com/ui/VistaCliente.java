package com.ui;


import com.socket.Mensaje;
import com.socket.SocketCliente;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import oracle.jrockit.jfr.JFR;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class VistaCliente extends javax.swing.JFrame {

    public SocketCliente cliente;
    public int puerto=0;
    public String serverAddr="";
    public String nombreDeUsuario, password;
    public Thread clientThread;
    public DefaultListModel modelo;//Aca es donde esta el contenido del JList. Lo manejo con este model
    public File file;
    
    public VistaCliente() {
    	getContentPane().setBackground(new Color(240, 255, 240));
        inicializarComponentesDeVentana();
        this.setTitle("Cliente de Chat V3.0");
        modelo.addElement("A TODOS"); //este es el primer elemento, para enviar mensajes a todos.
        jList1.setSelectedIndex(0);
        getContentPane().setLayout(null);
        getContentPane().add(jSeparator2);
        getContentPane().add(jSeparator1);
        getContentPane().add(jLabel4);
        getContentPane().add(jTextField3);
        getContentPane().add(jLabel3);
        getContentPane().add(jPasswordField1);
        getContentPane().add(jButton1);
        getContentPane().add(jButton2);
        getContentPane().add(jButton3);
        getContentPane().add(jTextField4);
        getContentPane().add(jButton4);
        getContentPane().add(jScrollPane1);
        getContentPane().add(jScrollPane2);
        
        JLabel lblClientesConectados = new JLabel("Clientes");
        lblClientesConectados.setBounds(287, 147, 86, 20);
        getContentPane().add(lblClientesConectados);
        
        JLabel lblSalaDeChat = new JLabel("Sala de Chat");
        lblSalaDeChat.setBounds(85, 147, 117, 20);
        getContentPane().add(lblSalaDeChat);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu mnMenuPrincipal = new JMenu("Menu Principal");
        menuBar.add(mnMenuPrincipal);
        
        JMenuItem mntmRegistrarse = new JMenuItem("Configurar IP");
        VistaCliente MiUi=this;
        mntmRegistrarse.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		PuertoIP pip=new PuertoIP(MiUi);
        		pip.setVisible(true);
        	}
        });
        mnMenuPrincipal.add(mntmRegistrarse);
        
        mnAyuda = new JMenu("Ayuda");
        menuBar.add(mnAyuda);
        
        mntmAcerca = new JMenuItem("Random");
        mnAyuda.add(mntmAcerca);
        
        this.addWindowListener(new WindowListener() {

            @Override public void windowOpened(WindowEvent e) {}
            @Override public void windowClosing(WindowEvent e) { 
            	try{
            		cliente.enviarMensaje(new Mensaje("MENSAJE", nombreDeUsuario, ".bye", "SERVER"));
            		clientThread.stop();
            		}catch(Exception ex){} 
            	}
            //esto deberia borrarlo.
            @Override public void windowClosed(WindowEvent e) {}
            @Override public void windowIconified(WindowEvent e) {}
            @Override public void windowDeiconified(WindowEvent e) {}
            @Override public void windowActivated(WindowEvent e) {}
            @Override public void windowDeactivated(WindowEvent e) {}
        });
      
    }
    

    @SuppressWarnings("unchecked")
    private void inicializarComponentesDeVentana() {
        jButton1 = new JButton();
        jButton1.setHorizontalAlignment(SwingConstants.LEFT);
        jButton1.setBackground(new Color(255, 99, 71));
        jButton1.setForeground(new Color(0, 0, 0));
        jButton1.setFont(new Font("Tahoma", Font.BOLD, 17));
        jButton1.setBounds(224, 33, 111, 29);
        jTextField3 = new JTextField();
        jTextField3.setBounds(110, 15, 81, 26);
        jLabel3 = new JLabel();
        jLabel3.setBounds(17, 57, 78, 20);
        jLabel4 = new JLabel();
        jLabel4.setBounds(30, 18, 65, 20);
        jButton3 = new JButton();
        jButton3.setBounds(0, 93, 111, 29);
        jPasswordField1 = new JPasswordField();
        jPasswordField1.setBounds(110, 54, 99, 26);
        jSeparator1 = new JSeparator();
        jSeparator1.setBounds(579, 99, 0, 10);
        jScrollPane1 = new JScrollPane();
        jScrollPane1.setBounds(17, 173, 255, 175);
        jTextArea1 = new JTextArea();
        jScrollPane2 = new JScrollPane();
        jScrollPane2.setBounds(287, 173, 124, 175);
        jList1 = new JList();
        jList1.setFont(new Font("Dialog", Font.BOLD, 16));
        jTextField4 = new JTextField();
        jTextField4.setBounds(19, 364, 253, 26);
        jButton4 = new JButton();
        jButton4.setBounds(101, 406, 86, 29);
        jButton2 = new JButton();
        jButton2.setBounds(126, 93, 111, 29);
        jSeparator2 = new JSeparator();
        jSeparator2.setBounds(579, 450, 0, 10);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Conectar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField3.setText("Usuario");
        jTextField3.setEnabled(false);

        jLabel3.setText("Password :");

        jLabel4.setText("Usuario: ");

        jButton3.setText("Registrarse");
        jButton3.setEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPasswordField1.setText("password");
        jPasswordField1.setEnabled(false);

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new Font("Dialog", Font.PLAIN, 14)); 
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jList1.setModel((modelo = new DefaultListModel()));
        jScrollPane2.setViewportView(jList1);

        jButton4.setText("Enviar");
        jButton4.setEnabled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton2.setText("Login");
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        //pack();
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    	 VistaCliente MiUi=this;
    	if(serverAddr.isEmpty()||puerto<999){
    		PuertoIP pip=new PuertoIP(MiUi);
    		pip.setVisible(true);
    	}
       // serverAddr = jTextField1.getText();
        //puerto = Integer.parseInt(jTextField2.getText());
        
        if(!serverAddr.isEmpty() && puerto>999){
            try{
                cliente = new SocketCliente(this);
                clientThread = new Thread(cliente);
                clientThread.start();
                cliente.enviarMensaje(new Mensaje("TEST", "testUser", "testContent", "SERVER"));
            }
            catch(Exception ex){
                jTextArea1.append("Aplicacion->Yo: Servidor No Encontrado\n");
            }
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        nombreDeUsuario = jTextField3.getText();
        password = jPasswordField1.getText();
        
        if(!nombreDeUsuario.isEmpty() && !password.isEmpty()){
            cliente.enviarMensaje(new Mensaje("LOGIN", nombreDeUsuario, password, "SERVER"));
        }
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        String mensaje = jTextField4.getText();
        String target = jList1.getSelectedValue().toString();
        
        if(!mensaje.isEmpty() && !target.isEmpty()){
            jTextField4.setText("");
            cliente.enviarMensaje(new Mensaje("MENSAJE", nombreDeUsuario, mensaje, target));
        }
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        nombreDeUsuario = jTextField3.getText();
        password = jPasswordField1.getText();
        
        if(!nombreDeUsuario.isEmpty() && !password.isEmpty()){
            cliente.enviarMensaje(new Mensaje("REGISTRARSE", nombreDeUsuario, password, "SERVER"));
        }
    }
    
    public boolean esWin32(){
        return System.getProperty("os.name").startsWith("Windows");
    }


    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch(Exception ex){
            System.out.println("Look & Feel exception");
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaCliente().setVisible(true);
            }
        });
    }
   
    public javax.swing.JButton jButton1;
    public javax.swing.JButton jButton2;
    public javax.swing.JButton jButton3;
    public javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    public javax.swing.JList jList1;
    public javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    public javax.swing.JTextArea jTextArea1;
    public javax.swing.JTextField jTextField3;
    public javax.swing.JTextField jTextField4;
    private JMenu mnAyuda;
    private JMenuItem mntmAcerca;
}
