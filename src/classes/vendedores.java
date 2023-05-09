package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import interfaces.crud;

public class vendedores implements crud {
  public int id;
  public int matricula;
  public String nome;

  public vendedores(){}

  public vendedores(int id){
    if(id > 0){
      String sql = "SELECT * FROM vendedores WHERE id = ?";
      
      try{
        Connection con = DB.conexao(); 
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet registro = stmt.executeQuery();
        
        while(registro.next()){
          this.setId(registro.getInt("id"));
          this.setNome(registro.getString("nome"));
          this.setMatricula(registro.getInt("matricula"));      
        }
        
      }catch(SQLException e){      	
        System.out.println("Erro no Consulta vendedores: " + e.toString()); 
      }
    }
  }

  public int getId() {
    return id;
  }

  public int getMatricula() {
    return matricula;
  }

  public String getNome() {
    return nome;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setMatricula(int matricula) {
    this.matricula = matricula;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  @Override
  public void adicionar() {
    String sql = "INSERT INTO vendedores (matricula, nome, fidelidade, quantidade) VALUES (?,?,?,?)";
    try{
      Connection con = DB.conexao();
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setInt(1, this.getMatricula());
      stmt.setString(2, this.getNome());
      stmt.setInt(3, this.getMatricula());
      stmt.execute();

    }catch(SQLException e){
      System.out.println("Erro na consulta de vendedores: "+e.toString());
    }
  }

  @Override
  public void deletar() {
    String sql = "DELETE FROM vendedores WHERE id = ?";
    try{
      Connection con = DB.conexao();
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setInt(1, this.getId());
      stmt.executeUpdate();
    }catch(SQLException e){
      System.out.println("Erro ao eliminar vendedor: "+ e.toString());
    }
  }

  @Override
  public ArrayList<vendedores> listar(){
    String sql = "SELECT * FROM vendedores";
    ArrayList<vendedores> vendedores = new ArrayList<>();

    try{
        Connection con = DB.conexao(); 
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet registros = stmt.executeQuery();

        while(registros.next()){
            vendedores temp = new vendedores();
            temp.setId(registros.getInt("id"));
            temp.setNome(registros.getString("nome"));
            temp.setMatricula(registros.getInt("matricula"));                
            vendedores.add(temp);	 
        }
    }catch(SQLException e){      	
        System.out.print("Erro ao listar vendedor: "+e.toString()); 
    }

    return vendedores;
}

  @Override
  public void atualizar() {
    String sql = "UPDATE vendedores SET matricula = ?, nome = ?, fidelidade = ?, quantidade = ?  WHERE id = ? ";
    try{
        Connection con = DB.conexao();     
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, this.getId());
        stmt.setInt(2, this.getMatricula());
        stmt.setString(2, this.getNome());   
        stmt.executeUpdate();

    }catch(SQLException e){      	
        System.out.print("Erro ao atualizar vendedor: "+e.toString()); 
    }   
  }

}