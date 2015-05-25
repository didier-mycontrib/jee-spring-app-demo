package tp.myapp.web.initdb.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InitDbServlet
 */
@WebServlet("/InitDbServlet")
public class InitDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitDbServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		try{
		Class.forName("org.hsqldb.jdbcDriver").newInstance();
		Connection connexion = DriverManager.getConnection("jdbc:hsqldb:mem:devise_db_mem", "sa",  "");
		
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("init_minibank_db.sql");
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String s= reader.readLine();
		while(s!=null){
			Statement statement = connexion.createStatement();
			statement.executeUpdate(s);
			statement.close();
			s= reader.readLine();
		}
		connexion.close() ;
		reader.close(); is.close();
		out.println("initialisation de la base de donnees  bien effectuee");
		}catch(Exception ex){
			out.println("echec initialisation base de donnees:" + ex.getMessage());
			ex.printStackTrace();
		}
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
