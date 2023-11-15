package upqroo.lineadebarrio;

import Componentes.Conversacion;
import Componentes.TableBoton;

public class Vesino {
    private String nombre;
    private TableBoton btn;
    private Conversacion pageConversacion;
    
    public Vesino(String nombre, TableBoton btn){
        System.out.println("Secreo: "+nombre);
        this.nombre = nombre;
        this.btn = btn;
        pageConversacion = new Conversacion(nombre);
        pageConversacion.setSize(831, 517);
        pageConversacion.setLocation(0,0);
    }
    public TableBoton GetBtn(){
        return btn;
    }
    public void PostMensaje(String mensaje){
        pageConversacion.AgregarMensaje(mensaje);
    }
    
    public Conversacion GetPanel(){
        System.out.println("Se llamo el panel de: "+nombre);        
        return pageConversacion;
    }
    public String GetNombre(){
        return nombre;
    }
}
