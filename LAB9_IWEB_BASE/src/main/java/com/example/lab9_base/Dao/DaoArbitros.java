package com.example.lab9_base.Dao;

import com.example.lab9_base.Bean.Arbitro;

import java.sql.*;
import java.util.ArrayList;

public class DaoArbitros extends DaoBase{
    public ArrayList<Arbitro> listarArbitros() {
        ArrayList<Arbitro> arbitros = new ArrayList<>();

        try(Connection conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM lab9.arbitro;")) {


            Arbitro arbitro = null;
            while (rs.next()) {
                arbitro = new Arbitro();
                arbitro.setIdArbitro(rs.getInt(1));
                arbitro.setNombre(rs.getString(2));
                arbitro.setPais(rs.getString(3));
                arbitros.add(arbitro);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return arbitros;
    }

    public void crearArbitro(Arbitro arbitro) {

        String sql = "INSERT INTO lab9.arbitro (nombre, pais) "
                + "VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, arbitro.getNombre());
            pstmt.setString(2, arbitro.getPais());

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public ArrayList<Arbitro> busquedaPais(String pais) {

        ArrayList<Arbitro> arbitros = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM lab9.arbitro WHERE pais=\"?\";")) {

            pstmt.setString(1, pais);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Arbitro arbitro = new Arbitro();

                    arbitro.setIdArbitro(rs.getInt(1));
                    arbitro.setNombre(rs.getString(2));
                    arbitro.setPais(rs.getString(3));


                    arbitros.add(arbitro);

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return arbitros;
    }

    public ArrayList<Arbitro> busquedaNombre(String nombre) {

        ArrayList<Arbitro> arbitrosXnombre = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM lab9.arbitro a WHERE\n" +
                     "a.nombre LIKE \"%=?%\" escape \"=\";")) {

            pstmt.setString(1, nombre);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Arbitro arbitro = new Arbitro();

                    arbitro.setIdArbitro(rs.getInt(1));
                    arbitro.setNombre(rs.getString(2));
                    arbitro.setPais(rs.getString(3));


                    arbitrosXnombre.add(arbitro);

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return arbitrosXnombre;
    }

    public Arbitro buscarArbitro(int id) {

        Arbitro arbitro = new Arbitro();;


        try(Connection conn = getConnection();
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM lab9.arbitro WHERE idArbitro=?;")){

            pstm.setInt(1,id);
            try (ResultSet rs = pstm.executeQuery();){
                if(rs.next()){
                    arbitro.setIdArbitro(rs.getInt(1));
                    arbitro.setNombre(rs.getString(2));
                    arbitro.setPais(rs.getString(3));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arbitro;
    }

    public void borrarArbitro(int id) {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE from lab9.arbitro WHERE idArbitro = ?")) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
