package aufgabenblatt_2_RESTFul_API;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Konten 
{
	private static int 					zaehler;
	private static List<Konto> 			alleKonten;
	private static Map<Integer, Kunde>  kontoZugehoerigkeit;
	private static Konten 				konten;
	
	/*Singleton Start*/
	private Konten()
	{
		zaehler 			= 0;
		alleKonten 			= new ArrayList<Konto>();
		kontoZugehoerigkeit = new HashMap<Integer, Kunde>();
	}
	public static Konten instanziereKonten()
	{
		if(konten == null)
		{
			konten = new Konten();
		}
		return konten;
	}
	/*Singleton End  */
	
	public static void fuegeKontoHinzu(List<Konto> liste, Kunde kunde, Konto konto)
	{
		liste.add(konto);
		alleKonten.add(konto);
		kontoZugehoerigkeit.put(konto.getKontoNummer(), kunde);
	}
	
	public static int generiereKontonummer()
	{
		return ++zaehler;
	}
	
	public static Konto getKontoDurchNummer(int nummer)
	{		
		for(Konto k : alleKonten)
		{
			if(k.getKontoNummer() == nummer)
			{
				return k;
			}
		}
		
		System.out.println("#####Server Meldet#####: Konto " + nummer + " exestiert nicht!");
		return null;
	}
	
	public static Kunde getKundeDurchNummer(int nummer)
	{
		
		for(Integer nr: kontoZugehoerigkeit.keySet())
		{
			if(nr == nummer)
			{
				return kontoZugehoerigkeit.get(nr);
			}
		}
		
		System.out.println("#####Server Meldet#####: Konto " + nummer + " exestiert nicht!");
		return null;
	}
	
	
}
