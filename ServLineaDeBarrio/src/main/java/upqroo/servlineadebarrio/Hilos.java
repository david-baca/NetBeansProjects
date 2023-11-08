package upqroo.servlineadebarrio;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JTextArea;

public class Hilos {
    private JTextArea valor;
    private Context _context = new Context();
    public Hilos(JTextArea valor) {
            this.valor=valor;
    }
    public void run() {
            try {
                    ServerSocket servidor = new ServerSocket(6198);
            while(true) {
                    Socket conecta = servidor.accept();
                    String ip;
                    InetAddress dir;
                    dir = conecta.getInetAddress();
                    ip = dir.getHostAddress(); 
                    DataInputStream recibe = new DataInputStream(conecta.getInputStream());
                    String texto;
                    texto=recibe.readUTF();
                    valor.append(texto+"\n");
                    Socket conecta2 = new Socket(ip,6199);
                    DataOutputStream manda = new DataOutputStream(conecta2.getOutputStream());
                    manda.writeUTF("dato recibido po servidor");
                    conecta.close();
                    conecta2.close();
            }
            } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
    }
}
