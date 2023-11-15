package Componentes;
import upqroo.lineadebarrio.Context;

public class Conversacion extends javax.swing.JPanel {
    Context _context = new Context();
    public Conversacion(String nombre) {
        initComponents();
        nombrevesino.setText(nombre);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        EnviarMensaje = new javax.swing.JButton();
        Mensaje = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        AreaConversacion = new javax.swing.JTextArea();
        nombrevesino = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));
        setPreferredSize(new java.awt.Dimension(831, 517));

        EnviarMensaje.setText("Enviar");
        EnviarMensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnviarMensajeActionPerformed(evt);
            }
        });

        AreaConversacion.setColumns(20);
        AreaConversacion.setRows(5);
        jScrollPane2.setViewportView(AreaConversacion);

        nombrevesino.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        nombrevesino.setForeground(new java.awt.Color(255, 255, 255));
        nombrevesino.setText("Nombre de vesino");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 723, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EnviarMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                    .addComponent(nombrevesino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(nombrevesino)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EnviarMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void EnviarMensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnviarMensajeActionPerformed
        String peticion = "MENSAJE&UPQROO&"+nombrevesino.getText()+"&UPQROO&"+_context.GetUser()+"&UPQROO&"+Mensaje.getText();
        _context.GenerarPeticion(peticion);
        String mensaje = Mensaje.getText();
        AreaConversacion.append(mensaje + "\n");
        Mensaje.setText("");
    }//GEN-LAST:event_EnviarMensajeActionPerformed
    
    public void AgregarMensaje(String MsjRecibido){
        AreaConversacion.append(MsjRecibido + "\n");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea AreaConversacion;
    private javax.swing.JButton EnviarMensaje;
    private javax.swing.JTextField Mensaje;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel nombrevesino;
    // End of variables declaration//GEN-END:variables
}
