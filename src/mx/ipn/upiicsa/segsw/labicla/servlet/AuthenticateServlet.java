package mx.ipn.upiicsa.segsw.labicla.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.ipn.upiicsa.segsw.labicla.valueobject.UserValueObject;

/**
 * 
 * @author Guillermo E. Martinez Barriga
 *
 */
@WebServlet("/authenticate.controller")
public class AuthenticateServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	
	/* definir variables de acceso a base de datos */
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost/el_rincon_del_tragon_db";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	/* definir variables de acceso a base de datos */
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doSomething(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doSomething(request, response);
	}
	/**
	 * @see HttpServlet#doSomething(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doSomething(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		
		if(email==null || email.trim().equals("") || password == null || password.trim().equals(""))
		{
			RequestDispatcher rd = request.getRequestDispatcher("error_parametros.html");
			rd.forward(request, response);
			return;
		}
		
		String sql = "SELECT * FROM users WHERE email = \'" + email + "\' AND password = \'" + password + "\'";

		System.out.println("AutenticarServlet - SQL - " + sql);

		// Prepara acceso a Base de Datos
		try 
		{
			Class.forName(DRIVER); // Carga Driver JDBC en memoria
			
			con = DriverManager.getConnection(URL, USER, PASSWORD);		// Establece conexion con base de datos
			
			stmt = con.createStatement();		// Crear un objeto Statement para darle intrucciones a la base de datos
			
			rs = stmt.executeQuery(sql); // Ejecuta query en base de datos para Valida que usario y passwors sean correcto
			
			
			if(rs.next()) // Encontro un registro -- Credenciales validas
			{
				UserValueObject user = new UserValueObject();
				
				user.setEmail(rs.getString("email"));
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				
				request.getSession().setAttribute("user", user);
				
				RequestDispatcher rd = request.getRequestDispatcher("main.view");
				rd.forward(request, response);
				return;				
			}
			else // Las credenciales NO son validas
			{
				request.setAttribute("error", "<font color=\"red\">Credenciales son invalidas</font>");
				
				RequestDispatcher rd = request.getRequestDispatcher("error.view");
				rd.forward(request, response);
			}
		}
		catch(ClassNotFoundException ex)
		{
			ex.printStackTrace();
			
			StackTraceElement[] st = ex.getStackTrace();
			
			StringBuffer sb = new StringBuffer();
			
			for(int i =0 ; i < st.length; i++)
			{
				sb.append(st[i].toString());
			}
		
			String mensajeError = "<font color=\"red\"><h1>Ocurrio un error</h1></font>"
					            + "<p>" +  ex.getMessage() + "</p>"
					            + "<p>" + sb.toString() + "</p>";
			
			request.setAttribute("error", mensajeError);

			RequestDispatcher rd = request.getRequestDispatcher("error.view");
			rd.forward(request, response);
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
			
			StackTraceElement[] st = ex.getStackTrace();
			
			StringBuffer sb = new StringBuffer();
			
			for(int i =0 ; i < st.length; i++)
			{
				sb.append(st[i].toString());
			}
		
			String mensajeError = "<font color=\"red\"><h1>Ocurrio un error con la base de datos</h1></font>"
					            + "<p>El query ejecutado fue [" + sql + "]"
					            + "<p>" +  ex.getMessage() + "</p>"
					            + "<p>" + sb.toString() + "</p>";
			
			request.setAttribute("error", mensajeError);
			
			RequestDispatcher rd = request.getRequestDispatcher("error.view");
			rd.forward(request, response);
		}
		finally
		{
			try{ if(rs != null) rs.close(); } catch(SQLException exi) { exi.printStackTrace();}
			try{ if(stmt != null) stmt.close(); } catch(SQLException exi) { exi.printStackTrace();}
			try{ if(con != null) con.close(); } catch(SQLException exi) { exi.printStackTrace();}
			
			System.out.println("AutenticarServlet - database resources were closed");
		}
	}
}
