/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Produtos;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.Calendar;
/**
 *
 * @author robson
 */
public class BdProdutos extends Bd.bd {

    public BdProdutos() {
        try{
            conexao();
        }catch(Exception e){
             JOptionPane.showMessageDialog(null, "Erro na conexão: "+e.getMessage());
        } 
    }
    
    public void atualizaEstoque(int codigo, double estoque){
        String sql = "update produtos set estoque+=? and codigo=?";
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setDouble(1, estoque);
            ps.setInt(2, codigo);
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar estoque: \n" + e.getMessage());
        }
        
    }

    
    
    
    public ArrayList pesquisaCodigo(int codigo){
        String sql = "";
        ArrayList lista = new ArrayList();
        try{
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
               Produto prod = new Produto();
                prod.setCodigo(rs.getInt("codigo"));
                prod.setEan(rs.getString("ean"));
                prod.setUn(rs.getString("un"));
                prod.setDescricao(rs.getString("descricao"));
                prod.setPrCusto(rs.getDouble("prCusto"));
                prod.setPrVenda(rs.getDouble("prVenda"));
                prod.setEstmin(rs.getInt("estmin"));
                Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate("datacad"));
                prod.setDatacad(data);
                prod.setLucro(rs.getString("lucro"));
                
                lista.add(prod);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao Pesquisar : "+e.getMessage());
        }
        
        return lista;
    }
    
    public ArrayList pesquisaEan(String ean){
        String sql = "";
        ArrayList lista = new ArrayList();
        try{
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Produto prod = new Produto();
                prod.setCodigo(rs.getInt("codigo"));
                prod.setEan(rs.getString("ean"));
                prod.setUn(rs.getString("un"));
                prod.setDescricao(rs.getString("descricao"));
                prod.setPrCusto(rs.getDouble("prCusto"));
                prod.setPrVenda(rs.getDouble("prVenda"));
                prod.setEstmin(rs.getInt("estmin"));
                Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate("datacad"));
                prod.setDatacad(data);
                prod.setLucro(rs.getString("lucro"));
                
                lista.add(prod);}
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao Pesquisar : "+e.getMessage());
        }
        
        return lista;
    }
    
    public ArrayList pesquisaDescricao(String descricao){
        String sql = "";
        ArrayList lista = new ArrayList();
        try{
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Produto prod = new Produto();
                prod.setCodigo(rs.getInt("codigo"));
                prod.setEan(rs.getString("ean"));
                prod.setUn(rs.getString("un"));
                prod.setDescricao(rs.getString("descricao"));
                prod.setPrCusto(rs.getDouble("prCusto"));
                prod.setPrVenda(rs.getDouble("prVenda"));
                prod.setEstmin(rs.getInt("estmin"));
                Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate("datacad"));
                prod.setDatacad(data);
                prod.setLucro(rs.getString("lucro"));
                
                lista.add(prod);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao Pesquisar : "+e.getMessage());
        }
        
        return lista;
    }
    
    public ArrayList pesquisa(String descricao){
        String sql = "select * from produto where descricao like '%"+ descricao+"%'";
        ArrayList lista = new ArrayList();
        try{
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Produto prod = new Produto();
                prod.setCodigo(rs.getInt("codigo"));
                prod.setEan(rs.getString("ean"));
                prod.setUn(rs.getString("un"));
                prod.setDescricao(rs.getString("descricao"));
                prod.setPrCusto(rs.getDouble("prCusto"));
                prod.setPrVenda(rs.getDouble("prVenda"));
                prod.setEstmin(rs.getInt("estmin"));
                Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate("datacad"));
                prod.setDatacad(data);
                prod.setLucro(rs.getString("lucro"));
                lista.add(prod);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao Pesquisar : "+e.getMessage());
        }
        
        return lista;
    }
    
    public ArrayList pesquisaAll(){
        String sql = "select * from produto";
        ArrayList lista = new ArrayList();
        try{
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Produto prod = new Produto();
                prod.setCodigo(rs.getInt("codigo"));
                prod.setEan(rs.getString("ean"));
                prod.setUn(rs.getString("un"));
                prod.setDescricao(rs.getString("descricao"));
                prod.setPrCusto(rs.getDouble("prCusto"));
                prod.setPrVenda(rs.getDouble("prVenda"));
                prod.setEstmin(rs.getInt("estmin"));
                Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate("datacad"));
                prod.setDatacad(data);
                prod.setLucro(rs.getString("lucro"));
                lista.add(prod);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao Pesquisar : "+e.getMessage());
        }
        
        return lista;
    }
    
    public Produto localiza(int codigo){
        String sql = "select * from produto where codigo='"+ codigo+"'";
        Produto prod = new Produto();
        try{
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                prod.setCodigo(rs.getInt("codigo"));
                prod.setEan(rs.getString("ean"));
                prod.setUn(rs.getString("un"));
                prod.setDescricao(rs.getString("descricao"));
                prod.setPrCusto(rs.getDouble("prCusto"));
                prod.setPrVenda(rs.getDouble("prVenda"));
                prod.setEstmin(rs.getInt("estmin"));
                Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate("datacad"));
                prod.setDatacad(data);
                prod.setLucro(rs.getString("lucro"));
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao localizar : "+e.getMessage());
        }
    return prod;    
    }
    
     public Produto localizaDesc(String descricao){
        String sql = "select * from produto where descricao='"+descricao+"'";
        Produto prod = new Produto();
        try{
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                prod.setCodigo(rs.getInt("codigo"));
                prod.setEan(rs.getString("ean"));
                prod.setUn(rs.getString("un"));
                prod.setDescricao(rs.getString("descricao"));
                prod.setPrCusto(rs.getDouble("prCusto"));
                prod.setPrVenda(rs.getDouble("prVenda"));
                prod.setEstmin(rs.getInt("estmin"));
                Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate("datacad"));
                prod.setDatacad(data);
                prod.setLucro(rs.getString("lucro"));
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao localizar : "+e.getMessage());
        }
    return prod;    
    }
}
   
