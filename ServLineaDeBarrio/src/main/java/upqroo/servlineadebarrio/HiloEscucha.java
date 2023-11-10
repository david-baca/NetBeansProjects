package upqroo.servlineadebarrio;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HiloEscucha extends Thread {
    private Context _context = new Context(); 

    public HiloEscucha() {
    }

    @Override
    public void run() {
          try {
            ServerSocket servidor = new ServerSocket(6198);
            while (true) {
                String respuesta = null;
                String ipcliente;
                Socket conecta = servidor.accept();
                
                InetAddress dir;
                dir = conecta.getInetAddress();
                ipcliente = dir.getHostAddress(); 
                DataInputStream recibe = new DataInputStream(conecta.getInputStream());
                String solicitud;
                solicitud=recibe.readUTF();
                //hasta aqui ya tenemos la solicitud en texto consecuentemente evaluamos
                String[] partes = solicitud.split("&");

                if (partes.length > 0) {
                    String comando = partes[0];

                    if (comando.equals("REGISTRO")) {
                        respuesta= _context.activarUsuario(partes[1],partes[2],ipcliente);
                    }
                    if (comando.equals("INICIO")) {
                         respuesta= Boolean.toString( _context.escribirUsuario(partes[1],partes[2]));
                    }
                    if (comando.equals("MENSAJE")) {
                        //primero detectamos a quien se le dirige el mensaje
                        String ipdestino=_context.buscarUsuario(partes[1]);
                        if(ipdestino==null){
                        respuesta="false";
                        }else{
                            Socket Enviar = new Socket(ipdestino,6200);
                            DataOutputStream manda = new DataOutputStream(Enviar.getOutputStream());
                            manda.writeUTF(partes[2]+" "+partes[3]);
                            Enviar.close();
                            respuesta ="true";
                        }
                    }
                    String ipdestino=_context.buscarUsuario(partes[1]);
                    Socket Enviar = new Socket(ipcliente,6199);
                    DataOutputStream manda = new DataOutputStream(Enviar.getOutputStream());
                    manda.writeUTF(respuesta);
                    Enviar.close();
                }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
