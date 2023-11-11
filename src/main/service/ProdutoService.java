package main.service;

import main.entity.Produto;
import main.entity.dto.ProdutoDTO;
import main.exception.ValidateProdutoException;
import main.service.validator.ProdutoValidator;

import java.util.List;

public class ProdutoService {
    private List<Produto> produtos;

    public ProdutoService(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public void insere(Produto produto) throws ValidateProdutoException {
        ProdutoValidator.insere(produto, produtos);
        produtos.add(produto);
    }

    public void retirarQuant(ProdutoDTO dto) throws ValidateProdutoException {
        Produto produto = ProdutoValidator.retirarQuant(dto, produtos);

        produto.retiraQuantidade(dto.getQuant());
    }

    public void adicionarQuant(ProdutoDTO dto) throws ValidateProdutoException {
        Produto produto = ProdutoValidator.adicionarQuant(dto, produtos);

        produto.adicionaQuantidade(dto.getQuant());
    }

    public void listar() {
        for (Produto prod : produtos) {
            System.out.println(prod.toString());
        }
    }

    public void listarAbaixoEstoque() {
        for (Produto prod : produtos) {
            if (prod.getQuantidade() < prod.getQuantidadeMinima())
                System.out.println(prod.toString());
        }
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

}
