package upqroo.servlineadebarrio;

public class Usuario {
    private String nombre;
    private boolean estado;
    private String ip;

    public Usuario(String nombre, boolean estado, String ip) {
        this.nombre = nombre;
        this.estado = estado;
        this.ip = ip;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean getEstado() {
        return estado;
    }
    
    public String getIp() {
        return ip;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
