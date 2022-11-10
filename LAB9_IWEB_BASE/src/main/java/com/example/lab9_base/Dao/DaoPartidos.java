package com.example.lab9_base.Dao;

import com.example.lab9_base.Bean.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

        /*
        Inserte su código aquí
        */
    }
}
