package dominio;

/**
 * Created by John on 04/06/2016.
 */
public class Produto {
    public String tipo;
    public int preco;

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public int getPreco() {
        return preco;
    }
}
