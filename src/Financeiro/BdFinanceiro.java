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
        String sql ="insert into LF(valor, tipo, es, data, hora) values (?,?,?,?,?)";
        try{
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setDouble(1, financ.getValor());
            ps.setInt(2, financ.getTipo());
            ps.setString(3, financ.getEs());
            ps.setDate(4, new java.sql.Date(financ.getData().getTime().getTime()));
            ps.setString(5, financ.getHora());
            ps.execute();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao Inserir : " +e.getMessage());
        }
    }
    
    
    public financeiro localiza(Calendar data, String hora){
        String sql = "select codigo from LF where data='"+data+"' and hora='"+hora+"'";
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
    
    public void exclui(int codigo){
        String sql = "delete from LF where codigo='"+codigo+"'";
        try {
            Statement st = getCon().createStatement();
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.execute();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir Financeiro:" +e.getMessage());
        }
    }


}
