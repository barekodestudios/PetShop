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
import java.util.Calendar;
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
            ps.setDate(3, new java.sql.Date(vendap.getData().getTime().getTime()));
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
    
    public void exclui(int codigo){
        String sql = "delete from LVP where codigo_venda=?";
        try{
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.execute();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL: " +e.getMessage());
        }
    }
    
     public VendaProduto localizaCodigo(int codigo){
        String sql = "select * from LVP where codigo='"+codigo+"'";
        VendaProduto vp = new VendaProduto();
        try{
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                vp.setCodigo(rs.getInt("codigo"));
                vp.setCodigoAnimal(rs.getInt("codigo_animal"));
                vp.setCodigocliente(rs.getInt("codigo_cliente"));
                Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate("data"));
                vp.setData(data);
                vp.setHora(rs.getString("hora"));
                vp.setTotal(rs.getDouble("total"));
                vp.setCodigoFinanceiro(rs.getInt("codigo_financeiro"));
            }
   
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL: " +e.getMessage());
        }
        return vp;
    }
     
     public VendaProduto localiza(Calendar data, String hora){
        String sql = "select * from LVP where data='"+new java.sql.Date(data.getTime().getTime())+"' and hora='"+hora+"'";
        VendaProduto registro = new VendaProduto();
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
