import java.util.Scanner;
import java.text.ParseException;
import java.util.Locale;

public class Main {
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

        // o switch está dentro de um loop pois queremos realizar diversas operações
        // consecutivas no programa
        while (opcao != 8) {
            System.out.println("Digite o número correspondente a operação desejada:");
            System.out.println("1) Cadastrar Unidade Básica de Saúde");
            System.out.println("2) Cadastrar Servidor Municipal");
            System.out.println("3) Cadastrar Vacina");
            System.out.println("4) Receber Lote de Vacinas");
            System.out.println("5) Agendar Vacinação");
            System.out.println("6) Cancelar Agendamento de Vacinação");
            System.out.println("7) Registar Vacinação Efetuada");
            System.out.println("8) Sair do Programa");

            opcao = scan.nextInt();
            System.out.println("opcao:" + opcao);
            lixo = scan.nextLine();
            m.escolheMenu(opcao);
        }
        System.out.println("Programa encerrado!");
        scan.close();
    }
}
