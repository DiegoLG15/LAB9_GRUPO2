package com.example.lab9_base.Dao;

import com.example.lab9_base.Bean.*;
import java.sql.*;
import java.util.ArrayList;

public class DaoSelecciones extends DaoBase{
    public ArrayList<Seleccion> listarSelecciones() {

       ArrayList<Seleccion> selecciones = new ArrayList<>();

        try(Connection conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM seleccion selec inner join estadio e on e.idEstadio = selec.estadio_idEstadio ;")) {

            while(rs.next()){
                Seleccion seleccion = new Seleccion();

                seleccion.setIdSeleccion(rs.getInt(1));
                seleccion.setNombre(rs.getString(2));
                seleccion.setTecnico(rs.getString(3));


                Estadio estadio = new Estadio();

                estadio.setIdEstadio(rs.getInt("e.idEstadio"));
                estadio.setNombre(rs.getString("e.nombre"));
                estadio.setProvincia(rs.getString("e.provincia"));
                estadio.setClub(rs.getString("e.club"));
                seleccion.setEstadio(estadio);


                selecciones.add(seleccion);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return selecciones;
    }

}
