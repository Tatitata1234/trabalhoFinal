package main.service.util;

import main.entity.Produto;

import java.util.List;

public class ProdutoUtils {

    private ProdutoUtils(){
    }
    public static Produto findById(int id, List<Produto> produtos) {
        for (Produto prod : produtos) {
            if (prod.getCodigo() == id)
                return prod;
        }
        return null;
    }

    public static Produto findByNome(String nome, List<Produto> produtos) {
        for (Produto prod: produtos) {
            if (prod.getNome().equals(nome))
                return prod;
        }
        return null;
    }
}
