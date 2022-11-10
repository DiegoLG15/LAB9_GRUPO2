package com.example.lab9_base.Controller;

import com.example.lab9_base.Bean.Arbitro;
import com.example.lab9_base.Bean.Partido;
import com.example.lab9_base.Dao.DaoArbitros;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ArbitroServlet", urlPatterns = {"/ArbitroServlet"})
public class ArbitroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("nombre");
        opciones.add("pais");
        ArrayList<String> paises = new ArrayList<>();
        paises.add("Peru");
        paises.add("Chile");
        paises.add("Argentina");
        paises.add("Paraguay");
        paises.add("Uruguay");
        paises.add("Colombia");
        Arbitro arbitro=new Arbitro();
        DaoArbitros arbitrodao = new DaoArbitros();

        bandera:switch (action) {

            case "buscar":

                String opcion =request.getParameter("tipo");
                String buscar = request.getParameter("buscar");

                request.setAttribute("opciones", opciones);

                if(opcion.equals("nombre")){
                    ArrayList<Arbitro> arbitroxNombre = arbitrodao.busquedaNombre(buscar);
                    request.setAttribute("listaArbitros", arbitroxNombre);
                    request.setAttribute("buscar",buscar);
                } else if(opcion.equals("pais")){
                    ArrayList<Arbitro> arbitroxPais = arbitrodao.busquedaPais(buscar);
                    request.setAttribute("listaArbitros", arbitroxPais);
                    request.setAttribute("buscar",buscar);
                }


                /**/
                view = request.getRequestDispatcher("/arbitros/list.jsp");
                view.forward(request, response);

                break;

            case "guardar":

                String nombre = request.getParameter("nombre");
                String pais = request.getParameter("pais");


                ArrayList<Arbitro> listaArbitros = arbitrodao.listarArbitros();
                for (Arbitro arbitro2: listaArbitros) {
                    if (nombre.equals(arbitro2.getNombre())) {
                        request.setAttribute("paises", paises);
                        String error5 = "Ningun nombre se puede repetir";
                        request.setAttribute("error5", error5);
                        view = request.getRequestDispatcher("arbitros/form.jsp");
                        view.forward(request, response);
                        break bandera;
                    }
                }
                arbitro.setNombre(nombre);
                arbitro.setPais(pais);

                arbitrodao.crearArbitro(arbitro);
                response.sendRedirect(request.getContextPath() + "/ArbitroServlet");

                break;

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;
        ArrayList<String> paises = new ArrayList<>();
        paises.add("Peru");
        paises.add("Chile");
        paises.add("Argentina");
        paises.add("Paraguay");
        paises.add("Uruguay");
        paises.add("Colombia");
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("nombre");
        opciones.add("pais");


        Arbitro arbitro;
        DaoArbitros arbitrodao = new DaoArbitros();
        String arbitroId;


        switch (action) {
            case "lista":
                request.setAttribute("opciones", opciones);
                request.setAttribute("listaArbitros", arbitrodao.listarArbitros());
                view = request.getRequestDispatcher("/arbitros/list.jsp");
                view.forward(request, response);
                break;
            case "crear":

                request.setAttribute("paises", paises);
                view = request.getRequestDispatcher("/arbitros/form.jsp");
                view.forward(request, response);
                break;
            case "borrar":
                arbitroId = request.getParameter("id");
                arbitrodao.borrarArbitro(Integer.parseInt(arbitroId));
                response.sendRedirect(request.getContextPath() + "/ArbitroServlet");
                break;
        }
    }
}
