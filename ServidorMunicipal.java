import java.util.Date;

public class ServidorMunicipal {
    private String nomeCompleto;
    private String matricula;
    private Ubs ubs;
    private Date nascimento;

    public ServidorMunicipal(String nomeCompleto, String matricula, Ubs ubs) {
        this.nomeCompleto = nomeCompleto;
        this.matricula = matricula;
        this.ubs = ubs;
    }

    public String getNomeCompleto() {
        return this.nomeCompleto;
    }

    public String getMatricula() {
        return this.matricula;
    }

    public Ubs getUbs() {
        return this.ubs;
    }

    public Date getData() {
        return this.nascimento;
    }

    public void setData(Date nascimento) {
        this.nascimento = nascimento;
    }

    @Override
    public String toString() {
        return nomeCompleto + ";" + matricula + ";" + ubs + ";" + nascimento;
    }

}
