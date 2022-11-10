<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.lab9_base.Bean.Seleccion" %>
<%@ page import="com.example.lab9_base.Bean.Arbitro" %>

<jsp:useBean type="java.util.ArrayList<com.example.lab9_base.Bean.Seleccion>" scope="request" id="listaSeleccion"/>
<jsp:useBean type="java.util.ArrayList<com.example.lab9_base.Bean.Arbitro>" scope="request" id="listaArbitro"/>


<%String error1 = (String) request.getAttribute("error1");%>
<%String error2 = (String) request.getAttribute("error2");%>
<%String error3 = (String) request.getAttribute("error3");%>
<%String error4 = (String) request.getAttribute("error4");%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css'/>
        <title>LAB 9</title>
    </head>
    <body>
        <jsp:include page="/includes/navbar.jsp"/>
        <div class='container'>
            <div class="row mb-4">
                <div class="col"></div>
                <div class="col-md-6">
                    <h1 class='mb-3'>Crear un Partido de Clasificatorias</h1>
                    <form method="POST" action="<%=request.getContextPath()%>/PartidoServlet?action=guardar">
                        <div class="form-group">
                            <label>Jornada</label>
                            <input type="number" class="form-control <%=error1!=null?"is-invalid":""%> <%=error2!=null?"is-invalid":""%>" name="jornada"    required>
                            <%if(error1!=null){%>
                            <div id="validationServer" class="invalid-tooltip">
                                <%=error1%>
                            </div>
                            <%}%>
                            <%if(error2!=null){%>
                            <div id="validationServer" class="invalid-tooltip">
                                <%=error2%>
                            </div>
                            <%}%>
                        </div>
                        <div class="form-group">
                            <label>Fecha</label>
                            <input class="form-control datetimepicker " id="fecha" name="fecha"
                                   type="date"  required/>
                            <%if(error3!=null){%>
                            <div id="validationServer" class="invalid-tooltip">
                                <%=error3%>
                            </div>
                            <%}%>
                        </div>
                        <div class="form-group">
                            <label>Selección local</label>
                            <select name="local" class="form-control <%=error3!=null?"is-invalid":""%> <%=error4!=null?"is-invalid":""%>"  required>

                            <% for (Seleccion seleccion : listaSeleccion) { %>
                                <option value="<%=seleccion.getIdSeleccion()%>"> <%=seleccion.getNombre()%> </option>
                            <% } %>
                            </select>
                            <%if(error3!=null){%>
                            <div id="validationServer" class="invalid-tooltip">
                                <%=error3%>
                            </div>
                            <%}%>
                            <%if(error4!=null){%>
                            <div id="validationServer" class="invalid-tooltip">
                                <%=error4%>
                            </div>
                            <%}%>
                        </div>
                        <div class="form-group">
                            <label>Selección Visitante</label>
                            <select name="visitante" class="form-control" required>
                                <% for (Seleccion seleccion : listaSeleccion) { %>
                                <option value="<%=seleccion.getIdSeleccion()%>"> <%=seleccion.getNombre()%> </option>
                                <% } %>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Árbitro</label>
                            <select name="arbitro" class="form-control" required>

                                <% for (Arbitro arbitro : listaArbitro) { %>
                                    <option value="<%=arbitro.getIdArbitro()%>"> <%=arbitro.getNombre()%> </option>
                                <% } %>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary">Guardar</button>
                        <a href="<%=request.getContextPath()%>/PartidoServlet" class="btn btn-danger">Cancelar</a>
                    </form>
                </div>
                <div class="col"></div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
                crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
                integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
                crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
                integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
                crossorigin="anonymous"></script>
    </body>
</html>
