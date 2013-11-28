/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuarios;

/**
 *
 * @author robson
 */
import java.sql.*;
import javax.swing.JOptionPane;
public class BdUsuarios extends Bd.bd{
    public BdUsuarios(){
        try{
            conexao();
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro: " +e.getMessage());
        }
    }
    public void insere(Usuario usuario){
        String sql = "insert into usuarios(nome, email, tipo, login ,senha) values(?,?,?,?,?)";
        try{
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getTipo());
            ps.setString(4, usuario.getLogin());
            ps.setString(5, usuario.getSenha());
            ps.execute();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL:" +e.getMessage());
        }
    }
    
    public void atualiza(Usuario usuario){
        String sql = "update usuarios set nome=?, email=?, tipo=?, login=?, senha=? where codigo=?";
        try{
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getTipo());
            ps.setString(4, usuario.getLogin());
            ps.setString(5, usuario.getSenha());
            ps.setInt(6, usuario.getCodigo());
            ps.execute();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL:" +e.getMessage());
        }
        
    }
    
    public Usuario localiza(String login, String senha){
        String sql = "select login,senha from usuarios where login='" + login + "' and senha='" + senha + "'";
        Usuarios.Usuario registro = new Usuario();
        try{
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                registro.setLogin(rs.getString("login"));
                registro.setSenha(rs.getString("senha"));
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL:" +e.getMessage());
        }
        
        return registro;
    }
    
    public Usuario localizaMail(String email){
        String sql = "select email,login,senha from usuarios where email = '" + email + "'";
        Usuarios.Usuario registro = new Usuario();
        try{
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                registro.setEmail(rs.getString("email"));
                registro.setLogin(rs.getString("login"));
                registro.setSenha(rs.getString("senha"));
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL:" +e.getMessage());
        }
        
        return registro;
    }
}
