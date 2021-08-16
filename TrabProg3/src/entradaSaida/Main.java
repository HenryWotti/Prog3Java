package entradaSaida;
import java.util.Scanner;

import interfaceUsuario.Inputs;
import interfaceUsuario.Menu;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Locale;
import java.util.ArrayList;

public class Main implements Serializable{
	
	
    public static void main(String[] args) throws ParseException {

        Locale.setDefault(new Locale("pt", "BR"));

        /**
         * No momento, todas os scans estão sendo feitos com uma informação por linha
         */
        Scanner scan = new Scanner(System.in);

        // para algumas leituras de numero seguidas de nextLine é preciso ler o lixo
        // para pegar o enter depois do número.
        String lixo;

        // a opcao será a chave do switch para saber qual operacao o usuario deseja
        // efetuar
        int opcao = 0;

        Menu m = new Menu();
        
        //Escritor escritor = new Escritor(args[1]);
        //if("--read-only".equals(args[0])) {}
        //if("--write-only".equals(args[0])) {}
        

        // o switch está dentro de um loop pois queremos realizar diversas operações
        // consecutivas no programa
        while (opcao != 11) {
            System.out.println("Digite o número correspondente a operação desejada:");
            System.out.println("1) Cadastrar Unidade Básica de Saúde");
            System.out.println("2) Cadastrar Servidor Municipal");
            System.out.println("3) Cadastrar Vacina");
            System.out.println("4) Receber Lote de Vacinas");
            System.out.println("5) Agendar Vacinação");
            System.out.println("6) Cancelar Agendamento de Vacinação");
            System.out.println("7) Registar Vacinação Efetuada");
            System.out.println("8) Relatório");
            System.out.println("9) Salvar");
            System.out.println("10) Carregar");
            System.out.println("11) Encerrar Programa");

            opcao = scan.nextInt();
            System.out.println("opcao:" + opcao);
            lixo = scan.nextLine();
            m.escolheMenu(opcao);
        }
        
        
        
        System.out.println("Programa encerrado!");
        scan.close();
    }
}
