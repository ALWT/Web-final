import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.*;
import interfaces.*;
import implement.*;
import java.rmi.registry.*;

@WebServlet(description = "ceva", urlPatterns = { "/Start" })
public class Start extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    
    public Start() 
    {
        super();    
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String resp="";
		resp+="<div class=\"row\">";
		System.out.println(Connection.getServ_list().size());
		for(Server s: Connection.getServ_list())
        {System.out.println("now");
			try 
			{  
				Registry r= LocateRegistry.getRegistry(s.getHost());
				System.out.println(r==null);
				DBManageinter db =(DBManageinter)r.lookup("DBManage-"+s.getDbase());
				System.out.println(db==null);
				//DBManageinter db = new DBManageReal("localhost",s.getDbase(),"root","");
	            List<Farmacie> fl = db.getFarmacii();
	            
	            System.out.println(fl==null);
	            if(fl!=null)
	            	for(Farmacie f : fl)
	            	{	System.out.println(f.getNume());
	            		resp+="<div class=\"floating-box col\" style=\"display=inline; margin-top: 25px\">"+f.getNume()+"<br>"+f.getAdresa()+"<br>"+f.getNrtel()+"<br>"+"</div>";     	
            } }
			catch (Exception e) 
			{
                System.err.println("ComputeEngine exception:");
                e.printStackTrace(); 
            }
		
        }
		resp+="</div>";
		System.out.println("start");
		response.getWriter().println(resp);	
	} 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
