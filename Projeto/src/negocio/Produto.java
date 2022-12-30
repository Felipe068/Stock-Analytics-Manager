package negocio;

public class Produto {

    private String produto;
    private String dataRepor;
    private double valor;
    private double custo;
    private double lucro;
    private int idProdutos;
    private int vendamedia;
    private int estoque;
   

    // Sets e Gets dos atributos
    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }
    
    public String getDataRepor() {
        return dataRepor;
    }

    public void setDataRepor(String dataRepor) {
        this.dataRepor = dataRepor;
    }
    
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }
    
     public double getLucro() {
        return lucro;
    }

    public void setLucro(double lucro) {
        this.lucro = lucro;
    }
    
      public int getVendamedia() {
        return vendamedia;
    }

    public void setVendamedia(int vendamedia) {
        this.vendamedia = vendamedia;
    }

    public int getIdProdutos() {
        return idProdutos;
    }

    public void setIdProdutos(int idProdutos) {
        this.idProdutos = idProdutos;
    }
    
    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

}
