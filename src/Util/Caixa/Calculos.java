/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Util.Caixa;

/**
 *
 * @author Pc
 */
public class Calculos {
    public static double calculo(Double saldo, Double valor,boolean operacao){
        if(operacao){
            saldo += valor;
        }else if(!operacao){
            saldo -= valor;
        }
        return saldo;
    }
}
