/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vendas.LP;

import Vendas.LS.LancServ;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author robson
 */
public class BdLancProd extends Bd.bd{

    public BdLancProd() {
        try{
            conexao();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro na conexão: "+e.getMessage());
        }
    }
    
    public void insere(LancProd lp){
        String sql = "insert into LP(codigo_Venda,codigo_produto,quantidade,prec_unit,prec_total)values(?,?,?,?,?)";
        try{
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setInt(1, lp.getCodigo_Venda());
            ps.setInt(2, lp.getCodigo_produto());
            ps.setInt(3, lp.getQuantidade());
            ps.setDouble(4, lp.getPrec_unit());
            ps.setDouble(5, lp.getPrec_total());
            ps.executeQuery();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao Inserir : " +e.getMessage());
        }
    }
    
    
    public ArrayList listVenda(int codigoVenda){
        String sql = "select * from LS where codigo_venda =" + codigoVenda;
        ArrayList lista = new ArrayList();
        try{
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                LancServ registro = new LancServ();
                registro.setCodigo_servico(rs.getInt("codigo_servico"));
                registro.setPreco(rs.getDouble("preco"));                
                
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL: " +e.getMessage());
        }
        return lista;
    }
    
    public void exclui(int codigo){
        String sql = "delete from LS where codigo_venda=?";
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro SQL: " +e.getMessage());
        }
    }
    
    
}
