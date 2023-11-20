package main.entity;

import java.util.List;
import java.util.Map;

public class Produto {
    public static final int TAMANHO_NUMERO = 15;
    public static final int TAMANHO_STRING = 50;
    private int codigo;
    private String nome;
    private int quantidade;
    private int quantidadeMinima;


    public Produto(int codigo, String nome, int quantidade, int quantidadeMinima) {
        this.codigo = codigo;
        this.nome = nome;
        this.quantidade = quantidade;
        this.quantidadeMinima = quantidadeMinima;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        List<Map<String,Integer>> campos = List.of(Map.of(String.valueOf(codigo),TAMANHO_NUMERO),
                Map.of( nome, TAMANHO_STRING), Map.of(String.valueOf(quantidade), TAMANHO_NUMERO),
                Map.of(String.valueOf(quantidadeMinima), TAMANHO_NUMERO));

        for (Map<String,Integer> campo: campos) {
            StringBuilder campostr = new StringBuilder(campo.keySet().stream().toList().get(0));
            int i = 0;
            int tamanhoPadrao = campo.get(campostr.toString());
            int tamanhoCampo = campostr.length();
            if (tamanhoCampo < tamanhoPadrao){
                for (i = tamanhoCampo; i < tamanhoPadrao; i++) {
                    campostr.append(" ");
                }
                string.append(campostr.append("|"));
            }
        }
        return string.toString();
    }

    public String toStringResumido() {
        StringBuilder string = new StringBuilder();
        List<Map<String,Integer>> campos = List.of(Map.of(String.valueOf(codigo),TAMANHO_NUMERO),
                Map.of( nome, TAMANHO_STRING));

        for (Map<String,Integer> campo: campos) {
            StringBuilder campostr = new StringBuilder(campo.keySet().stream().toList().get(0));
            int i = 0;
            int tamanhoPadrao = campo.get(campostr.toString());
            int tamanhoCampo = campostr.length();
            if (tamanhoCampo < tamanhoPadrao){
                for (i = tamanhoCampo; i < tamanhoPadrao; i++) {
                    campostr.append(" ");
                }
                string.append(campostr.append("|"));
            }
        }
        return string.toString();
    }

    public String formatarSaida() {
        return codigo + "," + nome + "," + quantidade + "," + quantidadeMinima;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(int quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public void adicionaQuantidade(int quant) {
        this.quantidade += quant;
    }

    public void retiraQuantidade(int quant) {
        this.quantidade -= quant;
    }
}
