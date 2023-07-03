package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Locadora;


public class LocadoraDAO extends GenericDAO {

    public void insert(Locadora locadora) {

        String sql = "INSERT INTO Locadora(id_usuario, CNPJ, cidade) VALUES (?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;

            statement.setLong(1, locadora.getId());
            statement.setString(2, locadora.getCNPJ());
            statement.setString(3, locadora.getCidade());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Locadora> getAll() {

        List<Locadora> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM Locadora INNER JOIN Usuario ON Locadora.id_usuario = Usuario.id";


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

                String CNPJ = resultSet.getString("CNPJ");
                String cidade = resultSet.getString("cidade");
             
                

                Locadora locadora = new Locadora(id, email, senha, nome, papel, CNPJ, cidade);
                listaClientes.add(locadora);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaClientes;
    }

    public void delete(Locadora locadora) {
        String sql = "DELETE FROM Locadora where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, locadora.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Locadora locadora) {

        String sql = "UPDATE Locadora SET CNPJ = ?, cidade = ? WHERE id_usuario = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, locadora.getCNPJ());
            statement.setString(2, locadora.getCidade());
            statement.setLong(3, locadora.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Locadora get(String CNPJ) {
        Locadora locadora = null;

        String sql = "SELECT * FROM Locadora INNER JOIN Usuario ON Locadora.id_usuario = Usuario.id WHERE Locadora.CNPJ = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, CNPJ);
            ResultSet resultSet = statement.executeQuery();  
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String papel = resultSet.getString("papel");
            
                String cidade = resultSet.getString("cidade");
              
                locadora = new Locadora(id, email, senha, nome, papel, CNPJ, cidade);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return locadora;
    }

    public Locadora get(Long id) {
        Locadora locadora = null;   
        String sql = "SELECT * FROM Locadora INNER JOIN Usuario ON Locadora.id_usuario = Usuario.id WHERE Locadora.id_usuario = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String papel = resultSet.getString("papel");
                
                String CNPJ = resultSet.getString("CNPJ");
                String cidade = resultSet.getString("cidade");
                locadora = new Locadora(id, email, senha, nome, papel, CNPJ, cidade);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
     return locadora;
    }

    
}
