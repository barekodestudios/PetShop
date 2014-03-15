/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Produtos;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.*;
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

    
    
    
    public ArrayList pesquisaCodigo(int codigo){
        String sql = "";
        ArrayList lista = new ArrayList();
        try{
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Produto p = new Produto();
                lista.add(p);
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
                Produto p = new Produto();
                lista.add(p);
            }
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
                Produto p = new Produto();
                lista.add(p);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao Pesquisar : "+e.getMessage());
        }
        
        return lista;
    }
    
    
}
