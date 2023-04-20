package it.prova.gestionebigliettiweb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.gestionebigliettiweb.service.MyServiceFactory;

/**
 * Servlet implementation class PrepareDeleteBigliettoServlet
 */
@WebServlet("/PrepareDeleteBigliettoServlet")
public class PrepareDeleteBigliettoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//------------------BINDING
				String parametroId = request.getParameter("idBiglietto");
				Long idDaRimuovere = Long.parseLong(parametroId);


				//------------------VALIDAZIONE
				if (!NumberUtils.isCreatable(parametroId)) {
				// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
				request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
				return;
				}


				//------------------BUSINESS
				try {
				request.setAttribute("bigliettoDaRimuovere",
				MyServiceFactory.getBigliettoServiceInstance().caricaSingoloElemento(idDaRimuovere));
				} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
				return;
				}


				//------------------FORWARD
				request.getRequestDispatcher("/biglietto/delete.jsp").forward(request, response);
				}
	

}
