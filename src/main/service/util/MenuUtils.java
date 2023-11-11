package main.service.util;

import main.entity.Produto;
import main.entity.dto.ProdutoDTO;
import main.exception.ValidateProdutoException;
import main.service.ProdutoService;

import java.util.List;
import java.util.Scanner;

public class MenuUtils {
    public static final String DIGITE_O_ID_DO_PRODUTO = "Digite o id do produto:";
    public static final String DIGITE_A_QUANTIDADE_DO_PRODUTO = "Digite a quantidade do produto:";
    public static final String DIGITE_A_OPCAO_QUE_GOSTARIA_DE_ACESSAR = "Digite a opção que gostaria de acessar:";
    public static final String DIGITE_A_QUANTIDADE_MINIMA_DO_PRODUTO = "Digite a quantidade mínima do produto:";
    public static final String DIGITE_O_NOME_DO_PRODUTO = "Digite o nome do produto:";

    static Scanner input = new Scanner(System.in);

    private MenuUtils(){
    }

    public static void exibeMenuOpcoes() {
        System.out.println("1: Insere");
        System.out.println("2: Adicionar quantidade");
        System.out.println("3: Retirar quantidade");
        System.out.println("4: Listar todos");
        System.out.println("5: Listar todos com estoque baixo");
        System.out.println("6: Sair");
        System.out.println();
    }

    public static Integer getInteger(String message) {
        System.out.println(message);
        int id;
        id = input.nextInt();
        input.nextLine();
        return id;
    }

    public static String getString(String message) {
        System.out.println(message);
        String nome;
        nome = input.nextLine();
        return nome;
    }

    public static void sair() {
        System.out.println("Saindo....");
        System.out.println("Até a próxima!");
    }

    public static ProdutoDTO getProdutoDTOFromUser() {
        return new ProdutoDTO(
                MenuUtils.getInteger(DIGITE_O_ID_DO_PRODUTO),
                MenuUtils.getInteger(DIGITE_A_QUANTIDADE_DO_PRODUTO)
        );
    }

    public static Produto getProdutoFromUser() {
        return new Produto(
                MenuUtils.getInteger(DIGITE_O_ID_DO_PRODUTO),
                MenuUtils.getString(DIGITE_O_NOME_DO_PRODUTO),
                MenuUtils.getInteger(DIGITE_A_QUANTIDADE_DO_PRODUTO),
                MenuUtils.getInteger(DIGITE_A_QUANTIDADE_MINIMA_DO_PRODUTO)
        );
    }

    public static void caminhoErrado() {
        System.out.println("Essa opção não existe!");
        System.out.println("Tente novamente!");
    }

    public static Integer selecionaOpcaoMenu() {
        return MenuUtils.getInteger(DIGITE_A_OPCAO_QUE_GOSTARIA_DE_ACESSAR);
    }

    public static void iniciaSistema() {
        System.out.println("Controle de estoque!");
        System.out.println("Menu produtos:");
    }

    public static void processaOpcaoMenu(ProdutoService produtoService, int opcao, List<Produto> produtos) {
        ProdutoDTO dto;
        try {
            switch (opcao) {
                case 1 -> {
                    Produto produto = MenuUtils.getProdutoFromUser();
                    produtoService.insere(produto);
                }
                case 2 -> {
                    dto = MenuUtils.getProdutoDTOFromUser();
                    produtoService.adicionarQuant(dto);
                }
                case 3 -> {
                    dto = MenuUtils.getProdutoDTOFromUser();
                    produtoService.retirarQuant(dto);
                }
                case 4 -> produtoService.listar();
                case 5 -> produtoService.listarAbaixoEstoque();
                case 6 -> {
                    ArquivoUtils.salvarArquivo(produtos);
                    MenuUtils.sair();
                }
                default -> MenuUtils.caminhoErrado();
            }
        } catch (ValidateProdutoException e) {
            ExceptionUtils.trataErro("Tente novamente", e);
        }
    }
}
