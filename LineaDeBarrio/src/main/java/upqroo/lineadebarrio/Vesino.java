package upqroo.lineadebarrio;

import Componentes.Conversacion;
import Componentes.TableBoton;

public class Vesino {
    private String nombre;
    private TableBoton btn;
    private static Conversacion pageConversacion;
    
    public Vesino(String nombre, TableBoton btn){
        this.nombre = nombre;
        this.btn = btn;
        pageConversacion = new Conversacion(nombre);
    }
    public TableBoton GetBtn(){
        return btn;
    }
    public void PostMensaje(String mensaje){
        pageConversacion.AgregarMensaje(mensaje);
    }
    
    public Conversacion GetPanel(){
        pageConversacion.setSize(831, 517);
        pageConversacion.setLocation(0,0);
        return pageConversacion;
    }
    public String GetNombre(){
        return nombre;
    }
}
