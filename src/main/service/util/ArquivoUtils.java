package main.service.util;

import main.entity.Estoque;
import main.entity.Produto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArquivoUtils {

    private ArquivoUtils(){
    }

    public static List<Produto> leArquivo() {
        List<Produto> produtos = new ArrayList<>();
        try {
            InputStream is = new FileInputStream("produtos.txt");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String s = br.readLine();

            while (s != null) {
                String[] temp = s.split(",");
                Produto produto = ProdutoUtils.fillProduto(temp);
                produtos.add(produto);
                s = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            ExceptionUtils.trataErro("Não conseguiu ler o arquivo", e);
        }
        return produtos;
    }



    public static void salvarArquivo() {
        try {
            OutputStream os = new FileOutputStream("produtos2.txt");
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);

            for (Produto prod : Estoque.getProdutos()) {
                bw.write(prod.formatarSaida() + "\n");
            }

            bw.close();
        } catch (IOException e){
            ExceptionUtils.trataErro("Não conseguiu gravar o arquivo", e);
        }
    }
}
