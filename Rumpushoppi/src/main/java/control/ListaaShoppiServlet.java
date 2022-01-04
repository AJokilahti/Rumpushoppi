package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.*;
import model.Tuote;

@WebServlet("/listaa_tuotteet")
public class ListaaShoppiServlet extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		ShoppiDAO shoppidao = new ShoppiJdbcDAO();
		//etsitään tuotelista tietokannasta
		List<Tuote> tuotteet = shoppidao.findAll();
		//Pyydetään tuotelistaa
		request.setAttribute("tuote", tuotteet);
		
		//annetaan tuotelista .jsp tiedoston kautta
		request.getRequestDispatcher("/WEB-INF/Shoppilista.jsp").forward(request, response);
	}
}
