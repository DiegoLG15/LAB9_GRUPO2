package com.example.lab9_base.Controller;
import com.example.lab9_base.Bean.Arbitro;
import com.example.lab9_base.Bean.Partido;
import com.example.lab9_base.Bean.Seleccion;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.example.lab9_base.Dao.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "PartidoServlet", urlPatterns = {"/PartidoServlet", ""})
public class PartidoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "guardar" : request.getParameter("action");
        RequestDispatcher view;
Partido partido;

        DaoPartidos daoPartidos = new DaoPartidos();
        DaoArbitros daoArbitros = new DaoArbitros();
        DaoSelecciones daoSelecciones = new DaoSelecciones();

        ArrayList<Partido> listaPartidosUnicos;
        //ArrayList<Integer> JornadasID = new ArrayList<>();

        bandera: switch (action) {

            case "guardar":




                partido = new Partido();




                //Validacion Jornada
                try {
                    partido.setNumeroJornada(Integer.parseInt(request.getParameter("jornada")));
                    if(partido.getNumeroJornada() < 0 ){


                       request.setAttribute("listaSeleccion", daoSelecciones.listarSelecciones());
                       request.setAttribute("listaArbitro", daoArbitros.listarArbitros());

                       request.setAttribute("error1", "El campo Jornada debe ser un entero mayor que cero ");
                       view = request.getRequestDispatcher("partidos/form.jsp");
                       view.forward(request, response);
                       break bandera;
                    }
                }catch (NumberFormatException e){

                    request.setAttribute("listaSeleccion", daoSelecciones.listarSelecciones());
                    request.setAttribute("listaArbitro", daoArbitros.listarArbitros());

                    request.setAttribute("error2", "El campo ingresado debe ser un numero entero");
                    view = request.getRequestDispatcher("partidos/form.jsp");
                    view.forward(request, response);
                    break bandera;
                }


                //enviamos fecha en string (en el bean es string)
                partido.setFecha(request.getParameter("fecha"));




                //validando seleccion local y visitante
                int idLocal = Integer.parseInt(request.getParameter("local"));
                int idVisitante = Integer.parseInt(request.getParameter("visitante"));
                if (idLocal == idVisitante ) {

                    request.setAttribute("listaSeleccion", daoSelecciones.listarSelecciones());
                    request.setAttribute("listaArbitro", daoArbitros.listarArbitros());

                    request.setAttribute("error3", "La seleccion visitante no puede ser igual a la local");
                    view = request.getRequestDispatcher("partidos/form.jsp");
                    view.forward(request, response);
                    break bandera;
                } else {

                    //evitar repeticion de partidos
                    listaPartidosUnicos = daoPartidos.encuentrosUnicos() ;
                    for (Partido partidoUnico : listaPartidosUnicos) {
                        if ( idLocal==partidoUnico.getSeleccionLocal().getIdSeleccion() && idVisitante==partidoUnico.getSeleccionVisitante().getIdSeleccion() ) {

                            request.setAttribute("listaSeleccion", daoSelecciones.listarSelecciones());
                            request.setAttribute("listaArbitro", daoArbitros.listarArbitros());

                            String error4 = "Ningun partido se puede repetir";
                            request.setAttribute("error4", error4);
                            view = request.getRequestDispatcher("partidos/form.jsp");
                            view.forward(request, response);
                            break bandera;
                        }
                    }


                    Seleccion seleccionlocal = new Seleccion();
                    seleccionlocal.setIdSeleccion(idLocal);
                    partido.setSeleccionLocal(seleccionlocal);

                    Seleccion seleccionvisitante = new Seleccion();
                    seleccionvisitante.setIdSeleccion(idVisitante);
                    partido.setSeleccionVisitante(seleccionvisitante);


                }



                Arbitro arbitro = new Arbitro();
                arbitro.setIdArbitro(Integer.parseInt(request.getParameter("arbitro")));
                partido.setArbitro(arbitro);

                daoPartidos.crearPartido(partido);

                response.sendRedirect(request.getContextPath() + "/PartidoServlet?action=lista");

                break bandera;
            default:
                response.sendRedirect(request.getContextPath() + "/PartidoServlet?action=lista");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;
        DaoPartidos daoPartidos = new DaoPartidos();
        DaoArbitros daoArbitros = new DaoArbitros();
        DaoSelecciones daoSelecciones = new DaoSelecciones();
        switch (action) {
            case "lista":
                request.setAttribute("listaPartidos", daoPartidos.listaDePartidos());
                view = request.getRequestDispatcher("index.jsp");
                view.forward(request, response);
                break;
            case "crear":
                request.setAttribute("listaPartidos", daoPartidos.listaDePartidos());
                request.setAttribute("listaSeleccion", daoSelecciones.listarSelecciones());
                request.setAttribute("listaArbitro", daoArbitros.listarArbitros());
                view = request.getRequestDispatcher("partidos/form.jsp");
                view.forward(request, response);

                break;
            default:
                request.setAttribute("listaPartidos", daoPartidos.listaDePartidos());
                view = request.getRequestDispatcher("index.jsp");
                view.forward(request, response);
        }

    }
    
    
}
