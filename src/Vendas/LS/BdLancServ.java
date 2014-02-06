/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vendas.LS;

/**
 *
 * @author robson
 */
import Servicos.BdServico;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class BdLancServ extends Bd.bd{
    public BdLancServ(){
        try{
            conexao();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro: " +e.getMessage());
        }
    }
    public void insere(LancServ ls){
        String sql = "insert into LS(codigo_venda, codigo_servico, preco) values (?,?,?)"; 
        try{
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setInt(1, ls.getCodigo_venda());
            ps.setInt(2, ls.getCodigo_servico());
            ps.setDouble(3, ls.getPreco());
            ps.execute();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL:" +e.getMessage());
        }
    }
    
    public void exclui(int codigo){
        String sql = "delete from LS where codigo = ?";
        try{
            Statement st = getCon().createStatement();
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.execute();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL:" +e.getMessage());
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
}
