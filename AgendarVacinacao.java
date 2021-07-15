import java.util.Date;

public class AgendarVacinacao {
    private Date dataHora;
    private Ubs ubs;
    private String nome;
    private Date dataNascimento;
    private String cpf;
    private boolean statusAgendamento;
    private boolean statusEfetuado;

    public AgendarVacinacao(Date dataHora, Ubs ubs, String nome, Date dataNascimento, String cpf) {
        this.dataHora = dataHora;
        this.ubs = ubs;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.statusAgendamento = true;
        this.statusEfetuado = false;
    }

    public Date getDataHora() {
        return this.dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public Ubs getUbs() {
        return this.ubs;
    }

    public void setUbs(Ubs ubs) {
        this.ubs = ubs;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
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

    public void printaStatus() {
        if (this.statusEfetuado == true) {

            System.out.println("Situação da Vacinação: Efetuada");
        }
        if (this.statusAgendamento == false) {
            System.out.println("Situação da Vacinação: Cancelada");
        }
        if (this.statusEfetuado == false && this.statusAgendamento == true) {
            System.out.println("Situação da Vacinação: Agendada");
        }
    }
}
