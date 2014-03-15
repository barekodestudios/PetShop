/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servicos;

/**
 *
 * @author robson
 */
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class BdServico extends Bd.bd{
    public BdServico (){
        try{
            conexao();
        } catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro: "  +e.getMessage());
        }
    }
    public void insere(Servico servico){
        String sql = "insert into Servico(nome,preco) values(?,?)";
        try{
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setString(1, servico.getNome());
            ps.setDouble(2, servico.getPreco());
            ps.execute();
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL: " +e.getMessage());
        }
    }
    
    public void atualiza(Servico servico){
        String sql = "update Servico set descricao=?, preco=?,  where codigo =?";
        try{
            PreparedStatement ps  = getCon().prepareStatement(sql);
            ps.setString(1, servico.getNome());
            ps.setDouble(2, servico.getPreco());
            ps.setInt(3, servico.getCodigo());
            ps.execute();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL: " +e.getMessage());
        }
    }
    
    public ArrayList pesquisa(String descricao){
        String sql = "select * from Servico where descricao like'%" + descricao +"%'";
        ArrayList lista = new ArrayList();
        try{
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Servico registro = new Servico();
                registro.setCodigo(rs.getInt("codigo"));
                registro.setNome(rs.getString("descricao"));
                registro.setPreco(rs.getDouble("preco"));
                lista.add(registro);
                
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL: " +e.getMessage());
        }
        return lista;
    }
        
    public Servico localiza(int codigo){
        String sql = "select * from Servico where codigo='" +codigo+"'";
        Servico registro = new Servico();
        try{
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                registro.setCodigo(rs.getInt("codigo"));
                registro.setNome(rs.getString("descricao"));
                registro.setPreco(rs.getDouble("preco"));
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL: " +e.getMessage());
        }
        return registro;
    }
    
    public Servico localizaNome(String nome){
        String sql = "select * from Servico where descricao ='" +nome+"'";
        Servico registro = new Servico();
        try{
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                registro.setCodigo(rs.getInt("Codigo"));
                registro.setNome(rs.getString("descricao"));
                registro.setPreco(rs.getDouble("preco"));
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL: " +e.getMessage());
        }
        return registro;
    }
}
