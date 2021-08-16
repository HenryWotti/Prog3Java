package interfaceUsuario;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import elementosDoDominio.AgendarVacinacao;
import elementosDoDominio.ServidorMunicipal;
import elementosDoDominio.Ubs;
import elementosDoDominio.Vacina;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Inputs {
	
	Map<String, Ubs> mapaUbs = new HashMap<>();
	
	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	public boolean verificaEntradaNumerica(String matricula) { //serve para validar a matricula do servidores e cpf nos agendamentos
		char[] matri = new char[matricula.length()];

        // transformando String em vetor de char para verificar se seus caracteres sao todos numeros
        for(int i = 0; i < matricula.length(); i++) {
        	matri[i] = matricula.charAt(i);
        }
            
		int[] mat = new int[matricula.length()];
        for(int i = 0; i < matricula.length(); i++) {
        	mat[i]=Character.getNumericValue(matri[i]);
        }
        
        //verificando todos os caracteres da matricula informada
		for(int i = 0; i < matricula.length(); i++) {
			if(mat[i] >= 0 && mat[i] <=9) { //se for valido, prossegue
				continue;
			}else {
				//se dado invalido
				return false;
			}
		}
		
		return true; //se dado valido
	}
	
	
	public boolean verificaData(String datinha) {
		Date data;
		try {
        	data = formato.parse(datinha);		
        }catch(ParseException e) {
        	return false;
        }
		return true;
	}
	
	public boolean verificaDataEHora(String dataHora) {
		Date data;
		try {
        	data = formato2.parse(dataHora);		
        }catch(ParseException e) {
        	return false;
        }
		return true;
	}
	
	public boolean verificaUBS(Ubs unidadeSaude) {
		if(unidadeSaude == null) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean verificaVacina(Vacina vacina) {
		if(vacina == null) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean verificaCPF(AgendarVacinacao agendamento) {
		if(agendamento == null) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean verificaUBSrepetida(Ubs ubs) {
		if(ubs == null) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public boolean verificaVacinaRepetida(Vacina vac) {
		if(vac == null) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean verificaMatriculaServidorRepetido(ServidorMunicipal servidor) {
		if(servidor == null) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean verificaAgendamentoRepetido(AgendarVacinacao agendamento) {
		if(agendamento == null) {
			return true;
		}else {
			return false;
		}
	}
}


