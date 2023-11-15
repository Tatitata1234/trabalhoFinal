package test.service;

import main.entity.Produto;
import main.entity.dto.ProdutoDTO;
import main.exception.ValidateProdutoException;
import main.service.ProdutoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ProdutoServiceTest {
    private List<Produto> produtos = new ArrayList<>();
    private ProdutoDTO dto;

    @Before
    public void init() {
        produtos.add(new Produto(1, "Caneca de cerâmica", 10, 5));
        produtos.add(new Produto(2, "Estojo rose", 67, 4));
        produtos.add(new Produto(345, "Caneta", 2, 5));

    }

    @Test
    public void deveInserirProduto() throws ValidateProdutoException {
        Produto expected = new Produto(3, "Exemplo", 10, 2);

        ProdutoService tested = new ProdutoService(produtos);
        tested.insere(expected);
        assertEquals(4, tested.getProdutos().size());
        assertEquals(expected, tested.getProdutos().get(3));
    }

    @Test
    public void shouldThrowErrorWhenProductCodeExists() {
        ProdutoService tested = new ProdutoService(produtos);

        Produto produto = new Produto(345, "Caneta", 2, 5);

        assertThrows("Código já existe!", ValidateProdutoException.class, () -> tested.insere(produto));
    }

    @Test
    public void shouldThrowErrorWhenProductNameExists() {
        ProdutoService tested = new ProdutoService(produtos);

        Produto produto = new Produto(346, "Caneta", 2, 5);

        assertThrows("Nome já existe!", ValidateProdutoException.class, () -> tested.insere(produto));
    }

    @Test
    public void shouldThrowErrorWhenProductIsNull() {
        ProdutoService tested = new ProdutoService(produtos);

        Produto produto = null;

        assertThrows("Produto nulo", ValidateProdutoException.class, () -> tested.insere(produto));
    }

    @Test
    public void shouldThrowErrorWhenQuantidadeMinimaMenorQueZero() {
        ProdutoService tested = new ProdutoService(produtos);

        Produto produto = new Produto(346, "Caneta", 2, -1);

        assertThrows("Quantidade mínima do produto inválida!", ValidateProdutoException.class, () -> tested.insere(produto));
    }

    @Test
    public void shouldThrowErrorWhenCodigoMenorQueZero() {
        ProdutoService tested = new ProdutoService(produtos);

        Produto produto = new Produto(-1, "Caneta", 2, 1);

        assertThrows("Código do produto inválida!", ValidateProdutoException.class, () -> tested.insere(produto));
    }

    @Test
    public void shouldThrowErrorWhenNomeVazio() {
        ProdutoService tested = new ProdutoService(produtos);

        Produto produto = new Produto(17, "", 2, 1);

        assertThrows("Código do produto inválida!", ValidateProdutoException.class, () -> tested.insere(produto));
    }

    @Test
    public void deveRetirarQuantidadeComSucesso() throws ValidateProdutoException {
        ProdutoService tested = new ProdutoService(produtos);
        dto = new ProdutoDTO(produtos.get(0).getCodigo(), (produtos.get(0).getQuantidade() - 1));

        tested.retirarQuant(dto);

        assertEquals(1, produtos.get(0).getQuantidade());
    }

    @Test
    public void naoDeveRetirarQuantidadeNegativa() {
        ProdutoService tested = new ProdutoService(produtos);
        dto = new ProdutoDTO(produtos.get(0).getCodigo(), -1);

        assertThrows("Quantidade invalida!", ValidateProdutoException.class, () -> tested.retirarQuant(dto));
    }

    @Test
    public void naoDeveDeixarQuantidadeNegativa() {
        ProdutoService tested = new ProdutoService(produtos);
        dto = new ProdutoDTO(produtos.get(0).getCodigo(), produtos.get(0).getQuantidade() + 1);

        assertThrows("Quantidade invalida!", ValidateProdutoException.class, () -> tested.retirarQuant(dto));
    }

    @Test
    public void naoDeveDeixarQuandoProdutoNaoExiste() {
        ProdutoService tested = new ProdutoService(produtos);
        dto = new ProdutoDTO(12345, 1);

        assertThrows("Produto não existe!", ValidateProdutoException.class, () -> tested.retirarQuant(dto));
    }

    @Test
    public void deveAdicionarQuantidade() throws ValidateProdutoException {
        ProdutoService tested = new ProdutoService(produtos);
        int quantidade = produtos.get(0).getQuantidade();
        dto = new ProdutoDTO(produtos.get(0).getCodigo(), quantidade);

        tested.adicionarQuant(dto);

        assertEquals(2 * quantidade, produtos.get(0).getQuantidade());
    }

    @Test
    public void naoDeveAdicionarQuantidadeNegativa() {
        ProdutoService tested = new ProdutoService(produtos);
        dto = new ProdutoDTO(produtos.get(0).getCodigo(), -1);

        assertThrows("Quantidade invalida!", ValidateProdutoException.class, () -> tested.adicionarQuant(dto));
    }

    @Test
    public void deveListarProdutos() {
        ProdutoService tested = new ProdutoService(produtos);

        List<String> retorno = tested.listar();

        assertEquals("[1\t|Caneca de cerâmica\t|10\t|5, 2\t|Estojo rose\t|67\t|4, 345\t|Caneta\t|2\t|5]", Arrays.toString(retorno.toArray()));
    }

    @Test
    public void deveListarProdutosAbaixoDaQuantidadeMinima() {
        ProdutoService tested = new ProdutoService(produtos);

        List<String> retorno = tested.listarAbaixoEstoque();

        assertEquals("[345\t|Caneta\t|2\t|5]", Arrays.toString(retorno.toArray()));
    }

    @Test
    public void naoDeveListarProdutosAbaixoDaQuantidadeMinima() {
        ProdutoService tested = new ProdutoService(new ArrayList<>());

        List<String> retorno = tested.listarAbaixoEstoque();

        assertEquals("[]", Arrays.toString(retorno.toArray()));
    }

}
