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

    public void escribirUsuario(Usuario usuario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            String linea = usuario.getNombre() + "," + usuario.getEstado() + "," + usuario.getIp();
            writer.write(linea);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        usuarios.add(usuario);
        DefaultTableModel modelo = (DefaultTableModel) TableUsers.getModel();
        modelo.addRow(new Object[] { usuario.getNombre(), usuario.getEstado(), usuario.getIp() });
        modelo.fireTableDataChanged();
    }

    public void leerUsuarios(javax.swing.JTable TableUsers) throws IOException {
        this.TableUsers = TableUsers;
        DefaultTableModel modelo = (DefaultTableModel) TableUsers.getModel();
        modelo.setRowCount(0); // Limpiar las filas existentes en la tabla

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String nombre = partes[0];
                    boolean estado = Boolean.parseBoolean(partes[1]);
                    String ip = partes[2];
                    Usuario usuario = new Usuario(nombre, estado, ip);
                    modelo.addRow(new Object[] { usuario.getNombre(), usuario.getEstado(), usuario.getIp() });
                    modelo.fireTableDataChanged();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
