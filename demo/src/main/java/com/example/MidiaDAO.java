package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MidiaDAO {

    private Connection connection;

    public MidiaDAO(Connection connection) {
        this.connection = connection;
    }


    // CRUD


    // CREATE
    public Midia criarMidia(Midia midia) throws SQLException {
        String sql = "INSERT INTO midias (titulo, tipo, franquia, nota) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, midia.getTitulo());
            stmt.setString(2, midia.getTipo().toLowerCase());
            stmt.setString(3, midia.getFranquia());

            if (midia.getNota() != null) {
                stmt.setInt(4, midia.getNota());
            } else {
                stmt.setNull(4, java.sql.Types.INTEGER);
            }

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Falha ao criar mídia, nenhuma linha afetada.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    midia.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Falha ao criar mídia, nenhum ID obtido.");
                }
            }
        }
        return midia;
    }

    // READ
    public List<Midia> listarMidias() throws SQLException {
        List<Midia> midias = new ArrayList<>();
        String sql = "SELECT * FROM midias";

        try (Statement stmt = this.connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                midias.add(new Midia(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("tipo"),
                    rs.getString("franquia"),
                    rs.getInt("nota")
                ));
            }
        }
        return midias;
    }


    // UPDATE
    public boolean atualizarMidia(Midia midia) throws SQLException {
        String sql = "UPDATE midias SET titulo = ?, tipo = ?, franquia = ?, nota = ? WHERE id = ?";

        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setString(1, midia.getTitulo());
            stmt.setString(2, midia.getTipo().toLowerCase());

            stmt.setString(3, midia.getFranquia());
            stmt.setInt(4, midia.getNota());
            stmt.setInt(5, midia.getId());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }


    // DELETE
    public boolean deletarMidia(int id) throws SQLException {
        String sql = "DELETE FROM midias WHERE id = ?";

        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }


    // FUNÇÕES DE AGREGAÇÃO


    // COUNT()
    public long totalMidias() throws SQLException {
        String sql = "SELECT COUNT(*) AS total_midias FROM midias";

        try (Statement stmt = this.connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getLong("total_midias");
            }
        }
        return 0;
    }

    // AGRUPAR POR TIPO
    public List<Midia> listarPorTipo(String tipo) throws SQLException {
        List<Midia> midias = new ArrayList<>();
        String sql = "SELECT * FROM midias WHERE tipo = ?";

        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setString(1, tipo.toLowerCase());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    midias.add(new Midia(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("tipo"),
                        rs.getString("franquia"),
                        rs.getInt("nota")
                    ));
                }
            }
        }
        return midias;
    }

    // AGRUPAR POR FRANQUIA
    public List<Midia> listarPorFranquia(String franquia) throws SQLException {
        List<Midia> midias = new ArrayList<>();
        String sql = "SELECT * FROM midias WHERE franquia = ?";

        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setString(1, franquia);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    midias.add(new Midia(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("tipo"),
                        rs.getString("franquia"),
                        rs.getInt("nota")
                    ));
                }
            }
        }
        return midias;
    }

    // QUANTIDADE POR TIPO
    public Map<String, Integer> midiasPorTipo() throws SQLException {
        Map<String, Integer> contagemPorTipo = new HashMap<>();
        String sql = "SELECT tipo, COUNT(*) as total " +
                    "FROM midias " +
                    "GROUP BY tipo " +
                    "ORDER BY total DESC";
        
        try (Statement stmt = this.connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                contagemPorTipo.put(
                    rs.getString("tipo"),
                    rs.getInt("total")
                );
            }
        }
        return contagemPorTipo;
    }



    
    // BUSCA
    public Midia buscarId(int id) throws SQLException {
        String sql = "SELECT id, titulo, tipo, franquia, nota FROM midias WHERE id = ?";

        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Midia(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("tipo"),
                        rs.getString("franquia"),
                        rs.getInt("nota")
                    );
                }
            }
        }
        return null;
    }

}
