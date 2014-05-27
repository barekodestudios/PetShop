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
import java.util.ArrayList;
import java.util.Calendar;
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
        String sql = "insert into LVS (codigo_cliente,codigo_animal, data, hora, total, codigo_financeiro) values (?,?,?,?,?,?)";
        try{
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setInt(1,vendas.getCodigocliente());
            ps.setInt(2, vendas.getCodigoAnimal());
            ps.setDate(3, new java.sql.Date(vendas.getData().getTime().getTime()));
            ps.setString(4, vendas.getHora());
            ps.setDouble(5, vendas.getTotal());
            ps.setInt(6, 0);
            ps.execute();
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL:" +e.getMessage());
        }
    }
    
    public void atualiza(VendaServico vendas){
        String sql ="update LVS set codigo_cliente = ?, codigo_animal = ?, total = ?, codigo_financeiro=? where codigo = ?";
        try{
            PreparedStatement  ps = getCon().prepareStatement(sql);
            ps.setInt(1, vendas.getCodigocliente());
            ps.setInt(2, vendas.getCodigoAnimal());
            ps.setDouble(3, vendas.getTotal());
            ps.setInt(4, vendas.getCodigoFinanceiro());
            ps.setInt(5, vendas.getCodigo());
            ps.execute();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL: " +e.getMessage());
        }
    }
    
    public void atualizaCodFinc(VendaServico vendas){
        String sql ="update LVS set codigo_financeiro=? where codigo = ?";
        try{
            PreparedStatement  ps = getCon().prepareStatement(sql);
            ps.setInt(1, vendas.getCodigoFinanceiro());
            ps.setInt(2, vendas.getCodigo());
            ps.execute();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL: " +e.getMessage());
        }
    }
    
    public void exclui(int codigoVenda){
        String sql = "delete from LVS where codigo=?";
        try {
            Statement st = getCon().createStatement();
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setInt(1, codigoVenda);
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir venda:" +e.getMessage());
        }
            
    }
    
    public VendaServico localiza(Calendar data, String hora){
        String sql = "select * from LVS where data='"+new java.sql.Date(data.getTime().getTime())+"' and hora='"+hora+"'";
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
    
    public VendaServico localizaCodigo(int codigo){
        String sql = "select * from LVS where codigo='"+codigo+"'";
        VendaServico vs = new VendaServico();
        try{
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                vs.setCodigo(rs.getInt("codigo"));
                vs.setCodigoAnimal(rs.getInt("codigo_animal"));
                vs.setCodigocliente(rs.getInt("codigo_cliente"));
                Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate("data"));
                vs.setData(data);
                vs.setHora(rs.getString("hora"));
                vs.setTotal(rs.getDouble("total"));
                vs.setCodigoFinanceiro(rs.getInt("codigo_financeiro"));
            }
   
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL: " +e.getMessage());
        }
        return vs;
    }
    
    public ArrayList pesquisa(){
        String sql ="select * from LVS";
        ArrayList lista = new ArrayList();
        try{
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                VendaServico vs = new VendaServico();
                vs.setCodigo(rs.getInt("codigo"));
                vs.setCodigoAnimal(rs.getInt("codigo_animal"));
                vs.setCodigocliente(rs.getInt("codigo_cliente"));
                Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate("data"));
                vs.setData(data);
                vs.setHora(rs.getString("hora"));
                vs.setTotal(rs.getDouble("total"));
                lista.add(vs);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL: " +e.getMessage());
        }
        return lista;
    }
}