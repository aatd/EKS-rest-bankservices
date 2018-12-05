package aufgabenblatt_2_RESTFul_API;

import java.util.ArrayList;
import java.util.List;


public class Transaktionen 
{	
	
	public static void fuegeTransaktionHinzu(List<Transaktion> liste, Transaktion aktion)
	{
		liste.add(aktion);
	}
	
	public static void fuehreTranskationDurch(Konto konto, Transaktion aktion)
	{
		TransaktionsTyp typ 	= aktion.getTyp();		
		int 			stand 	= konto.getKontostand(),
						betrag 	= aktion.getBetrag();
		
		if(typ == TransaktionsTyp.AUSZAHLUNG)
		{
			konto.setKontostand( stand - betrag );
		}
		else
		{
			konto.setKontostand( stand + betrag );
		}
	}

	public static List<Transaktion> gebeSpezielleTransaktionEinesKontosAus(List<Transaktion> aktionen, TransaktionsTyp typ)
	{
		List<Transaktion> out = new ArrayList<Transaktion>();
		
		for(Transaktion t: aktionen)
		{
			if(t.getTyp() == typ)
			{
				out.add(t);
			}
		}
		
		return out;
	}
}
