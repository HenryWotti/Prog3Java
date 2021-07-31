public class Ubs {
    private String nome;
    private String sigla;

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

    @Override
    public String toString() {
        return nome + ";" + sigla;
    }
}
