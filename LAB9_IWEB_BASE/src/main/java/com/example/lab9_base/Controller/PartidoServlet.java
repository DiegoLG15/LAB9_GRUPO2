package com.example.lab9_base.Controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.example.lab9_base.Dao.*;
import java.io.IOException;

@WebServlet(name = "PartidoServlet", urlPatterns = {"/PartidoServlet", ""})
public class PartidoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "guardar" : request.getParameter("action");
        RequestDispatcher view;

        switch (action) {

            case "guardar":
                /*
                Inserte su código aquí
                */
                break;

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        DaoPartidos daoPartidos = new DaoPartidos();
        RequestDispatcher view;
        switch (action) {
            case "lista":
                
                request.setAttribute("listaPartidos", daoPartidos.listaDePartidos());

                view = request.getRequestDispatcher("index.jsp");
                view.forward(request, response);
                break;
                break;
            case "crear":
                /*
                Inserte su código aquí
                 */
                break;

        }

    }
}
