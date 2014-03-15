/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Financeiro;

/**
 *
 * @author aluno
 */
public class TipoLancamento {
    private int codigo;
    private String lancamento;
    private String es;

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
     * @return the lancamento
     */
    public String getLancamento() {
        return lancamento;
    }

    /**
     * @param lancamento the lancamento to set
     */
    public void setLancamento(String lancamento) {
        this.lancamento = lancamento;
    }

    /**
     * @return the es
     */
    public String getEs() {
        return es;
    }

    /**
     * @param es the es to set
     */
    public void setEs(String es) {
        this.es = es;
    }
}
