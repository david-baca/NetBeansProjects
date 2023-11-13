package upqroo.servlineadebarrio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HiloEscucha extends Thread{
        private Context _context = new Context(); 

    public HiloEscucha() {
    }

    @Override
    public void run() {
        while (true) {
            try {
                ServerSocket servidor = new ServerSocket(6198);
                String respuesta = null;
                String ipcliente;
                Socket conecta = servidor.accept();
                
                InetAddress dir;
                dir = conecta.getInetAddress();
                ipcliente = dir.getHostAddress(); 
                DataInputStream recibe = new DataInputStream(conecta.getInputStream());
                String solicitud;
                boolean res=false;
                solicitud=recibe.readUTF();
                
                
                //hasta aqui ya tenemos la solicitud en texto consecuentemente evaluamos
                String[] partes = solicitud.split("&UPQROO&");
                if (partes.length > 0) {
                    String comando = partes[0];

                    if (comando.equals("INISIO")) {
                        res= _context.activarUsuario(partes[1],partes[2],ipcliente);
                        respuesta= "INISIO&UPQROO&"+res;
                    }
                    if (comando.equals("REGISTRAR")) {
                         respuesta= "REGISTRAR&UPQROO&"+(_context.escribirUsuario(partes[1],partes[2]));
                    }
                    if (comando.equals("MENSAJE")) {
                        //primero detectamos a quien se le dirige el mensaje
                        ipcliente=_context.buscarUsuario(partes[1]);
                        if(ipcliente==null){
                            ipcliente=dir.getHostAddress();
                            respuesta="MENSAJE&UPQROO&NULL&UPQROO&NULL";
                        }else{
                            respuesta ="MENSAJE&UPQROO&"+partes[2]+"&UPQROO&"+partes[3];
                        }
                    }
                    
                    Socket Enviar = new Socket(ipcliente,6199);
                    DataOutputStream manda = new DataOutputStream(Enviar.getOutputStream());
                    manda.writeUTF(respuesta);
                    System.out.println(respuesta+"Enviado a "+ipcliente+" Exito");
                    Enviar.close();
                    servidor.close();
                    
                    if(res){_context.TarsmiteUsuarios();}
                }
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
