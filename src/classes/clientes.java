package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import interfaces.crud;

public class clientes implements crud {
  public int id;
  public int cpf;
  public String nome;
  public double fidelidade;

  public clientes(){}

  public clientes(int id){
    if(id > 0){
      String sql = "SELECT * FROM clientes WHERE id = ?";
      
      try{
        Connection con = DB.conexao(); 
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet registro = stmt.executeQuery();
        
        while(registro.next()){
          this.setId(registro.getInt("id"));
          this.setNome(registro.getString("nome"));
          this.setFidelidade(registro.getDouble("fidelidade"));      
        }
        
      }catch(SQLException e){      	
        System.out.println("Erro no Consulta clientes: " + e.toString()); 
      }
    }
  }

  public int getId() {
    return id;
  }

  public int getCpf() {
    return cpf;
  }

  public String getNome() {
    return nome;
  }

  public double getFidelidade() {
    return fidelidade;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setCpf(int cpf) {
    this.cpf = cpf;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setFidelidade(double fidelidade) {
    this.fidelidade = fidelidade;
  }

  @Override
  public void adicionar() {
    String sql = "INSERT INTO clientes (cpf, nome, fidelidade, quantidade) VALUES (?,?,?,?)";
    try{
      Connection con = DB.conexao();
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setInt(1, this.getCpf());
      stmt.setString(2, this.getNome());
      stmt.setDouble(3, this.getFidelidade());
      stmt.execute();

    }catch(SQLException e){
      System.out.println("Erro na consulta de clientes: "+e.toString());
    }
  }

  @Override
  public void deletar() {
    String sql = "DELETE FROM clientes WHERE id = ?";
    try{
      Connection con = DB.conexao();
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setInt(1, this.getId());
      stmt.executeUpdate();
    }catch(SQLException e){
      System.out.println("Erro ao eliminar cliente: "+ e.toString());
    }
  }

  @Override
  public ArrayList<clientes> listar(){
    String sql = "SELECT * FROM clientes";
    ArrayList<clientes> clientes = new ArrayList<>();

    try{
        Connection con = DB.conexao(); 
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet registros = stmt.executeQuery();

        while(registros.next()){
            clientes temp = new clientes();
            temp.setId(registros.getInt("id"));
            temp.setNome(registros.getString("nome"));
            temp.setFidelidade(registros.getDouble("fidelidade"));                
            clientes.add(temp);	 
        }
    }catch(SQLException e){      	
        System.out.print("Erro ao listar cliente: "+e.toString()); 
    }

    return clientes;
}

  @Override
  public void atualizar() {
    String sql = "UPDATE clientes SET cpf = ?, nome = ?, fidelidade = ?, quantidade = ?  WHERE id = ? ";
    try{
        Connection con = DB.conexao();     
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, this.getId());
        stmt.setInt(2, this.getCpf());
        stmt.setString(2, this.getNome());   
        stmt.setDouble(3, this.getFidelidade());     
        stmt.executeUpdate();

    }catch(SQLException e){      	
        System.out.print("Erro ao atualizar cliente: "+e.toString()); 
    }   
  }

}