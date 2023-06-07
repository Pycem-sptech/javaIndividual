/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funcionalidade;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import database.Dados;
import database.DatabaseAzure;
import java.util.Scanner;

/**
 *
 * @author eduardo
 */
public class Main {
    DatabaseAzure dbAzure = new DatabaseAzure();
    
    public static void main(String[] args) {
        Looca looca = new Looca();
        Scanner sc = new Scanner(System.in);
        DatabaseAzure dbAzure = new DatabaseAzure();
        Dados dados = new Dados(looca);

        System.out.println("--------------------------------");
        System.out.println("| Bem Vindo ao Pycem Extractor |");
        System.out.println("--------------------------------");
        String usuario = "";
        String senha = "";
        do {
            System.out.println("Nome de usuário da máquina: ");
            usuario = sc.nextLine();
            System.out.println("Senha:");
            senha = sc.nextLine();
            System.out.println("Iniciando o processo de login...");

            if (!dbAzure.selectLogin(usuario, senha)) {
                System.out.println("Credenciais inválidas, tente novamente");
            }

        } while (!dbAzure.selectLogin(usuario, senha));

        System.out.println("Login Realizado com sucesso!");
        Integer totemId = dbAzure.selectIdTotem(usuario);
        Integer freqAlerta = dbAzure.selectAlerta(usuario).getFreqAlerta();
        Integer cpuAlerta = dbAzure.selectAlerta(usuario).getCpuAlerta();
        Integer cpuCritico = dbAzure.selectAlerta(usuario).getCpuCritico();
        Integer ramAlerta = dbAzure.selectAlerta(usuario).getRamAlerta();
        Integer ramCritico = dbAzure.selectAlerta(usuario).getRamCritico();
        Integer hdAlerta = dbAzure.selectAlerta(usuario).getHdAlerta();
        Integer hdCritico = dbAzure.selectAlerta(usuario).getCpuCritico();
        dbAzure.ligarMaquina(usuario, totemId);

        if (dbAzure.verificarCadastro(usuario)) {
            dados.inserirDadosComIntervalo(looca, cpuAlerta, cpuCritico, ramAlerta, ramCritico, hdAlerta, hdCritico, totemId, freqAlerta);

        } else {
            System.out.println("Realize o cadastro da sua máquina:");
            String respostaPreenchimentoAutomatico;
            do {
                System.out.println("Deseja preenchimento automático? (S/N)");
                respostaPreenchimentoAutomatico = sc.nextLine();
                if (!respostaPreenchimentoAutomatico.equals("S") && !respostaPreenchimentoAutomatico.equals("N")) {
                    System.out.println("Valor incorreto! Insira um valor válido");
                }

            } while (!respostaPreenchimentoAutomatico.equals("S") && !respostaPreenchimentoAutomatico.equals("N"));

            if (respostaPreenchimentoAutomatico.equals("S")) {
                String redeIpv6 = dados.getRedeIpv6();
                String redeMacAdress = dados.getRedeMacAdress();

                System.out.println("Identificação do Processador: ");
                String processadorID = dados.getProcessadorID();
                System.out.println(processadorID);
                System.out.println("Nome do Processador: ");
                String processadorNome = dados.getProcessadorNome();
                System.out.println(processadorNome);
                System.out.println("Número de CPU Físicas: ");
                String processadorCPUFisica = dados.getProcessadorCPUFisica();
                System.out.println(processadorCPUFisica);
                System.out.println("Número de CPUs Lógicas: ");
                String processadorCPULogica = dados.getProcessadorCPULogica();
                System.out.println(processadorCPULogica);
                System.out.println("Quantidade de Memória RAM: ");
                String memoriaRAM = dados.getMemoriaRAM();
                System.out.println(memoriaRAM);
                System.out.println("Armazenamento de Dados: ");
                System.out.println("Nome: ");
                String memoriaMassa = dados.getMemoriaMassaNome();
                System.out.println(memoriaMassa);
                System.out.println("Tamanho: ");
                String memoriaMassaTamanho = dados.getMemoriaMassaTamanho();
                System.out.println(memoriaMassaTamanho);
                System.out.println("Tipo (SDD ou HD): ");
                String memoriaMassaTipo = sc.nextLine();
                System.out.println("Realizando cadastro...");
                dbAzure.atualizarCadastro(usuario, processadorID, processadorNome, memoriaRAM, memoriaMassaTipo, memoriaMassaTamanho, redeIpv6, redeMacAdress);
                System.out.println("Cadastro realizado com sucesso");
                dados.inserirDadosComIntervalo(looca, cpuAlerta, cpuCritico, ramAlerta, ramCritico, hdAlerta, hdCritico, totemId, freqAlerta);
                
            } else if (respostaPreenchimentoAutomatico.equals("n")) {
                //Rede
                String redeIpv6 = dados.getRedeIpv6();
                String redeMacAdress = dados.getRedeMacAdress();

                System.out.println("Identificação do Processador: ");
                String processadorID = sc.nextLine();
                System.out.println("Nome do Processador: ");
                String processadorNome = sc.nextLine();
                System.out.println("Número de CPU Físicas: ");
                String processadorCPUFisica = sc.nextLine();
                System.out.println("Número de CPU Lógicas: ");
                String processadorCPULogica = sc.nextLine();
                System.out.println("Quantidade de Memoria RAM: ");
                String memoriaRAM = sc.nextLine();
                System.out.println("Armazenamento de Dados: ");
                System.out.println("Nome: ");
                String memoriaMassaNome = sc.nextLine();
                System.out.println("Tamanho: ");
                String memoriaMassaTamanho = sc.nextLine();
                System.out.println("Tipo(SDD ou HD): ");
                String memoriaMassaTipo = sc.nextLine();
                System.out.println("Realizando cadastro...");
                dbAzure.atualizarCadastro(usuario, processadorID, processadorNome, memoriaRAM, memoriaMassaTipo, memoriaMassaTamanho, redeIpv6, redeMacAdress);
                System.out.println("Cadastro realizado com sucesso");
                dados.inserirDadosComIntervalo(looca, cpuAlerta, cpuCritico, ramAlerta, ramCritico, hdAlerta, hdCritico, totemId, freqAlerta);
            }
        }
    }
    
    
}
