package aufgabenblatt_2_RESTFul_API;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class BankserviceClient
{	
	public static void main(String[] args) 
	{	
		//Beispielhafte verwendung der API 	
		Client 		c = ClientBuilder.newClient();
		WebTarget 	t = c.target("http://localhost:54555/bankservices");
		
		posteNeuenKunden(t,				 new Kunde      ("Thorsten" , "Aachen"));
		posteNeuenKunden(t,				 new Kunde      ("Andreas" , "Paderborn"));
		getKunde(t, "Thorsten");
		getKunde(t, "Andreas");
		posteNeuesKonto (t,	"Thorsten",     "85");
		posteNeuesKonto (t,	"Thorsten",   "285");
		posteNeuesKonto (t,	"Andreas",   "1085");
		posteTransaktion(t, "3", 		 new Transaktion( TransaktionsTyp.EINZAHLUNG, 17 ));
		posteTransaktion(t, "3", 		 new Transaktion( TransaktionsTyp.AUSZAHLUNG, 185 ));
		posteTransaktion(t, "2", 		 new Transaktion( TransaktionsTyp.EINZAHLUNG, 185 ));
		getAlleKontenEinensKunden(t, "Thorsten");
		getAlleKunden(t);
		getSpezielleTransaktionEinesKontos(t, "3", TransaktionsTyp.EINZAHLUNG);
		getAktuellenKontostand(t, "1");
		getAktuellenKontostand(t, "2");
		getAktuellenKontostand(t, "3");
	}
	
	public static void posteNeuenKunden(WebTarget t, Kunde neuerKunde)
	{
		Kunde 			zuSchickenderKunde;
		Entity<Kunde> 	entity;

		entity				= Entity.entity( neuerKunde, MediaType.APPLICATION_XML );
		
		zuSchickenderKunde 	= t.path   (        "kunden"         )
							   .request(MediaType.APPLICATION_XML)
							   .accept (MediaType.APPLICATION_XML)
							   .post   (   entity,    Kunde.class);
		
		System.out.println(
							"Client hat neuen Kunden anlegen: \n" + 
							"####Output####: " + zuSchickenderKunde.getName() + "  hinzugefuegt\n"
						   );	
	}
	
	public static void getKunde(WebTarget t, String name)
	{
		Response response;
		
		response = t.path  (        "kunden"         )
				   .path   (          name           )
				   .request(                         )
				   .accept (MediaType.APPLICATION_XML)
				   .get    (                         );
		
		String kunde = response.readEntity(String.class);
		
		System.out.println(
							"Client will Abfragen: \n" + 
							"####Output####: \n	Kundenname: " + name +   ".\n"
							+ "	KundenObjekt: " + kunde + "\n"
						  );
	}
	
	public static void posteNeuesKonto (WebTarget t, String kundenName, String startguthaben)
	{
		String 			response;
		Entity<String> 	entity;

		entity		= Entity.entity( 
										startguthaben,  
										MediaType.TEXT_PLAIN
									);
		
		response 	= t.path   (          "kunden"         )
					   .path   (         kundenName        )
					   .request(    MediaType.TEXT_PLAIN   )
					   .accept ( MediaType.APPLICATION_XML )
					   .post   (    entity,   String.class );
		
		System.out.println(
							"Client hat neues Konto angelegt: \n" + 
							"####Output####: \n	Kunde: " + kundenName +", \n" +
							"	Kontonummer: ." + response + ".\n"
						   );	
	}
	
	public static void posteTransaktion(WebTarget t, String kontonummer, Transaktion aktion)
	{
		Response			    response;
		Entity<Transaktion> 	entity;

		entity					= Entity.entity( aktion, MediaType.APPLICATION_XML );
		
		response 	= t.path   (         "kunden"          )
					   .path   (        kontonummer        )
					   .path   (       "transaktion"       )
					   .request( MediaType.APPLICATION_XML )
					   .accept ( MediaType.APPLICATION_XML )
					   .post   (          entity           );
		
		String transaktion = response.readEntity(String.class);		
		System.out.println(
							"Client hat neuen Transaktion angelegt: \n" + 
							"####Output####: \n	Transaktionsobjekt: " + transaktion + "\n"
						   );
	}

	public static void getAlleKontenEinensKunden(WebTarget t , String name)
	{
		Response response;
		
		response = t.path  (         "kunden"          )
				   .path   (           name            )
				   .path   (         "konten"          )
				   .request(                           )
				   .accept ( MediaType.APPLICATION_XML )
				   .get    (                           );
		
		String konten = response.readEntity(String.class);
		
		System.out.println(
							"Client will alle Konten eines Kunden abfragen \n" + 
							"####Output####: \n"
							+ "	Kundenname: " + name +   "\n"
							+ "	KundenObjekt: " + konten + "\n"
						  );
	}

	public static void getAlleKunden(WebTarget t)
	{
		Response response;
		
		response = t.path  (        "kunden"         )
				   .request(                         )
				   .accept (MediaType.APPLICATION_XML)
				   .get    (                         );
		
		String kunde = response.readEntity(String.class);
		
		System.out.println(
							"Client will alle Kunden abfrage: \n" + 
							"####Output####: \n"
							+ "	KundenObjekt: " + kunde + "\n"
						  );
	}

	public static void getSpezielleTransaktionEinesKontos(WebTarget t, String kontonummer, TransaktionsTyp typ)
	{
		Response response;
		
		response = t.path     (         "kunden"          )
				   .path      (        kontonummer        )
				   .queryParam(        "typ", typ         )
				   .request   (                           )
				   .accept    ( MediaType.APPLICATION_XML )
				   .get       (                           );
		
		String kunde = response.readEntity(String.class);
		
		System.out.println(
							"Client will " + typ + " des Kontos " + kontonummer + " abfragen \n" + 
							"####Output####: \n"
							+ "	KundenObjekt: " + kunde + "\n"
						  );
	}
	
	public static void getAktuellenKontostand (WebTarget t, String kontonummer)
	{
		Response response;
		
		response = t.path   (         "kunden"          )
				    .path   (        kontonummer        )
				    .path   (          "stand"          )
				    .request(                           )
				    .accept ( MediaType.TEXT_PLAIN      )
				    .get    (                           );
		
		String stand = response.readEntity(String.class);
		
		System.out.println(
							"Client will Momentanen Kontostand abfragen: \n" + 
							"####Output####: \n	Konto: " + kontonummer + "\n"
							+ "	Kontstand: " + stand + "EURO.\n"
						  );
	}
}
