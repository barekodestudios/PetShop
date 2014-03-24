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
import Financeiro.BdFinanceiro;
import Servicos.BdServico;
import Servicos.Servico;
import Vendas.BdVendaServico;
import Vendas.LS.BdLancServ;
import Vendas.LS.LancServ;
import Vendas.VendaServico;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
            preencheComboNovoAnimal();
            
        }
        if(!novo){
            jButton1.setEnabled(false);
            bConcluiVenda.setEnabled(false);
            chkCadastro.setEnabled(false);
            comboAnimal.setEnabled(false);
            tCliente.setEnabled(false);
            jButton2.setEnabled(false);
            preencheTabela(codigoVenda);
            preencheComboAnimal();
        }
    }
    
     private Calendar getDate(){
        Calendar data = Calendar.getInstance();
        return data;
    }
    
     private String getTime(){
         DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
         Date date = new Date();
         return dateFormat.format(date);
     }
    
    
    
    private void preencheComboNovoAnimal(){
        ArrayList c  = bda.pesquisa("");
        for(Iterator it = c.iterator(); it.hasNext();){
            Animais.Animal a = (Animal) it.next();
            comboAnimal.addItem(a.getNome());
        }
    }
    
    private void preencheComboAnimal(){
        Animal al = bda.localiza(codigoAnimal);
        comboAnimal.addItem(al.getNome());
        comboAnimal.setSelectedItem(al.getNome());
    }
    
    
    
    
    
    public static  void contaTotal(){
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
        if(chkCadastro.isSelected() == true){
            vendas.setCodigocliente(codigoCliente);
            vendas.setCodigoAnimal(codigoAnimal);
        } else {
            vendas.setCodigocliente(0);
            vendas.setCodigocliente(0);
        }
        vendas.setData(getDate());
        vendas.setHora(getTime());
        vendas.setTotal(Double.parseDouble(tTotal.getText()));
    }
    
    private void VendaServicoToTela(){
        codigoVenda = vendas.getCodigo();
        codigoCliente  = vendas.getCodigocliente();
        codigoAnimal = vendas.getCodigoAnimal();
        tTotal.setText(Double.toString(vendas.getTotal()));
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
    
    private void insereFinanceiroVenda(){
        ConcluiPagamentoVenda t = new ConcluiPagamentoVenda();
        t.tValor.setText(tTotal.getText());
        t.tAnimal.setText((String) comboAnimal.getSelectedItem());
        t.tCliente.setText(tCliente.getText());
        t.setHora(getTime());
        t.setData(getDate());
        t.setVisible(true);
        t.setCodigoVenda(codigoVenda);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tServicos = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        bConcluiVenda = new javax.swing.JButton();
        bExclui = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        comboAnimal = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        chkCadastro = new javax.swing.JCheckBox();
        tCliente = new javax.swing.JLabel();
        tTotal = new javax.swing.JLabel();

        setTitle("Venda de Serviços");
        setResizable(false);

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

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel3.setText("Total R$:");

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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Do Animal"));

        jLabel2.setText("Animal:");

        comboAnimal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboAnimalItemStateChanged(evt);
            }
        });

        jLabel4.setText("Cliente:");

        chkCadastro.setSelected(true);
        chkCadastro.setText("Animal Cadastrado");
        chkCadastro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkCadastroItemStateChanged(evt);
            }
        });

        tCliente.setForeground(new java.awt.Color(0, 95, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(chkCadastro))
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(chkCadastro)
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(comboAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addContainerGap())
        );

        tTotal.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(bConcluiVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bExclui, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bConcluiVenda, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                            .addComponent(bExclui, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                        .addGap(22, 22, 22))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        setSize(new java.awt.Dimension(659, 606));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void comboAnimalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboAnimalItemStateChanged
        Animal animal = bda.localizaNomeCodigo((String) comboAnimal.getSelectedItem());
        codigoAnimal = animal.getCodigo();
        codigoCliente = animal.getDono();
        Cliente client = bdc.localizaCodigoNome(animal.getDono());
        tCliente.setText(client.getNome());
    }//GEN-LAST:event_comboAnimalItemStateChanged

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
                //bdvs.insere(vendas);
                VendaServico codVenda = bdvs.localiza(vendas.getData(), vendas.getHora());
                //getTableItens(codVenda.getCodigo());
                insereFinanceiroVenda();
                this.dispose();
            /*}else{
                bdvs.atualiza(vendas);
                 this.dispose();*/
            }
        }else if(modelo.getRowCount() == 0 ){
            JOptionPane.showMessageDialog(null, "Não foi adicionado nenhum serviço nesta venda!!" );
        }
       
    }//GEN-LAST:event_bConcluiVendaActionPerformed

    private void bExcluiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bExcluiActionPerformed
        BdFinanceiro bdf = new BdFinanceiro();
        VendaServico vs = bdvs.localizaCodigo(codigoVenda);
        bdf.exclui(vs.getCodigoFinanceiro());
        bdls.exclui(codigoVenda);
       bdvs.exclui(codigoVenda);
       //em venda de produto tem que adicionar a quantidade no estoque novamente e ai sim excluir o bdlp;
    }//GEN-LAST:event_bExcluiActionPerformed

    private void chkCadastroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkCadastroItemStateChanged
        if(chkCadastro.isSelected() == false){
            comboAnimal.setEnabled(false);
            tCliente.setEnabled(false);
        } else {
            comboAnimal.setEnabled(true);
            tCliente.setEnabled(true);
        }
    }//GEN-LAST:event_chkCadastroItemStateChanged

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
    private javax.swing.JCheckBox chkCadastro;
    private javax.swing.JComboBox comboAnimal;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel tCliente;
    public static javax.swing.JTable tServicos;
    private static javax.swing.JLabel tTotal;
    // End of variables declaration//GEN-END:variables
}
