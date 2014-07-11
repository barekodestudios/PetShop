/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Fornecedores;

/**
 *
 * @author Nill
 */
public class Fornecedor {
    private int codigo;
    private String doc;
    private Pessoa pesTipo;
    private String nome;
    private String ie;
    private String endereco;
    private String numero;
    private String bairro;
    private String cep;
    private String tel1;
    private String tel2;
    private String cel1;
    private String cel2;
    private String obs;

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
     * @return the doc
     */
    public String getDoc() {
        return doc;
    }

    /**
     * @param doc the doc to set
     */
    public void setDoc(String doc) {
        this.doc = doc;
    }

    /**
     * @return the pesTipo
     */
    public Pessoa getPesTipo() {
        return pesTipo;
    }

    /**
     * @param pesTipo the pesTipo to set
     */
    public void setPesTipo(Pessoa pesTipo) {
        this.pesTipo = pesTipo;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the ie
     */
    public String getIe() {
        return ie;
    }

    /**
     * @param ie the ie to set
     */
    public void setIe(String ie) {
        this.ie = ie;
    }

    /**
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro the bairro to set
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * @return the tel1
     */
    public String getTel1() {
        return tel1;
    }

    /**
     * @param tel1 the tel1 to set
     */
    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    /**
     * @return the tel2
     */
    public String getTel2() {
        return tel2;
    }

    /**
     * @param tel2 the tel2 to set
     */
    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    /**
     * @return the cel1
     */
    public String getCel1() {
        return cel1;
    }

    /**
     * @param cel1 the cel1 to set
     */
    public void setCel1(String cel1) {
        this.cel1 = cel1;
    }

    /**
     * @return the cel2
     */
    public String getCel2() {
        return cel2;
    }

    /**
     * @param cel2 the cel2 to set
     */
    public void setCel2(String cel2) {
        this.cel2 = cel2;
    }

    /**
     * @return the obs
     */
    public String getObs() {
        return obs;
    }

    /**
     * @param obs the obs to set
     */
    public void setObs(String obs) {
        this.obs = obs;
    }
    
    
    public enum Pessoa{
        FISICA, JURIDICA
    }
    
    }
