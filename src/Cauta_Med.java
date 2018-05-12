import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Farmacie;
import classes.Med_Farmacie;
import implement.DBManageReal;
import interfaces.DBManageinter;

import classes.*;
import interfaces.*;
import implement.*;
import java.rmi.registry.*;
import java.sql.SQLException;

@WebServlet("/Cauta_Med")
public class Cauta_Med extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cauta_Med() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String nume = request.getParameter("nume");
		
		String resp="", aux="";
		int contor=0;
		for(Server s: Connection.getServ_list())
        { 
			contor++;
			try 
			{  
				//Registry r= LocateRegistry.getRegistry(s.getHost());
				//DBManageinter db =(DBManageinter)r.lookup("DBManage-"+s.getDbase());
				//Medicamentinter medd =(Medicamentinter)r.lookup("Medicament-"+s.getDbase());
			    DBManageReal db = new DBManageReal("localhost",s.getDbase(),"root","");
	            MedicamentReal medd = new MedicamentReal("localhost",s.getDbase(),"root","");
	            
	            Medicament med = db.getMedicamentName(nume);//caut numele medicamentului in tabela Medicament
	            int id = med.getID();
	            int cantitate = Integer.parseInt(request.getParameter("nr"));//cantitate medicament
	            int ok=0; //pp ca nu exista medicament in nicio farmacie
	            
	            if (med!=null)
	            {  	
	            	if(contor==1)
	            	{
	            		resp +="<div class=\"poza\" style=\"margin-top: 100px\"><img src=img\\"+med.getPoza()+" alt=medicament height=\"148\" width=\"248\"></div>";
	            		resp +="<div class=\"descriere\" style=\"background-color: #ffffff; opacity: 0.5;\">"+med.getDescriere()+"</br></br>"+"<div style=\"font-size: 25px\"><b> Pret :"+med.getPret()+" lei</b>"+"</div></div>";

	            	}
    				
    				List<Farmacie> fl = medd.getFarm(id);
	            	if(fl != null)
<<<<<<< HEAD
            		{
	            		resp+="<div class=\"row\">";
	            		resp+="<div class=\"col-4\">";
=======
            		{resp+="<div class=\"row\">";
            		resp+="<div class=\"col\">";
>>>>>>> 75e7eeede860b69bf4b648e9482dcb2fa54d1508
	                   	String farmacie = ""; 
            			for(Farmacie f : fl)
        				{
            				Med_Farmacie  meddd = medd.getMed_Farm(id, f.getID());
        					if(meddd.getCantitate() >= cantitate)
        					{
        						farmacie += f.getNume()+"</br>";
        						ok=1;
<<<<<<< HEAD
                				aux="<button class=\"btn btn-dark\" style=\"margin-left: 500px; margin-top:50px;\" onClick=\"buymedicament('"+med.getNume()+"','"+s.getDbase()+"','"+f.getNume()+"');\">Adauga in WishList in " +f.getNume()+"</button><br>";
                				resp+=aux; 
        					}  
        				}    
                         
            			resp+="</div>";
            			resp+="<div class=\"col-4\">";
=======
                				aux="<button class=\"btn btn-dark\" style=\"margin-left: 300px; margin-top:50px;\" onClick=\"buymedicament('"+med.getNume()+"','"+s.getDbase()+"','"+f.getNume()+"');\">Adauga in WishList in " +f.getNume()+"</button><br>";
                				resp+=aux; 
        					}  
        				}
                         resp+="</div>";
            			resp+="<div class=\"col\">";
>>>>>>> 75e7eeede860b69bf4b648e9482dcb2fa54d1508
            			if (ok==0)
            				resp +="<div class=\"disponibilitate\" style=\"background-color: #ffffff; margin-top:50px; opacity: 0.5;\"><b>"+"Nu exista in stoc in "+s.getDbase()+"</b><br></div>";
            			else
            			{
<<<<<<< HEAD
            				resp +="<div class=\"disponibilitate\" style=\"background-color: #ffffff; margin-top:50px; opacity: 0.5;\"><b>"+"Disponibil in: "+s.getDbase()+"</b><br><div style=\"margin-left: 125px\">"+farmacie+"</br>"+"</div></div>";
=======
            				resp +="<div class=\"disponibilitate\" style=\"background-color: #ffffff; opacity: 0.5;\"><b>"+"Disponibil in: "+s.getDbase()+"</b><br><div style=\"margin-left: 125px\">"+farmacie+"</br>"+"</div></div>";
>>>>>>> 75e7eeede860b69bf4b648e9482dcb2fa54d1508
                   		}
                   		resp+="</div>";
                   		resp+="</div>";
            		}
	            } 
            } 
			catch(NumberFormatException e)
	      	{
				resp+="<div class=\"disponibilitate\" style=\"background-color: #ffffff; opacity: 0.5;\">Cantitate invalida</div>";
				System.err.println("Cantitate invalida");
	      	}
			catch(NullPointerException e)
	      	{
				resp+="<div class=\"disponibilitate\" style=\"background-color: #ffffff; opacity: 0.5;\">Medicament invalid</div>";
				System.err.println("Medicament invalid");
	      	}
			catch (Exception e) 
			{
                System.err.println("ComputeEngine exception:");
                e.printStackTrace();
            }
			resp+="</br></br></br>";
        }
		response.getWriter().println(resp);	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
