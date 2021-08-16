package entradaSaida;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import elementosDoDominio.Listas;

public class Escritor  implements Serializable{
	private File arquivoDeSerializacao;
	
	
	
	public void escritor(String nomeDoArquivoSerializacao) {
		arquivoDeSerializacao = new File (nomeDoArquivoSerializacao);
	}
	
	public void serializar(Listas listinhas) {
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivoDeSerializacao));
			out.writeObject(listinhas);
			out.close();
		}
		catch(IOException e) {
			System.out.println("Erro de entrada/saida");
		}
	}
	
	public Listas desserializar() {
		Listas listonas = null;
		
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivoDeSerializacao));
			listonas = (Listas) in.readObject();
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

