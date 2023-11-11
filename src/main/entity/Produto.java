package main.entity;

public class Produto {
    private int codigo;
    private String nome;
    private int quantidade;
    private int quantidadeMinima;

    public Produto() {
    }

    public Produto(int codigo, String nome, int quantidade, int quantidadeMinima) {
        this.codigo = codigo;
        this.nome = nome;
        this.quantidade = quantidade;
        this.quantidadeMinima = quantidadeMinima;
    }

    @Override
    public String toString() {
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
