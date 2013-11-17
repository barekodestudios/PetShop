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
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro SQL:" +e.getMessage());
        }
    }
    
}
