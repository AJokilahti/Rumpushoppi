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

@WebServlet("/lisaa_tuote")
public class UusiTuoteLisaaServlet extends HttpServlet{

	//Palauttaa selaimeen tyhjän lomakkeen
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.getRequestDispatcher("/WEB-INF/tuotelisayslomake.jsp").forward(request, response);
	}
	
	/**
	 * Vastaanottaa selaimelta tulleen tuotteen lisäyspyynnön
	 * 
	 * @param request
	 *            pyyntö
	 * @param response
	 *            vastaus
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		try {
			String nimi = request.getParameter("nimi");
			String kuvaus = request.getParameter("kuvaus");
			String hintaStr = request.getParameter("hinta");
			
			double hinta = Double.parseDouble(hintaStr);
			
			Tuote tuote = new Tuote(nimi, kuvaus, hinta);
			//testataan sysolla, onnistuuko tuotteen luominen
			System.out.println("Tuote: " + tuote.toString());
			
			ShoppiDAO shoppidao = new ShoppiJdbcDAO();
			
			boolean onnistuikoLisays = shoppidao.addTuote(tuote);
			//testataan onnistuuko tuotteen lisäys
			System.out.println(onnistuikoLisays);
			
			if(onnistuikoLisays) {
				response.sendRedirect("/listaa_tuotteet");
			} else {
				
				request.setAttribute("viesti", "Tuotteen lisäyksessä tietokantaan tapahtui virhe.");
				// servlet kutsuu .jsp:tä
				request.getRequestDispatcher("/WEB-INF/tapahtumaraportti.jsp").forward(request, response);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("viesti", "Tuotteen lisäyksessä tietokantaan tapahtui virhe");
			request.getRequestDispatcher("/WEB-INF/tapahtumaraportti.jsp").forward(request, response);
		} 
	}
}
