import java.util.Date;

public class ServidorMunicipal {
    private String nomeCompleto;
    private int matricula;
    private Ubs ubs;
    private Date nascimento;

    public ServidorMunicipal(String nomeCompleto, int matricula, Ubs ubs) {
        this.nomeCompleto = nomeCompleto;
        this.matricula = matricula;
        this.ubs = ubs;
    }

    public String getNomeCompleto() {
        return this.nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public int getMatricula() {
        return this.matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public Ubs getUbs() {
        return this.ubs;
    }

    public void setUbs(Ubs ubs) {
        this.ubs = ubs;
    }

    public Date getData() {
        return this.nascimento;
    }

    public void setData(Date nascimento) {
        this.nascimento = nascimento;
    }

}
