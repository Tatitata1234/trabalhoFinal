package main;

import main.entity.Estoque;
import main.service.ProdutoService;
import main.service.util.ArquivoUtils;
import main.service.util.MenuUtils;

public class Main {
    public static void main(String[] args) {
        Estoque.setProdutos(ArquivoUtils.leArquivo());
        ProdutoService produtoService = new ProdutoService();
        int opcao;
        MenuUtils.iniciaSistema();

        do {
            MenuUtils.exibeMenuOpcoes();
            opcao = MenuUtils.selecionaOpcaoMenu();
            MenuUtils.processaOpcaoMenu(produtoService, opcao);
        } while (opcao != 6);
    }
}
