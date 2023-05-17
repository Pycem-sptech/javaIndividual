/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

/**
 *
 * @author Usuário
 */
public class Alerta {
    
    private Integer idAlerta;
    private Integer freqAlerta;
    private Integer cpuAlerta;
    private Integer cpuCritico;
    private Integer ramAlerta;
    private Integer ramCritico;
    private Integer hdAlerta;
    private Integer hdCritico;
    private Integer fkEmpresa;

    public Alerta() {
    }

    public Alerta(Integer idAlerta, Integer freqAlerta, Integer cpuAlerta, Integer cpuCritico, Integer ramAlerta, Integer ramCritico, Integer hdAlerta, Integer hdCritico, Integer fkEmpresa) {
        this.idAlerta = idAlerta;
        this.freqAlerta = freqAlerta;
        this.cpuAlerta = cpuAlerta;
        this.cpuCritico = cpuCritico;
        this.ramAlerta = ramAlerta;
        this.ramCritico = ramCritico;
        this.hdAlerta = hdAlerta;
        this.hdCritico = hdCritico;
        this.fkEmpresa = fkEmpresa;
    }
    
    public Integer getIdAlerta() {
        return idAlerta;
    }

    public void setIdAlerta(Integer idAlerta) {
        this.idAlerta = idAlerta;
    }

    public Integer getFreqAlerta() {
        return freqAlerta;
    }

    public void setFreqAlerta(Integer freqAlerta) {
        this.freqAlerta = freqAlerta;
    }

    public Integer getCpuAlerta() {
        return cpuAlerta;
    }

    public void setCpuAlerta(Integer cpuAlerta) {
        this.cpuAlerta = cpuAlerta;
    }

    public Integer getCpuCritico() {
        return cpuCritico;
    }

    public void setCpuCritico(Integer cpuCritico) {
        this.cpuCritico = cpuCritico;
    }

    public Integer getRamAlerta() {
        return ramAlerta;
    }

    public void setRamAlerta(Integer ramAlerta) {
        this.ramAlerta = ramAlerta;
    }

    public Integer getRamCritico() {
        return ramCritico;
    }

    public void setRamCritico(Integer ramCritico) {
        this.ramCritico = ramCritico;
    }

    public Integer getHdAlerta() {
        return hdAlerta;
    }

    public void setHdAlerta(Integer hdAlerta) {
        this.hdAlerta = hdAlerta;
    }

    public Integer getHdCritico() {
        return hdCritico;
    }

    public void setHdCritico(Integer hdCritico) {
        this.hdCritico = hdCritico;
    }

    public Integer getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(Integer fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }
    
    
}
