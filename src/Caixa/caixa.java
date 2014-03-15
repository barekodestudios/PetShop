/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Caixa;

import java.util.Calendar;

/**
 *
 * @author robson
 */
public class caixa {
    private int codigo;
    private double saldo;
    private Calendar data;
    private boolean aberto;
    
    
    public int getCodigo() {
        return codigo;
    }

    public double getSaldo() {
        return saldo;
    }

    public Calendar getData() {
        return data;
    }

    public boolean isAberto() {
        return aberto;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public void setAberto(boolean aberto) {
        this.aberto = aberto;
    }
    
    
}
