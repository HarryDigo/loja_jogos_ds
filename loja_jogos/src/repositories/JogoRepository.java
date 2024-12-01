package repositories;

import config.DbConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Jogo;

public class JogoRepository {
    
    public void createJogo(Jogo jogo) {
        String sql = """
        INSERT INTO jogo (titulo, imagem, tipo, classificacao, desenvolvedor, preco, lancamento)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (
            Connection conn = DbConnection.getConnection();
            PreparedStatement stmt =conn.prepareStatement(sql)
        ) {

            stmt.setString(1, jogo.getTitulo());
            stmt.setBlob(2, jogo.getImagem());
            stmt.setBoolean(3, jogo.isTipo());
            stmt.setInt(4, jogo.getClassificacao());
            stmt.setString(5, jogo.getDesenvolvedor());
            stmt.setDouble(6, jogo.getPreco());
            stmt.setDate(7, jogo.getLancamento());

            int affectedLines = stmt.executeUpdate();
            if (affectedLines > 0) {
                System.out.println("Jogo adicionado com sucesso");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar contato");
        }
    }

    public List<Jogo> listAllJogos() {
        List<Jogo> jogos = new ArrayList<>();
        String sql = "SELECT * FROM jogo";

        try (
            Connection conn = DbConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {

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
            System.out.println("Erro ao listar todos os jogos");
        }

        return jogos;
    }

    public Jogo getJogoById(int id) {
        Jogo jogo = null;
        String sql = "SELECT * FROM jogo WHERE id = ?";

        try (
            Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                jogo = new Jogo(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getBlob("imagem"),
                    rs.getBoolean("tipo"),
                    rs.getInt("classificacao"),
                    rs.getString("desenvolvedor"),
                    rs.getDouble("preco"),
                    rs.getDate("lancamento")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar todos os jogos");
        }

        return jogo;
    }

    public void updateJogo(Jogo jogo) {
        String sql = """
        UPDATE jogo
            SET titulo = ?, imagem = ?, tipo = ?, classificacao = ?, desenvolvedor = ?, preco = ?, lancamento = ?
            WHERE id = ?
        """;

        try (
            Connection conn = DbConnection.getConnection();
            PreparedStatement stmt =conn.prepareStatement(sql)
        ) {

            stmt.setString(1, jogo.getTitulo());
            stmt.setBlob(2, jogo.getImagem());
            stmt.setBoolean(3, jogo.isTipo());
            stmt.setInt(4, jogo.getClassificacao());
            stmt.setString(5, jogo.getDesenvolvedor());
            stmt.setDouble(6, jogo.getPreco());
            stmt.setDate(7, jogo.getLancamento());
            stmt.setInt(8, jogo.getId());

            int affectedLines = stmt.executeUpdate();
            if (affectedLines > 0) {
                System.out.println("Jogo adicionado com sucesso");
            } else {
                System.out.println("Jogo não encontrado");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar contato");
        }
    }

    public void deleteJogo(int id) {
        String sql = "DELETE FROM jogo WHERE id = ?";

        try (
            Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            int affectedLines = stmt.executeUpdate();

            if (affectedLines > 0) {
                System.out.println("Jogo deletado com sucesso");
            } else {
                System.out.println("Jogo não encontrado");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao deletar jogo");
        }
    }
}
