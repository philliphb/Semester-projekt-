/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PressentationLayer;

import ServiceLayer.Controller;
import ServiceLayer.Entity.Building;
import ServiceLayer.Entity.Customer;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Oliver
 */
@WebServlet(name = "ImageController", urlPatterns = {"/ImageController"})
@MultipartConfig
public class ImageController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        //Tim
        try {
            HttpSession session = request.getSession(true);
            Building building = (Building) session.getAttribute("building");
            String floorno = (String) session.getAttribute("floorno");
            Controller con = (Controller) session.getAttribute("Controller");
            InputStream photoStream = con.getImage(building.getBuilding_id(), floorno);
            // Prepare streams.
            BufferedInputStream input = null;
            BufferedOutputStream output = null;
            try {
                input = new BufferedInputStream(photoStream, 16177215);
                output = new BufferedOutputStream(response.getOutputStream(), 16177215);
                byte[] buffer = new byte[16177215];
                int length;
                length = input.read(buffer);
                output.write(buffer, 0, length);
            } finally {
                output.close();
                input.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        //Oliver
        String do_this = request.getParameter("do_this");
        HttpSession session = request.getSession(true);
        Customer c = (Customer) session.getAttribute("LoggedInCustomer");
        Controller con = (Controller) session.getAttribute("Controller");
        //Floor floor = new Floor;
        switch (do_this) {
            case "showfloors":
                session.setAttribute("floorno", request.getParameter("floorno"));
                forward(request, response, "/Floorplan.jsp");
                break;
            case "setimage":
                Building building = (Building) session.getAttribute("building");
                String floorno = (String) session.getAttribute("floorno");
                Part filePart = request.getPart("file");
                InputStream inputstream = filePart.getInputStream();
                con.setImage(inputstream, building.getBuilding_id(), floorno);
                if (c.getUser_role().equals("admin")) {
                    forward(request, response, "/AdminBuildings.jsp");
                } else {
                    forward(request, response, "/CustomerBuildings.jsp");
                }
                break;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
//        @Override
//        public String getServletInfo
//        
//            () {
//        return "Short description";
//        }// </editor-fold>
    protected void forward(HttpServletRequest request, HttpServletResponse response, String url) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
        requestDispatcher.forward(request, response);
    }
}
