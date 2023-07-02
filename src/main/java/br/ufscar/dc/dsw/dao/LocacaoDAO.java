package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

import br.ufscar.dc.dsw.domain.Locacao;


public class LocacaoDAO extends GenericDAO {

    public void insert(Locacao locacao) {

        String sql = "INSERT INTO Locacao (cliente_CPF, locadora_CNPJ, data_hora) VALUES (?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;

            statement = conn.prepareStatement(sql);
            statement.setString(1, locacao.getCliente().getCPF());
            statement.setString(2, locacao.getLocadora().getCNPJ());
            statement.setTimestamp(3, java.sql.Timestamp.valueOf(locacao.getDataHora()));
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

     public List<Locacao> getAll() {
        List<Locacao> listaLocacoes = new ArrayList<>();
        
        String sql = "SELECT * FROM locacao;";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();
;
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String CPF = resultSet.getString("CPF");
                String CNPJ = resultSet.getString("CNPJ");
                LocalDateTime data_hora = resultSet.getTimestamp("data_hora").toLocalDateTime();
                Locacao locacao = new Locacao(new ClienteDAO().get(CPF), new LocadoraDAO().get(CNPJ), data_hora);
                listaLocacoes.add(locacao); 
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaLocacoes;
    }

    public void delete(Locacao locacao) {
        
        String sql = "DELETE FROM locacao WHERE cliente_CPF = ?, locadora_CNPJ = ?, data_hora = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, locacao.getCliente().getCPF());
            statement.setString(2, locacao.getLocadora().getCNPJ());
            statement.setTimestamp(3, Timestamp.valueOf(locacao.getDataHora()));
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Locacao get(String CPF, String CNPJ, LocalDateTime data_hora) {
        Locacao locacao = null;   
        String sql = "SELECT * FROM locacao WHERE cliente_CPF = ? AND locadora_CNPJ = ? AND data_hora = ?;";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setString(1, CPF);
            statement.setString(2, CNPJ);
            statement.setTimestamp(3, Timestamp.valueOf(data_hora));

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                locacao = new Locacao(new ClienteDAO().get(CPF), new LocadoraDAO().get(CNPJ), data_hora);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
     return locacao;
    }



    
}
