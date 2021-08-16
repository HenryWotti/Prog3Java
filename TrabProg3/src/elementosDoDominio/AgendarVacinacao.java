package elementosDoDominio;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class AgendarVacinacao implements Serializable {
    private Date dataHora;
    private Ubs ubs;
    private String nome;
    private Date dataNascimento;
    private String cpf;
    private boolean statusAgendamento;
    private boolean statusEfetuado;
    private Vacina vacinaAgendada;

    public AgendarVacinacao(Date dataHora, Ubs ubs, String nome, Date dataNascimento, String cpf, Vacina vac) {
        this.dataHora = dataHora;
        this.ubs = ubs;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.statusAgendamento = true;
        this.statusEfetuado = false;
        this.vacinaAgendada = vac;
    }

    public int getIdade() {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(this.dataNascimento);
        int dateYear = calendar.get(Calendar.YEAR);
    	
        int idade = 2021 - dateYear;    			
    			
    	return idade;
    }
    
    public Vacina getVacinaAgendada() {
		return vacinaAgendada;
	}

	public Date getDataHora() {
        return this.dataHora;
    }

    public Ubs getUbs() {
        return this.ubs;
    }

    public String getNome() {
        return this.nome;
    }

    public Date getDataNascimento() {
        return this.dataNascimento;
    }

    public String getCpf() {
        return this.cpf;
    }

    public boolean getStatusAgendamento() {
        return this.statusAgendamento;
    }

    public boolean getStatusEfetuado() {
        return this.statusEfetuado;
    }

    public void cancela() {
        this.statusAgendamento = false;
    }

    public void efetuaVacinacao() {
        this.statusEfetuado = true;
    }

    @Override
    public String toString() {
        return ubs + ";" + nome + ";" + cpf + ";" + "StatusAgendamento:" + statusAgendamento + ";" + "StatusEfetuado:"
                + statusEfetuado + ";" + dataHora + ";" + dataNascimento + ";"
                + printaStatus(statusEfetuado, statusAgendamento);
    }

    public String printaStatus(boolean statusEfetuado, boolean statusAgendado) {
        if (statusEfetuado == true) {
            return "Situação da Vacinação: Efetuada";
        } else if (statusEfetuado == false && statusAgendado == true) {
            return "Situação da Vacinação: Agendada";
        } else {
            return "Situação da Vacinação: Cancelada";
        }
    }

}
