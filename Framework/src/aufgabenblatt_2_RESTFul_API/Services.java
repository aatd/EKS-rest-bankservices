package aufgabenblatt_2_RESTFul_API;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Path("bankservices")
public class Services 
{	
	/*1.)POST .../kunden									 */ //Done
	@POST @Path("kunden") 
	@Consumes(MediaType.APPLICATION_XML) @Produces(MediaType.APPLICATION_XML)
	public Kunde meldeNeuenKundenAn(Kunde k)
	{		
		Kunden.instanziereKunden();
		Kunden.fuegeKundenHinzu(k);
		
		System.out.println(
				"#####Server meldet######: Neuer Kunde hinzugefuegt.\n"
				+ "	Name: " + k.getName() + "\n	"
				+ "Adresse: " + k.getAdresse()
				);
		
		return k;
	}
	
	
	
	/*2.)GET  .../kunden/{name}								 */ //Done
	@GET @Path("kunden/{name}") 
	@Produces(MediaType.APPLICATION_XML)
	public Kunde frageKundeAb(@PathParam("name") String name)
	{
		Kunden.instanziereKunden();
		Kunde k = Kunden.getKundeDurchName(name);		
		
		System.out.println(
							"#####Server meldet######: Frage Kunden ab.\n"
							+ "	Name: "   + k.getName() + "\n	"
							+ "Adresse: " + k.getAdresse()
						  );
		
		return k;
	}
	
	
	
	/*3.)POST .../kunden/{name}								 */ //Done
	@POST @Path("kunden/{name}") 
	@Consumes(MediaType.TEXT_PLAIN) @Produces(MediaType.TEXT_PLAIN)
	public String meldeNeuesKontoAn(
									@PathParam("name") String kundenName, 
													   String startguthaben
								   )
	{
		Konten.instanziereKonten();
		
		Kunde 		 kunde  = Kunden.getKundeDurchName(kundenName);
		Konto 		 konto  = new Konto(kunde, Integer.parseInt(startguthaben));
		kunde.setKonten(new ArrayList<Konto>());
		List<Konto>  konten = kunde.getKonten();
		Konten.fuegeKontoHinzu(konten, kunde, konto);
		
		System.out.println(
				"#####Server meldet######: Der Kunde " + kunde.getName() + " hat ein neues Konto angelegt.\n"
							+ "	Kontonummer: "  + konto.getKontoNummer() + "\n"
							+ "	Startguthaben: " + startguthaben + "Euro."
						   );
		
		return konto.getKontoNummer()+"";
	}
	
	
	
	/*4.)POST .../kunden/{konto}/transaktion				 */ //Done
	@POST @Path("kunden/{konto}/transaktion") 
	@Consumes(MediaType.APPLICATION_XML) @Produces(MediaType.APPLICATION_XML)
	public Konto uebetransaktionAus( 
										@PathParam( "konto" ) 	String 			kontonummer, 
																Transaktion 	aktion
								    )
	{	
		
		Konto 				  konto = Konten.getKontoDurchNummer(Integer.parseInt(kontonummer));
		List<Transaktion>  aktionen = konto.getTransaktionen();
		
		Transaktionen.fuegeTransaktionHinzu(aktionen, aktion);			
		Transaktionen.fuehreTranskationDurch(konto, aktion);
		
		System.out.println(
				"#####Server meldet######: Neue " + aktion.getTyp() + " getaetigt\n"
							+ "	Kontonummer: "  + konto.getKontoNummer() + "\n"
							+ "	Guthaben: " + konto.getKontostand() + "Euro."
						   );
		
		return konto;
	}
	
	
	
	/*5.)GET  .../kunden/{name}/konten						 */ 
	@GET @Path("kunden/{name}/konten") @Produces(MediaType.APPLICATION_XML)
	public Kunde frageAlleKontenAb(@PathParam("name") String name)
	{		
		return Kunden.getKundeDurchName(name);
	}
	
	
	
	/*6.)GET  .../kunden/{konto}/stand						 */ //Done		
	@GET @Path("kunden/{konto}/stand") 
	@Produces(MediaType.TEXT_PLAIN)
	public String frageKontoStandAb(@PathParam("konto") String kontonummer)
	{
		Konto konto = Konten.getKontoDurchNummer(Integer.parseInt(kontonummer));
		return konto.getKontostand() + "";
	}
	
	
	
	/*7.)GET  .../kunden/{konto}?typ={transaktionstyp}		 */ 
	@GET @Path("/kunden/{konto}/transaktionen") 
	@Produces(MediaType.APPLICATION_XML)
	public Konto frageTransaktionenAb(
													@PathParam ( "konto" ) 	String 			konto, 
													@QueryParam(  "typ"  ) 	TransaktionsTyp typ
											     )
	{
		/*List<Transaktion>*/Konto kontoListe = Konten.getKontoDurchNummer(Integer.parseInt(konto));
		return kontoListe;	
	}
	
	
	
	/*8.)GEt  .../kunden									 */ //Done
	@GET @Path("kunden") 
	@Produces(MediaType.APPLICATION_XML)
	public List<Kunde> zeigeAlleKunden()
	{
		return Kunden.getAlleKunden();	
	}


}