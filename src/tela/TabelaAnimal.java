/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tela;

import Animais.Animal;
import Animais.BdAnimal;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author robson
 */
public class TabelaAnimal extends javax.swing.JFrame {
    BdAnimal bd = new BdAnimal();
    /**
     * Creates new form TabelaAnimal
     */
    public TabelaAnimal() {
        initComponents();
        preencheTabela();
    }
    
    private void preencheTabela() { 
        DefaultTableModel modelo = (DefaultTableModel) tAnimais.getModel(); 
        int i = modelo.getRowCount(); 
        while (i-- > 0) { 
            modelo.removeRow(i); 
        } 
        ArrayList c = bd.pesquisa(tFiltro.getText()); 
        for (Iterator it = c.iterator(); it.hasNext();) { 
            Animais.Animal a = (Animal) it.next(); 
            modelo.addRow(new Object[]{a.getNome(), a.getRaca(), a.getIdade(), 
            a.getPorte(), a.getNameDono(), a.getCodigo()});
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tFiltro = new javax.swing.JTextField();
        bFiltro = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tAnimais = new javax.swing.JTable();

        setTitle("Cadastros de Animais");

        jLabel1.setText("Pesquisar");

        tFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tFiltroActionPerformed(evt);
            }
        });

        bFiltro.setText("OK");
        bFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bFiltroActionPerformed(evt);
            }
        });

        jScrollPane2.setName("Clientes cadastrados"); // NOI18N
        jScrollPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jScrollPane2MouseReleased(evt);
            }
        });

        tAnimais.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nome", "Raca", "Idade", "Porte", "Dono", "Código"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tAnimais.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tAnimaisMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tAnimais);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addComponent(tFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bFiltro)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bFiltro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tFiltroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tFiltroActionPerformed

    private void bFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bFiltroActionPerformed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_bFiltroActionPerformed

    private void tAnimaisMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tAnimaisMouseReleased
        if(evt.getClickCount() == 2){
            Point p = evt.getPoint();
            int row = tAnimais.rowAtPoint(p);
            int col = tAnimais.columnAtPoint(p);
            DefaultTableModel modelo = (DefaultTableModel) tAnimais.getModel();
            int codigo = (Integer) modelo.getValueAt(row, 5);
            CadastroAnimal t = new CadastroAnimal();
            t.setNovo(false);
            t.setAnimal(bd.localiza(codigo));
            t.setVisible(true);
        }
    }//GEN-LAST:event_tAnimaisMouseReleased

    private void jScrollPane2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MouseReleased

    }//GEN-LAST:event_jScrollPane2MouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TabelaAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TabelaAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TabelaAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TabelaAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TabelaAnimal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bFiltro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tAnimais;
    private javax.swing.JTextField tFiltro;
    // End of variables declaration//GEN-END:variables
}