package main.service;

import main.entity.Produto;
import main.entity.dto.ProdutoDTO;
import main.exception.ValidateProdutoException;
import main.service.validator.ProdutoValidator;

import java.util.ArrayList;
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

    public List<String> listar() {
        List<String> strings = new ArrayList<>();
        for (Produto prod : produtos) {
            strings.add(prod.toString());
        }
        return strings;
    }

    public List<String> listarAbaixoEstoque() {
        List<String> strings = new ArrayList<>();
        for (Produto prod : produtos) {
            if (prod.getQuantidade() < prod.getQuantidadeMinima()) {
                strings.add(prod.toString());
            }
        }
        return strings;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

}
