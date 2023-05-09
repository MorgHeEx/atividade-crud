package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import interfaces.crud;

public class produtos implements crud {
  public int id;
  public int categoria_id;
  public String nome;
  public double preco;
  public int quantidade;

  public produtos(){}

  public produtos(int id){
    if(id > 0){
      String sql = "SELECT * FROM produtos WHERE id = ?";
      
      try{
        Connection con = DB.conexao(); 
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet registro = stmt.executeQuery();
        
        while(registro.next()){
          this.setId(registro.getInt("id"));
          this.setNome(registro.getString("nome"));
          this.setPreco(registro.getDouble("preco"));
          this.setQuantidade(registro.getInt("quantidade"));      
          
        }
        
      }catch(SQLException e){      	
        System.out.println("Erro na consulta produtos: "+e.toString()); 
      }
    }
  }

  public int getId() {
    return id;
  }

  public int getCategoria_id() {
    return categoria_id;
  }

  public String getNome() {
    return nome;
  }

  public double getPreco() {
    return preco;
  }

  public int getQuantidade() {
    return quantidade;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setCategoria_id(int categoria_id) {
    this.categoria_id = categoria_id;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setPreco(double preco) {
    this.preco = preco;
  }

  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }

  @Override
  public void adicionar() {
    String sql = "INSERT INTO produtos (categoria_id, nome, preco, quantidade) VALUES (?,?,?,?)";
    try{
      Connection con = DB.conexao();
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setInt(1, this.getCategoria_id());
      stmt.setString(2, this.getNome());
      stmt.setDouble(3, this.getPreco());
      stmt.setInt(4, this.getQuantidade());
      stmt.execute();

    }catch(SQLException e){
      System.out.println("Erro na consulta de produtos"+e.toString());
    }
  }

  @Override
  public void deletar() {
    String sql = "DELETE FROM produtos WHERE id = ?";
    try{
      Connection con = DB.conexao();
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setInt(1, this.getId());
      stmt.executeUpdate();
    }catch(SQLException e){
      System.out.println("Erro ao excluir produto: "+e.toString());
    }
  }

  @Override
  public ArrayList<produtos> listar(){
    String sql = "SELECT * FROM produtos";
    ArrayList<produtos> produtos = new ArrayList<>();

    try{
        Connection con = DB.conexao(); 
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet registros = stmt.executeQuery();

        while(registros.next()){
            produtos temp = new produtos();
            temp.setId(registros.getInt("id"));
            temp.setNome(registros.getString("nome"));
            temp.setPreco(registros.getDouble("preco"));
            temp.setQuantidade(registros.getInt("quantidade"));
                
            produtos.add(temp);	 
        }
    }catch(SQLException e){      	
        System.out.print("Erro ao listar produto: "+e.toString()); 
    }

    return produtos;
}

  @Override
  public void atualizar() {
    String sql = "UPDATE produtos SET categoria_id = ?, nome = ?, preco = ?, quantidade = ?  WHERE id = ? ";
    try{
        Connection con = DB.conexao();     
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, this.getCategoria_id());
        stmt.setString(2, this.getNome());   
        stmt.setDouble(3, this.getPreco());     
        stmt.setInt(4, this.getQuantidade());
        stmt.setInt(5, this.getId());
        stmt.executeUpdate();

    }catch(SQLException e){      	
        System.out.print("Erro ao atualizar produto: "+e.toString()); 
    }   
  }

}