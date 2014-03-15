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
import javax.swing.JOptionPane;
public class BdTipoFinanceiro extends Bd.bd {

    public BdTipoFinanceiro() {
        try{
            conexao();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro de conexão : "+ e.getMessage());
        }
    }
    
    
    public void insere(TipoLancamento tl){
        String sql = "insert into Tipo_Lanc(lancamento,es)values(?,?)";
        try{
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setString(1, tl.getLancamento());
            ps.setString(2, tl.getEs());
            ps.execute();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao Inserir : "+e.getMessage());
        }
    }
    
    public void atualiza(TipoLancamento tl ){
        String sql = "update Tipo_Lanc set lancamento=?, es=? where codigo=?";
        try{
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setString(1, tl.getLancamento());
            ps.setString(2, tl.getEs());
            ps.setInt(3, tl.getCodigo());
            ps.execute();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao Inserir : "+e.getMessage());
        }
    }
    
    public TipoLancamento localiza(String lanc){
        String sql = "SELECT * FROM Tipo_Lanc WHERE lancamento='" + lanc + "'";
        TipoLancamento tl = new TipoLancamento();
        try{
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                tl.setCodigo(rs.getInt("codigo"));
                tl.setLancamento(rs.getString("lancamento"));
                tl.setEs(rs.getString("es"));
                
            }
        }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "Erro ao Localizar : "+e.getMessage());
        }
        return tl;
    }
    
    public TipoLancamento localizaByCodigo(int codigo){
        String sql = "SELECT * FROM Tipo_Lanc WHERE lancamento='" + codigo + "'";
        TipoLancamento tl = new TipoLancamento();
        try{
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                tl.setCodigo(rs.getInt("codigo"));
                tl.setLancamento(rs.getString("lancamento"));
                tl.setEs(rs.getString("es"));
                
            }
        }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "Erro ao Localizar : "+e.getMessage());
        }
        return tl;
    }
    
    public ArrayList pesquisa(String es){
        String sql = "SELECT * FROM Tipo_Lanc WHERE es="+es;
        ArrayList lista = new ArrayList();
        try{
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                TipoLancamento tl = new TipoLancamento();
                tl.setCodigo(rs.getInt("codigo"));
                tl.setLancamento(rs.getString("lancamento"));
                tl.setEs(rs.getString("es"));
                lista.add(tl);
            }
        }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "Erro ao Pesquisar : "+e.getMessage());
        }
        return lista;
    }
    
    public ArrayList pesquisaAll(String lancamento){
        String sql = "SELECT * FROM Tipo_Lanc where lancamento like '%" + lancamento + "%'";
        ArrayList lista = new ArrayList();
        try{
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                TipoLancamento tl = new TipoLancamento();
                tl.setCodigo(rs.getInt("codigo"));
                tl.setLancamento(rs.getString("lancamento"));
                tl.setEs(rs.getString("es"));
                lista.add(tl);
            }
        }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "Erro ao Pesquisar : "+e.getMessage());
        }
        return lista;
    }
    
    
}
