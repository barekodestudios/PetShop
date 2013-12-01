/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientes;

/**
 *
 * @author robson
 */
import java.awt.List;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class BdClientes extends Bd.bd {
    public BdClientes(){
        try{
            conexao();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro: " +e.getMessage());
        }
    }
    
    public void insere(Cliente cliente){
        String sql = "insert into clientes(nome, cpf, rg, telefone, celular, endereco, bairro, cep, cidade, estado, complemento, datacadastro) values (?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getRg());
            ps.setString(4, cliente.getTelefone());
            ps.setString(5, cliente.getCelular());
            ps.setString(6, cliente.getEndereco());
            ps.setString(7, cliente.getBairro());
            ps.setString(8, cliente.getCep());
            ps.setString(9, cliente.getCidade());
            ps.setString(10, cliente.getEstado());
            ps.setString(11, cliente.getComplemento());
            ps.setString(12, cliente.getDatacadastro());
            ps.execute();
            
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL:" +e.getMessage());
            
        }
    }
    
    public void atualiza(Cliente cliente){
        String sql = "update clientes set nome=?, cpf=?, rg=?, telefone = ?, Celular=?, endereco=?, bairro=?, cep=?, cidade=?, estado=?, complemento=? where codigo=?";
        try{
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getRg());
            ps.setString(4, cliente.getTelefone());
            ps.setString(5, cliente.getCelular());
            ps.setString(6, cliente.getEndereco());
            ps.setString(7, cliente.getBairro());
            ps.setString(8, cliente.getCep());
            ps.setString(9, cliente.getCidade());
            ps.setString(10, cliente.getEstado());
            ps.setString(11, cliente.getComplemento());
            ps.setInt(12, (cliente.getCodigo()));
            ps.execute();
            
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL:" +e.getMessage());
        }    
    }
    
    public void exclui(int codigo){
        String sql = "delete from clientes where codigo=?";
        try{
            Statement st = getCon().createStatement();
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.execute();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL:" +e.getMessage());
        }
    }
    
    public ArrayList pesquisa(String busca){
        String sql = "select * from clientes where nome like  '%" + busca + "%'";
        ArrayList lista = new ArrayList();
        try{
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                Cliente registro = new Cliente();
                registro.setCodigo(rs.getInt("codigo"));
                registro.setNome(rs.getString("nome"));
                registro.setCpf(rs.getString("cpf"));
                registro.setRg(rs.getString("rg"));
                registro.setTelefone(rs.getString("telefone"));
                registro.setCelular(rs.getString("celular"));
                registro.setEndereco(rs.getString("endereco"));
                registro.setBairro(rs.getString("bairro"));
                registro.setCep(rs.getString("cep"));
                registro.setCidade(rs.getString("cidade"));
                registro.setEstado(rs.getString("estado"));
                registro.setComplemento(rs.getString("complemento"));
                registro.setDatacadastro(rs.getString("datacadastro"));
                
                lista.add(registro);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL:" +e.getMessage());
        }
        return lista;
    }
    
    public Cliente localizaCodigoNome(int codigo){
        String sql = "select * from clientes where codigo='" + codigo + "'";
        Cliente registro = new Cliente();
        try{
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                registro.setNome(rs.getString("nome"));
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL:" +e.getMessage());    
        }
        return registro;
    }
    
    public Cliente localizaNomeCodigo(String nome){
        String sql = "select * from clientes where nome='" + nome + "'";
        Cliente registro = new Cliente();
        try{
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                registro.setCodigo(rs.getInt("codigo"));
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL:" +e.getMessage());    
        }
        return registro;
    }
    
     public Cliente localiza(int codigo){
        String sql = "select * from clientes where codigo='" + codigo + "'";
        Cliente registro = new Cliente();
        try{
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                registro.setCodigo(rs.getInt("codigo"));
                registro.setNome(rs.getString("nome"));
                registro.setCpf(rs.getString("cpf"));
                registro.setRg(rs.getString("rg"));
                registro.setTelefone(rs.getString("telefone"));
                registro.setCelular(rs.getString("celular"));
                registro.setEndereco(rs.getString("endereco"));
                registro.setBairro(rs.getString("bairro"));
                registro.setCep(rs.getString("cep"));
                registro.setCidade(rs.getString("cidade"));
                registro.setEstado(rs.getString("estado"));
                registro.setComplemento(rs.getString("complemento"));
                registro.setDatacadastro(rs.getString("datacadastro"));
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro SQL:" +e.getMessage());    
        }
        return registro;
    }
    
    
}
