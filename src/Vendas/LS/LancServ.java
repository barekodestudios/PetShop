/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vendas.LS;

/**
 *
 * @author robson
 */
public class LancServ {
    private int codigo;
    private int codigo_venda;
    private int codigo_servico;
    private double preco;

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the codigo_venda
     */
    public int getCodigo_venda() {
        return codigo_venda;
    }

    /**
     * @param codigo_venda the codigo_venda to set
     */
    public void setCodigo_venda(int codigo_venda) {
        this.codigo_venda = codigo_venda;
    }

    /**
     * @return the codigo_servico
     */
    public int getCodigo_servico() {
        return codigo_servico;
    }

    /**
     * @param codigo_servico the codigo_servico to set
     */
    public void setCodigo_servico(int codigo_servico) {
        this.codigo_servico = codigo_servico;
    }

    /**
     * @return the preco
     */
    public double getPreco() {
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }
}
