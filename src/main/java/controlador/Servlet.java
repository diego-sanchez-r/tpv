/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Crud;
import modelo.Productos;

/**
 *
 * @author Diego_Sanchez
 */
public class Servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = "listar";
            if(request.getParameter("op")!=null){
               op=request.getParameter("op");
            }
            if (op.equals("listar")) {
                List<Productos> listaProductos=Crud.getProductos();
                request.setAttribute("listado", listaProductos);
                request.setAttribute("mensaje", "");
                request.getRequestDispatcher("listar.jsp").forward(request, response);
            }
            if (op.equals("borrar")) {
                int id = Integer.parseInt(request.getParameter("id"));
                if(Crud.destroyProducto(id)>0){
                    request.setAttribute("mensaje", "Producto con id "+id+"Borrado");
                }else{
                    request.setAttribute("mensaje", "No se ha borrado ningun producto");
                }
                List<Productos> listaProductos=Crud.getProductos();
                request.setAttribute("listado", listaProductos);
                request.getRequestDispatcher("listar.jsp").forward(request, response);
            }
            if (op.equals("actualizar")) {
                int id = Integer.parseInt(request.getParameter("id"));
                Productos miProducto = Crud.getProducto(id);
                request.setAttribute("operacion", "actualizardatos");
                request.setAttribute("producto", miProducto);
                request.getRequestDispatcher("actualizar.jsp").forward(request, response);
            }
            if (op.equals("actualizardatos")) {
                request.setAttribute("operacion", "insertar");
                int id = Integer.parseInt(request.getParameter("id"));
                String nombre = request.getParameter("nombre");
                String categoria = request.getParameter("categoria");
                String imagen = request.getParameter("imagen");
                float precio = Float.parseFloat(request.getParameter("precio"));
                Productos p = new Productos();
                p.setId(id);
                p.setNombre(nombre);
                p.setPrecio(precio);
                p.setCategoria(categoria);
                p.setImagen(imagen);
                Crud.actualizaProducto(p);
                if(Crud.actualizaProducto(p)>0){
                    request.setAttribute("mensaje", "Producto con id "+id+"Actualizado");
                }else{
                    request.setAttribute("mensaje", "No se ha actualizaro ningun producto");
                }
                request.setAttribute("producto", p);
                request.getRequestDispatcher("actualizar.jsp").forward(request, response);
            }
            if (op.equals("insertar")) {
                request.setAttribute("operacion", "insertarDatos");
                request.setAttribute("mensaje", "");
                request.getRequestDispatcher("actualizar.jsp").forward(request, response);
            }
            if (op.equals("insertarDatos")) {
//                int id = Integer.parseInt(request.getParameter("id"));
                String nombre = request.getParameter("nombre");
                String categoria = request.getParameter("categoria");
                String imagen = request.getParameter("imagen");
                float precio = Float.parseFloat(request.getParameter("precio"));
                Productos p = new Productos();
                p.setNombre(nombre);
                p.setPrecio(precio);
                p.setCategoria(categoria);
                p.setImagen(imagen);
                Crud.insertaProducto(p);
//                if(Crud.actualizaProducto(p)>0){
//                    request.setAttribute("mensaje", "Producto insertado");
//                }else{
//                    request.setAttribute("mensaje", "No se ha insertado ningun producto");
//                }
                List<Productos> listaProductos=Crud.getProductos();
                request.setAttribute("listado", listaProductos);
                request.getRequestDispatcher("listar.jsp").forward(request, response);
            }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
