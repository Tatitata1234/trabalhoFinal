package main.entity;

import java.util.ArrayList;
import java.util.List;

public class Estoque {
    private static List<Produto> produtos = new ArrayList<>();

    private static int nextId = 100;

    private Estoque(){
    }

    public static List<Produto> getProdutos() {
        ordenaProdutos();
        return produtos;
    }

    public static void setProdutos(List<Produto> produtos) {
        Estoque.produtos = produtos;
        int tam = produtos.size();
        nextId = produtos.get(tam-1).getCodigo()+1;
    }

    public static void add(Produto produto) {
        if (produto.getCodigo() == 0) {
            produto.setCodigo(nextId);
            nextId++;
            produtos.add(produto);
        } else {
            produtos.add(produto);
        }
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
