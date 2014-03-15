/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Util.Estado;


import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.*;

/**
 *
 * @author robson
 */
public class BdEstado extends Bd.bd {

    public BdEstado() {
        try{
            conexao();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro de Conexão : "+e.getMessage());
        }
    }
    
    public ArrayList pesquisa(){
        String sql = "select * from  estados ";
        ArrayList lista = new ArrayList();
        try{
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Estado estado = new Estado();
                estado.setNome(rs.getString("nome"));
                lista.add(estado);
            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro na Pesquisa:" +e.getMessage());
        }
        return lista;
    }
    
    
}
