package upqroo.lineadebarrio;

import Componentes.Conversacion;
import Componentes.TableBoton;

public class Vesino {
    private String nombre;
    private TableBoton btn;
    private Conversacion pageConversacion = null;
    
    public Vesino(String nombre, TableBoton btn){
        this.nombre = nombre;
        this.btn = btn;
    }
    public TableBoton GetBtn(){
        return btn;
    }
    public Conversacion GetPanel(){
        pageConversacion = new Conversacion(nombre);
        pageConversacion.setSize(831, 517);
        pageConversacion.setLocation(0,0);
        return pageConversacion;
    }
    public String GetNombre(){
        return nombre;
    }
}
