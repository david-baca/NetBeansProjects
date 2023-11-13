package Componentes;

import upqroo.lineadebarrio.Context;

public class TableBoton extends javax.swing.JPanel {
    Context _context = new Context();
    private String nombre;
    public TableBoton(String nombre) {
        initComponents();
        this.nombre = nombre;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CargarContenido = new javax.swing.JButton();

        CargarContenido.setText("jButton1");
        CargarContenido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CargarContenidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CargarContenido, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CargarContenido)
        );
    }// </editor-fold>//GEN-END:initComponents
        
    private void CargarContenidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CargarContenidoActionPerformed
        System.out.print("Clik");
    }//GEN-LAST:event_CargarContenidoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CargarContenido;
    // End of variables declaration//GEN-END:variables
}
