package test.service;

import main.entity.Estoque;
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
    private ProdutoService tested;
    private Produto produto;

    @Before
    public void init() {
        produtos.add(new Produto(1, "Caneca de cerâmica", 10, 5));
        produtos.add(new Produto(2, "Estojo rose", 67, 4));
        produtos.add(new Produto(345, "Caneta", 2, 5));
        Estoque.setProdutos(produtos);
        tested = new ProdutoService();
        produto = new Produto(3, "Exemplo", 10, 2);
        dto = new ProdutoDTO(produtos.get(0).getCodigo(), (produtos.get(0).getQuantidade() - 1));
    }

    @Test
    public void deveInserirProduto() throws ValidateProdutoException {
        tested.insere(produto);
        assertEquals(4, Estoque.getProdutos().size());
        assertEquals(produto, Estoque.getProdutos().get(3));
    }

    @Test
    public void shouldThrowErrorWhenProductCodeExists() {
        produto.setCodigo(produtos.get(0).getCodigo());
        assertThrows("Código já existe!", ValidateProdutoException.class, () -> tested.insere(produto));
    }

    @Test
    public void shouldThrowErrorWhenProductNameExists() {
        produto.setNome(produtos.get(0).getNome());
        assertThrows("Nome já existe!", ValidateProdutoException.class, () -> tested.insere(produto));
    }

    @Test
    public void shouldThrowErrorWhenProductIsNull() {
        produto = null;
        assertThrows("Produto nulo", ValidateProdutoException.class, () -> tested.insere(produto));
    }

    @Test
    public void shouldThrowErrorWhenQuantidadeMinimaMenorQueZero() {
        produto.setQuantidadeMinima(-1);
        assertThrows("Quantidade mínima do produto inválida!", ValidateProdutoException.class, () -> tested.insere(produto));
    }

    @Test
    public void shouldThrowErrorWhenCodigoMenorQueZero() {
        produto.setCodigo(-1);
        assertThrows("Código do produto inválida!", ValidateProdutoException.class, () -> tested.insere(produto));
    }

    @Test
    public void shouldThrowErrorWhenNomeVazio() {
        produto.setNome("");
        assertThrows("Código do produto inválida!", ValidateProdutoException.class, () -> tested.insere(produto));
    }

    @Test
    public void deveRetirarQuantidadeComSucesso() throws ValidateProdutoException {
        tested.retirarQuant(dto);
        assertEquals(1, produtos.get(0).getQuantidade());
    }

    @Test
    public void naoDeveRetirarQuantidadeNegativa() {
        dto.setQuant(-1);
        assertThrows("Quantidade invalida!", ValidateProdutoException.class, () -> tested.retirarQuant(dto));
    }

    @Test
    public void naoDeveDeixarQuantidadeNegativa() {
        dto.setQuant(produtos.get(0).getQuantidade() + 1);
        assertThrows("Quantidade invalida!", ValidateProdutoException.class, () -> tested.retirarQuant(dto));
    }

    @Test
    public void naoDeveDeixarQuandoProdutoNaoExiste() {
        dto.setId(12345);
        assertThrows("Produto não existe!", ValidateProdutoException.class, () -> tested.retirarQuant(dto));
    }

    @Test
    public void deveAdicionarQuantidade() throws ValidateProdutoException {
        int quantidade = produtos.get(0).getQuantidade();
        dto.setQuant(quantidade);

        tested.adicionarQuant(dto);

        assertEquals(2 * quantidade, produtos.get(0).getQuantidade());
    }

    @Test
    public void naoDeveAdicionarQuantidadeNegativa() {
        dto.setQuant(-1);
        assertThrows("Quantidade invalida!", ValidateProdutoException.class, () -> tested.adicionarQuant(dto));
    }

    @Test
    public void deveListarProdutos() {
        List<String> retorno = tested.listar();
        assertEquals("[1\t|Caneca de cerâmica\t|10\t|5, 2\t|Estojo rose\t|67\t|4, 345\t|Caneta\t|2\t|5]", Arrays.toString(retorno.toArray()));
    }

    @Test
    public void deveListarProdutosAbaixoDaQuantidadeMinima() {
        List<String> retorno = tested.listarAbaixoEstoque();
        assertEquals("[345\t|Caneta\t|2\t|5]", Arrays.toString(retorno.toArray()));
    }

    @Test
    public void naoDeveListarProdutosAbaixoDaQuantidadeMinima() {
        Estoque.setProdutos(new ArrayList<>());
        List<String> retorno = tested.listarAbaixoEstoque();
        assertEquals("[]", Arrays.toString(retorno.toArray()));
    }

}
