package simulador;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fausto on 3/16/16.
 */
public class MetricaRejuvenecimento {
    private Integer iteracoes = 0;
    private Integer nFalhas = 0;
    private List<Integer> nRejuvenecimentoAteFalhar = new ArrayList<>();
    private List<Double> tempoUltimoRejuvenescimento = new ArrayList<>();

    public void incrementaIteracoes(){
        iteracoes++;
    }

    public void incrementaNFalhas(){
        nFalhas++;
    }

    public Double getProbabilidadeDeFalhar(){
        return 1.0 * nFalhas/ iteracoes;
    }

    public void adicionaTempoDesdeUltimoRejuvenescimento(Double tempoUltimoRejuvenescimento){
        this.tempoUltimoRejuvenescimento.add(tempoUltimoRejuvenescimento);
    }

    public void adicionaNRejuvenecimentosAteFalhar(Integer nRejuvenecimentosAteFalhar){
        this.nRejuvenecimentoAteFalhar.add(nRejuvenecimentosAteFalhar);
    }

    public Double getnRejuvenecimentoAteFalhar() {
        return 1.0 * nRejuvenecimentoAteFalhar.stream().mapToInt(x -> x).sum() / nRejuvenecimentoAteFalhar.size();
    }

    public Double getTempoUltimoRejuvenescimento() {
        return tempoUltimoRejuvenescimento.stream().mapToDouble(x -> x).sum() / nRejuvenecimentoAteFalhar.size();
    }
}
