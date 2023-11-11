package test.service;

import main.entity.Produto;
import main.exception.ValidateProdutoException;
import main.service.ProdutoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ProdutoServiceTest {
    List<Produto> produtos =new ArrayList<>();

    @Before
    public void init(){
        produtos.add(new Produto(1,"Caneca de cerâmica",10,5));
        produtos.add(new Produto(2,"Estojo rose",67,4));
        produtos.add(new Produto(345,"Caneta",2,5));
    }
    @Test
    public void shouldWhen() throws ValidateProdutoException {
        ProdutoService tested = new ProdutoService(produtos);

        Produto produto = new Produto(3, "Exemplo", 10, 2);

        tested.insere(produto);

        assertEquals(4, tested.getProdutos().size());
        assertEquals(produto, tested.getProdutos().get(3));
    }

    @Test
    public void shouldThrowErrorWhenProductCodeExists() {
        ProdutoService tested = new ProdutoService(produtos);

        Produto produto = new Produto(345,"Caneta",2,5);

    assertThrows("Código já existe!", ValidateProdutoException.class,()->tested.insere(produto));
    }

    @Test
    public void shouldThrowErrorWhenProductNameExists() {
        ProdutoService tested = new ProdutoService(produtos);

        Produto produto = new Produto(346,"Caneta",2,5);

        assertThrows("Nome já existe!", ValidateProdutoException.class,()->tested.insere(produto));
    }

    @Test
    public void shouldThrowErrorWhenProductIsNull() {
        ProdutoService tested = new ProdutoService(produtos);

        Produto produto = null;

        assertThrows("Produto nulo", ValidateProdutoException.class,()->tested.insere(produto));
    }

    @Test
    public void shouldThrowErrorWhenQuantidadeMinimaMenorQueZero() {
        ProdutoService tested = new ProdutoService(produtos);

        Produto produto = new Produto(346,"Caneta",2,-1);

        assertThrows("Quantidade mínima do produto inválida!", ValidateProdutoException.class,()->tested.insere(produto));
    }

    @Test
    public void shouldThrowErrorWhenCodigoMenorQueZero() {
        ProdutoService tested = new ProdutoService(produtos);

        Produto produto = new Produto(-1,"Caneta",2,1);

        assertThrows("Código do produto inválida!", ValidateProdutoException.class,()->tested.insere(produto));
    }

    @Test
    public void shouldThrowErrorWhenNomeVazio() {
        ProdutoService tested = new ProdutoService(produtos);

        Produto produto = new Produto(17,"",2,1);

        assertThrows("Código do produto inválida!", ValidateProdutoException.class,()->tested.insere(produto));
    }

}
