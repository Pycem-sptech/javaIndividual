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
        System.out.println("| Bem-vindo ao Pycem Extractor |");
        System.out.println("--------------------------------");
        String usuario = "";
        String senha = "";
        //Login
        do {
            System.out.println("Usuário da máquina: ");
            usuario = sc.nextLine();
            System.out.println("Senha:");
            senha = sc.nextLine();
            System.out.println("Efetuando login...");

            if (!dbAzure.selectLogin(usuario, senha)) {
                System.out.println("Login inválido, tente novamente");
            }

        } while (!dbAzure.selectLogin(usuario, senha));

        System.out.println("Login Realizado com sucesso!");
        Integer idTotem = dbAzure.selectIdTotem(usuario);
        Integer freqAlerta = dbAzure.selectAlerta(usuario).getFreqAlerta();
        Integer cpuAlerta = dbAzure.selectAlerta(usuario).getCpuAlerta();
        Integer cpuCritico = dbAzure.selectAlerta(usuario).getCpuCritico();
        Integer ramAlerta = dbAzure.selectAlerta(usuario).getRamAlerta();
        Integer ramCritico = dbAzure.selectAlerta(usuario).getRamCritico();
        Integer hdAlerta = dbAzure.selectAlerta(usuario).getHdAlerta();
        Integer hdCritico = dbAzure.selectAlerta(usuario).getCpuCritico();
        dbAzure.ligarMaquina(usuario, idTotem);

        // Verificar se é o primeiro cadastro
        if (dbAzure.verificarCadastro(usuario)) {
            // Inserir dados, tanto no local como na azure
            dados.inserirDadosComIntervalo(looca, cpuAlerta, cpuCritico, ramAlerta, ramCritico, hdAlerta, hdCritico, idTotem, freqAlerta);

        } else {
            //Caso seja o primeira vez que entrou
            System.out.println("Faça o cadastro da sua máquina:");
            String respostaPreenchimentoAutomatico;
            do {
                System.out.println("Deseja preenchimento automático? (s/n)");
                respostaPreenchimentoAutomatico = sc.nextLine();
                if (!respostaPreenchimentoAutomatico.equals("s") && !respostaPreenchimentoAutomatico.equals("n")) {
                    System.out.println("Valor errado! Insira um valor correto");
                }

            } while (!respostaPreenchimentoAutomatico.equals("s") && !respostaPreenchimentoAutomatico.equals("n"));

            if (respostaPreenchimentoAutomatico.equals("s")) {
                //Rede
                String redeIpv6 = dados.getRedeIpv6();
                String redeMacAdress = dados.getRedeMacAdress();

                System.out.println("ID Processador: ");
                String processadorID = dados.getProcessadorID();
                System.out.println(processadorID);
                System.out.println("Nome Processador: ");
                String processadorNome = dados.getProcessadorNome();
                System.out.println(processadorNome);
                System.out.println("CPU Físicas");
                String processadorCPUFisica = dados.getProcessadorCPUFisica();
                System.out.println(processadorCPUFisica);
                System.out.println("CPU Lógicas");
                String processadorCPULogica = dados.getProcessadorCPULogica();
                System.out.println(processadorCPULogica);
                System.out.println("Memória RAM(Quantidade):");
                String memoriaRAM = dados.getMemoriaRAM();
                System.out.println(memoriaRAM);
                System.out.println("Memória de Massa:");
                System.out.println("Nome: ");
                String memoriaMassa = dados.getMemoriaMassaNome();
                System.out.println(memoriaMassa);
                System.out.println("Tamanho: ");
                String memoriaMassaTamanho = dados.getMemoriaMassaTamanho();
                System.out.println(memoriaMassaTamanho);
                System.out.println("Tipo(SDD ou HD): ");
                String memoriaMassaTipo = sc.nextLine();
                System.out.println("Realizando cadastro...");
                // Inserindo as informações no banco de dados
                dbAzure.atualizarCadastro(usuario, processadorID, processadorNome, memoriaRAM, memoriaMassaTipo, memoriaMassaTamanho, redeIpv6, redeMacAdress);
                System.out.println("Cadastro realizado com sucesso");
                dados.inserirDadosComIntervalo(looca, cpuAlerta, cpuCritico, ramAlerta, ramCritico, hdAlerta, hdCritico, idTotem, freqAlerta);
                
            } else if (respostaPreenchimentoAutomatico.equals("n")) {
                //Rede
                String redeIpv6 = dados.getRedeIpv6();
                String redeMacAdress = dados.getRedeMacAdress();

                System.out.println("ID Processador: ");
                String processadorID = sc.nextLine();
                System.out.println("Nome Processador: ");
                String processadorNome = sc.nextLine();
                System.out.println("CPU Físicas");
                String processadorCPUFisica = sc.nextLine();
                System.out.println("CPU Lógicas");
                String processadorCPULogica = sc.nextLine();
                System.out.println("Memória RAM(Quantidade):");
                String memoriaRAM = sc.nextLine();
                System.out.println("Memória de Massa:");
                System.out.println("Nome: ");
                String memoriaMassaNome = sc.nextLine();
                System.out.println("Tamanho: ");
                String memoriaMassaTamanho = sc.nextLine();
                System.out.println("Tipo(SDD ou HD): ");
                String memoriaMassaTipo = sc.nextLine();
                System.out.println("Realizando cadastro...");
                // Inserindo as informações no banco de dados
                dbAzure.atualizarCadastro(usuario, processadorID, processadorNome, memoriaRAM, memoriaMassaTipo, memoriaMassaTamanho, redeIpv6, redeMacAdress);
                System.out.println("Cadastro realizado com sucesso");
                dados.inserirDadosComIntervalo(looca, cpuAlerta, cpuCritico, ramAlerta, ramCritico, hdAlerta, hdCritico, idTotem, freqAlerta);
            }
        }
    }
    
    
}
