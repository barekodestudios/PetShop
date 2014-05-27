/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tela;

import Animais.Animal;
import Animais.BdAnimal;
import Caixa.BdCaixa;
import Caixa.caixa;
import Clientes.BdClientes;
import Clientes.Cliente;
import Financeiro.BdFinanceiro;
import Produtos.BdProdutos;
import Produtos.Produto;
import Util.Caixa.Calculos;
import Vendas.BdVendaProduto;
import Vendas.LP.BdLancProd;
import Vendas.LP.LancProd;
import Vendas.LS.LancServ;
import Vendas.VendaProduto;
import java.awt.Point;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static tela.TelaVendaServicos.tServicos;
/**
 *
 * @author robson
 */
public class TelaVendaProdutos extends javax.swing.JFrame {
    VendaProduto vendap = new VendaProduto();
    BdVendaProduto bdvp = new BdVendaProduto();
    BdClientes bdc = new BdClientes();
    BdAnimal bda = new BdAnimal();
    BdLancProd bdlp = new BdLancProd();
    private boolean novo = true;
    private int codigoCliente = 0;
    private int codigoAnimal = 0;
    private int codigoVenda = 0;
    /**
     * Creates new form TelaVendaProdutos
     */
    public TelaVendaProdutos() {
        initComponents();
        if(novo){
            preencheComboNovoAnimal();
            verificaCaixa();
        }
    }
    
    private void verificaCaixa(){
        BdCaixa bdc = new BdCaixa();
        if(!bdc.localizaVenda(getDate(), true)){
            JOptionPane.showMessageDialog(this, "O caixa ainda não foi aberto hoje!");
            this.dispose();
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
    
    /*private void preencheComboCliente(String nome){
        ArrayList c = bdc.pesquisa("");
        for (Iterator it = c.iterator(); it.hasNext();){
            Clientes.Cliente a = (Cliente) it.next();
            comboCliente.addItem(a.getNome());
        }
        comboCliente.setSelectedItem(nome);
    }*/
    
    /*private void preencheComboAnimal(String animal){
        ArrayList c = bda.pesquisaPorCliente(codigoCliente);
        for(Iterator it = c.iterator(); it.hasNext();){
            Animais.Animal a = (Animal) it.next();
            comboAnimal.addItem(a.getNome());
        }
        comboCliente.setSelectedItem(animal);
    }*/
  
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
    
    /*private void vendaProdutoToTela(){
        codigoAnimal = vendap.getCodigoAnimal();
        Animal animal = bda.localiza(codigoAnimal);
        preencheComboAnimal(animal.getNome());
        codigoCliente = vendap.getCodigocliente();
        Cliente cliente = bdc.localizaCodigoNome(codigoCliente);
        preencheComboCliente(cliente.getNome());
        tTotal.setText(Double.toString(vendap.getTotal()));
    }*/
    
    private void preencheTabela(int codigoVenda){
        BdLancProd bdlp = new BdLancProd();
        DefaultTableModel modelo = (DefaultTableModel) tProdutos.getModel();
        int i = modelo.getRowCount();
        while(i-- > 0){
            modelo.removeRow(i);
        }
        ArrayList c = bdlp.listVenda(codigoVenda);
        for(Iterator it = c.iterator(); it.hasNext();){
            LancProd lp = (LancProd) it.next();
            int codigo = lp.getCodigo_produto();
            BdProdutos bdp = new BdProdutos();
            Produtos.Produto prod = bdp.localiza(codigo);
            modelo.addRow(new Object[]{lp.getCodigo_produto(), prod.getDescricao(), lp.getQuantidade(), lp.getPrec_unit(), lp.getPrec_total()});
            //necessita fazer as classes de lançamento de venda de produto e bdlancvendaproduto;
        }
        
    }
    
     private void getTableItens(int codVenda){
        LancProd lp = new LancProd();
        DefaultTableModel modelo = (DefaultTableModel) tServicos.getModel();
        int  i = modelo.getRowCount();
        for(int r = 0; r < i; r++){
            lp.setCodigo_Venda(codVenda);
            lp.setCodigo_produto(Integer.parseInt((String) modelo.getValueAt(r, 0)));
            lp.setQuantidade((Integer) modelo.getValueAt(r, 2));
            lp.setPrec_unit((double) modelo.getValueAt(r, 3));
            lp.setPrec_total((double) modelo.getValueAt(r, 4));
            bdlp.insere(lp);
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
    
    private void insereFinanceiroVenda(){
        ConcluiPagamentoVendaServico t = new ConcluiPagamentoVendaServico();
        t.tValor.setText(tTotal.getText());
        t.tAnimal.setText((String) comboAnimal.getSelectedItem());
        t.tCliente.setText(tCliente.getText());
        t.setHora(getTime());
        t.setData(getDate());
        t.setCodigoVenda(codigoVenda);
        t.setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tProdutos = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        tTotal = new javax.swing.JLabel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descrição", "Quantidate", "Preço Unitario", "Preço Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tProdutos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tProdutos);
        if (tProdutos.getColumnModel().getColumnCount() > 0) {
            tProdutos.getColumnModel().getColumn(0).setResizable(false);
            tProdutos.getColumnModel().getColumn(1).setResizable(false);
            tProdutos.getColumnModel().getColumn(2).setResizable(false);
            tProdutos.getColumnModel().getColumn(3).setResizable(false);
            tProdutos.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel3.setText("Total R$:");

        tTotal.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N

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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DefaultTableModel modelo = (DefaultTableModel) tProdutos.getModel();
        int linha = (Integer) tProdutos.getSelectedRow();
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

        DefaultTableModel modelo = (DefaultTableModel) tProdutos.getModel();
        if(modelo.getRowCount() != 0){

            if(novo){
                telaToVendaProduto();
                bdvp.insere(vendap);
                VendaProduto codVenda = bdvp.localiza(vendap.getData(), vendap.getHora());
                getTableItens(codVenda.getCodigo());
                codigoVenda = codVenda.getCodigo();
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
        VendaProduto vp = bdvp.localizaCodigo(codigoVenda);
        Financeiro.financeiro f = bdf.localizaCodigo(vp.getCodigoFinanceiro());
        //Para excluir venda apenas no mesmo dia, e ainda tem que fazer com que o Caixa(saldo)
        //seja atualizado reduzindo o valor da venda;
        BdCaixa bdcx= new BdCaixa();
        caixa cx = new caixa();
        cx = bdcx.localiza(f.getData(), false, true);
        cx.setSaldo(Calculos.calculo(cx.getSaldo(), f.getValor(), false));
        bdcx.atualiza(cx);
        bdf.exclui(vp.getCodigoFinanceiro());
        ArrayList c = bdlp.listVenda(codigoVenda);
        for(Iterator it = c.iterator(); it.hasNext();){
            LancProd lp = (LancProd) it.next();
            BdProdutos bdp = new BdProdutos();
            bdp.atualizaEstoque(lp.getCodigo_produto(), -lp.getQuantidade());
        }
        bdlp.exclui(codigoVenda);
        bdvp.exclui(codigoVenda);
        this.dispose();
        //em venda de produto tem que adicionar a quantidade no estoque novamente e ai sim excluir o bdlp;
    }//GEN-LAST:event_bExcluiActionPerformed

    private void comboAnimalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboAnimalItemStateChanged
        Animal animal = bda.localizaNomeCodigo((String) comboAnimal.getSelectedItem());
        codigoAnimal = animal.getCodigo();
        codigoCliente = animal.getDono();
        Cliente client = bdc.localizaCodigoNome(animal.getDono());
        tCliente.setText(client.getNome());
    }//GEN-LAST:event_comboAnimalItemStateChanged

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
    public static javax.swing.JTable tProdutos;
    private static javax.swing.JLabel tTotal;
    // End of variables declaration//GEN-END:variables
}
