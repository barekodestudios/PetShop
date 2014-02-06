/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vendas.LP;

/**
 *
 * @author robson
 */
public class LancProd {
    private int codigo;
    private int codigo_Venda;
    private int codigo_produto;
    private int quantidade;
    private double prec_unit;
    private double prec_total;

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
     * @return the codigo_Venda
     */
    public int getCodigo_Venda() {
        return codigo_Venda;
    }

    /**
     * @param codigo_Venda the codigo_Venda to set
     */
    public void setCodigo_Venda(int codigo_Venda) {
        this.codigo_Venda = codigo_Venda;
    }

    /**
     * @return the codigo_produto
     */
    public int getCodigo_produto() {
        return codigo_produto;
    }

    /**
     * @param codigo_produto the codigo_produto to set
     */
    public void setCodigo_produto(int codigo_produto) {
        this.codigo_produto = codigo_produto;
    }

    /**
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the prec_unit
     */
    public double getPrec_unit() {
        return prec_unit;
    }

    /**
     * @param prec_unit the prec_unit to set
     */
    public void setPrec_unit(double prec_unit) {
        this.prec_unit = prec_unit;
    }

    /**
     * @return the prec_total
     */
    public double getPrec_total() {
        return prec_total;
    }

    /**
     * @param prec_total the prec_total to set
     */
    public void setPrec_total(double prec_total) {
        this.prec_total = prec_total;
    }
}
