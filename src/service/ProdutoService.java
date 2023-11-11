package service;

import entity.Produto;
import entity.dto.ProdutoDTO;
import exception.ValidateProdutoException;
import service.util.ProdutoUtils;
import service.validator.ProdutoValidator;

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

    public void retirarQuant(ProdutoDTO dto) {
        ProdutoValidator.retirarQuant(dto, produtos);

        Produto produto = ProdutoUtils.findById(dto.getId(), produtos);

        produto.adicionaQuantidade(dto.getQuant());
    }

    public void adicionarQuant(ProdutoDTO dto) throws ValidateProdutoException {
        ProdutoValidator.adicionarQuant(dto, produtos);

        Produto produto = ProdutoUtils.findById(dto.getId(), produtos);

        produto.adicionaQuantidade(dto.getQuant());
    }

    public void listar() {
    }

    public void listarAbaixoEstoque() {
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
