import entity.Produto;
import entity.dto.ProdutoDTO;
import exception.ValidateProdutoException;
import service.ProdutoService;
import service.util.ArquivoUtils;
import service.util.MenuUtils;

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
            MenuUtils.processaOpcaoMenu(produtoService, opcao);
        } while (opcao != 6);
    }
}
