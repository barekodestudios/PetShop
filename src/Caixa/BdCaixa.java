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
import javax.swing.JOptionPane;
public class BdCaixa extends Bd.bd {

    public BdCaixa() {
        try {
            conexao();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Conectar!" + e.getMessage(), "ERRO DE CONEXÃO!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
