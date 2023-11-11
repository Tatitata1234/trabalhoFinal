package main.entity.dto;

public class ProdutoDTO {
    private int id;
    private int quant;

    public ProdutoDTO(Integer id, Integer quant) {
        this.id = id;
        this.quant = quant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }
}
