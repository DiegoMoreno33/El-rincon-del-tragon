package mx.ipn.upiicsa.segsw.labicla.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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
@WebServlet("/main.view")
public class MainServlet extends HttpServlet implements Servlet {
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
		response.setContentType("text/html"); // // Establece tipo de contenido a enviar al cliente (browser)
		
		PrintWriter out = response.getWriter(); // regresa una refencia al stream de salida hacia el cliente
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");
		out.println("<head>");
		out.println("<title>El Rinc&oacute;n del Trag&oacute;n</title>");
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
			out.println("<form action=\"search_recipes.controller\" class=\"form-inline\" method=\"GET\">");
			out.println("<input class=\"form-control mr-sm-2\" type=\"search\" placeholder=\"Escribe tu platillo\" aria-label=\"Search\" name=\"criterio\">");
			out.println("<button class=\"btn btn-warning my-2 my-sm-0\" type=\"submit\" value=\"Buscar\">Buscar</button>");
			out.println("</form>");
			out.println("</nav>");
			// No hay usuario autenticado
			out.println("<div class=\"limiter\">");
			out.println("<div class=\"container-login100\">");
			out.println("<div class=\"wrap-login100\">");
			out.println("<div class=\"login100-form-title\" style=\"background-image: url(images/comida2.jpg);\">");
			out.println("<span class=\"login100-form-title-1\">");
			out.println("Iniciar sesi&oacute;n");
			out.println("</span>");
			out.println("</div>");
		
			//****EMAIL Y PASSWORD*****
			out.println("<form class=\"login100-form validate-form\" action=\"authenticate.controller\" method=\"GET\">");
			out.println("<div class=\"wrap-input100 validate-input m-b-26\" data-validate=\"Username is required\">");
			out.println("<span class=\"label-input100\">");
			out.println("Email");
			out.println("</span>");
			out.println("<input type=\"email\" pattern=\"^[a-zA-Z0-9.!#$%&*+/=?^_`{|}~]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$\" class=\"input100\" id=\"email\" placeholder=\"example@example.com\" name=\"email\">");
			out.println("<span class=\"focus-input100\">");
			out.println("</span>");
			out.println("</div>");
			
			out.println("<div class=\"wrap-input100 validate-input m-b-18\" data-validate = \"Password is required\">");
			out.println("<span class=\"label-input100\">");
			out.println("Password");
			out.println("</span>");
			out.println("<input type=\"password\" class=\"input100\" placeholder=\"Ingresa tu contraseña\" name=\"password\">");
			out.println("<span class=\"focus-input100\">");
			out.println("</span>");
			out.println("</div>");
			
			out.println("<div class=\"container-login100-form-btn\">");
			out.println("<input type=\"submit\" class=\"login100-form-btn\" value=\"Entrar\">");
			out.println("</div>");
			out.println("</form>");
			out.println("</div>");
			out.println("</div>");
			out.println("</div>");	
			//****EMAIL Y PASSWORD*****
			
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
			out.println("<div class=\"limiter\">");
			out.println("<div class=\"container-login100\">");
			out.println("<div class=\"wrap-login100\">");
			out.println("<div class=\"login100-form-title\" style=\"background-image: url(images/comida.jpg);\">");
			out.println("<span class=\"login100-form-title-1\">");
			out.println("Bienvenido");
			out.println("</span>");
			out.println("</div>");
			//out.println("<div>" + user.getName() + " <a href=\"logout.controller\">Salir</a></div>");
			out.println("<center><h1>Conoce nuestro men&uacute;</h1><center>");
			
			/*carrusel*/
			out.println("<div id=\"carouselExampleControls\" class=\"carousel slide\" data-ride=\"carousel\">");
			out.println("<div class=\"carousel-inner\">");
			out.println("<div class=\"carousel-item active\">");
			out.println("<img class=\"d-block w-100\" src=\"images/uno.jpg\" alt=\"First slide\">");
			out.println("<div class=\"carousel-caption d-none d-md-block\">");
			out.println("<h5>Taquitos dorados</h5>");
			out.println("<p>ricos taquitos dorados con guacamole</p>");
			out.println("</div>");
			out.println("</div>");
			out.println("<div class=\"carousel-item\">");
			out.println("<img class=\"d-block w-100\" src=\"images/dos.jpg\" alt=\"Second slide\">");
			out.println("<div class=\"carousel-caption d-none d-md-block\">");
			out.println("<h5>Tacos al pastor</h5>");
			out.println("<p>Tradicionales y sabrosos tacos al pastor</p>");
			out.println("</div>");
			out.println("</div>");
			out.println("<div class=\"carousel-item\">");
			out.println("<img class=\"d-block w-100\" src=\"images/tres.jpg\" alt=\"Third slide\">");
			out.println("<div class=\"carousel-caption d-none d-md-block\">");
			out.println("<h5>Hamburguesas</h5>");
			out.println("<p>Hamburguesas con carne de res y queso derretido</p>");
			out.println("</div>");
			out.println("</div>");
			out.println("</div>");
			out.println("<a class=\"carousel-control-prev\" href=\"#carouselExampleControls\" role=\"button\" data-slide=\"prev\">");
			out.println("<span class=\"carousel-control-prev-icon\" aria-hidden=\"true\">");
			out.println("</span>");
			out.println("<span class=\"sr-only\">Previous");
			out.println("</span>");
			out.println("</a>");
			out.println("<a class=\"carousel-control-next\" href=\"#carouselExampleControls\" role=\"button\" data-slide=\"next\">");
			out.println("<span class=\"carousel-control-next-icon\" aria-hidden=\"true\"></span>");
			out.println("<span class=\"sr-only\">Next</span>");
			out.println("</a>");
			out.println("</div>");
			
		}
		
		
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

		out.close();
	}
}
