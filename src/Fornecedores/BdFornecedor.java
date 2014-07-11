/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Fornecedores;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Nill
 */
public class BdFornecedor extends Bd.bd{

    public BdFornecedor() {
        try {
            conexao();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro na conexão MYSQL: \n" + e.getMessage());
        }
    }
    
    public void insere(Fornecedor forn){
        String sql = "";
        try{
            PreparedStatement ps = getCon().prepareStatement(sql);
            
            ps.executeQuery();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar fornecedor: \n" +e.getMessage());
        }
    }
}
