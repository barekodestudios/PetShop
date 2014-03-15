/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vendas.LP;

import javax.swing.JOptionPane;
import java.sql.*;
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
    
    
    
}
