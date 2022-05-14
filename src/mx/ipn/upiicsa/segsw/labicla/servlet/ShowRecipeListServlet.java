package mx.ipn.upiicsa.segsw.labicla.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.ipn.upiicsa.segsw.labicla.valueobject.RecipeValueObject;
import mx.ipn.upiicsa.segsw.labicla.valueobject.UserValueObject;

/**
 * 
 * @author Guillermo E. Martinez Barriga
 *
 */
@WebServlet("/recipe_list.view")
public class ShowRecipeListServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

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
		response.setContentType("text/html");  // Establece el tipo de contenido que el servlet va a enviar al cliente
		PrintWriter out = response.getWriter(); // Abre un canal de comunicacion con el cliente   // Portal multidimensional from Toulouse
		
		List<RecipeValueObject> recipeList = (List<RecipeValueObject>) request.getAttribute("recipeList");
		
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");
		out.println("<head>");
		out.println("<title>Recipes</title>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		out.println("<link rel=\"icon\" type=\"image/png\" href=\"images/icons/favicon.ico\"/>");
		
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"vendor/bootstrap/css/bootstrap.min.css\">");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"fonts/font-awesome-4.7.0/css/font-awesome.min.css\"");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"fonts/Linearicons-Free-v1.0.0/icon-font.min.css\">");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"vendor/animate/animate.css\">");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"vendor/css-hamburgers/hamburgers.min.css\">");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"vendor/animsition/css/animsition.min.css\">");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"vendor/select2/select2.min.css\">");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"vendor/daterangepicker/daterangepicker.css\">");
		
		out.println("<link href=\"//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css\" rel=\"stylesheet\" id=\"bootstrap-css\">");
		out.println("<script src=\"//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js\"></script>");
		out.println("<script src=\"//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>");
	
		
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/util.css\">");
		out.println("<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">");

		out.println("</head>");
		
		out.println("<body>");
		
		UserValueObject user = (UserValueObject) request.getSession().getAttribute("user");
		
		if(user == null)
		{
			out.println("<nav class=\"navbar navbar-light bg-light\">");
			out.println("<a class=\"navbar-brand\" href=\"#\">");
			out.println("<img src=\"images/elrincon.png\" width=\"40\" height=\"40\" class=\"d-inline-block align-top\" alt=\"\">");
			out.println("El Rinc&oacute;n del Trag&oacute;n</a>");
			out.println("<form class=\"form-inline\" action=\"authenticate.controller\" method=\"GET\">");
			out.println("<div class=\"form-group\">");
			out.println("Email ");
			out.println("<input type=\"email\" class=\"form-control\" placeholder=\"Enter username\" pattern=\"[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{1,5}\" name=\"email\">");
			out.println("</div>");
			
			out.println("<div class=\"form-group\">");
			out.println("Password  ");
			out.println("<input type=\"password\" class=\"form-control\" placeholder=\"Enter password\" pattern=\"^[a-z0-9_]{6,18}$\" name=\"password\">");
			
			out.println("</div>");
			out.println("<input type=\"submit\" class=\"btnChico\" value=\"Entrar\">");
			out.println("</form>");
			out.println("<br>");
			out.println("<br>");
			out.println("<form action=\"search_recipes.controller\" class=\"form-inline\" method=\"GET\">");
			out.println("<input class=\"form-control mr-sm-2\" type=\"search\" placeholder=\"Escribe tu platillo\" aria-label=\"Search\" name=\"criterio\">");
			out.println("<button class=\"btn btn-warning my-2 my-sm-0\" type=\"submit\" value=\"Buscar\">Buscar</button>");
			out.println("</form>");
			out.println("</nav>");
			// No hay usuario autenticado
			
			// Enviar formulario de autenticacion
			
		}
		else
		{
			out.println("<nav class=\"navbar navbar-light bg-light\">");
			out.println("<a class=\"navbar-brand\" href=\"#\">");
			out.println("<img src=\"images/elrincon.png\" width=\"40\" height=\"40\" class=\"d-inline-block align-top\" alt=\"\">");
			out.println("El Rinc&oacute;n del Trag&oacute;n</a>");
			out.println("<div>" + user.getName() + " <a href=\"logout.controller\">Salir</a></div>");
			out.println("<form action=\"search_recipes.controller\" class=\"form-inline\" method=\"GET\">");
			out.println("<input class=\"form-control mr-sm-2\" type=\"search\" placeholder=\"Escribe tu platillo\" aria-label=\"Search\" name=\"criterio\">");
			out.println("<button class=\"btn btn-warning my-2 my-sm-0\" type=\"submit\" value=\"Buscar\">Buscar</button>");
			out.println("</form>");
			out.println("</nav>");
			// SI hay usuario autenticado
			//out.println("<div>" + user.getName() + " <a href=\"logout.controller\">Salir</a></div>");
		}
		out.println("<br>");
				
		if(recipeList.size() == 0)
		{
			// Mostra informacion de recetas no encontradas
			out.println("<font SIZE=5 color=\"red\" ><p style=\"text-align:center;\">No se encontraron recetas</font></p>");
		}
		else
		{
			out.println("<div class=\"bonito\">");
			out.println("<table style=\"margin: 0 auto;\" border=\"1\">");
			
			out.println("<tr style=\"Background-color:#FFA633\">");
			out.println("<th>Nombre</th>");
			out.println("<th>Descripcion</th>");
			out.println("<th>By</th>");
			out.println("<th>&nbsp;</th>");
			out.println("</tr>");
			
			for(int i = 0; i < recipeList.size(); i++ )
			{
				out.println("<tr>");
				out.println("<td>");
				out.println(recipeList.get(i).getName()); // Mostrar informacion
				 // Mostrar informacion
				out.println("</td>");
				out.println("<td>");
				out.println(recipeList.get(i).getDescription());
				out.println("</td>");
				out.println("<td>");
				out.println(recipeList.get(i).getRecipeCreatorEmail());
				out.println("</td>");
				
				out.println("<td>");
				out.println("<a href=\"get_recipe_details.controller?id=" + recipeList.get(i).getId() + "\">Ver Detalle</a>");

				out.println("</td>");
				
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("</div>");
			
		}
		out.println("<br>");
		
		
		out.println("<a class=\"btn btn-primary mb1 bg-purple\" href=\"/el-rincon-del-tragon\">Principal</a>");
		out.println("<br>");
		out.println("<br>");
		out.println("</div>");
		
		out.println("<footer>");
		out.println("<div class= \"jumbotron text-center\" style=\"background: linear-gradient(45deg, #F39C12, #E67E22);\">");
		out.println("<p style=\"color: white\">Todos los derechos reservados a:");
		out.println("</p>");
		out.println("<figure>");
		out.println("<a href=\"https://teamslandercompany.000webhostapp.com/\">");
		out.println("<img src=\"images/slander.png\" alt=\"Image\" width=\"200\" height=\"200\" data-toggle=\"tooltip\" title=\"SLANDER COMPANY\">");
		out.println("</a>");
		out.println("</figure>");
	    out.println("<p style=\"color: white\">Equipo Slander ");
		out.println("</p>");
	    out.println("</div>");
	    out.println("</footer>");

		out.println("<script src=\"vendor/jquery/jquery-3.2.1.min.js\"></script>");
		out.println("<script src=\"vendor/animsition/js/animsition.min.js\"></script>");
		out.println("<script src=\"vendor/bootstrap/js/popper.js\"></script>");
		out.println("<script src=\"vendor/bootstrap/js/bootstrap.min.js\"></script>");
		out.println("<script src=\"vendor/select2/select2.min.js\"></script>");
		out.println("<script src=\"vendor/daterangepicker/moment.min.js\"></script>");
		out.println("<script src=\"vendor/daterangepicker/daterangepicker.js\"></script>");
		out.println("<script src=\"vendor/countdowntime/countdowntime.js\"></script>");
		out.println("<script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>");
		out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js\" integrity=\"sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49\" crossorigin=\"anonymous\"></script>");
		out.println("<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js\" integrity=\"sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy\" crossorigin=\"anonymous\"></script>");
	
		out.println("<script src=\"js/main.js\"></script>");
		
		out.println("</body>");
		out.println("</html>");
	}
}
