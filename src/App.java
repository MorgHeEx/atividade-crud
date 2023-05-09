import classes.produtos;
import classes.categorias;
import classes.clientes;
import classes.vendas;
import classes.vendedores;
import classes.DB;

public class App {
  public static void main(String[] args) throws Exception {
    produtos prod = new produtos();
    prod.setId(1);
    System.out.println(prod.getNome());
  }
}