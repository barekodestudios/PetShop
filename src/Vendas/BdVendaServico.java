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
public class BdVendaServico extends Bd.bd {
    public BdVendaServico(){
        try{
            conexao();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro " +e.getMessage());
        }
    }
    
    public void insere(VendaServico vendas){
        String sql = "insert into LVS (codigo_cliente,codigo_animal, data, hora, total) values (?,?,?,?,?)";
        try{
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setInt(1,vendas.getCodigocliente());
            ps.setInt(2, vendas.getCodigoAnimal());
            ps.setString(3, vendas.getData());
            ps.setString(4, vendas.getHora());
            ps.setDouble(5, vendas.getTotal());
            ps.execute();
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL:" +e.getMessage());
        }
    }
    
    public void atualiza(VendaServico vendas){
        String sql ="update LVS set codigo_cliente = ?, codigo_animal = ?, total = ? where codigo = ?";
        try{
            PreparedStatement  ps = getCon().prepareStatement(sql);
            ps.setInt(1, vendas.getCodigocliente());
            ps.setInt(2, vendas.getCodigoAnimal());
            ps.setDouble(3, vendas.getTotal());
            ps.execute();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL: " +e.getMessage());
        }
    }
    
    public VendaServico localiza(String data, String hora){
        String sql = "select * from LVS where data ='" + data +"' and hora = '" + hora +"'";
        VendaServico registro = new VendaServico();
        try{
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                registro.setCodigo(rs.getInt("codigo"));
            }
   
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL: " +e.getMessage());
        }
        return registro;
    }
}