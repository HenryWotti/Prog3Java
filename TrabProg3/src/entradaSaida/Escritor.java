package entradaSaida;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import elementosDoDominio.AgendarVacinacao;
import elementosDoDominio.Listas;
import elementosDoDominio.Lote;
import elementosDoDominio.ServidorMunicipal;
import elementosDoDominio.Ubs;
import elementosDoDominio.Vacina;

public class Escritor  implements Serializable{
	private File arquivoDeSerializacao;
	private ArrayList<Ubs> listUbs = new ArrayList<Ubs>();
	private ArrayList<ServidorMunicipal> listServidor = new ArrayList<ServidorMunicipal>();
	private ArrayList<Vacina> listVacina = new ArrayList<Vacina>();
	private ArrayList<Lote> listLotes = new ArrayList<Lote>();
	private ArrayList<AgendarVacinacao> listAgendamentos = new ArrayList<AgendarVacinacao>();
	
	public Escritor() {
		
	}
	
	public void escritor(String nomeDoArquivoSerializacao) {
		arquivoDeSerializacao = new File (nomeDoArquivoSerializacao);
	}
	
	public void serializar(ArrayList<Ubs> listinhas) {
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivoDeSerializacao));
			out.writeObject(listinhas);
			out.close();
		}
		catch(IOException e) {
			System.out.println("Erro de entrada/saida");
		}
	}
	
	public ArrayList<Ubs> desserializar() {
		ArrayList<Ubs> listonas = null;
		
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivoDeSerializacao));
			listonas = (ArrayList<Ubs>) in.readObject();
			in.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("Arquivo de serializacao não encontrado");
		}
		catch(IOException e) {
			System.out.println("Erro de entrada/saida na desserializacao");
		}
		catch(ClassNotFoundException e) {
			System.out.println("Classe não existe");
		}
		return listonas;
	}
}

