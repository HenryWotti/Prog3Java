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

import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



public class Menu {

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
    
    String nomeArq;
    File arq = new File(nomeArq);
    //PrintWriter gravarArq = new PrintWriter(arq);
    
    String lixo;

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

                // cadastrando UBS em uma mapa de UBSs
                mapaUbs.put(sigla, new Ubs(nome, sigla));
                // através da chave sigla, é possivel acessar a ubs do mapa de UBSs
                Ubs ubs = mapaUbs.get(sigla);
                listaUbs.add(ubs); //adiciona as Ubs em uma lista para ordenacao posterior :)

                // System.out.print("Nome UBS:");
                // System.out.println(ubs.getNome());
                // System.out.print("Sigla UBS:");
                // System.out.println(ubs.getSigla());
                break;

            case 2:
                System.out.println("Servidores até agora:");
                for (Map.Entry<String, ServidorMunicipal> entry : mapaServidor.entrySet()) {
                    System.out.println(entry.getValue());
                }
                System.out.println("Digite o NOME, MATRICULA do servidor e a SIGLA da ubs");
                String nomeServidor;
                String matricula;
                Date data;
                String siglaUbs; // tem que achar a UBS de acordo com a chave sigla

                nomeServidor = scan.nextLine();
                matricula = scan.nextLine();
                siglaUbs = scan.nextLine();
                // data = formato.parse(scan.nextLine()); // opcional
                Ubs ubsServidor = mapaUbs.get(siglaUbs);

                mapaServidor.put(matricula, new ServidorMunicipal(nomeServidor, matricula, ubsServidor));

                ServidorMunicipal servidor = mapaServidor.get(matricula);
                System.out.println("Deseja cadastrar a data? Digite 1 para sim ou 0 para nao");
                int cadastroOpcional;
                cadastroOpcional = scan.nextInt();
                lixo = scan.nextLine();
                if (cadastroOpcional == 1) {
                    System.out.println("Digite a data:");
                    data = formato.parse(scan.nextLine());
                    servidor.setData(data);
                }

                // System.out.print("Nome Servidor:");
                // System.out.println(servidor.getNomeCompleto());
                // System.out.print("Matricula:");
                // System.out.println(servidor.getMatricula());
                // // System.out.print("Data de nascimento:");
                // // System.out.println(formato.format(servidor.getData()));
                // System.out.print("UBS (nome/sigla):\n");
                // System.out.println(ubsServidor.getNome());
                // System.out.println(ubsServidor.getSigla());
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
                
                int cont = 0;
                for (int i = 0; i < listaNomeDoencas.size(); i++) {
                    if(doenca.compareTo(listaNomeDoencas.get(i)) != 0) { //para adicionar doenca na lista sem repetir
                    	cont++;
                    }
                }
                if(cont == listaNomeDoencas.size()) {
            		listaNomeDoencas.add(doenca); //adicionando na lista de nome de doencas para ordenar e imprimir o relatorio 2
            	}
                
                // colocar vacina em uma mapa de vacinas, sua chave será seu nome
                mapaVacina.put(nomeVacina, new Vacina(nomeVacina, doenca));
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

                System.out.println("A vacina é de dose única? Digite 1 para sim e 0 para nao");
                int confereDose;
                confereDose = scan.nextInt();
                lixo = scan.nextLine();
                if (confereDose == 1) {
                    System.out.println("Forneca o link:");
                    link = scan.nextLine();
                    vacina.setLink(link);
                } else {
                    System.out.println("Forneca respectivamente o intervalo min e max entre doses:");
                    intervaloMin = scan.nextInt();
                    lixo = scan.nextLine();
                    intervaloMax = scan.nextInt();
                    lixo = scan.nextLine();
                    vacina.setIntervaloMin(intervaloMin);
                    vacina.setIntervaloMax(intervaloMax);
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
                //System.out.println(vac.getEfeitosColaterais().toString());
                

                // System.out.print("Nome Vacina: ");
                // System.out.println(vacina.getNomeVacina());
                // System.out.print("Fabricante:");
                // System.out.println(vacina.getFabricante());
                // System.out.print("Doenca:");
                // System.out.println(vacina.getDoenca());
                break;

            case 4:
                System.out.println("Lotes até agora:");
                //for(int i = 0; i < listaLotes.size(); i++) {
                	System.out.println(listaLotes);
                //}
                
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
                Vacina vacinaDoLote = mapaVacina.get(nomeVac);
                
                Lote lote = new Lote(vacinaDoLote, ubsLote, dataEntrega, quantidade, custoPorDose, fonte);
                listaLotes.add(lote);
                
                // System.out.print("Data de entrega: ");
                // System.out.println(formato.format(lote.getData()));
                // System.out.print("Quantidade entregue:");
                // System.out.println(lote.getQuantidade());
                // System.out.print("Custo por Dose:");
                // System.out.println(lote.getCustoPorDose());
                // System.out.print("Fonte:");
                // System.out.println(lote.getFonte());
                // System.out.print("Vacina (nome/doenca):");
                // System.out.println(vacinaLote.getNomeVacina());
                // System.out.println(vacinaLote.getDoenca());
                // System.out.print("UBS (nome/sigla):");
                // System.out.println(ubsLote.getNome());
                // System.out.println(ubsLote.getSigla());
                break;

            case 5:
                System.out.println("Agendamentos até agora:");
                for (Map.Entry<String, AgendarVacinacao> entry : mapaAgendamento.entrySet()) {
                    System.out.println(entry.getValue());
                }
                System.out.println("Digite a DATA e HORA, SIGLA da ubs, NOME, DATA de nascimento e CPF do paciente");
                Date dataHora;
                String siglaU; // chave
                String nomePessoa;
                Date dataNascimento;
                String cpf1;
                String nomeVacinaAgendada;

                
                
                dataHora = formato2.parse(scan.nextLine());
                siglaU = scan.nextLine();
                nomePessoa = scan.nextLine();
                dataNascimento = formato.parse(scan.nextLine());
                cpf1 = scan.nextLine();
                nomeVacinaAgendada = scan.nextLine();
                
                //encontrando vacina e Ubs no respectivos mapas de acordo com as chaves lidas
                Vacina vacinaAgendada = mapaVacina.get(nomeVacinaAgendada);
                Ubs ubsAgendamento = mapaUbs.get(siglaU);
                
                ubsAgendamento.contaAgendados();

                if(mapaAgendamento == null) { //caso seja o primeiro cadastro a ser realizado
                	ubsAgendamento.setPeriodoInicial(dataHora);
                	ubsAgendamento.setPeriodoFinal(dataHora);
                }
                
                mapaAgendamento.put(cpf1,
                        new AgendarVacinacao(dataHora, ubsAgendamento, nomePessoa, dataNascimento, cpf1, vacinaAgendada));
                
                AgendarVacinacao agendamento = mapaAgendamento.get(cpf1); //pegando agendamento do mapa para adicionar na lista
                
                listaCidadaosAgendados.add(agendamento); //adicionando novo agendamento na lista de cidadaos agendados

                
                // System.out.print("Data e Hora: ");
                // System.out.println(formato2.format(agendamento.getDataHora()));
                // System.out.print("Nome Paciente:");
                // System.out.println(agendamento.getNome());
                // System.out.print("Data Nascimento:");
                // System.out.println(formato.format(agendamento.getDataNascimento()));
                // System.out.print("CPF:");
                // System.out.println(agendamento.getCpf());
                // System.out.print("Status de Agendamento:");
                // System.out.println(agendamento.getStatusAgendamento());
                // System.out.print("Status de Atendimento:");
                // System.out.println(agendamento.getStatusEfetuado());
                // printaStatus(agendamento.getStatusEfetuado(),
                // agendamento.getStatusAgendamento());
                break;

            case 6:
                System.out.println("Agendamentos até agora:");
                for (Map.Entry<String, AgendarVacinacao> entry : mapaAgendamento.entrySet()) {
                    System.out.println(entry.getValue());
                }
                System.out.println("Digite o CPF");
                String cpf2; // chave para achar o agendamento

                cpf2 = scan.nextLine();

                // achar o agendamento pela chave cpf
                AgendarVacinacao agendamentoCancela = mapaAgendamento.get(cpf2);
                Ubs ubsRegistrada = agendamentoCancela.getUbs();
                ubsRegistrada.contaCancelados();

                // método que cancela um agendamento.
                agendamentoCancela.cancela();

                // System.out.print("Data e Hora: ");
                // System.out.println(formato2.format(agendamentoCancela.getDataHora()));
                // System.out.print("Nome Paciente:");
                // System.out.println(agendamentoCancela.getNome());
                // System.out.print("Data Nascimento:");
                // System.out.println(formato.format(agendamentoCancela.getDataNascimento()));
                // System.out.print("CPF:");
                // System.out.println(agendamentoCancela.getCpf());
                // System.out.print("Status de Agendamento:");
                // System.out.println(agendamentoCancela.getStatusAgendamento());
                // System.out.print("Status de Atendimento:");
                // System.out.println(agendamentoCancela.getStatusEfetuado());
                // printaStatus(agendamentoCancela.getStatusEfetuado(),
                // agendamentoCancela.getStatusAgendamento());
                break;

            case 7:
                System.out.println("Agendamentos até agora:");
                for (Map.Entry<String, AgendarVacinacao> entry : mapaAgendamento.entrySet()) {
                    System.out.println(entry.getValue());
                }
                System.out.println("Digite o CPF");
                String cpf3;

                cpf3 = scan.nextLine();

                // achar o agendamento pelo cpf
                AgendarVacinacao agendamentoEfetua = mapaAgendamento.get(cpf3);
                
                Ubs ubsRegistrad = agendamentoEfetua.getUbs();
                ubsRegistrad.contaVacinados();

                // registra agendamento como vacinacao efetuada
                agendamentoEfetua.efetuaVacinacao();

                // System.out.print("Data e Hora: ");
                // System.out.println(formato2.format(agendamentoEfetua.getDataHora()));
                // System.out.print("Nome Paciente:");
                // System.out.println(agendamentoEfetua.getNome());
                // System.out.print("Data Nascimento:");
                // System.out.println(formato.format(agendamentoEfetua.getDataNascimento()));
                // System.out.print("CPF:");
                // System.out.println(agendamentoEfetua.getCpf());
                // System.out.print("Status de Agendamento:");
                // System.out.println(agendamentoEfetua.getStatusAgendamento());
                // System.out.print("Status de Atendimento:");
                // System.out.println(agendamentoEfetua.getStatusEfetuado());
                // printaStatus(agendamentoEfetua.getStatusEfetuado(),
                // agendamentoEfetua.getStatusAgendamento());
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
            		int contTotalDoses = 0;
            		int contDosesFederal = 0;
            		double contCustoFederal = 0;
            		int contDosesEstadual = 0;
            		double contCustoEstadual = 0;
            		String federal = "f";
            		double mediaFederal = 0;
            		double mediaEstadual = 0;
            		
            		System.out.println("Entregas de Vacina por Doença");
            		ordenaVacina(listaVacinas);
            		for(int i = 0; i < listaNomeDoencas.size(); i++) {
            			System.out.println(listaNomeDoencas.get(i));
            			
            			for (int j = 0; j < listaVacinas.size(); j++) {
                           
            				if(listaNomeDoencas.get(i).compareTo(listaVacinas.get(j).getDoenca()) == 0) {
                            	System.out.println(listaVacinas.get(j));
                            	
                            	for(int k = 0; k < listaLotes.size(); k++) {
                            		if(listaVacinas.get(j).getNomeVacina().compareTo(listaLotes.get(k).getVacina().getNomeVacina()) == 0) {
                            			contTotalDoses += listaLotes.get(k).getQuantidade(); //quantidade total de doses recebidas para a doenca
                            			
                            			if(listaLotes.get(k).getFonte().compareTo(federal) == 0){
                            				contDosesFederal+= listaLotes.get(k).getQuantidade(); //total de doses vindas do governo federal
                            				contCustoFederal += listaLotes.get(k).getCustoPorDose() *  listaLotes.get(k).getQuantidade(); //soma dos custos das doses federais com peso
                            			}else {
                            				contDosesEstadual += listaLotes.get(k).getQuantidade(); //total de doses vindas do governo estadual
                            				contCustoEstadual += listaLotes.get(k).getCustoPorDose() *  listaLotes.get(k).getQuantidade(); //soma dos custos das doses estaduais com peso
                            			}                         			
                            			
                            		}
                            	}
                            	mediaFederal = contCustoFederal / contDosesFederal; //custo medio das doses entregues pelo governo federal
                            	mediaEstadual = contCustoEstadual / contDosesEstadual; //custo medio das doses entregues pelo governo estadual
                            	                         	
                            	
                            }
                        }
            			System.out.println("Total de doses para essa doenca:" + contTotalDoses);
                    	System.out.println("Media custo Federal" + mediaFederal);
                    	System.out.println("Media custo Estadual" + mediaEstadual);
                    	contDosesFederal = 0;
                    	contDosesEstadual = 0;
                    	contCustoFederal = 0;
                    	contCustoEstadual = 0;
                    	contTotalDoses = 0;
            		}
            		
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
            	// Serializa um objeto no formato binário em um arquivo
            	  // armazenado no local do segundo parâmetro
            	  
            	saveObject(vacina, nomeArq);

            	
            	break;
            case 10:
            	System.out.println("Carregar");
            	
          	  // Deserializa o objeto armazenado no caminho passado e retorna este
          	  // objeto sem nenhum tipo de casting
          	  private static Object loadObject(String filename) throws ClassNotFoundException, IOException {
          	    // Abre o arquivo para a leitura
          	    ObjectInputStream objstream = new ObjectInputStream(new FileInputStream(filename));

          	    // Lê os bytes e cria o objeto na memória
          	    Object object = objstream.readObject();

          	    // Fecha o arquivo
          	    objstream.close();

          	    // Retorna o objeto sem casting
          	    return object;
          	  }
            	
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
    
    public void ordenaVacina(ArrayList<Vacina> listaVacina) {
    	Collections.sort(listaVacina, Comparator.comparing(Vacina::getNomeVacina));
    }
    
    //função para escrita em arquivo
    private static void saveObject(Serializable object, String nomeArq) throws IOException {
	    // Criando o arquivo e o objeto da classe ObjectOutputStream
	    ObjectOutputStream objstream = new ObjectOutputStream(new FileOutputStream(nomeArq));

	    // O método writeObject() automaticamente transforma o conteúdo do
	    // objeto em bytes. Se a classe não implementar Serialize, um erro será gerado
	    objstream.writeObject(object);

	    // Fechando o arquivo e salvando os dados
	    objstream.close();
	  }
    
    
    // public void printaStatus(boolean statusEfetuado, boolean statusAgendado) {
    // if (statusEfetuado == true) {

    // System.out.println("Situação da Vacinação: Efetuada");
    // }
    // if (statusAgendado == false) {
    // System.out.println("Situação da Vacinação: Cancelada");
    // }
    // if (statusEfetuado == false && statusAgendado == true) {
    // System.out.println("Situação da Vacinação: Agendada");
    // }
    // }
}