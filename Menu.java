import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class Menu {
    public static void main(String[] args) throws ParseException {

        /**
         * No momento, todas os scans estão sendo feitos com uma informação por linha
         */

        Scanner scan = new Scanner(System.in);
        // declarando dois formatos diferentes, um pra data convencional e outro para
        // data com hora
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        // a opcao será a chave do switch para saber qual operacao o usuario deseja
        // efetuar
        int opcao = 0;
        // para algumas leituras de numero seguidas de nextLine é preciso ler o lixo
        // para pegar o enter depois do número.
        String lixo;

        Map<String, Ubs> mapaUbs = new HashMap<>();
        Map<String, Vacina> mapaVacina = new HashMap<>();
        Map<String, AgendarVacinacao> mapaAgendamento = new HashMap<>();

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
            lixo = scan.nextLine();
            switch (opcao) {
                case 1:
                    System.out.println("Digite o NOME e a SIGLA da ubs:");
                    String nome;
                    String sigla;

                    nome = scan.nextLine();
                    sigla = scan.nextLine();

                    // cadastrando UBS em uma mapa de UBSs
                    mapaUbs.put(sigla, new Ubs(nome, sigla));
                    // através da chave sigla, é possivel acessar a ubs do mapa de UBSs
                    Ubs ubs = mapaUbs.get(sigla);

                    System.out.print("Nome UBS:");
                    System.out.println(ubs.getNome());
                    System.out.print("Sigla UBS:");
                    System.out.println(ubs.getSigla());
                    break;

                case 2:
                    System.out.println(
                            "Digite o NOME, MATRICULA do servidor e a SIGLA da ubs e a DATA de nascimento do servidor");
                    String nomeServidor;
                    int matricula;
                    Date data;
                    String siglaUbs; // tem que achar a UBS de acordo com a chave sigla

                    nomeServidor = scan.nextLine();
                    matricula = scan.nextInt();
                    lixo = scan.nextLine();
                    siglaUbs = scan.nextLine();
                    data = formato.parse(scan.nextLine()); // opcional

                    Ubs ubsServidor = mapaUbs.get(siglaUbs);

                    ServidorMunicipal servidor = new ServidorMunicipal(nomeServidor, matricula, ubsServidor);
                    servidor.setData(data);

                    System.out.print("Nome Servidor:");
                    System.out.println(servidor.getNomeCompleto());
                    System.out.print("Matricula:");
                    System.out.println(servidor.getMatricula());
                    System.out.print("Data de nascimento:");
                    System.out.println(formato.format(servidor.getData()));
                    System.out.print("UBS:");
                    System.out.println(ubsServidor.getNome());
                    System.out.println(ubsServidor.getSigla());
                    break;

                case 3:
                    System.out.println("Digite o NOME da vacina, FABRICANTE e DOENCA:");
                    String nomeVacina;
                    String fabricante;
                    String doenca;

                    nomeVacina = scan.nextLine();
                    fabricante = scan.nextLine();
                    doenca = scan.nextLine();

                    // colocar vacina em uma mapa de vacinas, sua chave será seu nome
                    mapaVacina.put(nomeVacina, new Vacina(nomeVacina, doenca));
                    Vacina vacina = mapaVacina.get(nomeVacina);
                    vacina.setFabricante(fabricante);

                    System.out.print("Nome Vacina: ");
                    System.out.println(vacina.getNomeVacina());
                    System.out.print("Fabricante:");
                    System.out.println(vacina.getFabricante());
                    System.out.print("Doenca:");
                    System.out.println(vacina.getDoenca());
                    break;

                case 4:
                    System.out.println(
                            "Digite o NOME da vacina, SIGLA da ubs, DATA, QUANTIDADE entregue, CUSTOPORDOSE e FONTE");
                    String nomeVac;
                    String siglaUnidade;
                    Date dataEntrega;
                    int quantidade;
                    double custoPorDose;
                    String fonte;

                    nomeVac = scan.nextLine();
                    siglaUnidade = scan.nextLine();
                    dataEntrega = formato.parse(scan.nextLine());
                    quantidade = scan.nextInt();
                    custoPorDose = scan.nextDouble();
                    lixo = scan.nextLine();
                    fonte = scan.nextLine();

                    // achar a UBS pela siglaUnidade
                    Ubs ubsLote = mapaUbs.get(siglaUnidade);
                    // achar a vacina pela nomeVac
                    Vacina vacinaLote = mapaVacina.get(nomeVac);

                    ReceberLoteVacina lote = new ReceberLoteVacina(vacinaLote, ubsLote, dataEntrega, quantidade,
                            custoPorDose, fonte);

                    System.out.print("Data de entrega: ");
                    System.out.println(formato.format(lote.getData()));
                    System.out.print("Quantidade entregue:");
                    System.out.println(lote.getQuantidade());
                    System.out.print("Custo por Dose:");
                    System.out.println(lote.getCustoPorDose());
                    System.out.print("Fonte:");
                    System.out.println(lote.getFonte());
                    System.out.print("Vacina (nome/doenca):");
                    System.out.println(vacinaLote.getNomeVacina());
                    System.out.println(vacinaLote.getDoenca());
                    System.out.print("UBS (nome/sigla):");
                    System.out.println(ubsLote.getNome());
                    System.out.println(ubsLote.getSigla());
                    break;

                case 5:
                    System.out
                            .println("Digite a DATA e HORA, SIGLA da ubs, NOME, DATA de nascimento e CPF do paciente");
                    Date dataHora;
                    String siglaU; // chave
                    String nomePessoa;
                    Date dataNascimento;
                    String cpf1;

                    dataHora = formato2.parse(scan.nextLine());
                    siglaU = scan.nextLine();
                    nomePessoa = scan.nextLine();
                    dataNascimento = formato.parse(scan.nextLine());
                    cpf1 = scan.nextLine();

                    Ubs ubsAgendamento = mapaUbs.get(siglaU);

                    mapaAgendamento.put(cpf1,
                            new AgendarVacinacao(dataHora, ubsAgendamento, nomePessoa, dataNascimento, cpf1));
                    AgendarVacinacao agendamento = mapaAgendamento.get(cpf1);

                    System.out.print("Data e Hora: ");
                    System.out.println(formato2.format(agendamento.getDataHora()));
                    System.out.print("Nome Paciente:");
                    System.out.println(agendamento.getNome());
                    System.out.print("Data Nascimento:");
                    System.out.println(formato.format(agendamento.getDataNascimento()));
                    System.out.print("CPF:");
                    System.out.println(agendamento.getCpf());
                    System.out.print("Status de Agendamento:");
                    System.out.println(agendamento.getStatusAgendamento());
                    System.out.print("Status de Atendimento:");
                    System.out.println(agendamento.getStatusEfetuado());
                    agendamento.printaStatus();
                    break;

                case 6:
                    System.out.println("Digite o CPF");
                    String cpf2; // chave para achar o agendamento

                    cpf2 = scan.nextLine();

                    // achar o agendamento pela chave cpf
                    AgendarVacinacao agendamentoCancela = mapaAgendamento.get(cpf2);

                    // método que cancela um agendamento.
                    agendamentoCancela.cancela();

                    System.out.print("Data e Hora: ");
                    System.out.println(formato2.format(agendamentoCancela.getDataHora()));
                    System.out.print("Nome Paciente:");
                    System.out.println(agendamentoCancela.getNome());
                    System.out.print("Data Nascimento:");
                    System.out.println(formato.format(agendamentoCancela.getDataNascimento()));
                    System.out.print("CPF:");
                    System.out.println(agendamentoCancela.getCpf());
                    System.out.print("Status de Agendamento:");
                    System.out.println(agendamentoCancela.getStatusAgendamento());
                    System.out.print("Status de Atendimento:");
                    System.out.println(agendamentoCancela.getStatusEfetuado());
                    agendamentoCancela.printaStatus();
                    break;

                case 7:
                    System.out.println("Digite o CPF");
                    String cpf3;

                    cpf3 = scan.nextLine();

                    // achar o agendamento pelo cpf
                    AgendarVacinacao agendamentoEfetua = mapaAgendamento.get(cpf3);

                    // registra agendamento como vacinacao efetuada
                    agendamentoEfetua.efetuaVacinacao();
                    System.out.print("Data e Hora: ");
                    System.out.println(formato2.format(agendamentoEfetua.getDataHora()));
                    System.out.print("Nome Paciente:");
                    System.out.println(agendamentoEfetua.getNome());
                    System.out.print("Data Nascimento:");
                    System.out.println(formato.format(agendamentoEfetua.getDataNascimento()));
                    System.out.print("CPF:");
                    System.out.println(agendamentoEfetua.getCpf());
                    System.out.print("Status de Agendamento:");
                    System.out.println(agendamentoEfetua.getStatusAgendamento());
                    System.out.print("Status de Atendimento:");
                    System.out.println(agendamentoEfetua.getStatusEfetuado());
                    agendamentoEfetua.printaStatus();
                    break;

                case 8:
                    break;

                default:
                    break;
            }
        }
        System.out.println("Programa encerrado!");
        scan.close();
    }
}