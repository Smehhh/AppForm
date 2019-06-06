package credit.web;

import credit.analyst.Analyst;
import credit.entities.ClientRequest;
import credit.storage.CookieStorage;
import credit.storage.SessionStorage;
import credit.storage.Storage;
import credit.util.JsonHelper;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "CreditServlet", urlPatterns = {"/index.jsp"})
public class CreditServlet extends HttpServlet {


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
        request.setCharacterEncoding("UTF-8");
        
        Analyst analyst = getAnalyst(request, response).loadFromStorage();
        
        renderView(request, response, analyst);
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
        request.setCharacterEncoding("UTF-8");
        
        Analyst analyst = getAnalyst(request, response).parseRequest(request);
        
        if(analyst.getClientRequest() == null) {
            renderError(request, response, "Incorrect request");
            return;
        }
        
        if(analyst.validate().length > 0) {
            renderView(request, response, analyst);
            return;
        } 
        
        response.setContentType("application/json; charset = UTF-8");
        try(OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream(), "UTF-8")) {
            JsonHelper.toJSON(analyst.getClientRequest()).writeJSONString(writer);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void renderView(HttpServletRequest request, HttpServletResponse response, Analyst analyst) throws ServletException, IOException {
        request.setAttribute("analyst", analyst);
        request.setAttribute("clientRequest", analyst.getClientRequest());
        request.setAttribute("errors", analyst.validate());
        getServletContext().getRequestDispatcher("/WEB-INF/templates/index.jsp").forward(request, response);
    }
    
    private void renderError(HttpServletRequest request, HttpServletResponse response, String error) throws ServletException, IOException {
        request.setAttribute("error", error);
        getServletContext().getRequestDispatcher("/WEB-INF/templates/error.jsp").forward(request, response);
    }
    
    private static Analyst getAnalyst(HttpServletRequest request, HttpServletResponse response) {
        Storage storage = new CookieStorage(request, response);
        Analyst analyst = new Analyst(storage);
        return analyst;
    }
}
