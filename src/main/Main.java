package main;

import main.entity.Produto;
import main.service.ProdutoService;
import main.service.util.ArquivoUtils;
import main.service.util.MenuUtils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Produto> produtos = ArquivoUtils.leArquivo();
        ProdutoService produtoService = new ProdutoService(produtos);
        int opcao;
        MenuUtils.iniciaSistema();

        do {
            MenuUtils.exibeMenuOpcoes();
            opcao = MenuUtils.selecionaOpcaoMenu();
            MenuUtils.processaOpcaoMenu(produtoService, opcao, produtos);
        } while (opcao != 6);
    }
}
