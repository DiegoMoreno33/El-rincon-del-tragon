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

//import mx.ipn.upiicsa.segsw.labicla.valueobject.ProductValueObject;
import mx.ipn.upiicsa.segsw.labicla.valueobject.RecipeValueObject;

/**
 * 
 * @author Guillermo E. Martinez Barriga
 *
 */
@WebServlet("/get_recipe_details.controller")
public class GetRecipeDetailsServlet extends HttpServlet implements Servlet {
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
		
		String id = request.getParameter("id");
		
		
		String sql = "SELECT * FROM recipes WHERE id = " + id; /* Armar instruccion SQL a ejecutar */
		
		System.out.println(sql);
		
		try {
			
			// Abrir conexion a la base de datos
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			// Abrir conexion a la base de datos
			
			
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(sql); // Ejecutar instruccion SQL
			
			RecipeValueObject recipe = null;

			if(rs.next())
			{
				// Crear Value Object u asignar valores de la base de datos
				recipe = new RecipeValueObject();
	
				recipe.setId(rs.getInt("id"));
				recipe.setRecipeCreatorEmail(rs.getString("recipe_creator_email"));
				recipe.setName(rs.getString("name"));
				recipe.setDescription(rs.getString("description"));
				recipe.setIngredientList(rs.getString("ingredient_list"));
				recipe.setProcedure(rs.getString("recipe_procedure"));
				recipe.setImage(rs.getString("image"));
				
			}
			
			//Agregar recipe a request para que lo tome el siguiente view
			request.setAttribute("recipe", recipe);
			
			RequestDispatcher rd = request.getRequestDispatcher("recipe_details.view");
			rd.forward(request, response);
			return;
			
			//Crear dispatcher y mandar llamar siguiente view
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
		}
	}
}
