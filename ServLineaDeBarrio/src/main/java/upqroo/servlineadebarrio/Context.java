package upqroo.servlineadebarrio;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class Context {
    private static javax.swing.JTable TableUsers;
    public static List<Usuario> usuarios = new ArrayList<>();
    private String archivo = "Usuarios.txt";
    
    public String usuariosActivos(){
        String request = "";
        for (Usuario item : usuarios) {
            if (item.getEstado()) {
                // Si el usuario está activo, agrega su información a la cadena
                request=request+item.getNombre()+"&UPQROO&";
            }
        }
        return request;
    }
    
    public String buscarUsuario(String nombre){
        for (Usuario item : usuarios) {
            if (item.getNombre().equals(nombre)) {
                return item.getIp();
            }
        }
        return null;
    }

    public boolean activarUsuario(String nombre, String contrasena,String ip) {
        boolean res = false;
        int filaActualizada = -1;
        String ipNueva="";
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            if (usuario.getNombre().equals(nombre) &&
                usuario.getContrasena().equals(contrasena)) {
                
                res=true;
                usuario.setEstado(true);
                usuario.setIp(ip);
                ipNueva = ip;
                filaActualizada = i; // Almacenamos el índice de la fila actualizada
                
            }
        }

        if (filaActualizada != -1) {
            // Actualizar la tabla solo si se ha encontrado y actualizado un usuario
            actualizarFilaTabla(filaActualizada, ipNueva);
        }

        return res;
    }
    
    public void TarsmiteUsuarios(){
        for (Usuario item : usuarios) {
            if(item.getEstado()){
                try{
                    String UsersActivos = "ACTUALIZA&UPQROO&" + usuariosActivos();
                    Socket Enviar = new Socket(item.getIp(),6199);
                    DataOutputStream manda = new DataOutputStream(Enviar.getOutputStream());
                    manda.writeUTF(UsersActivos);
                }catch (IOException e) {e.printStackTrace();}
            }
        }
    }
    
    private void actualizarFilaTabla(int rowIndex, String ip) {
        DefaultTableModel modelo = (DefaultTableModel) TableUsers.getModel();
        modelo.setValueAt(true, rowIndex, 1); // Actualizar el estado en la columna 1
        modelo.setValueAt(ip, rowIndex, 2); // Actualizar la ip en la columna 1
        modelo.fireTableCellUpdated(rowIndex, 1);
    }

    public String escribirUsuario(String nombre, String contrasena) {
        for (Usuario item : usuarios) {
            if (item.getNombre().equals(nombre)) {
                return "Ya existe un usuario con ese nombre";
            }
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            String linea = nombre + "," + contrasena;
            writer.write(linea);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Usuario user = new Usuario(nombre, false, null, contrasena);
        usuarios.add(user);
        DefaultTableModel modelo = (DefaultTableModel) TableUsers.getModel();
        modelo.addRow(new Object[]{nombre, false, null});
        modelo.fireTableDataChanged();
        
        return "Usuario creado";
    }

    public void leerUsuarios(javax.swing.JTable table) throws IOException {
        TableUsers = table;
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setRowCount(0); // Limpiar las filas existentes en la tabla

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length >= 2) {
                    String nombre = partes[0];
                    String contrasena = partes[1];
                    // Estado inicialmente falso y IP nula
                    Usuario usuario = new Usuario(nombre, false, null, contrasena);
                    modelo.addRow(new Object[]{usuario.getNombre(), usuario.getEstado(), usuario.getIp()});
                    modelo.fireTableDataChanged();
                    usuarios.add(usuario);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}