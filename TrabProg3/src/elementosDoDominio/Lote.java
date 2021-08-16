package elementosDoDominio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class Lote implements Serializable {
    private Vacina vacina;
    private Ubs ubs;
    private Date data;
    private int quantidade;
    private double custoPorDose;
    private String fonte;
    
    public Lote() {
    	
    }
    
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
    
    public void relatorio2(ArrayList<Vacina> listaVacinas, ArrayList<String> listaNomeDoencas, ArrayList<Lote> listaLotes) {
    	int contTotalDoses = 0;
		int contDosesFederal = 0;
		double contCustoFederal = 0;
		int contDosesEstadual = 0;
		double contCustoEstadual = 0;
		String federal = "f";
		double mediaFederal = 0;
		double mediaEstadual = 0;
		
		System.out.println("Entregas de Vacina por Doença");
		ordenaVacina(listaVacinas);
		for(int i = 0; i < listaNomeDoencas.size(); i++) {
			System.out.println(listaNomeDoencas.get(i));
			
			for (int j = 0; j < listaVacinas.size(); j++) {
               
				if(listaNomeDoencas.get(i).compareTo(listaVacinas.get(j).getDoenca()) == 0) {
                	System.out.println(listaVacinas.get(j));
                	
                	for(int k = 0; k < listaLotes.size(); k++) {
                		if(listaVacinas.get(j).getNomeVacina().compareTo(listaLotes.get(k).getVacina().getNomeVacina()) == 0) {
                			contTotalDoses += listaLotes.get(k).getQuantidade(); //quantidade total de doses recebidas para a doenca
                			
                			if(listaLotes.get(k).getFonte().compareTo(federal) == 0){
                				contDosesFederal+= listaLotes.get(k).getQuantidade(); //total de doses vindas do governo federal
                				contCustoFederal += listaLotes.get(k).getCustoPorDose() *  listaLotes.get(k).getQuantidade(); //soma dos custos das doses federais com peso
                			}else {
                				contDosesEstadual += listaLotes.get(k).getQuantidade(); //total de doses vindas do governo estadual
                				contCustoEstadual += listaLotes.get(k).getCustoPorDose() *  listaLotes.get(k).getQuantidade(); //soma dos custos das doses estaduais com peso
                			}                         			
                			
                		}
                	}
                	mediaFederal = contCustoFederal / contDosesFederal; //custo medio das doses entregues pelo governo federal
                	mediaEstadual = contCustoEstadual / contDosesEstadual; //custo medio das doses entregues pelo governo estadual
                }
            }
			System.out.println("Total de doses para essa doenca: " + contTotalDoses);
        	System.out.println("Media custo Federal: " + mediaFederal);
        	System.out.println("Media custo Estadual: " + mediaEstadual);
        	contDosesFederal = 0;
        	contDosesEstadual = 0;
        	contCustoFederal = 0;
        	contCustoEstadual = 0;
        	contTotalDoses = 0;
		}
    }
		
	public void ordenaVacina(ArrayList<Vacina> listaVacina) {
	    	Collections.sort(listaVacina, Comparator.comparing(Vacina::getNomeVacina));
	}

    @Override
    public String toString() {
        return vacina + ";" + ubs + ";" + quantidade + ";" + custoPorDose + ";" + fonte + ";" + data;
    }
}
