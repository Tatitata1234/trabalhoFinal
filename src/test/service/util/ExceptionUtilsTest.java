package test.service.util;

import main.service.util.ExceptionUtils;
import org.junit.Test;

public class ExceptionUtilsTest {

    @Test
    public void mensagemErro(){
        Exception e = new Exception("Produto inválido");
        ExceptionUtils.trataErro("Produto inválido", e);
    }
}
