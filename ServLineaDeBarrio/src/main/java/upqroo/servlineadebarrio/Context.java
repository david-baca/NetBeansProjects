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

    public void activarUsuario(Usuario user) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombreUsuario)) {
                usuario.setEstado(true);
                usuario = user; // Asigna la IP deseada
                // Puedes agregar más lógica si es necesario
            }
        }
    }

    public void escribirUsuario(Usuario usuario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            String linea = usuario.getNombre() + "," + usuario.getContrasena();
            writer.write(linea);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        usuarios.add(usuario);
        DefaultTableModel modelo = (DefaultTableModel) TableUsers.getModel();
        modelo.addRow(new Object[]{usuario.getNombre(), usuario.getEstado(), usuario.getIp()});
        modelo.fireTableDataChanged();
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
                    Usuario usuario = new Usuario(nombre, false, null);
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