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
import java.sql.*;
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
            
        }catch (Exception e){
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
}
