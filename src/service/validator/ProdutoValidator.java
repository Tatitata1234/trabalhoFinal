package service.validator;

import entity.Produto;
import entity.dto.ProdutoDTO;
import exception.ValidateProdutoException;
import service.util.ProdutoUtils;

import java.util.List;
import java.util.Objects;

public class ProdutoValidator {

    public static final String DO_PRODUTO_INVALIDO = " do produto inválido!";
    private static final String JA_EXISTE = " já existe!";
            private static final String NAO_EXISTE = " não existe!";
    public static final String INVALIDA = " invalida!";

    private ProdutoValidator() {
    }

    public static void insere(Produto produto, List<Produto> produtos) throws ValidateProdutoException {
        if (Objects.isNull(produto))
            throw new ValidateProdutoException("Produto nulo");

        if (produto.getCodigo() == 0)
            throw new ValidateProdutoException("Código" + DO_PRODUTO_INVALIDO);

        if (produto.getQuantidadeMinima() == 0)
            throw new ValidateProdutoException("Quantidade mínima" + DO_PRODUTO_INVALIDO);

        if (produto.getNome().isEmpty() || produto.getNome().isBlank())
            throw new ValidateProdutoException("Nome" + DO_PRODUTO_INVALIDO);

        if (Objects.nonNull(ProdutoUtils.findById(produto.getCodigo(), produtos)))
            throw new ValidateProdutoException("Código" + JA_EXISTE);

        if (Objects.nonNull(ProdutoUtils.findByNome(produto.getNome(), produtos)))
            throw new ValidateProdutoException("Nome" + JA_EXISTE);
    }

    public static void adicionarQuant(ProdutoDTO dto, List<Produto> produtos) throws ValidateProdutoException {
        if (dto.getQuant() <= 0)
            throw new ValidateProdutoException("Quantidade " + INVALIDA);
        if (Objects.isNull(ProdutoUtils.findById(dto.getId(), produtos)))
            throw new ValidateProdutoException("Produto" + NAO_EXISTE);
    }

    public static void retirarQuant(ProdutoDTO dto, List<Produto> produtos) {
    }
}
