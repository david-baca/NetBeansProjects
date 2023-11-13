package upqroo.lineadebarrio;
import Componentes.ButtonEditor;
import Componentes.Conversacion;
import Componentes.RenderTable;
import Componentes.TableBoton;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

public class Context {
    private static String ip = "";
    private int puerto = 6198;
    private String peticion;
    private static String NombreUsuario;
    private static List<Vesino> vesinos = new ArrayList<>();
    private static javax.swing.JLabel respuesta;
    private static JFrame frame;
    private static javax.swing.JMenu Menu;
    private static javax.swing.JPanel conversacion;
    public Context(){}
    //Inicio
    public boolean TryIpServer(String ip){
        try (Socket socket = new Socket()) {
            // Establece un tiempo de espera para la conexión en milisegundos
            socket.connect(new InetSocketAddress(ip, puerto));
            this.ip = ip;
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    //Para REGISTRAR
    public void AgregarLabel(javax.swing.JLabel label){this.respuesta = label;} 
    public void AgregarRespuesta(String res){ this.respuesta.setText(res);}
    //PARA INISIO
    public void AgregarNombreUsuario(String nombre){this.NombreUsuario = nombre;}
    public void AgregarFrame(JFrame frame) {this.frame = frame;}
    public void CerrarFrame(){frame.dispose();}
    //PARA AGREGAR vesinos
    public void AgregarMenuTabla(javax.swing.JMenu menu){this.Menu = menu;}
    public void AgregarConversacionPanel(javax.swing.JPanel conversacion){this.conversacion=conversacion;}
    //PARA MENSAJES
    public String GetUser(){return NombreUsuario;}
    public Vesino getVesino(String nombre){
        for (Vesino vesino : vesinos) {
            if (vesino.GetNombre().equals(nombre)) {
                return vesino; // Devuelve el Vesino si encuentra un nombre coincidente
            }
        }
        return null; // Devuelve null si no se encuentra el nombre
    }
    
    public void CargarVesinoActivo(String nombre){
        Vesino NewVesino = null;
        if(vesinos != null && !vesinos.isEmpty()){//si es diferente de null
            boolean existeEnVesinos = false;
            
            for (Vesino item : vesinos) {
                if (nombre.equals(item.GetNombre())) {
                    existeEnVesinos = true;
                    break;
                }
            }
            
            if (!existeEnVesinos && !nombre.equals(NombreUsuario)) {
                TableBoton btn = new TableBoton(nombre);
                NewVesino = new Vesino(nombre, btn);
            }
        }else{
            if (!nombre.equals(NombreUsuario)) {
                TableBoton btn = new TableBoton(nombre);
                NewVesino = new Vesino(nombre, btn);
            }
        }
        if(NewVesino != null){
            vesinos.add(NewVesino);
            ActualizarMenu();
        }
        
    }
    public void GenerarPeticion(String Peticion){
        this.peticion = Peticion;
        try {
            Socket socket = new Socket(ip, puerto);
            DataOutputStream envia = new DataOutputStream(socket.getOutputStream());
            envia.writeUTF(peticion);
            System.out.println(peticion);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void ActualizarMenu() {
        Menu.removeAll(); // Limpiamos todos los items actuales del menú
        for (Vesino item : vesinos) {
            javax.swing.JMenuItem menuItem = new javax.swing.JMenuItem(item.GetNombre());
            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CargarConversacion(item);
                }
            });
            Menu.add(menuItem);
        }
        // Refrescamos el JMenu
        Menu.revalidate();
        Menu.repaint();
    }
    
    private void CargarConversacion(Vesino vesino){
        conversacion.removeAll();
        Conversacion panel = vesino.GetPanel();
        conversacion.add(panel, BorderLayout.CENTER);
        conversacion.revalidate();
        conversacion.repaint();
    }
    
    private class enviarmensaje implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                    System.out.print("cllkeo");
            }
    }

    
}

