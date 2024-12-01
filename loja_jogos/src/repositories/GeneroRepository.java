package repositories;

import config.DbConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Genero;

public class GeneroRepository {
    
    public void createGenero(Genero genero) {
        String sql = """
        INSERT INTO genero (nome)
            VALUES (?)
        """;

        try (
            Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, genero.getNome());

            int affectedLines = stmt.executeUpdate();
            if (affectedLines > 0) {
                System.out.println("Gênero adicionado com sucesso");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar gênero");
        }
    }

    public List<Genero> listAllGeneros() {
        List<Genero> generos = new ArrayList<>();
        String sql = "SELECT * FROM genero";

        try (
            Connection conn = DbConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {

            while (rs.next()) {
                Genero genero = new Genero(
                    rs.getInt("id"),
                    rs.getString("nome")
                );
                generos.add(genero);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar todos os gêneros");
        }

        return generos;
    }

    public Genero getGeneroById(int id) {
        Genero genero = null;
        String sql = "SELECT * FROM genero WHERE id = ?";

        try (
            Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                genero = new Genero(
                    rs.getInt("id"),
                    rs.getString("nome")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar gênero");
        }

        return genero;
    }

    public void updateGenero(Genero genero) {
        String sql = """
        UPDATE genero
            SET nome = ?
            WHERE id = ?
        """;

        try (
            Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, genero.getNome());
            stmt.setInt(2, genero.getId());

            int affectedLines = stmt.executeUpdate();
            if (affectedLines > 0) {
                System.out.println("Gênero atualizado com sucesso");
            } else {
                System.out.println("Gênero não encontrado");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar gênero");
        }
    }

    public void deleteGenero(int id) {
        String sql = "DELETE FROM genero WHERE id = ?";

        try (
            Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            int affectedLines = stmt.executeUpdate();

            if (affectedLines > 0) {
                System.out.println("Gênero deletado com sucesso");
            } else {
                System.out.println("Gênero não encontrado");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao deletar Gênero");
        }
    }
}
