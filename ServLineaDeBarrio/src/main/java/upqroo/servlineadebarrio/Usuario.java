package upqroo.servlineadebarrio;

public class Usuario {
    private String nombre;
    private String contrasena;
    private boolean estado;
    private String ip;

    public Usuario(String nombre, boolean estado, String ip, String contrasena) {
        this.nombre = nombre;
        this.estado = estado;
        this.ip = ip;
    }

    public String getNombre() {
        return nombre;
    }
    
    public String getContrasena() {
        return contrasena;
    }

    public boolean getEstado() {
        return estado;
    }
    
    public String getIp() {
        return ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
