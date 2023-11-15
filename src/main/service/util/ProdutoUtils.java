package main.service.util;

import main.entity.Estoque;
import main.entity.Produto;

import java.util.List;

public class ProdutoUtils {

    private ProdutoUtils(){
    }
    public static Produto findById(int id) {
        for (Produto prod : Estoque.getProdutos()) {
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

    public static Produto fillProduto(String[] temp) {
        return new Produto(
                Integer.parseInt(temp[0]),
                temp[1],
                Integer.parseInt(temp[2]),
                Integer.parseInt(temp[3])
        );
    }
}
