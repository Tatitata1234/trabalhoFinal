package test.service.util;

import main.entity.Produto;
import main.service.util.ProdutoUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProdutoUtilsTest {
    @Test
    public void deveMapearProduto(){
        String[] prod = {"10","Caneta","10","5"};
        Produto produto = ProdutoUtils.fillProduto(prod);

        assertEquals(Integer.parseInt(prod[0]), produto.getCodigo());
        assertEquals(prod[1], produto.getNome());
        assertEquals(Integer.parseInt(prod[2]), produto.getQuantidade());
        assertEquals(Integer.parseInt(prod[3]), produto.getQuantidadeMinima());
    }
}
