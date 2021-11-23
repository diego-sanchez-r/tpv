<%-- 
    Document   : listarProductos
    Created on : 15 nov. 2021, 17:36:55
    Author     : Diego_Sanchez
--%>
<%@page import="java.util.List"%>
<%@page import="modelo.Productos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body>
        <h1>Listado de productos</h1>
        <% 
            List<Productos> listaProductos = ( List<Productos> ) request.getAttribute("listado"); 
            String mensaje = (String) request.getAttribute("mensaje");
        %>
        <h2 class="alert alert-success"><%=mensaje%></h2>
        <table class="table">
            <% for (Productos p: listaProductos) { %>
            <tr>
                <td><%=p.getNombre() %></td>
                <td><%=p.getCategoria() %></td>
                <td><%=p.getPrecio() %></td>
                <td><a href="Servlet?op=borrar&id=<%=p.getId() %>" onclick="return Confirmacion()">Borrar</a></td>
                <td><a href="Servlet?op=actualizar&id=<%=p.getId() %>">Editar</a></td>
            </tr>
            <%}%>
        </table>
        
        <script>
                 function Confirmacion()
                {
                    if (confirm('¿Estás SEGURO de que quieres eliminar?')==true){
                    alert("El registro ha sido eliminado correctamente");
                    return true;
                    }
                    else
                        return false;
		}	
            </script>
    </body>
</html>
