public class Vacina {
    private String nomeVacina;
    private String fabricante;
    private String doenca;

    public Vacina(String nomeVacina, String doenca) {
        this.nomeVacina = nomeVacina;
        this.doenca = doenca;
    }

    public String getNomeVacina() {
        return this.nomeVacina;
    }

    public void setNomeVacina(String nomeVacina) {
        this.nomeVacina = nomeVacina;
    }

    public String getFabricante() {
        return this.fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getDoenca() {
        return this.doenca;
    }

    public void setDoenca(String doenca) {
        this.doenca = doenca;
    }

}
