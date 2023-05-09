package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import interfaces.crud;

public class categorias implements crud {
  public int id;
  public String nome;

  public categorias(){}

  public categorias(int id){
    if(id > 0){
      String sql = "SELECT * FROM categorias WHERE id = ?";
      
      try{
        Connection con = DB.conexao(); 
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet registro = stmt.executeQuery();
        
        while(registro.next()){
          this.setId(registro.getInt("id"));
          this.setNome(registro.getString("nome"));
          
        }
        
      }catch(SQLException e){      	
        System.out.println("Erro na consulta de categorias: " + e.toString()); 
      }
    }
  }

  public int getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  @Override
  public void adicionar() {
    String sql = "INSERT INTO categorias (nome) VALUES (?)";
    try{
      Connection con = DB.conexao();
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setDouble(1, this.getId());
      stmt.setString(2, this.getNome());
      stmt.execute();

    }catch(SQLException e){
      System.out.println("Erro na consulta de categorias: "+e.toString());
    }
  }

  @Override
  public void deletar() {
    String sql = "DELETE FROM categorias WHERE id = ?";
    try{
      Connection con = DB.conexao();
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setInt(1, this.getId());
      stmt.executeUpdate();
    }catch(SQLException e){
      System.out.println("Erro ao excluir categoria: "+e.toString());
    }
  }

  @Override
  public ArrayList<categorias> listar(){
    String sql = "SELECT * FROM categorias";
    ArrayList<categorias> categorias = new ArrayList<>();

    try{
        Connection con = DB.conexao(); 
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet registros = stmt.executeQuery();

        while(registros.next()){
            categorias temp = new categorias();
            temp.setId(registros.getInt("id"));
            temp.setNome(registros.getString("nome"));
                
            categorias.add(temp);	 
        }
    }catch(SQLException e){      	
        System.out.print("Erro ao listar categoria: "+e.toString()); 
    }

    return categorias;
}

  @Override
  public void atualizar() {
    String sql = "UPDATE categorias SET nome = ?, WHERE id = ? ";
    try{
        Connection con = DB.conexao();     
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, this.getNome());   
        stmt.setInt(2, this.getId());
        stmt.executeUpdate();

    }catch(SQLException e){      	
        System.out.print("Erro ao atualizar categoria: "+e.toString()); 
    }   
  }

}