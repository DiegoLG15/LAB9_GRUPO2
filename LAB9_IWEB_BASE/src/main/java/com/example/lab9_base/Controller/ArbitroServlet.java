package com.example.lab9_base.Controller;

import com.example.lab9_base.Bean.Arbitro;
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
        Arbitro arbitro=new Arbitro();
        DaoArbitros arbitrodao = new DaoArbitros();

        switch (action) {

            case "buscar":

                String searchText = request.getParameter("searchText");
                request.setAttribute("opciones", opciones);

                for(String opcion:opciones){
                    if(opcion.equals("nombre")){
                        ArrayList<Arbitro> arbitroxNombre = arbitrodao.busquedaNombre(searchText);
                        request.setAttribute("lista", arbitroxNombre);
                        request.setAttribute("searchText",searchText);
                    } else {
                        ArrayList<Arbitro> arbitroxPais = arbitrodao.busquedaPais(searchText);
                        request.setAttribute("lista", arbitroxPais);
                        request.setAttribute("searchText",searchText);
                    }
                }

                /**/




                RequestDispatcher requestDispatcher = request.getRequestDispatcher("list.jsp");
                requestDispatcher.forward(request, response);

                break;

            case "guardar":

                String nombre = request.getParameter("nombre");
                String pais = request.getParameter("pais");

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
