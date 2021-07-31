public class Vacina {
    private String nomeVacina;
    private String fabricante;
    private String doenca;
    private String link;
    private int intervaloMax;
    private int intervaloMin;

    public Vacina(String nomeVacina, String doenca) {
        this.nomeVacina = nomeVacina;
        this.doenca = doenca;
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

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return this.link;
    }

    public int getIntervaloMax() {
        return this.intervaloMax;
    }

    public int getIntervaloMin() {
        return this.intervaloMin;
    }

    public void setIntervaloMax(int intervaloMax) {
        this.intervaloMax = intervaloMax;
    }

    public void setIntervaloMin(int intervaloMin) {
        this.intervaloMin = intervaloMin;
    }

    @Override
    public String toString() {
        return nomeVacina + ";" + doenca + ";" + fabricante;
    }

}
