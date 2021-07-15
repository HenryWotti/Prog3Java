import java.util.Date;

public class ReceberLoteVacina {
    private Vacina vacina;
    private Ubs ubs;
    private Date data;
    private int quantidade;
    private double custoPorDose;
    private String fonte;

    public ReceberLoteVacina(Vacina vacina, Ubs ubs, Date data, int quantidade, double custoPorDose, String fonte) {
        this.vacina = vacina;
        this.ubs = ubs;
        this.data = data;
        this.quantidade = quantidade;
        this.custoPorDose = custoPorDose;
        this.fonte = fonte;
    }

    public Vacina getVacina() {
        return this.vacina;
    }

    public void setVacina(Vacina vacina) {
        this.vacina = vacina;
    }

    public Ubs getUbs() {
        return this.ubs;
    }

    public void setUbs(Ubs ubs) {
        this.ubs = ubs;
    }

    public Date getData() {
        return this.data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getCustoPorDose() {
        return this.custoPorDose;
    }

    public void setCustoPorDose(double custoPorDose) {
        this.custoPorDose = custoPorDose;
    }

    public String getFonte() {
        return this.fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

}
