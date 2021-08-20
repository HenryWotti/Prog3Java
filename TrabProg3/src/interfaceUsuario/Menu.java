package interfaceUsuario;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import elementosDoDominio.AgendarVacinacao;
import elementosDoDominio.Lote;
import elementosDoDominio.ServidorMunicipal;
import elementosDoDominio.Ubs;
import elementosDoDominio.Vacina;
import entradaSaida.Escritor;

import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Menu implements Serializable {

    Scanner scan = new Scanner(System.in);
    // declarando dois formatos diferentes, um pra data convencional e outro para
    // data com hora
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    Map<String, Ubs> mapaUbs = new HashMap<>();
    ArrayList<Ubs> listaUbs = new ArrayList<Ubs>();
    Map<String, ServidorMunicipal> mapaServidor = new HashMap<>();
    ArrayList<Lote> listaLotes = new ArrayList<Lote>();
    Map<String, Vacina> mapaVacina = new HashMap<>();
    Map<String, AgendarVacinacao> mapaAgendamento = new HashMap<>();
    ArrayList<String> listaNomeDoencas = new ArrayList<String>();
    ArrayList<Vacina> listaVacinas = new ArrayList<Vacina>();
    ArrayList<AgendarVacinacao> listaCidadaosAgendados = new ArrayList<AgendarVacinacao>();
    Map<Vacina, Lote> mapaLotes = new HashMap<>();
    
    String nomeArq;
    //File arq = new File(nomeArq);
    //PrintWriter gravarArq = new PrintWriter(arq);
    
    String lixo;
    
    Inputs in = new Inputs();

    public void escolheMenu(int opcao) throws ParseException {
        switch (opcao) {
            case 1:
                System.out.println("Ubs's até agora:");
                for (Map.Entry<String, Ubs> entry : mapaUbs.entrySet()) {
                    System.out.println(entry.getValue());
                }

                System.out.println("Digite o NOME e a SIGLA da ubs:");
                String nome;
                String sigla;

                nome = scan.nextLine();
                sigla = scan.nextLine();
                	
                Ubs teste = mapaUbs.get(sigla);
                boolean ubsRepetidaValidacao = in.verificaUBSrepetida(teste);
                if(!ubsRepetidaValidacao) {
                	System.out.println("Cadastro repetido: " + sigla);
                	break;
                }
                
                // cadastrando UBS em uma mapa de UBSs
                mapaUbs.put(sigla, new Ubs(nome, sigla));
                // através da chave sigla, é possivel acessar a ubs do mapa de UBSs
                Ubs ubs = mapaUbs.get(sigla);
                listaUbs.add(ubs); //adiciona as Ubs em uma lista para ordenacao posterior :)

                break;

            case 2:
                System.out.println("Servidores até agora:");
                for (Map.Entry<String, ServidorMunicipal> entry : mapaServidor.entrySet()) {
                    System.out.println(entry.getValue());
                }
                System.out.println("Digite o NOME, MATRICULA do servidor e a SIGLA da ubs");
                String nomeServidor;
                String matricula;
                Date data = null;
                String siglaUbs; // tem que achar a UBS de acordo com a chave sigla   

                //recebendo dados do usuario
                nomeServidor = scan.nextLine();
                matricula = scan.nextLine();
                siglaUbs = scan.nextLine();
                
                //verificando inputs recebidos
                boolean matValidacao = in.verificaEntradaNumerica(matricula);
                if(matValidacao == false) {
                	System.out.println("Dado inválido: " + matricula);
                	break;
                }
                
                ServidorMunicipal teste2 = mapaServidor.get(matricula);
                boolean matriculaRepetidaValidacao = in.verificaMatriculaServidorRepetido(teste2);
                if(!matriculaRepetidaValidacao) {
                	System.out.println("Cadastro repetido: " + matricula);
                	break;
                }
                
                //busca e validacao da UBS informada
                Ubs ubsServidor = mapaUbs.get(siglaUbs);
                boolean ubsValidacao1 = in.verificaUBS(ubsServidor);
                if(!ubsValidacao1) {
                	System.out.println("Referência inválida: " + siglaUbs);
                	break;
                }
                
                //registro do servidor no mapa de servidores
                mapaServidor.put(matricula, new ServidorMunicipal(nomeServidor, matricula, ubsServidor));

                //cadastro opcional da data de nascimento do servidor
                ServidorMunicipal servidor = mapaServidor.get(matricula);
                System.out.println("Deseja cadastrar a data? Digite 1 para sim ou 0 para nao");
                int cadastroOpcional;
                cadastroOpcional = scan.nextInt();
                lixo = scan.nextLine();
                if (cadastroOpcional == 1) {
                    System.out.println("Digite a data:");
                    //validando data de nascimento
                    String datinha = scan.nextLine();
                    
                    boolean dataValidacao = in.verificaData(datinha);
                    if(!dataValidacao) {
                    	System.out.println("Dado inválido: " + datinha);
                    	break;
                    }else {
                    	data = formato.parse(datinha);
                    	servidor.setData(data);	
                    }
                }
                
                break;

            case 3:
                System.out.println("Vacinas até agora:");
                for (Map.Entry<String, Vacina> entry : mapaVacina.entrySet()) {
                    System.out.println(entry.getValue());
                }
                System.out.println("Digite o NOME da vacina, DOENCA");
                String nomeVacina;
                String fabricante;
                String doenca;
                String link;
                String efeito;
                int intervaloMin;
                int intervaloMax;
                ArrayList<String> listaDeEfeitos = new ArrayList<String>();
                nomeVacina = scan.nextLine();
                doenca = scan.nextLine();
                
                Vacina teste3 = mapaVacina.get(nomeVacina);
                boolean nomeVacRepetidoValidacao = in.verificaVacinaRepetida(teste3);
                if(!nomeVacRepetidoValidacao) {
                	System.out.println("Cadastro repetido: " + nomeVacina);
                	break;
                }
                
                int cont = 0;
                for (int i = 0; i < listaNomeDoencas.size(); i++) {
                    if(doenca.compareTo(listaNomeDoencas.get(i)) != 0) { //para adicionar doenca na lista sem repetir
                    	cont++;
                    }
                }
                if(cont == listaNomeDoencas.size()) {
            		listaNomeDoencas.add(doenca); //adicionando na lista de nome de doencas para ordenar e imprimir o relatorio 2
            	}  
                
                System.out.println("A vacina é de dose única? Digite 1 para sim e 0 para nao");
                int confereDose;
                confereDose = scan.nextInt();
                lixo = scan.nextLine();
                if (confereDose == 1) {
                    System.out.println("Forneca o link:");
                    link = scan.nextLine();
                    // colocar vacina em uma mapa de vacinas, sua chave será seu nome
                    mapaVacina.put(nomeVacina, new Vacina(nomeVacina, doenca, link));
                } else {
                    System.out.println("Forneca respectivamente o intervalo min e max entre doses:");
                    intervaloMin = scan.nextInt();
                    lixo = scan.nextLine();
                    intervaloMax = scan.nextInt();
                    lixo = scan.nextLine();
                    // colocar vacina em uma mapa de vacinas, sua chave será seu nome
                    mapaVacina.put(nomeVacina, new Vacina(nomeVacina, doenca, intervaloMin, intervaloMax));
                }
                
                Vacina vacina = mapaVacina.get(nomeVacina);
                listaVacinas.add(vacina); //adiciona vacina na lista de vacinas	
                
                System.out.println("Deseja cadastrar a fabricante? Digite 1 para sim ou 0 para nao");
                int cadastroOpcional2;
                cadastroOpcional2 = scan.nextInt();
                lixo = scan.nextLine();
                if (cadastroOpcional2 == 1) {
                    System.out.println("Digite o fabricante:");
                    fabricante = scan.nextLine();
                    vacina.setFabricante(fabricante);
                }

                System.out.println("A vacina possui efeito colateral forte? Digite 1 para sim e 0 para nao");
                int efeitoColateral;
                efeitoColateral = scan.nextInt();
                lixo = scan.nextLine();
                while (efeitoColateral == 1) {
                	System.out.println("Informe um efeito colateral e a porcentagem de ocorrência");
                	efeito = scan.nextLine();
                	listaDeEfeitos.add(efeito); //guarda efeito colateral e porcentagem de ocorrencia
                	System.out.println("Possui mais efeitos colaterais? Digite 1 para sim e 0 para nao");
                	efeitoColateral = scan.nextInt(); //atualiza variavel do while
                    lixo = scan.nextLine();	
                }
                vacina.setEfeitosColaterais(listaDeEfeitos); //atribui-se efeitos colaterais registrados a vacina
                Vacina vac = mapaVacina.get("coronavac");

                break;

            case 4:
                System.out.println("Lotes até agora:");
                System.out.println(listaLotes);
                
                System.out.println(
                        "Digite o NOME da vacina, SIGLA da ubs, DATA, QUANTIDADE entregue, CUSTOPORDOSE e FONTE");
                String nomeVac;
                String siglaUnidade;
                String dataEntrega;
                int quantidade;
                double custoPorDose;
                String fonte;
                Date data1;

                nomeVac = scan.nextLine();
                siglaUnidade = scan.nextLine();
                dataEntrega = scan.nextLine();
                quantidade = scan.nextInt();
                custoPorDose = scan.nextDouble();
                lixo = scan.nextLine();
                fonte = scan.nextLine();

                //buscando no mapa e validando a vacina informada
                Vacina vacinaDoLote = mapaVacina.get(nomeVac);
                boolean vacinaValidacao1 = in.verificaVacina(vacinaDoLote);
                if(!vacinaValidacao1) {
                	System.out.println("Referência inválida: " + nomeVac);
                	break;
                }
                
                
                //buscando no mapa e validando UBS informada
                Ubs ubsLote = mapaUbs.get(siglaUnidade);
                boolean ubsValidacao = in.verificaUBS(ubsLote);
                if(!ubsValidacao) {
                	System.out.println("Referência inválida: " + siglaUnidade);
                	break;
                }
                
                //validacao da data informada
                boolean dataValidacao = in.verificaData(dataEntrega);
                if(!dataValidacao) {
                	System.out.println("Data inválida: " + dataEntrega);
                	break;
                }else {
                	data1 = formato.parse(dataEntrega);
                }
                
                //registrando lote e adicionando na lista e no mapa de lotes
                Lote lote = new Lote(vacinaDoLote, ubsLote, data1, quantidade, custoPorDose, fonte);
                listaLotes.add(lote);
                mapaLotes.put(vacinaDoLote, lote);

                break;

            case 5:
                System.out.println("Agendamentos até agora:");
                for (Map.Entry<String, AgendarVacinacao> entry : mapaAgendamento.entrySet()) {
                    System.out.println(entry.getValue());
                }
                System.out.println("Digite a DATA e HORA, SIGLA da ubs, NOME, DATA de nascimento, CPF do paciente e a vacina agendada");
                String dataEhora;
                Date dataHora;
                String siglaU; // chave
                String nomePessoa;
                String nascimento;
                Date dataNascimento;
                String cpf1;
                String nomeVacinaAgendada;
     
                
                dataEhora = scan.nextLine();
                siglaU = scan.nextLine();
                nomePessoa = scan.nextLine();
                nascimento = scan.nextLine();
                cpf1 = scan.nextLine();
                nomeVacinaAgendada = scan.nextLine();
                
                //validacao dos dados
                boolean dataHoraValidacao = in.verificaDataEHora(dataEhora);
                if(!dataHoraValidacao) {
                	System.out.println("Data inválida: " + dataEhora);
                	break;
                }else {
                	dataHora = formato.parse(dataEhora);
                }
                
                //encontrando e validando a ubs informada
                Ubs ubsAgendamento = mapaUbs.get(siglaU);
                boolean ubsValidacao2 = in.verificaUBS(ubsAgendamento);
                if(!ubsValidacao2) {
                	System.out.println("Referência inválida: " + siglaU);
                	break;
                }
                ubsAgendamento.contaAgendados();
                
                //validando data de nascimento
                boolean nascimentoValidacao = in.verificaData(nascimento);
                if(!nascimentoValidacao) {
                	System.out.println("Data inválida: " + nascimento);
                	break;
                }else {
                	dataNascimento = formato.parse(nascimento);
                }
                
                //validando cpf
                boolean cpfValidacao = in.verificaEntradaNumerica(cpf1);
                if(cpfValidacao == false) {
                	System.out.println("Dado inválido: " + cpf1);
                	break;
                }
   
                AgendarVacinacao teste4 = mapaAgendamento.get(cpf1);
                boolean agendamentoRepetidoValidacao = in.verificaAgendamentoRepetido(teste4);
                if(!agendamentoRepetidoValidacao) {
                	System.out.println("Cadastro repetido: " + cpf1);
                	break;
                }
                
                //encontrando e validando a vacina informada
                Vacina vacinaAgendada = mapaVacina.get(nomeVacinaAgendada);
                boolean vacinaValidacao = in.verificaVacina(vacinaAgendada);
                if(!vacinaValidacao) {
                	System.out.println("Referência inválida: " + nomeVacinaAgendada);
                	break;
                }                
                
                if(mapaAgendamento == null) { //caso seja o primeiro cadastro a ser realizado
                	ubsAgendamento.setPeriodoInicial(dataHora);
                	ubsAgendamento.setPeriodoFinal(dataHora);
                }
                
                //registro do agendamento no mapa de agendamento
                mapaAgendamento.put(cpf1,
                        new AgendarVacinacao(dataHora, ubsAgendamento, nomePessoa, dataNascimento, cpf1, vacinaAgendada));
                
                AgendarVacinacao agendamento = mapaAgendamento.get(cpf1); //pegando agendamento do mapa para adicionar na lista
                
                listaCidadaosAgendados.add(agendamento); //adicionando novo agendamento na lista de cidadaos agendados

                break;

            case 6:
                System.out.println("Agendamentos até agora:");
                for (Map.Entry<String, AgendarVacinacao> entry : mapaAgendamento.entrySet()) {
                    System.out.println(entry.getValue());
                }
                System.out.println("Digite o CPF");
                String cpf2; // chave para achar o agendamento

                cpf2 = scan.nextLine();    

                boolean cpf2Validacao = in.verificaEntradaNumerica(cpf2);
                if(cpf2Validacao == false) {
                	System.out.println("Dado inválido: " + cpf2);
                	break;
                }
                
                // achar o agendamento pela chave cpf
                AgendarVacinacao agendamentoCancela = mapaAgendamento.get(cpf2);
                boolean agendamentoValidacao = in.verificaCPF(agendamentoCancela);
                if(!agendamentoValidacao) {
                	System.out.println("Cidadao nao possui agendamento ativo: " + cpf2);
                	break;
                }
                
                Ubs ubsRegistrada = agendamentoCancela.getUbs();
                ubsRegistrada.contaCancelados();

                // método que cancela um agendamento.
                agendamentoCancela.cancela();

                break;

            case 7:
                System.out.println("Agendamentos até agora:");
                for (Map.Entry<String, AgendarVacinacao> entry : mapaAgendamento.entrySet()) {
                    System.out.println(entry.getValue());
                }
                System.out.println("Digite o CPF e a MATRICULA do servidor");
                String cpf3;
                String matricula3;

                cpf3 = scan.nextLine();
                boolean cpf3Validacao = in.verificaEntradaNumerica(cpf3);
                if(cpf3Validacao == false) {
                	System.out.println("Dado inválido: " + cpf3);
                	break;
                }
                
                matricula3 = scan.nextLine(); 
                ServidorMunicipal servidorM = mapaServidor.get(matricula3);
                if(servidorM == null) {
                	System.out.println("Servidor não encontrado");
                }
                                             
                // achar o agendamento pelo cpf e validar
                AgendarVacinacao agendamentoEfetua = mapaAgendamento.get(cpf3);
                boolean efetuaValidacao = in.verificaCPF(agendamentoEfetua);
                if(!efetuaValidacao) {
                	System.out.println("Cidadao nao possui agendamento ativo: " + cpf3);
                	break;
                }
                
                Ubs ubsRegistrad = agendamentoEfetua.getUbs();
                ubsRegistrad.contaVacinados();

                //verificar melhor depois, mapa é o ideal? fica a indagação
                Vacina vacinaEfetuada = agendamentoEfetua.getVacinaAgendada();
                Lote loteDaVac = mapaLotes.get(vacinaEfetuada);
                if(loteDaVac == null) {
                	System.out.println(ubsRegistrad.getNome() + " nao possui a vacina " + vacinaEfetuada.getNomeVacina() + " (" + vacinaEfetuada.getDoenca() + ") em estoque em " + agendamentoEfetua.getDataHora());
                	break;
                }
                Ubs ubsDaVac = loteDaVac.getUbs();
                
                boolean vacinaNaUbs = in.verificaVacinaNaUbs(ubsDaVac, ubsRegistrad);
                if(!vacinaNaUbs) {
                	System.out.println(ubsRegistrad.getNome() + " nao possui a vacina " + vacinaEfetuada.getNomeVacina() + " (" + vacinaEfetuada.getDoenca() + ") em estoque em " + agendamentoEfetua.getDataHora());
                	break;
                }
                //pegamos a ubs que o servidor informado pela matricula trabalha
                Ubs ubsServidor2 = servidorM.getUbs();
                boolean ubsServidorValidacao = in.verificaUbsDoServidor(ubsServidor2, agendamentoEfetua.getUbs());
                if(!ubsServidorValidacao) {
                	System.out.println("Servidor " + servidorM.getNomeCompleto() + "(Matricula: " + matricula3 + ") nao esta lotado na " + agendamentoEfetua.getUbs().getNome() + ".");
                	break;
                }
                
                // registra agendamento como vacinacao efetuada
                agendamentoEfetua.efetuaVacinacao();

                break;

            case 8:
            	System.out.println("Relatorio");
            	System.out.println("Qual relatório gostaria que fosse exibido?");
            	System.out.println("1 - Vacinação por UBS");
            	System.out.println("2 - Entregas de Vacina por Doença");
            	System.out.println("3 - Comunicados aos cidadão vacinados");
            	
            	
            	int option = scan.nextInt();
                lixo = scan.nextLine();
            	switch(option) {
            	case 1: 
            		System.out.println("Vacinação por UBS");
            		for (Map.Entry<String, Ubs> entry : mapaUbs.entrySet()) {
                        System.out.println(entry.getValue());
                        System.out.println();
                    }
            		ordenaUbs(listaUbs);
            		System.out.println(listaUbs);
            		
            		break;
            	case 2:
            		Lote loteRelatorio = new Lote();
            		loteRelatorio.relatorio2(listaVacinas, listaNomeDoencas, listaLotes);
            		
            		break;
            	case 3:
            		System.out.println("Comunicados aos cidadão vacinados");
            		
            		for(int i = 0; i < listaCidadaosAgendados.size(); i++) {
            			System.out.println("Nome:" + listaCidadaosAgendados.get(i).getNome());
            			System.out.println("Idade:" + listaCidadaosAgendados.get(i).getIdade());

            			//verificando se a vacina é dose única ou não
            			if(listaCidadaosAgendados.get(i).getVacinaAgendada().getLink() != null) {
            				System.out.println("Link dose única:" + listaCidadaosAgendados.get(i).getVacinaAgendada().getLink());
            			}else { //caso seja dose dupla
            				System.out.println("Intervalos dose dupla:" );
            				System.out.println(listaCidadaosAgendados.get(i).getVacinaAgendada().getIntervaloMin() + " - " + listaCidadaosAgendados.get(i).getVacinaAgendada().getIntervaloMax());
            			}
            			//verificando se ha efeitos colaterais registrados
            			if(listaCidadaosAgendados.get(i).getVacinaAgendada().getEfeitosColaterais() != null) {
            				for(int j = 0; j < listaCidadaosAgendados.get(i).getVacinaAgendada().getEfeitosColaterais().size(); j++){
            					System.out.println(listaCidadaosAgendados.get(i).getVacinaAgendada().getEfeitosColaterais().get(j));
            				}
            			}     			
            		}
            		
            		break;
            	default:
            		break;
            		
            	}
 	
                break;              
            case 9:
            	System.out.println("Salvar");
            	System.out.println("Informe o nome do arquivo");
            	nomeArq = scan.nextLine();
            	Escritor escritor = new Escritor();
            	escritor.escritor(nomeArq);
            	escritor.serializar(listaUbs);
            	// Serializa um objeto no formato binário em um arquivo
            	  // armazenado no local do segundo parâmetro
            	  
            	//saveObject(vacina, nomeArq);

            	
            	break;
            case 10:
            	System.out.println("Carregando...");
            	Escritor escritor2 = new Escritor();
            	System.out.println("Informe o nome do arquivo");
            	nomeArq = scan.nextLine();
            	escritor2.escritor(nomeArq);
            	ArrayList<Ubs> listaUbsDescarregada = new ArrayList<Ubs>();
            	listaUbsDescarregada = escritor2.desserializar();
            	listaUbs.addAll(listaUbsDescarregada);
            	
            	break;
            case 11: //encerra programa
            	break;

            default:
                break;
        }
    }

	public void ordenaUbs(ArrayList<Ubs> listaUbs) {
    	Collections.sort(listaUbs, Comparator.comparing(Ubs::getTotalAgendados).reversed().thenComparing(Ubs::getSigla));
    }
    
//    public void ordenaDoenca(ArrayList<String> listaDoenca) {
//    	Collections.sort(listaDoenca);
//    }
    
    
      
    
}