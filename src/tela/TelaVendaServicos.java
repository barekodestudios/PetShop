/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tela;

import Animais.Animal;
import Animais.BdAnimal;
import Clientes.BdClientes;
import Clientes.Cliente;
import Servicos.BdServico;
import Vendas.BdVendaServico;
import Vendas.LS.BdLancServ;
import Vendas.LS.LancServ;
import Vendas.VendaServico;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author robson
 */
public class TelaVendaServicos extends javax.swing.JFrame {
    VendaServico vendas = new VendaServico();
    BdVendaServico bdvs = new BdVendaServico();
    BdLancServ bdls = new BdLancServ();
    BdClientes bdc = new BdClientes();
    BdAnimal bda = new BdAnimal();
    private boolean novo = true;
    private int codigoCliente = 0;
    private int codigoAnimal = 0;
    private int codigoVenda = 0;
    
    /**
     * Creates new form TelaVendaServicos
     */
    public TelaVendaServicos() {
        initComponents();
        if(novo){
            preencheComboNovoCliente();
        }
        if(!novo){
            preencheTabela(codigoVenda);
            jButton1.setEnabled(false);
            bConcluiVenda.setEnabled(false);
        }
    }
    
     private String getDate(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }
    
     private String getTime(){
         DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
         Date date = new Date();
         return dateFormat.format(date);
     }
    
    
    private void preencheComboNovoCliente(){
        ArrayList c = bdc.pesquisa(""); 
        for (Iterator it = c.iterator(); it.hasNext();) { 
            Clientes.Cliente a = (Cliente) it.next(); 
            comboCliente.addItem(a.getNome());
        }
    }
    
    private void preencheComboNovoAnimal(){
        ArrayList c = bda.pesquisaPorCliente(codigoCliente);
        for(Iterator it = c.iterator(); it.hasNext();){
            Animais.Animal a = (Animal) it.next();
            comboAnimal.addItem(a.getNome());
        }
    }
    
    private void preencheComboCliente(String nome){
        ArrayList c = bdc.pesquisa("");
        for (Iterator it = c.iterator(); it.hasNext();){
            Clientes.Cliente a = (Cliente) it.next();
            comboCliente.addItem(a.getNome());
        }
        comboCliente.setSelectedItem(nome);
    }
    
    private void preencheComboAnimal(String animal){
        ArrayList c = bda.pesquisaPorCliente(codigoCliente);
        for(Iterator it = c.iterator(); it.hasNext();){
            Animais.Animal a = (Animal) it.next();
            comboAnimal.addItem(a.getNome());
        }
        comboCliente.setSelectedItem(animal);
    }
    
    public static void contaTotal(){
        DefaultTableModel modelo = (DefaultTableModel) tServicos.getModel();
        int i = modelo.getRowCount();
        double total = 0;
        for(int r = 0; r < i; r++) {
            //double valor = 0;
            double valor = (double) modelo.getValueAt(r, 2);
            
            total += valor;
           tTotal.setText(Double.toString(total));    
        }
        if(i == 0){
            tTotal.setText("");
        }
    }
        
    
        
    private void preencheTabela(int codigoVenda){
        DefaultTableModel modelo = (DefaultTableModel) tServicos.getModel();
        int i = modelo.getRowCount();
        while(i-- > 0){
            modelo.removeRow(i);
        }       
        ArrayList c = bdls.listVenda(codigoVenda);
        for(Iterator it = c.iterator(); it.hasNext();){
            LancServ ls = (LancServ) it.next();
            int codigo = ls.getCodigo_servico();
            BdServico bds = new BdServico();
            Servicos.Servico srv = bds.localiza(codigo);
            modelo.addRow(new Object[]{ls.getCodigo_servico(), srv.getNome(), ls.getPreco()});
        }
        
    }
    
    private void telaToVendaServico(){
        vendas.setCodigocliente(codigoCliente);
        vendas.setCodigoAnimal(codigoAnimal);
        vendas.setData(getDate());
        vendas.setHora(getTime());
        vendas.setTotal(Double.parseDouble(tTotal.getText()));
    }
    
    private void getTableItens(int codVenda){
        LancServ ls = new LancServ();
        DefaultTableModel modelo = (DefaultTableModel) tServicos.getModel();
        int  i = modelo.getRowCount();
        for(int r = 0; r < i; r++){
            ls.setCodigo_venda(codVenda);
            ls.setCodigo_servico(Integer.parseInt((String) modelo.getValueAt(r, 0)));
            ls.setPreco((double) modelo.getValueAt(r, 2));
            bdls.insere(ls);
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
        tTotal = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tServicos = new javax.swing.JTable();
        comboAnimal = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        comboCliente = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        bConcluiVenda = new javax.swing.JButton();
        bExclui = new javax.swing.JButton();

        setTitle("Venda de Serviços");
        setResizable(false);

        jLabel1.setText("Cliente:");

        tTotal.setEditable(false);
        tTotal.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        tTotal.setFocusable(false);

        tServicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descrição", "Preço Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tServicos);

        comboAnimal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboAnimalItemStateChanged(evt);
            }
        });

        jLabel2.setText("Animal:");

        comboCliente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboClienteItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel3.setText("Total R$");

        jButton1.setText("Retirar Serviço");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Adicionar Serviço");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        bConcluiVenda.setText("Concluir Serviço");
        bConcluiVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConcluiVendaActionPerformed(evt);
            }
        });

        bExclui.setText("Excluir Serviço");
        bExclui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bExcluiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 167, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(bConcluiVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bExclui, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(comboCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(comboAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bExclui, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bConcluiVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(22, 22, 22))))
        );

        setSize(new java.awt.Dimension(672, 559));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void comboAnimalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboAnimalItemStateChanged
        Animal animal = bda.localizaNomeCodigo((String) comboAnimal.getSelectedItem());
        codigoAnimal = animal.getDono();
    }//GEN-LAST:event_comboAnimalItemStateChanged

    private void comboClienteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboClienteItemStateChanged
        preencheComboNovoAnimal();
        Cliente cliente = bdc.localizaNomeCodigo((String) comboCliente.getSelectedItem());
        codigoCliente = cliente.getCodigo();
        comboAnimal.removeAllItems();
        preencheComboNovoAnimal();
    }//GEN-LAST:event_comboClienteItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DefaultTableModel modelo = (DefaultTableModel) tServicos.getModel();
        int linha = (Integer) tServicos.getSelectedRow();
        try{
            modelo.removeRow(linha);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Uma linha deve ser selecionada!");
        }
        contaTotal();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        AdicionarServico t = new AdicionarServico();
        t.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void bConcluiVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConcluiVendaActionPerformed
 
        DefaultTableModel modelo = (DefaultTableModel) tServicos.getModel();
        if(modelo.getRowCount() != 0){
            telaToVendaServico();
            if(novo){
                bdvs.insere(vendas);
                VendaServico codVenda = bdvs.localiza(vendas.getData(), vendas.getHora());
                getTableItens(codVenda.getCodigo());
            }else{
                bdvs.atualiza(vendas);
            }
        }else if(modelo.getRowCount() == 0 ){
            JOptionPane.showMessageDialog(null, "Não foi adicionado nenhum serviço nesta venda!!" );
        }
        this.dispose();
    }//GEN-LAST:event_bConcluiVendaActionPerformed

    private void bExcluiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bExcluiActionPerformed
        
    }//GEN-LAST:event_bExcluiActionPerformed

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
            java.util.logging.Logger.getLogger(TelaVendaServicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaVendaServicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaVendaServicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaVendaServicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaVendaServicos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bConcluiVenda;
    private javax.swing.JButton bExclui;
    private javax.swing.JComboBox comboAnimal;
    private javax.swing.JComboBox comboCliente;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tServicos;
    public static javax.swing.JTextField tTotal;
    // End of variables declaration//GEN-END:variables
}
