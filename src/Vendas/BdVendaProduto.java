/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vendas;

/**
 *
 * @author robson
 */
import java.sql.*;
import javax.swing.JOptionPane;
public class BdVendaProduto extends Bd.bd {
    public BdVendaProduto(){
        try{
            conexao();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro " +e.getMessage());
        }
    }
    
    public void insere(VendaProduto vendap){
        String sql = "insert into LVP (codigo_cliente,codigo_animal, data, hora, total) values (?,?,?,?,?)";
        try{
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setInt(1,vendap.getCodigocliente());
            ps.setInt(2, vendap.getCodigoAnimal());
            ps.setString(3, vendap.getData());
            ps.setString(4, vendap.getHora());
            ps.setDouble(5, vendap.getTotal());
            ps.execute();
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL:" +e.getMessage());
        }
    }
    
    public void atualiza(VendaProduto vendap){
        String sql ="update LVP set codigo_cliente = ?, codigo_animal = ?, total = ? where codigo = ?";
        try{
            PreparedStatement  ps = getCon().prepareStatement(sql);
            ps.setInt(1, vendap.getCodigocliente());
            ps.setInt(2, vendap.getCodigoAnimal());
            ps.setDouble(3, vendap.getTotal());
            ps.execute();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL: " +e.getMessage());
        }
    }
}
