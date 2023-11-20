package main.entity;

import java.util.ArrayList;
import java.util.List;

public class Estoque {
    private static List<Produto> produtos = new ArrayList<>();

    private Estoque(){
    }

    public static List<Produto> getProdutos() {
        ordenaProdutos();
        return produtos;
    }

    public static void setProdutos(List<Produto> produtos) {
        Estoque.produtos = produtos;
    }

    public static void add(Produto produto) {
        produtos.add(produto);
    }

    private static void ordenaProdutos() {
        int i = 0;

        while (i < (produtos.size() - 1) ) {
            if (produtos.get(i).getCodigo() > produtos.get(i+1).getCodigo()){
                Produto temp = produtos.get(i);
                produtos.set(i,produtos.get(i+1));
                produtos.set(i+1,temp);
            }
            i++;
        }
    }
}
