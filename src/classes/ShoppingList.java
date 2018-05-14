package classes;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingList 
{
	public static  List<MedicamentShop> buylist=new ArrayList<MedicamentShop>();
	
	public  static void addMedicament(Medicament m,int quant,String f)
	{
		buylist.add(new MedicamentShop(m,quant,f));
	}
	
	public static List<MedicamentShop> getMedicaments()
	{	
		return buylist;
	}
	
	public static void checkout()
	{
		buylist=new ArrayList<MedicamentShop>();
	}
}
