package main.service.util;

import main.entity.Produto;
import main.entity.dto.ProdutoDTO;
import main.exception.ValidateProdutoException;
import main.service.ProdutoService;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuUtils {
    public static final String DIGITE_O_ID_DO_PRODUTO = "Digite o id do produto:";
    public static final String DIGITE_A_QUANTIDADE_DO_PRODUTO = "Digite a quantidade do produto:";
    public static final String DIGITE_A_OPCAO_QUE_GOSTARIA_DE_ACESSAR = "Digite a opção que gostaria de acessar:";
    public static final String DIGITE_A_QUANTIDADE_MINIMA_DO_PRODUTO = "Digite a quantidade mínima do produto:";
    public static final String DIGITE_O_NOME_DO_PRODUTO = "Digite o nome do produto:";

    private static Scanner input = new Scanner(System.in);

    private MenuUtils() {
    }

    public static void exibeMenuOpcoes() {
        timeout(0.5);
        System.out.println(ColorUtils.PURPLE_BOLD + "\nMenu produtos:" + ColorUtils.RESET);
        System.out.println("1: Insere");
        System.out.println("2: Adicionar quantidade");
        System.out.println("3: Retirar quantidade");
        System.out.println("4: Listar todos");
        System.out.println("5: Listar todos com estoque baixo");
        System.out.println("6: Sair");
        System.out.println();
    }

    public static Integer getInteger(String message) {
        int id;
        try {
            System.out.println(message);
            id = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException e) {
            input = new Scanner(System.in);
            ExceptionUtils.trataErro("Numero inválido");
            timeout(2);
            id = getInteger(message);
        }
        return id;
    }

    public static String getString(String message) {
        System.out.println(message);
        String nome;
        nome = input.nextLine();
        if (nome.length() > 50){
            ExceptionUtils.trataErro("Nome pode ter até 50 caracteres!");
            input = new Scanner(System.in);
            getString(message);
        }
        return nome;
    }

    public static void sair() {
        timeout(2);
        System.out.println("Saindo....");
        System.out.println("Até a próxima!");
    }

    public static ProdutoDTO getProdutoDTOFromUser() {
        System.out.println("Digite informações do produto:");
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
        timeout(2);
    }

    private static void timeout(double tempoSeg) {
        try {
            Thread.sleep((long) (tempoSeg * 1000));
        } catch (Exception ignored) {
        }
    }

    public static Integer selecionaOpcaoMenu() {
        return MenuUtils.getInteger(DIGITE_A_OPCAO_QUE_GOSTARIA_DE_ACESSAR);
    }

    public static void iniciaSistema() {
        System.out.println("Controle de estoque!");
    }

    public static void processaOpcaoMenu(ProdutoService produtoService, int opcao) {
        ProdutoDTO dto;
        try {
            switch (opcao) {
                case 1 -> {
                    System.out.println();
                    timeout(1);
                    Produto produto = MenuUtils.getProdutoFromUser();
                    System.out.println("Inserindo produto...");
                    produtoService.insere(produto);
                    mensagemSucesso();
                }
                case 2 -> {
                    timeout(1);
                    produtoService.listarIdENome().forEach(System.out::println);
                    dto = MenuUtils.getProdutoDTOFromUser();
                    produtoService.adicionarQuant(dto);
                    System.out.println("Adicionando quantidade...");
                    mensagemSucesso();
                }
                case 3 -> {
                    timeout(1);
                    produtoService.listarIdENome().forEach(System.out::println);
                    dto = MenuUtils.getProdutoDTOFromUser();
                    produtoService.retirarQuant(dto);
                    System.out.println("Retirando quantidade...");
                    mensagemSucesso();
                }
                case 4 -> {
                    List<String> lista = produtoService.listar();
                    lista.forEach(System.out::println);
                    voltarMenu();
                }
                case 5 -> {
                    List<String> lista = produtoService.listarAbaixoEstoque();
                    lista.forEach(System.out::println);
                    voltarMenu();
                }
                case 6 -> {
                    ArquivoUtils.salvarArquivo();
                    MenuUtils.sair();
                }
                default -> {
                    MenuUtils.caminhoErrado();
                    timeout(1.5);
                }
            }
        } catch (ValidateProdutoException e) {
            ExceptionUtils.trataErro("Tente novamente", e);
        }
    }

    private static void mensagemSucesso() {
        timeout(0.5);
        System.out.println(ColorUtils.GREEN_BOLD + "Operação realizada com sucesso!" + ColorUtils.RESET);
        timeout(0.5);
    }

    private static void voltarMenu() {
        System.out.println(ColorUtils.BLUE_BOLD + "Para continuar pressione enter" + ColorUtils.RESET);
        input.nextLine();
    }

    public static void getHeader(List<String> strings) {
        strings.add(ColorUtils.YELLOW + "Código         |Nome                                              |Quantidade     |Quantidade míni|"
        + ColorUtils.RESET);
    }


    public static void getHeaderResumido(List<String> strings) {
        strings.add(ColorUtils.YELLOW + "Código         |Nome                                              |"
                + ColorUtils.RESET);
    }
}
