import java.util.Date;

public class Lote {
    private Vacina vacina;
    private Ubs ubs;
    private Date data;
    private int quantidade;
    private double custoPorDose;
    private String fonte;
    

    public Lote(Vacina vacina, Ubs ubs, Date data, int quantidade, double custoPorDose, String fonte) {
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

    public Ubs getUbs() {
        return this.ubs;
    }

    public Date getData() {
        return this.data;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public double getCustoPorDose() {
        return this.custoPorDose;
    }

    public String getFonte() {
        return this.fonte;
    }

    @Override
    public String toString() {
        return vacina + ";" + ubs + ";" + quantidade + ";" + custoPorDose + ";" + fonte + ";" + data;
    }
}
