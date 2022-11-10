package com.example.lab9_base.Dao;

import com.example.lab9_base.Bean.Partido;

import java.util.ArrayList;

public class DaoPartidos {
    public ArrayList<Partido> listaDePartidos() {

        ArrayList<Partido> partidos = new ArrayList<>();
        /*
        try(Connection conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM magenta.enemigos e\n" +
                    "inner join clases_enemigos clase on e.idClaseEnemigo = clase.idClaseEnemigo\n" +
                    "left join genero g on e.idGenero = g.idGenero\n" +
                    "left join objetos o on e.idObjeto = o.idObjeto where e.borradoLogico=0 order by e.idEnemigo;")) {

            while(rs.next()){
                Enemigo enemigo = new Enemigo();

                enemigo.setIdEnemigo(rs.getInt(1));
                enemigo.setNombreEnemigo(rs.getString(2));
                enemigo.setAtaque(rs.getInt(3));
                enemigo.setExperienciaDerrotado(rs.getInt(4));
                enemigo.setProbDejarObjeto(rs.getDouble(5));

                Genero genero = new Genero();
                genero.setIdGenero(rs.getInt("g.idGenero"));
                genero.setInicial(rs.getString("inicial"));
                enemigo.setGenero(genero);

                Objeto objeto = new Objeto();
                objeto.setIdObjeto(rs.getInt("o.idObjeto"));
                objeto.setNombreObjeto(rs.getString("nombreObjeto"));
                objeto.setEfecto(rs.getString("efecto"));
                objeto.setPeso(rs.getFloat("peso"));
                enemigo.setObjeto(objeto);

                ClaseEnemigo claseEnemigo = new ClaseEnemigo();
                claseEnemigo.setIdClaseEnemigo(rs.getInt("clase.idClaseEnemigo"));
                claseEnemigo.setNombreClase(rs.getString("nombreClase"));
                enemigo.setClaseEnemigo(claseEnemigo);
                enemigo.setBorradoLogico(rs.getInt("borradoLogico"));

                listaEnemigos.add(enemigo);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaEnemigos;
    }
        */
        return partidos;
    }

    public void crearPartido(Partido partido) {

        /*
        Inserte su código aquí
        */
    }
}
