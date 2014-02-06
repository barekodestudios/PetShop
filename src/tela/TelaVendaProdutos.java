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
import javax.swing.table.DefaultTableModel;
import Vendas.BdVendaProduto;
import Vendas.VendaProduto;
import java.awt.Point;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.swing.JOptionPane;
/**
 *
 * @author robson
 */
public class TelaVendaProdutos extends javax.swing.JFrame {
    VendaProduto vendap = new VendaProduto();
    BdVendaProduto bdvp = new BdVendaProduto();
    BdClientes bdc = new BdClientes();
    BdAnimal bda = new BdAnimal();
    private boolean novo = true;
    private int codigoCliente = 0;
    private int codigoAnimal = 0;
    /**
     * Creates new form TelaVendaProdutos
     */
    public TelaVendaProdutos() {
        initComponents();
        if(novo){
            preencheComboNovoCliente();
            
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
  
    public static void calcSubTotUnit(){
        DefaultTableModel modelo = (DefaultTableModel) tProdutos.getModel();
        int i = modelo.getRowCount();
        for(int r = 0; r < i; r++){
            String val = (String) modelo.getValueAt(r, 3);
            String quant = (String) modelo.getValueAt(r, 2);
            double valor = Double.parseDouble(val);
            int qtde = Integer.parseInt(quant);
            double subTotal = valor*qtde;
            modelo.setValueAt(subTotal, r, 4);
        }
    }
    
    private void telaToVendaProduto(){
        vendap.setCodigoAnimal(codigoAnimal);
        vendap.setCodigocliente(codigoCliente);
        vendap.setTotal(Double.parseDouble(tTotal.getText()));
        vendap.setData(getDate());
        vendap.setHora(getTime());
        
    }
    
    private void vendaProdutoToTela(){
        codigoAnimal = vendap.getCodigoAnimal();
        Animal animal = bda.localiza(codigoAnimal);
        preencheComboAnimal(animal.getNome());
        codigoCliente = vendap.getCodigocliente();
        Cliente cliente = bdc.localizaCodigoNome(codigoCliente);
        preencheComboCliente(cliente.getNome());
        tTotal.setText(Double.toString(vendap.getTotal()));
        
        
        
    }
    
    private void preencheTabela(int codigoVenda){
        DefaultTableModel modelo = (DefaultTableModel) tProdutos.getModel();
        int i = modelo.getRowCount();
        while(i-- > 0){
            modelo.removeRow(i);
        }
        ArrayList c = bdlp.localizaVenda(codigoVenda);
        for(Iterator it = c.iterator(); it.hasNext();){
            //necessita fazer as classes de lançamento de venda de produto e bdlancvendaproduto;
        }
        
    }
    
    public static void contaTotal(){
        DefaultTableModel modelo = (DefaultTableModel) tProdutos.getModel();
        int i = modelo.getRowCount();
        double total = 0;
        for(int r = 0; r < i; r++) {
            //double valor = 0;
            double valor = (double) modelo.getValueAt(r, 4);
            
            total += valor;
           tTotal.setText( Double.toString(total));    
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        comboCliente = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        comboAnimal = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tProdutos = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        tTotal = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        bConcluiVenda = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Cliente:");

        comboCliente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboClienteItemStateChanged(evt);
            }
        });

        jLabel2.setText("Animal:");

        comboAnimal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboAnimalItemStateChanged(evt);
            }
        });

        tProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descrição", "Quantidade", "Preço Unit.", "Preço Total"
            }
        ));
        jScrollPane1.setViewportView(tProdutos);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel3.setText("Total R$");

        tTotal.setEditable(false);
        tTotal.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        tTotal.setFocusable(false);

        jButton2.setText("Adicionar Produto");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        bConcluiVenda.setText("Concluir Venda");
        bConcluiVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConcluiVendaActionPerformed(evt);
            }
        });

        jButton1.setText("Retirar Produto");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(bConcluiVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(22, 22, 22))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        AdicionarProduto t = new AdicionarProduto();
        t.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void bConcluiVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConcluiVendaActionPerformed
        telaToVendaProduto();
        if(novo){
            bdvp.insere(vendap);
        }else{
            bdvp.atualiza(vendap);
        }
        this.dispose();
    }//GEN-LAST:event_bConcluiVendaActionPerformed

    private void comboClienteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboClienteItemStateChanged
        preencheComboNovoAnimal();
        Cliente cliente = bdc.localizaNomeCodigo((String) comboCliente.getSelectedItem());
        codigoCliente = cliente.getCodigo();
        comboAnimal.removeAllItems();
        preencheComboNovoAnimal();
    }//GEN-LAST:event_comboClienteItemStateChanged

    private void comboAnimalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboAnimalItemStateChanged
        Animal animal = bda.localizaNomeCodigo((String) comboAnimal.getSelectedItem());
        codigoAnimal = animal.getDono();
    }//GEN-LAST:event_comboAnimalItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DefaultTableModel modelo = (DefaultTableModel) tProdutos.getModel();
        int linha = (Integer) tProdutos.getSelectedRow();
        try{
            modelo.removeRow(linha);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Uma linha deve ser selecionada!");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(TelaVendaProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaVendaProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaVendaProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaVendaProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaVendaProdutos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bConcluiVenda;
    private javax.swing.JComboBox comboAnimal;
    private javax.swing.JComboBox comboCliente;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tProdutos;
    public static javax.swing.JTextField tTotal;
    // End of variables declaration//GEN-END:variables
}
