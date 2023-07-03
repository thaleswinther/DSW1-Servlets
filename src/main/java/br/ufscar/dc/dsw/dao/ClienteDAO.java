package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;


import br.ufscar.dc.dsw.domain.Cliente;


public class ClienteDAO  extends GenericDAO {
    
    public void insert(Cliente cliente) {

        String sql = "INSERT INTO Cliente (id_usuario, CPF, telefone, sexo, data_nascimento) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;

            statement.setLong(1, cliente.getId());
            statement.setString(2, cliente.getCPF());
            statement.setString(3, cliente.getTelefone());
            statement.setString(4, cliente.getSexo());
            java.util.Date dataNascimentoUtil = cliente.getDataNascimento();
            long dataNascimentoMillis = dataNascimentoUtil.getTime();
            java.sql.Date dataNascimentoSql = new java.sql.Date(dataNascimentoMillis);
            statement.setDate(5, dataNascimentoSql);
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Cliente> getAll() {

        List<Cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente INNER JOIN Usuario ON Cliente.id_usuario = Usuario.id";
        
        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {


                Long id = resultSet.getLong("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String papel = resultSet.getString("papel");

                String CPF = resultSet.getString("CPF");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                Date data_nascimento = resultSet.getDate("data_nascimento");

                Cliente cliente = new Cliente(id, email, senha, nome, papel, CPF, telefone, sexo, data_nascimento);
                listaClientes.add(cliente);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaClientes;
    }

    public void delete(Cliente cliente) {
        String sql = "DELETE FROM Cliente where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, cliente.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Cliente cliente) {
        String sql = "UPDATE Cliente SET CPF = ?, telefone = ?, sexo = ?, data_nascimento = ? WHERE id_usuario = ?;";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cliente.getCPF());
            statement.setString(2, cliente.getTelefone());
            statement.setString(3, cliente.getSexo());
            
            java.util.Date dataNascimentoUtil = cliente.getDataNascimento();
            java.sql.Date dataNascimentoSql = new java.sql.Date(dataNascimentoUtil.getTime());
            statement.setDate(4, dataNascimentoSql);

            statement.setLong(5, cliente.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Cliente get(String CPF) {
        Cliente cliente = null;

        String sql = "SELECT * FROM Cliente INNER JOIN Usuario ON Cliente.id_usuario = Usuario.id WHERE Cliente.CPF = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, CPF);
            ResultSet resultSet = statement.executeQuery();  
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String papel = resultSet.getString("papel");
            
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                Date data_nascimento = resultSet.getDate("data_nascimento");
                cliente = new Cliente(id, email, senha, nome, papel, CPF, telefone, sexo, data_nascimento);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }

     public Cliente get(Long id) {
        Cliente cliente = null;
        String sql = "SELECT * FROM Cliente INNER JOIN Usuario ON Cliente.id_usuario = Usuario.id WHERE Cliente.id_usuario = ?";
     ;

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String papel= resultSet.getString("papel");
               
                String CPF = resultSet.getString("CPF");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                Date data_nascimento = resultSet.getDate("data_nascimento");
                cliente = new Cliente(id, email, senha, nome, papel, CPF, telefone, sexo, data_nascimento);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }
   
}

     

