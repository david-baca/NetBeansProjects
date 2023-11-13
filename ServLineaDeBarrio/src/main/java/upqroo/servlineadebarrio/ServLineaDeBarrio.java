package upqroo.servlineadebarrio;

import Vistas.Servidor;
import java.io.IOException;

public class ServLineaDeBarrio {
    public static void main(String[] args) throws IOException {
        Servidor ventana = new Servidor();
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        HiloEscucha hilo = new HiloEscucha();
        hilo.start();
    }
    
    
}
