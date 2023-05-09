package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
  public static String servidor = "127.0.0.1:3306";
  public static String usuario = "root";
  public static String senha = "";
  public static String nome_banco = "crud";

  public static Connection conexao() {
    Connection conexao = null;

    try {

      Class.forName("com.mysql.cj.jdbc.Driver");

      conexao = DriverManager.getConnection("jdbc:mysql://" + servidor + "/" + nome_banco, usuario, senha);

    } catch (SQLException e) {
      System.out.println("Erro de conexão" + e.toString());

    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    }

    return conexao;

  }

}