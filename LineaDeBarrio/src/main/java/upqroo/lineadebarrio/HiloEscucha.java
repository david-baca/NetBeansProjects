package upqroo.lineadebarrio;
import Componentes.Conversacion;
import Vistas.Dashboard;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import Componentes.Conversacion;

public class HiloEscucha extends Thread{
    private int puertoEscucha = 6199;
    Context _context = new Context();
    
    public HiloEscucha(){
    }

    public void run(){
        ServerSocket escucha;
        try { 
            escucha = new ServerSocket(puertoEscucha);
            Socket conecta;
            while(true) {
                conecta = escucha.accept();
                DataInputStream recibe = new DataInputStream(conecta.getInputStream());
                String texto;
                texto=recibe.readUTF();
                String[] partes = texto.split("&UPQROO&");
                String respuesta="...";
                if (partes.length > 0) {
                    String comando = partes[0];
                    if (comando.equals("INISIO")) {
                        if(partes[1].equals("true")){
                            _context.CerrarFrame();
                            Dashboard ventana = new Dashboard();
                            ventana.setVisible(true);
                            ventana.setLocationRelativeTo(null);
                        }else{
                            respuesta="Usuario o contrasena incorrectos";
                        }
                    }
                    if (comando.equals("ACTUALIZA")) {
                        for(int i=1;i<partes.length;i++){
                                _context.CargarVesinoActivo(partes[i]);
                        }
                    }
                    if (comando.equals("REGISTRAR")) {respuesta=partes[1];}
                    if (comando.equals("MENSAJE")) {
                        Vesino vesino = _context.getVesino(respuesta=partes[1]);
                        if(vesino != null){
                            Conversacion panel = vesino.GetPanel();
                            panel.AgregarMensaje(partes[2]+": "+partes[3]);                            
                        }
                    }
                }
                
                _context.AgregarRespuesta(respuesta);
                conecta.close();
            }
        } catch (IOException e) {
                // TODO Auto-generated catch block
                _context.AgregarRespuesta(e.getMessage());
        }
    }
}

