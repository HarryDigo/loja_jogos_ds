package repositories;

import config.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Genero;
import models.Jogo;
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

    public JogoGenero getJogoGeneroById(int id) {
        JogoGenero relacao = null;
        String sql = "SELECT * FROM jogo_genero WHERE id = ?";

        try (
            Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                relacao = new JogoGenero(
                    rs.getInt("id"),
                    rs.getInt("jogo_id"),
                    rs.getInt("genero_id")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar relação");
        }

        return relacao;
    }

    public void updateJogoGenero(JogoGenero relacao) {
        String sql = """
        UPDATE jogo_genero
            SET jogo_id = ?, genero_id = ?
            WHERE id = ?
        """;

        try (
            Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, relacao.getJogoId());
            stmt.setInt(2, relacao.getGeneroId());
            stmt.setInt(3, relacao.getId());

            int affectedLines = stmt.executeUpdate();
            if (affectedLines > 0) {
                System.out.println("Relação atualizada com sucesso");
            } else {
                System.out.println("Relação não encontrada");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar relação");
        }
    }

    public void deleteJogoGenero(int id) {
        String sql = "DELETE FROM jogo_genero WHERE id = ?";

        try (
            Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            int affectedLines = stmt.executeUpdate();

            if (affectedLines > 0) {
                System.out.println("Relação deletada com sucesso");
            } else {
                System.out.println("Relação não encontrada");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao deletar relação");
        }
    }

    public List<Jogo> listJogosByGenero(int genero_id) {
        List<Jogo> jogos = new ArrayList<>();
        String sql = """
            SELECT jogo.*
                FROM jogo
                JOIN jogo_genero ON jogo.id = jogo_genero.jogo_id
                WHERE jogo_genero.genero_id = ?
        """;

        try (
            Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {

            stmt.setInt(1, genero_id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Jogo jogo = new Jogo(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getBlob("imagem"),
                    rs.getBoolean("tipo"),
                    rs.getInt("classificacao"),
                    rs.getString("desenvolvedor"),
                    rs.getDouble("preco"),
                    rs.getDate("lancamento")
                );
                jogos.add(jogo);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar jogos por gênero");
        }
        return jogos;
    }

    public List<Genero> listGenerosByJogo(int jogo_id) {
        List<Genero> generos = new ArrayList<>();
        String sql = """
            SELECT genero.*
                FROM genero
                JOIN jogo_genero ON genero.id = jogo_genero.genero_id
                WHERE jogo_genero.jogo_id = ?
        """;

        try (
            Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {

            stmt.setInt(1, jogo_id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Genero genero = new Genero(
                    rs.getInt("id"),
                    rs.getString("nome")
                );
                generos.add(genero);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar gêneros por jogo");
        }
        return generos;
    }
}
