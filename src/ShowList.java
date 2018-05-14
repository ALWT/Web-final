import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.MedicamentShop;
import classes.ShoppingList;

/**
 * Servlet implementation class ShowList
 */
@WebServlet("/ShowList")
public class ShowList extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowList() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		float total=0;
		String resp="<div style=\"font-size: 25px\"><b>Wishlist</b> <br></div>";
		
		resp="<div style=\"font-size: 20px\">";
		for(MedicamentShop ms:ShoppingList.getMedicaments())
		{
			resp+=ms.m.getNume()+" "+ms.quant+"x"+ms.m.getPret()+"="+ms.quant*ms.m.getPret()+" din "+ms.f;
			total+=ms.quant*ms.m.getPret();
			resp+="</br>";
			
		}
		resp+="</br></div>";
		resp+="<div style=\"font-size: 25px\"><b>Total: "+total+"</b></div>";
		response.getWriter().append(resp);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
