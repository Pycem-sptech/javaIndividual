/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.rede.Rede;
import com.github.britooo.looca.api.util.Conversor;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Dados {

    private String redeIpv6;
    private String redeMacAdress;
    private String processadorID;
    private String processadorFabricante;
    private String processadorNome;
    private String processadorCPUFisica;
    private String processadorCPULogica;
    private String memoriaRAM;
    private String memoriaMassaNome;
    private String memoriaMassaTamanho;
    private DatabaseAzure dbAzure = new DatabaseAzure();
    private DatabaseMySQL dbMySQL = new DatabaseMySQL();

    public Dados(Looca looca) {
        // Capturando dados da rede
        //Rede rede = looca.getRede();
        //if (rede.getGrupoDeInterfaces().getInterfaces().size() >= 0) {
        //    this.redeIpv6 = String.format("%s", rede.getGrupoDeInterfaces().getInterfaces().get(0).getEnderecoIpv6().get(0));
        //    this.redeMacAdress = String.format("%s", rede.getGrupoDeInterfaces().getInterfaces().get(0).getEnderecoMac());
        //} else {
        //    this.redeIpv6 = "Não encontrado";
        //    this.redeMacAdress = "Não encontrado";
        //}

        // Capturando dados do processador
        Processador processador = looca.getProcessador();
        this.processadorID = String.format("%s", processador.getId());
        this.processadorFabricante = String.format("%s", processador.getFabricante());
        this.processadorNome = String.format("%s", processador.getNome());
        this.processadorCPUFisica = String.format("%d", processador.getNumeroCpusFisicas());
        this.processadorCPULogica = String.format("%d", processador.getNumeroCpusLogicas());

        // Capturando dados da memória ram
        Memoria memoria = looca.getMemoria();
        this.memoriaRAM = String.format("%s", Conversor.formatarBytes(memoria.getTotal()));

        DiscoGrupo grupoDeDiscos = looca.getGrupoDeDiscos();
        List<Disco> discos = grupoDeDiscos.getDiscos();
        this.memoriaMassaNome = "";
        this.memoriaMassaTamanho = "";
        for (int i = 0; i < discos.size(); i++) {
            memoriaMassaNome = String.format("%s", discos.get(0).getNome());
            memoriaMassaTamanho = String.format("%s", Conversor.formatarBytes(discos.get(0).getTamanho()));
        }
    }

    public void atualizarDados(
            Looca looca,
            Integer cpuAlerta,
            Integer cpuCritico,
            Integer ramAlerta,
            Integer ramCritico,
            Integer hdAlerta,
            Integer hdCritico,
            Integer freqAlerta) {
        Processador processador = looca.getProcessador();
        String usoProcessador = String.format("%.0f", processador.getUso());

        Memoria memoria = looca.getMemoria();
        Double memoriaEmUso = memoria.getEmUso() / 1073741824.0;
        Double memoriaTotal = memoria.getTotal() / 1073741824.0;
        Double porcentagemRam = memoriaEmUso * 100 / memoriaTotal;
        String porcentagemRamFinal = String.format("%.0f", porcentagemRam);

        DiscoGrupo grupoDeDiscos = looca.getGrupoDeDiscos();
        Double memoriaMassaDisponivel = grupoDeDiscos.getVolumes().get(1).getDisponivel() / 1073741824.0;
        Double memoriaMassaTotal = grupoDeDiscos.getVolumes().get(1).getTotal() / 1073741824.0;
        Double porcentagemMemoriaMassa = 100 - memoriaMassaDisponivel * 100 / memoriaMassaTotal;
        String porcentagemMemoriaMassaFinal = String.format("%.0f", porcentagemMemoriaMassa);

        String statusCPU;
        String statusRam;
        String statusHd;
        if (cpuAlerta < processador.getUso()) {
            statusCPU = "Saudavel";
        } else if (cpuCritico > processador.getUso()) {
            statusCPU = "Alerta";
        } else {
            statusCPU = "Critico";
        }

        if (ramAlerta < porcentagemRam) {
            statusRam = "Saudavel";
        } else if (ramCritico > porcentagemRam) {
            statusRam = "Alerta";
        } else {
            statusRam = "Critico";
        }

        if (hdAlerta < porcentagemMemoriaMassa) {
            statusHd = "Saudavel";
        } else if (hdAlerta > porcentagemMemoriaMassa) {
            statusHd = "Alerta";
        } else {
            statusHd = "Critico";
        }

        System.out.println("Status da CPU: " + statusCPU);
        System.out.println("Status da Memória RAM: " + statusRam);
        System.out.println("Status do HD: " + statusHd);
        System.out.println("Uso processador: " + usoProcessador + "%");
        System.out.println("Porcentagem de uso da Memória RAM: " + porcentagemRamFinal + "%");
        System.out.println("Porcentagem de uso da Memória de Massa: " + porcentagemMemoriaMassaFinal + "%");

    }

    public void inserirDados(
            Looca looca,
            Integer cpuAlerta,
            Integer cpuCritico,
            Integer ramAlerta,
            Integer ramCritico,
            Integer hdAlerta,
            Integer hdCritico,
            Integer fkTotem
    ) {
        Processador processador = looca.getProcessador();
        String usoProcessador = String.format("%.0f", processador.getUso());

        Memoria memoria = looca.getMemoria();
        Double memoriaEmUso = memoria.getEmUso() / 1073741824.0;
        Double memoriaTotal = memoria.getTotal() / 1073741824.0;
        Double porcentagemRam = memoriaEmUso * 100 / memoriaTotal;
        String porcentagemRamFinal = String.format("%.0f", porcentagemRam);

        DiscoGrupo grupoDeDiscos = looca.getGrupoDeDiscos();
        Double memoriaMassaDisponivel = grupoDeDiscos.getVolumes().get(1).getDisponivel() / 1073741824.0;
        Double memoriaMassaTotal = grupoDeDiscos.getVolumes().get(1).getTotal() / 1073741824.0;
        Double porcentagemMemoriaMassa = 100 - memoriaMassaDisponivel * 100 / memoriaMassaTotal;
        String porcentagemMemoriaMassaFinal = String.format("%.0f", porcentagemMemoriaMassa);

        String statusCPU;
        String statusRam;
        String statusHd;
        if (cpuAlerta < processador.getUso()) {
            statusCPU = "Saudavel";
        } else if (cpuCritico > processador.getUso()) {
            statusCPU = "Alerta";
        } else {
            statusCPU = "Critico";
        }

        if (ramAlerta < porcentagemRam) {
            statusRam = "Saudavel";
        } else if (ramCritico > porcentagemRam) {
            statusRam = "Alerta";
        } else {
            statusRam = "Critico";
        }

        if (hdAlerta < porcentagemMemoriaMassa) {
            statusHd = "Saudavel";
        } else if (hdCritico > porcentagemMemoriaMassa) {
            statusHd = "Alerta";
        } else {
            statusHd = "Critico";
        }

        System.out.println("Status da CPU: " + statusCPU);
        System.out.println("Status da Memória RAM: " + statusRam);
        System.out.println("Status do HD: " + statusHd);
        System.out.println("Uso processador: " + usoProcessador + "%");
        System.out.println("Porcentagem de uso da Memória RAM: " + porcentagemRamFinal + "%");
        System.out.println("Porcentagem de uso da Memória de Massa: " + porcentagemMemoriaMassaFinal + "%");

        dbAzure.inserirDados(usoProcessador, porcentagemRamFinal, porcentagemMemoriaMassaFinal, statusCPU, statusRam, statusHd, fkTotem);
        dbMySQL.inserirDados(usoProcessador, porcentagemRamFinal, porcentagemMemoriaMassaFinal);

    }

    public void inserirDadosComIntervalo(
            Looca looca,
            Integer cpuAlerta,
            Integer cpuCritico,
            Integer ramAlerta,
            Integer ramCritico,
            Integer hdAlerta,
            Integer hdCritico,
            Integer fkTotem,
            Integer freqAlerta) {
        Integer intervalo = freqAlerta * 1000;
        System.out.println("Coletando dados:");

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                
                inserirDados(looca,
                        cpuAlerta,
                        cpuCritico,
                        ramAlerta,
                        ramCritico,
                        hdAlerta,
                        hdCritico,
                        fkTotem);
                System.out.println("");
            }
        }, 0, intervalo);
    }

    public String getRedeIpv6() {
        return redeIpv6;
    }

    public void setRedeIpv6(String redeIpv6) {
        this.redeIpv6 = redeIpv6;
    }

    public String getRedeMacAdress() {
        return redeMacAdress;
    }

    public void setRedeMacAdress(String redeMacAdress) {
        this.redeMacAdress = redeMacAdress;
    }

    public String getProcessadorID() {
        return processadorID;
    }

    public void setProcessadorID(String processadorID) {
        this.processadorID = processadorID;
    }

    public String getProcessadorFabricante() {
        return processadorFabricante;
    }

    public void setProcessadorFabricante(String processadorFabricante) {
        this.processadorFabricante = processadorFabricante;
    }

    public String getProcessadorNome() {
        return processadorNome;
    }

    public void setProcessadorNome(String processadorNome) {
        this.processadorNome = processadorNome;
    }

    public String getProcessadorCPUFisica() {
        return processadorCPUFisica;
    }

    public void setProcessadorCPUFisica(String processadorCPUFisica) {
        this.processadorCPUFisica = processadorCPUFisica;
    }

    public String getProcessadorCPULogica() {
        return processadorCPULogica;
    }

    public void setProcessadorCPULogica(String processadorCPULogica) {
        this.processadorCPULogica = processadorCPULogica;
    }

    public String getMemoriaRAM() {
        return memoriaRAM;
    }

    public void setMemoriaRAM(String memoriaRAM) {
        this.memoriaRAM = memoriaRAM;
    }

    public String getMemoriaMassaNome() {
        return memoriaMassaNome;
    }

    public void setMemoriaMassaNome(String memoriaMassaNome) {
        this.memoriaMassaNome = memoriaMassaNome;
    }

    public String getMemoriaMassaTamanho() {
        return memoriaMassaTamanho;
    }

    public void setMemoriaMassaTamanho(String memoriaMassaTamanho) {
        this.memoriaMassaTamanho = memoriaMassaTamanho;
    }

}
