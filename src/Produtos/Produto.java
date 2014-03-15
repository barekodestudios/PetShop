/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Produtos;

import java.util.Calendar;

/**
 *
 * @author robson
 */
public class Produto {
    private int codigo;
    private String ean;
    private String un;
    private String descricao;
    private double prVenda;
    private double prCusto;
    private int estmin;
    private Calendar datacad;
    private String lucro;

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
     * @return the ean
     */
    public String getEan() {
        return ean;
    }

    /**
     * @param ean the ean to set
     */
    public void setEan(String ean) {
        this.ean = ean;
    }

    /**
     * @return the un
     */
    public String getUn() {
        return un;
    }

    /**
     * @param un the un to set
     */
    public void setUn(String un) {
        this.un = un;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the prVenda
     */
    public double getPrVenda() {
        return prVenda;
    }

    /**
     * @param prVenda the prVenda to set
     */
    public void setPrVenda(double prVenda) {
        this.prVenda = prVenda;
    }

    /**
     * @return the prCusto
     */
    public double getPrCusto() {
        return prCusto;
    }

    /**
     * @param prCusto the prCusto to set
     */
    public void setPrCusto(double prCusto) {
        this.prCusto = prCusto;
    }

    /**
     * @return the estmin
     */
    public int getEstmin() {
        return estmin;
    }

    /**
     * @param estmin the estmin to set
     */
    public void setEstmin(int estmin) {
        this.estmin = estmin;
    }

    /**
     * @return the datacad
     */
    public Calendar getDatacad() {
        return datacad;
    }

    /**
     * @param datacad the datacad to set
     */
    public void setDatacad(Calendar datacad) {
        this.datacad = datacad;
    }

    /**
     * @return the lucro
     */
    public String getLucro() {
        return lucro;
    }

    /**
     * @param lucro the lucro to set
     */
    public void setLucro(String lucro) {
        this.lucro = lucro;
    }
}
