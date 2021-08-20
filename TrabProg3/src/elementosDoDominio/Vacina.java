package elementosDoDominio;
import java.io.Serializable;
import java.util.ArrayList;

public class Vacina implements Serializable {
    private String nomeVacina;
    private String fabricante;
    private String doenca;
    private String link;
    private int intervaloMax;
    private int intervaloMin;
    private ArrayList<String> efeitosColaterais;

    public Vacina(String nomeVacina, String doenca, String link) {
    	//super();
        this.nomeVacina = nomeVacina;
        this.doenca = doenca;
        this.link = link;
    }
    
    public Vacina(String nomeVacina, String doenca, int intervaloMin, int intervaloMax) {
    	//super();
        this.nomeVacina = nomeVacina;
        this.doenca = doenca;
        this.intervaloMin = intervaloMin;
        this.intervaloMax = intervaloMax;
    }

    public String getNomeVacina() {
        return this.nomeVacina;
    }

    public String getDoenca() {
        return this.doenca;
    }

    public String getFabricante() {
        return this.fabricante;
    }

    public String getLink() {
		return link;
	}

	public int getIntervaloMax() {
		return intervaloMax;
	}

	public int getIntervaloMin() {
		return intervaloMin;
	}

	public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }
    
    @Override
    public String toString() {
        return nomeVacina + ";" + doenca + ";" + fabricante;
    }

	public ArrayList<String> getEfeitosColaterais() {
		return efeitosColaterais;
	}

	public void setEfeitosColaterais(ArrayList<String> efeitosColaterais) {
		this.efeitosColaterais = efeitosColaterais;
	}

}
