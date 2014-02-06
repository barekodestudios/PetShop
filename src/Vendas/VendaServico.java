/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vendas;

/**
 *
 * @author robson
 */
public class VendaServico {
    private int codigo;
    private int codigocliente;
    private int codigoAnimal;
    private String data;
    private String hora;
    private double total;

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
     * @return the codigocliente
     */
    public int getCodigocliente() {
        return codigocliente;
    }

    /**
     * @param codigocliente the codigocliente to set
     */
    public void setCodigocliente(int codigocliente) {
        this.codigocliente = codigocliente;
    }

    /**
     * @return the codigoAnimal
     */
    public int getCodigoAnimal() {
        return codigoAnimal;
    }

    /**
     * @param codigoAnimal the codigoAnimal to set
     */
    public void setCodigoAnimal(int codigoAnimal) {
        this.codigoAnimal = codigoAnimal;
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * @return the hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }
}
