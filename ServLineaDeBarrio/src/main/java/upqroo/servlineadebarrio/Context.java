package upqroo.servlineadebarrio;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class Context {
    private static javax.swing.JTable TableUsers;
    public static List<Usuario> usuarios = new ArrayList<>();
    private String archivo = "Usuarios.txt";
    
    public String usuariosActivos(){
        String request = null;
        for (Usuario item : usuarios) {
            if (item.getEstado()) {
                // Si el usuario está activo, agrega su información a la cadena
                request=request+item.getNombre()+" ";
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

    public String activarUsuario(String nombre, String contrasena,String ip) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre) &&
                usuario.getContrasena().equals(contrasena)){
                usuario.setEstado(true);
                usuario.setIp(ip);
                
                return usuariosActivos();
            }
        }
        return null;
    }

    public boolean escribirUsuario(String nombre, String contrasena) {
        for (Usuario item : usuarios) {
            if (item.getNombre().equals(nombre)) {
                return false;
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
        
        return true;
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