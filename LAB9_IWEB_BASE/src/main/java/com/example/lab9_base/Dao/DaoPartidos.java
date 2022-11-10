package com.example.lab9_base.Dao;

import com.example.lab9_base.Bean.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DaoPartidos extends DaoBase{
    public ArrayList<Partido> listaDePartidos() {

        ArrayList<Partido> partidos = new ArrayList<>();


        try(Connection conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT p.idPartido, p.numeroJornada, p.fecha, seleclocal.nombre, selecvisit.nombre, esta.nombre, ar.nombre FROM lab9.partido p\n" +
                    "left join seleccion seleclocal on seleclocal.idSeleccion = p.seleccionLocal \n" +
                    "left join seleccion selecvisit on selecvisit.idSeleccion = p.seleccionVisitante \n" +
                    "left join estadio esta on esta.idEstadio = seleclocal.estadio_idEstadio\n" +
                    "left join arbitro ar on p.arbitro = ar.idArbitro\n" +
                    "order by p.idPartido;")) {

            while(rs.next()){
                Partido partido = new Partido();

                partido.setIdPartido(rs.getInt(1));
                partido.setNumeroJornada(rs.getInt(2));
                partido.setFecha(rs.getString(3));

                Seleccion seleccionLocal = new Seleccion();
                seleccionLocal.setNombre(rs.getString("seleclocal.nombre"));
                Estadio estadioLocal = new Estadio();
                estadioLocal.setNombre(rs.getString("esta.nombre"));
                seleccionLocal.setEstadio(estadioLocal);
                partido.setSeleccionLocal(seleccionLocal);


                Seleccion seleccionVisitante = new Seleccion();
                seleccionVisitante.setNombre(rs.getString("selecvisit.nombre"));
                partido.setSeleccionVisitante(seleccionVisitante);


                Arbitro arbitro = new Arbitro();
                arbitro.setNombre(rs.getString("ar.nombre"));
                partido.setArbitro(arbitro);


                partidos.add(partido);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return partidos;
    }

    public void crearPartido(Partido partido) {

       String sql = "INSERT INTO partido (seleccionLocal, seleccionVisitante, arbitro, fecha,numeroJornada) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {


            pstmt.setInt(1, partido.getSeleccionLocal().getIdSeleccion());
            pstmt.setInt(2, partido.getSeleccionVisitante().getIdSeleccion());
            pstmt.setInt(3, partido.getArbitro().getIdArbitro());


            String pattern = "yyyy-MM-dd"; //definimos el formato de fecha
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern); //constructor
            Date date_java_util = simpleDateFormat.parse(partido.getFecha()); //parseamoos la fecha en string a Date de java.util.Date
            java.sql.Date sql_date = new java.sql.Date(date_java_util.getTime()); //pasamos de java.util.date a sql.date
            pstmt.setDate(4,sql_date); //seteamos el sql.date


            pstmt.setInt(5, partido.getNumeroJornada());

            

            pstmt.executeUpdate();



        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    
    
    public ArrayList<Partido> encuentrosUnicos() {

        ArrayList<Partido> partidos = new ArrayList<>();


        try(Connection conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT p.idPartido, seleclocal.idSeleccion, selecvisit.idSeleccion FROM lab9.partido p\n" +
                    "left join seleccion seleclocal on seleclocal.idSeleccion = p.seleccionLocal \n" +
                    "left join seleccion selecvisit on selecvisit.idSeleccion = p.seleccionVisitante \n" +
                    "order by p.idPartido;")) {

            while(rs.next()){
                Partido partido = new Partido();

                partido.setIdPartido(rs.getInt(1));

                Seleccion seleccionLocal = new Seleccion();
                seleccionLocal.setIdSeleccion(rs.getInt(2));
                partido.setSeleccionLocal(seleccionLocal);

                Seleccion seleccionVisitante = new Seleccion();
                seleccionVisitante.setIdSeleccion(rs.getInt(3));
                partido.setSeleccionVisitante(seleccionVisitante);


                partidos.add(partido);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return partidos;
    }
    
    
    
}
