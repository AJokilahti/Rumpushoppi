package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.ShoppiDAO;
import database.ShoppiJdbcDAO;

@WebServlet("/poista_tuote")
public class PoistaTuoteServlet extends HttpServlet{

	
	/**
	 * Vastaanottaa selaimelta tulleen tuotteen poistopyynnön
	 * 
	 * @param request
	 *            pyyntö
	 * @param response
	 *            vastaus
	 */

	protected void doGet (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		
		try {
			// Sijoitetaan muuttujaan pyynnön parametrina tullut poistettavan tuotteen
			// id-arvo
			String idStr = request.getParameter("id");
			int tuoteId = Integer.parseInt(idStr);
			// Luodaan shoppidao
			ShoppiDAO shoppidao = new ShoppiJdbcDAO();
			// Poistetaan tuotteen tiedot tietokannasta
			boolean poistoOnnistui = shoppidao.removeTuote(tuoteId);
			if (poistoOnnistui) {
				// uudelleenohjataan pyyntö /listaa_tuotteet-endpointiin
				response.sendRedirect("/listaa_tuotteet");
			} else {
				request.setAttribute("viesti", "Tuotteen poistossa tapahtui virhe.");
				// servlet kutsuu jsp:tä
				request.getRequestDispatcher("/WEB-INF/tapahtumaraportti.jsp").forward(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();	
			request.setAttribute("viesti", "Sovelluksessa tapahtui virhe,");
			// servlet kutsuu jsp:tä
			request.getRequestDispatcher("/WEB-INF/tapahtumaraportti.jsp").forward(request, response);
		}
	}
}
