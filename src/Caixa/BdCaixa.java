/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Caixa;

/**
 *
 * @author robson
 */
import java.sql.*;
import java.util.Calendar;
import javax.swing.JOptionPane;
public class BdCaixa extends Bd.bd {

    public BdCaixa() {
        try {
            conexao();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Conectar!" + e.getMessage(), "ERRO DE CONEXÃO!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void insere(caixa cx){
        String sql = "insert into caixa (saldo,data,aberto)values(?,?,?)";
        try{
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setDouble(1, cx.getSaldo());
            ps.setDate(2,new java.sql.Date(cx.getData().getTime().getTime()));
            ps.setBoolean(3, cx.isAberto());
            ps.execute();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao Abrir Novo Caixa!" + e.getMessage(), "ERRO DE INSERÇÃO!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void atualiza(caixa cx){
        //Verificar se tem que ter o where deata>= ou se pode ser só =
        String sql = "update caixa set saldo=?, aberto=? where data=?";
        try{
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setDouble(1, cx.getSaldo());
            ps.setBoolean(2,cx.isAberto());
            ps.setDate(3, new java.sql.Date(cx.getData().getTime().getTime()));
            ps.execute();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL: " +e.getMessage());
        }
    }
    
    public boolean localizaVenda(Calendar data, boolean aberto){
        String sql = "select * from caixa where data='"+new java.sql.Date(data.getTime().getTime())+"' and aberto='"+aberto+"' order by data desc";
        try{
            Statement st= getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.last()){
                return true;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL: " +e.getMessage());
        }
        return false;
    }
    
    
    public caixa localiza(Calendar data,boolean busca, boolean aberto){
        String sql = "select * from caixa where data='"+new java.sql.Date(data.getTime().getTime())+"' and aberto='"+aberto+"' order by data desc";
        caixa sld = new caixa();
        try{
            Statement st= getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.last()){
                Calendar dt = Calendar.getInstance();
                dt.setTime(rs.getDate("data"));
                sld.setData(dt);
                sld.setAberto(rs.getBoolean("aberto"));
                sld.setSaldo(rs.getDouble("saldo"));
                
            } else if(!busca){
                caixa sd = new caixa();
                Calendar dat;
                dat = Calendar.getInstance();
                dat = localizaUltimaData();
                sld = localiza2(dat);
                sld.setData(data);
                insere(sld);
            }   
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL: " +e.getMessage());
        }
        return sld;
    }
    
    public Calendar localizaUltimaData(){
        String sql = "select data from caixa order By data desc";
        Calendar data = Calendar.getInstance();
        try{
            Statement st= getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.last()){
                data.setTime(rs.getDate("data"));
            }else{
                data = Calendar.getInstance();
            }   
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL: " +e.getMessage());
        }
        return data;
    }
    
    public caixa localiza2(Calendar data){
        String sql = "select * from caixa where data='"+new java.sql.Date(data.getTime().getTime())+"'";
        caixa sld = new caixa();
        try{
            Statement st= getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.last()){
                //sld.getData().setTime(rs.getDate("data"));
                sld.setSaldo(rs.getDouble("saldo"));
            }else{
                
                
            }   
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL: " +e.getMessage());
        }
        return sld;
    }
}
