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
import Clientes.BdClientes;
import java.awt.List;
import java.sql.*;
import java.util.ArrayList;
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
    
    public void excluir(int codigo){
        String sql = "delete from animais where codigo=?";
        try{
            Statement st = getCon().createStatement();
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.execute();            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL:" +e.getMessage());
        }
    }
    
    public ArrayList pesquisa(String busca){
        BdClientes bdc= new BdClientes();
        String sql = "select * from animais where dono like '%" + busca + "%'";
        ArrayList lista = new ArrayList();
        try{
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Animal registro = new Animal();
                registro.setCodigo(rs.getInt("codigo"));
                registro.setNome(rs.getString("nome"));
                registro.setRaca(rs.getString("raca"));
                registro.setIdade(rs.getInt("idade"));
                registro.setPorte(rs.getString("porte"));
                Clientes.Cliente result = bdc.localizaCodigoNome(rs.getInt("dono"));
                registro.setNameDono(result.getNome());
                lista.add(registro);
            }           
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL:" +e.getMessage());
        }
        return lista;
    }
    
    public Animal localiza(int codigo){
        String sql = "select * from animais where codigo ='" + codigo +"'";
        Animal registro = new Animal();
        try{
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                registro.setNome(rs.getString("nome"));
                registro.setRaca(rs.getString("raca"));
                registro.setIdade(rs.getInt("idade"));
                registro.setPorte(rs.getString("porte"));
                registro.setDono(rs.getInt("dono"));
                registro.setCodigo(rs.getInt("codigo"));
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL:" +e.getMessage());
        }
        return registro;
    }
}
