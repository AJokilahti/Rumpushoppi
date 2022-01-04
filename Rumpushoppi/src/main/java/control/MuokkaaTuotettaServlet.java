package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.ShoppiDAO;
import database.ShoppiJdbcDAO;
import model.Tuote;

@WebServlet("/muokkaa_tuote")
public class MuokkaaTuotettaServlet extends HttpServlet{

	//Palauttaa selaimeen lomakkeen tuotteen nykyisillä tiedoilla
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		// Sijoitetaan muuttujaan pyynnön parametrina tullut päivitettävän tuotteen
		// id-arvo
		String idStr = request.getParameter("id");
		int tuoteId = Integer.parseInt(idStr);
		// Luodaan shoppidao
		ShoppiDAO shoppidao = new ShoppiJdbcDAO();
		Tuote tuote = shoppidao.findById(tuoteId);
		request.setAttribute("tuote", tuote);
		
		request.getRequestDispatcher("/WEB-INF/muokkauslomake.jsp").forward(request, response);
	}
	
	/**
	 * Vastaanottaa selaimelta tulleen tuotteen muokkauspyynnön
	 * 
	 * @param request
	 *            pyyntö
	 * @param response
	 *            vastaus
	 */
	 protected void doPost (HttpServletRequest request, HttpServletResponse response)
	 	throws ServletException, IOException{
		 try {
			 	String idStr = request.getParameter("id");
				int tuoteId = Integer.parseInt(idStr);
				// Luodaan shoppidao
				ShoppiDAO shoppidao = new ShoppiJdbcDAO();
				Tuote tuote = shoppidao.findById(tuoteId);
				tuote.setNimi(request.getParameter("nimi"));
				tuote.setKuvaus(request.getParameter("kuvaus"));
				String hintaStr = request.getParameter("hinta");
				double hinta = Double.parseDouble(hintaStr);
				tuote.setHinta(hinta);
				System.out.println(tuote.toString());
			
				// Päivitetään tuotteen tiedot tietokannassa
				boolean muokkausOnnistui = shoppidao.updateTuote(tuote.getNimi(), tuote.getKuvaus(), tuote.getHinta(), tuoteId);
				if (muokkausOnnistui) {
					// uudelleenohjataan pyyntö /listaa_tuotteet-endpointiin
					response.sendRedirect("/listaa_tuotteet");
				} else {
					request.setAttribute("viesti", "Tuotteen muokkauksessa tapahtui virhe.");
					// servlet kutsuu jsp:tä
					request.getRequestDispatcher("/WEB-INF/tapahtumaraportti.jsp").forward(request, response);
				}
				
			} catch (Exception e) {
				e.printStackTrace();	
				request.setAttribute("viesti", "Sovelluksessa tapahtui virhe.");
				// servlet kutsuu jsp:tä
				request.getRequestDispatcher("/WEB-INF/tapahtumaraportti.jsp").forward(request, response);
			}
	 }
}
