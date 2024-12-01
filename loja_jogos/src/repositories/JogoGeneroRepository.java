package repositories;

import config.DbConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.JogoGenero;

public class JogoGeneroRepository {
    
    public void createJogoGenero(JogoGenero relacao) {
        String sql = """
        INSERT INTO jogo_genero (jogo_id, genero_id)
            VALUES (?, ?)
        """;

        try (
            Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, relacao.getJogoId());
            stmt.setInt(2, relacao.getGeneroId());

            int affectedLines = stmt.executeUpdate();
            if (affectedLines > 0) {
                System.out.println("Relação adicionada com sucesso");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar relação");
        }
    }

    public List<JogoGenero> listAllJogoGeneros() {
        List<JogoGenero> relacoes = new ArrayList<>();
        String sql = "SELECT * FROM jogo_genero";

        try (
            Connection conn = DbConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {

            while (rs.next()) {
                JogoGenero relacao = new JogoGenero(
                    rs.getInt("id"),
                    rs.getInt("jogo_id"),
                    rs.getInt("genero_id")
                );
                relacoes.add(relacao);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar todas as relações");
        }

        return relacoes;
    }
}
