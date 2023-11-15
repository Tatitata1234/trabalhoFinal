package main.entity;

import java.util.ArrayList;
import java.util.List;

public class Estoque {
    private static List<Produto> produtos = new ArrayList<>();

    private Estoque(){
    }

    public static List<Produto> getProdutos() {
        return produtos;
    }

    public static List<Produto> getProdutosListado() {
        int i = 0;

        while (i < (Estoque.produtos.size() - 1) ) {
            if (Estoque.produtos.get(i).getCodigo() > Estoque.produtos.get(i+1).getCodigo()){
                Produto temp = Estoque.produtos.get(i);
                Estoque.produtos.set(i,Estoque.produtos.get(i+1));
                Estoque.produtos.set(i+1,temp);
            }
            i++;
        }
        return produtos;
    }

    public static void setProdutos(List<Produto> produtos) {
        Estoque.produtos = produtos;
    }

    public static void add(Produto produto) {
        produtos.add(produto);
    }
}
