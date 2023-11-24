package main.service;

import main.entity.Estoque;
import main.entity.Produto;
import main.entity.dto.ProdutoDTO;
import main.exception.ValidateProdutoException;
import main.service.util.MenuUtils;
import main.service.validator.ProdutoValidator;

import java.util.ArrayList;
import java.util.List;

public class ProdutoService {

    public ProdutoService() {
    }

    public void insere(Produto produto) throws ValidateProdutoException {
        ProdutoValidator.insere(produto);
        Estoque.add(produto);
    }

    public void retirarQuant(ProdutoDTO dto) throws ValidateProdutoException {
        Produto produto = ProdutoValidator.retirarQuant(dto);

        produto.retiraQuantidade(dto.getQuant());
    }

    public void adicionarQuant(ProdutoDTO dto) throws ValidateProdutoException {
        Produto produto = ProdutoValidator.adicionarQuant(dto);

        produto.adicionaQuantidade(dto.getQuant());
    }

    public List<String> listar() {
        List<String> strings = new ArrayList<>();
        MenuUtils.getHeader(strings);
        for (Produto prod : Estoque.getProdutos()) {
            strings.add(prod.toString());
        }
        return strings;
    }

    public List<String> listarAbaixoEstoque() {
        List<String> strings = new ArrayList<>();
        MenuUtils.getHeader(strings);
        for (Produto prod : Estoque.getProdutos()) {
            if (prod.getQuantidade() < prod.getQuantidadeMinima()) {
                strings.add(prod.toString());
            }
        }
        return strings;
    }

    public List<String> listarIdENome() {
        List<String> strings = new ArrayList<>();
        MenuUtils.getHeaderResumido(strings);
        Estoque.getProdutos().forEach(item -> strings.add(item.toStringResumido()));
        return strings;
    }

}
