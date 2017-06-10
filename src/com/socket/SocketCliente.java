package com.socket;

import com.ui.VistaCliente;
import java.io.*;
import java.net.*;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * Se encarga de recibir los comandos por medio del pipe de entrada..
 */
public class SocketCliente implements Runnable{
    
    public int puerto;
    public String direccionDelServidor;
    public Socket socket;
    public VistaCliente ui;
    public ObjectInputStream entrada;
    public ObjectOutputStream salida;

    
    public SocketCliente(VistaCliente vistaCliente) throws IOException{
        ui = vistaCliente; this.direccionDelServidor = ui.serverAddr; this.puerto = ui.puerto;
        socket = new Socket(InetAddress.getByName(direccionDelServidor), puerto);
            
        salida = new ObjectOutputStream(socket.getOutputStream());
        //SI no limpio a veces quedaba con basura en los intentos anteriores. usando PrintLn
        salida.flush();
        entrada = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        boolean mantenerCorriendo = true;
        while(mantenerCorriendo){
            try {
            	//aca se bloquea el hilo hasta que llegue info al pipe de entrada
                Mensaje mensaje = (Mensaje) entrada.readObject();
                System.out.println("Entrada: "+mensaje.toString());
                
                if(mensaje.tipo.equals("MENSAJE")){
                    if(mensaje.destinatario.equals(ui.nombreDeUsuario)){
                        ui.jTextArea1.append(mensaje.remitente+" ->Yo]: " + mensaje.contenido + "\n");
                    }
                    else{
                        ui.jTextArea1.append(mensaje.remitente +"->"+mensaje.destinatario +": " + mensaje.contenido + "\n");
                    }
                                            
                }
                //-------------------------------------------
                // Comando Login
                //-------------------------------------------
                else if(mensaje.tipo.equals("LOGIN")){
                    if(mensaje.contenido.equals("TRUE")){
                        ui.jButton2.setEnabled(false);
                        ui.jButton3.setEnabled(false);                        
                        ui.jButton4.setEnabled(true);
                        ui.jTextArea1.append("SERVER->Yo: Login Exitoso\n");
                        ui.jTextField3.setEnabled(false);
                        ui.jPasswordField1.setEnabled(false);
                    }
                    else{
                        ui.jTextArea1.append("SERVER->Yo: Login Fallido\n");
                    }
                }
              //-------------------------------------------
                // Comando Test, usado para probar conexion la privera vez que se comunica cliente con servidor.
                //-------------------------------------------
                else if(mensaje.tipo.equals("TEST")){
                    ui.jButton1.setEnabled(false);
                    ui.jButton2.setEnabled(true);
                    ui.jButton3.setEnabled(true);
                    ui.jTextField3.setEnabled(true);
                    ui.jPasswordField1.setEnabled(true);

                    
                }
              //-------------------------------------------
                // Comando nuevo usuario,ya registrado.
                //-------------------------------------------
                else if(mensaje.tipo.equals("NUEVO_USUARIO")){
                    if(!mensaje.contenido.equals(ui.nombreDeUsuario)){
                        boolean yaEexiste = false;
                        for(int i = 0; i < ui.modelo.getSize(); i++){
                            if(ui.modelo.getElementAt(i).equals(mensaje.contenido)){
                            	yaEexiste = true;
                            	break;
                            }
                        }
                        if(!yaEexiste){ ui.modelo.addElement(mensaje.contenido); }
                    }
                }
                //-------------------------------------------
                // Comando nuevo registro
                //-------------------------------------------
                else if(mensaje.tipo.equals("REGISTRARSE")){
                    if(mensaje.contenido.equals("TRUE")){
                        ui.jButton2.setEnabled(false);
                        ui.jButton3.setEnabled(false);
                        ui.jButton4.setEnabled(true); 
                        ui.jTextArea1.append("SERVER->Yo: Registro Exitoso\n");
                    }
                    else{
                        ui.jTextArea1.append("SERVER->Yo: Registro Fallido\n");
                    }
                }
                //-------------------------------------------
                // Comando salir del sistema
                //-------------------------------------------
                else if(mensaje.tipo.equals("SALIR")){
                    if(mensaje.contenido.equals(ui.nombreDeUsuario)){
                        ui.jTextArea1.append(mensaje.remitente +"->Yo: Saliendo..\n");
                        ui.jButton1.setEnabled(true);
                        ui.jButton4.setEnabled(false); 
                        
                        for(int i = 1; i < ui.modelo.size(); i++){
                            ui.modelo.removeElementAt(i);
                        }
                        
                        ui.clientThread.stop();
                    }
                    else{
                        ui.modelo.removeElement(mensaje.contenido);
                        ui.jTextArea1.append(mensaje.remitente +"->All: "+ mensaje.contenido +" Se Ha desconectado..\n");
                    }
                }

            }
            catch(Exception ex) {
                mantenerCorriendo = false;
                ui.jTextArea1.append("Aplicacion->Yo: Coneccion Falló\n");
                ui.jButton1.setEnabled(true);
                ui.jButton4.setEnabled(false); 
                //Quita el usuario del modelo del JList
                for(int i = 1; i < ui.modelo.size(); i++){
                    ui.modelo.removeElementAt(i);
                }
                
                ui.clientThread.stop();
                
                System.out.println("Error en: SocketClient run()");
                ex.printStackTrace();
            }
        }
    }
    //Finalmente envio el paquete mensaje. Ya no envio lineas de texto con printLn.
    public void enviarMensaje(Mensaje mensaje){
        try {
            salida.writeObject(mensaje);
            salida.flush();
            System.out.println("Saliente: "+mensaje.toString());
        } 
        catch (IOException ex) {
            System.out.println("Error en SocketClient enviarMensaje()");
        }
    }
    
    public void closeThread(Thread t){
        t = null;
    }
}
