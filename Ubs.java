import java.util.Date;

public class Ubs {
    private String nome;
    private String sigla;
    private int totalVacinados = 0;
    private int totalAgendados = 0;
    private int totalCancelamentos = 0;
    private Date periodoInicial;
    private Date periodoFinal;
    
    public int getTotalVacinados() {
		return totalVacinados;
	}

	public int getTotalAgendados() {
		return totalAgendados;
	}

	public int getTotalCancelamentos() {
		return totalCancelamentos;
	}
	
    public Date getPeriodoInicial() {
		return periodoInicial;
	}

	public void setPeriodoInicial(Date periodoInicial) {
		this.periodoInicial = periodoInicial;
	}

	public Date getPeriodoFinal() {
		return periodoFinal;
	}

	public void setPeriodoFinal(Date periodoFinal) {
		this.periodoFinal = periodoFinal;
	}
	
    public Ubs(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
    }

    public String getNome() {
        return this.nome;
    }

    public String getSigla() {
        return this.sigla;
    }

    public void contaVacinados() {
    	this.totalVacinados++;
    }
    
    public void contaAgendados() {
    	this.totalAgendados++;
    }
    
    public void contaCancelados() {
    	this.totalCancelamentos++;
    }
    
    public void daPeriodoInicial(Date dataRegistrada) {
         if(dataRegistrada.compareTo(this.periodoInicial) < 0) {
            System.out.println("Date 1 occurs before Date 2");
            this.periodoInicial = dataRegistrada; //nova data registrada eh anterior, portanto subtitui a antiga data
         }
    }
    
    public void daPeriodoFinal(Date dataRegistrada) {
        if(dataRegistrada.compareTo(this.periodoFinal) > 0) {
           System.out.println("Date 1 occurs before Date 2");
           this.periodoFinal = dataRegistrada; //nova data registrada eh posterior, portanto subtitui a antiga data
        }
        
   }
    @Override
    public String toString() {
        return nome + ";" + sigla + ";" + totalAgendados + ";" + totalVacinados + "/";
    }
}
