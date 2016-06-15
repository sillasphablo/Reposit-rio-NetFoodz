package dominio;

import java.util.List;
import dominio.Produto;

/**
 * Created by John on 08/06/2016.
 */
public class Carrinho {
    public List listaDeProdutos;




    public void setListaDeProdutos(List listaDeProdutos) {
        this.listaDeProdutos = listaDeProdutos;
    }

    public List getListaDeProdutos() {
        return listaDeProdutos;
    }

    public void adicionarProduto (Produto produto){
        this.listaDeProdutos.add(produto);
    }
    public void retirarProduto (Produto produto){

    }
}