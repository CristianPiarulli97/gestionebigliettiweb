package it.prova.gestionebigliettiweb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.gestionebigliettiweb.service.BigliettoService;
import it.prova.gestionebigliettiweb.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteDeleteBigliettoServlet
 */
@WebServlet("/ExecuteDeleteBigliettoServlet")
public class ExecuteDeleteBigliettoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//------------------BINDING
				String parametroId = request.getParameter("idDaRimuovereParam");
				Long idDaVisualizzare = Long.parseLong(parametroId);

				//------------------VALIDAZIONE
				if (!NumberUtils.isCreatable(parametroId)) {
				request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
				return;
				}

				//------------------BUSINESS
				try {
				BigliettoService bigliettoServiceInstance = MyServiceFactory.getBigliettoServiceInstance();
				bigliettoServiceInstance.rimuovi(idDaVisualizzare);
				request.setAttribute("listaBigliettiAttribute", bigliettoServiceInstance.listAll());
				} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
				return;
				}

				//------------------FORWARD
				request.getRequestDispatcher("/biglietto/results.jsp").forward(request, response);


				}

}
