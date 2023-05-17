/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

/**
 *
 * @author Usuário
 */
public class Totem {
    
    private Integer idTotem;
    private String usuario;
    private String senha;
    private Integer numeroSerie;
    private String processador;
    private String freqProcessador;
    private Integer ram;
    private String tipoArmazenamento;
    private Integer qtdArmazenamento;
    private String ipv4;
    private String macAdress;
    private String estado;
    private Integer fkUnidade;
    
    public Totem(){
    
    }

    public String getProcessador() {
        return processador;
    }
    
    public void setProcessador(String processador) {
        this.processador = processador;
    }
    
    public Integer getIdTotem() {
        return idTotem;
    }

    public void setIdTotem(Integer idTotem) {
        this.idTotem = idTotem;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(Integer numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getFreqProcessador() {
        return freqProcessador;
    }

    public void setFreqProcessador(String freqProcessador) {
        this.freqProcessador = freqProcessador;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public String getTipoArmazenamento() {
        return tipoArmazenamento;
    }

    public void setTipoArmazenamento(String tipoArmazenamento) {
        this.tipoArmazenamento = tipoArmazenamento;
    }

    public Integer getQtdArmazenamento() {
        return qtdArmazenamento;
    }

    public void setQtdArmazenamento(Integer qtdArmazenamento) {
        this.qtdArmazenamento = qtdArmazenamento;
    }

    public String getIpv4() {
        return ipv4;
    }

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }

    public String getMacAdress() {
        return macAdress;
    }

    public void setMacAdress(String macAdress) {
        this.macAdress = macAdress;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getFkUnidade() {
        return fkUnidade;
    }

    public void setFkUnidade(Integer fkUnidade) {
        this.fkUnidade = fkUnidade;
    }

    @Override
    public String toString() {
        return "Totem{" + "idTotem=" + idTotem + ", usuario=" + usuario + ", senha=" + senha + ", numeroSerie=" + numeroSerie + ", freqProcessador=" + freqProcessador + ", ram=" + ram + ", tipoArmazenamento=" + tipoArmazenamento + ", qtdArmazenamento=" + qtdArmazenamento + ", ipv4=" + ipv4 + ", macAdress=" + macAdress + ", estado=" + estado + ", fkUnidade=" + fkUnidade + '}';
    }
    
    
}
