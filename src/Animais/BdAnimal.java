/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animais;

/**
 *
 * @author robson
 */
import java.sql.*;
import javax.swing.JOptionPane;
public class BdAnimal extends Bd.bd {
    public BdAnimal(){
       try{
           conexao();
           
       }catch (Exception e){
           JOptionPane.showMessageDialog(null, "Erro: " +e.getMessage());
       }
    }
    public void insere(Animal animal){
        String sql = "insert into animais(nome, raca, idade, porte, dono) values(?,?,?,?,?)";
        try{
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setString(1, animal.getNome());
            ps.setString(2, animal.getRaca());
            ps.setInt(3, animal.getIdade());
            ps.setString(4, animal.getPorte());
            ps.setInt(5, animal.getDono());
            ps.execute();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL:" +e.getMessage());
        }
    }
    
    public void atualiza(Animal animal){
        String sql ="update animais set nome=?, raca=?, idade=?, porte=?, dono=? where codigo=?";
        try{
           PreparedStatement ps = getCon().prepareStatement(sql);
           ps.setString(1, animal.getNome());
           ps.setString(2, animal.getRaca());
           ps.setInt(3, animal.getIdade());
           ps.setString(4, animal.getPorte());
           ps.setInt(5, animal.getDono());
           ps.execute();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL:" +e.getMessage());
        }
    }
}
