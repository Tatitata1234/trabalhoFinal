package main.service.validator;

import main.entity.Estoque;
import main.entity.Produto;
import main.entity.dto.ProdutoDTO;
import main.exception.ValidateProdutoException;
import main.service.util.ProdutoUtils;

import java.util.Objects;

public class ProdutoValidator {

    public static final String DO_PRODUTO_INVALIDO = " do produto inválido!";
    private static final String JA_EXISTE = " já existe!";
    private static final String NAO_EXISTE = " não existe!";
    public static final String INVALIDA = " invalida!";

    private ProdutoValidator() {
    }

    public static void insere(Produto produto) throws ValidateProdutoException {
        if (Objects.isNull(produto))
            throw new ValidateProdutoException("Produto nulo");

        if (produto.getCodigo() < 0)
            throw new ValidateProdutoException("Código" + DO_PRODUTO_INVALIDO);

        if (produto.getQuantidadeMinima() <= 0)
            throw new ValidateProdutoException("Quantidade mínima" + DO_PRODUTO_INVALIDO);

        if (produto.getNome().isEmpty() || produto.getNome().isBlank())
            throw new ValidateProdutoException("Nome" + DO_PRODUTO_INVALIDO);

        if (Objects.nonNull(ProdutoUtils.findById(produto.getCodigo())))
            throw new ValidateProdutoException("Código" + JA_EXISTE);

        if (Objects.nonNull(ProdutoUtils.findByNome(produto.getNome(), Estoque.getProdutos())))
            throw new ValidateProdutoException("Nome" + JA_EXISTE);
    }

    public static Produto adicionarQuant(ProdutoDTO dto) throws ValidateProdutoException {
        validaQuantidade(dto.getQuant());

        return getProduto(dto);
    }

    public static Produto retirarQuant(ProdutoDTO dto) throws ValidateProdutoException {
        validaQuantidade(dto.getQuant());

        Produto produto = getProduto(dto);

        validaDiferencaQuantidades(dto, produto);

        return produto;
    }

    private static void validaDiferencaQuantidades(ProdutoDTO dto, Produto produto) throws ValidateProdutoException {
        if (produto.getQuantidade() - dto.getQuant() < 0)
            throw new ValidateProdutoException("Quantidade" + INVALIDA);
    }

    private static void validaQuantidade(int quant) throws ValidateProdutoException {
        if (quant <= 0)
            throw new ValidateProdutoException("Quantidade " + INVALIDA);
    }

    private static Produto getProduto(ProdutoDTO dto) throws ValidateProdutoException {
        Produto produto = ProdutoUtils.findById(dto.getId());
        if (Objects.isNull(produto))
            throw new ValidateProdutoException("Produto" + NAO_EXISTE);
        return produto;
    }
}
