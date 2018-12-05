package aufgabenblatt_2_RESTFul_API;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class Kunden 
{	
	private static ArrayList<Kunde> alleKunden;
	private static Kunden kunden;
		
	/*Singleton Start*/
	private Kunden()
	{
		alleKunden = new ArrayList<Kunde>();
	}
	public static Kunden instanziereKunden()
	{
		if(kunden == null)
		{
			kunden = new Kunden();
		}
		return kunden;
	}
	/*Singleton End  */
	
	public static void fuegeKundenHinzu(Kunde k)
	{
		alleKunden.add(k);
	}
	
	public static Kunde getKundeDurchName(String name)
	{		
		for(Kunde k : alleKunden)
		{
			if(k.getName().equals(name))
			{
				return k;
			}
		}
		
		return null;
	}
	
	public static List<Kunde> getAlleKunden()
	{
		return alleKunden;
	}
}