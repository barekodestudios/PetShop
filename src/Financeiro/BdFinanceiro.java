/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Financeiro;

/**
 *
 * @author aluno
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
public class BdFinanceiro extends Bd.bd {

    public BdFinanceiro() {
        try{
            conexao();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro na conexão: "+e.getMessage());
        }
    }

    public void insere(financeiro financ){
        String sql ="insert into LF(valor, tipo, es, data, hora, descricao) values (?,?,?,?,?,?)";
        try{
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setDouble(1, financ.getValor());
            ps.setInt(2, financ.getTipo());
            ps.setString(3, financ.getEs());
            ps.setDate(4, new java.sql.Date(financ.getData().getTime().getTime()));
            ps.setString(5, financ.getHora());
            ps.setString(6, financ.getDescricao());
            ps.execute();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao Inserir : " +e.getMessage());
        }
    }
    
    
    public financeiro localiza(Calendar data, String hora){
        String sql = "select codigo from LF where data='"+new java.sql.Date(data.getTime().getTime())+"' and hora='"+hora+"'";
        financeiro registro = new financeiro();
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
    
    public financeiro localizaCodigo(int codigo){
        String sql = "select * from LF where codigo='"+codigo+"'";
        financeiro finc = new financeiro();
        try{
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
               Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate("data"));
                finc.setData(data);
                finc.setCodigo(rs.getInt("codigo"));
                finc.setEs(rs.getString("es"));
                finc.setHora(rs.getString("hora"));
                finc.setTipo(rs.getInt("tipo"));
                finc.setValor(rs.getDouble("valor"));
                finc.setDescricao(rs.getString("descricao"));
                
            }
   
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL: " +e.getMessage());
        }
        return finc;
    }
    
    
    
    public void exclui(int codigo){
        String sql = "delete from LF where codigo=?";
        try {
            Statement st = getCon().createStatement();
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.execute();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir Financeiro:" +e.getMessage());
        }
    }
    
    public ArrayList pesquisaTab(Calendar dti, Calendar dtf, String tipo){
        String sql ="select * from LF where data between '" + new java.sql.Date(dti.getTime().getTime()) +"' AND '" + new java.sql.Date(dtf.getTime().getTime()) + "' and es='"+tipo+"'";
        ArrayList lista = new ArrayList();
        try{
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                financeiro finc = new financeiro();
                Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate("data"));
                finc.setData(data);
                finc.setCodigo(rs.getInt("codigo"));
                finc.setEs(rs.getString("es"));
                finc.setHora(rs.getString("hora"));
                finc.setTipo(rs.getInt("tipo"));
                finc.setValor(rs.getDouble("valor"));
                finc.setDescricao(rs.getString("descricao"));
                lista.add(finc);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao Pesquisar Financeiro:" +e.getMessage());
        }
    return lista;
    } 


}
