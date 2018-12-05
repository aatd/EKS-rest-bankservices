package aufgabenblatt_2_RESTFul_API;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Konto 
{
	private int 				stand, 
								nummer;
	private Kunde 				kunde;
	private List<Transaktion> 	aktionen;
	
	public Konto () {}
	public Konto(Kunde kunde, int startkapital)
	{
		this.stand    = startkapital;
		this.nummer   = Konten.generiereKontonummer();
		this.kunde    = kunde;
		this.aktionen = new ArrayList<Transaktion>();
	}
		
	@XmlElement
	public void setKunde(Kunde kunde)
	{
		this.kunde = kunde;
	}
	public Kunde getKunde()
	{
		return this.kunde;
	}
	
	@XmlElement
	public void setKontoNummer(int kontoNummer)
	{
		this.nummer = kontoNummer;
	}
	public int getKontoNummer()
	{
		return nummer;
	}
	
	@XmlElement
	public void setKontostand(int stand)
	{
		this.stand = stand;
	}
	public int getKontostand()
	{
		return stand;
	}
	
	@XmlElement( name = "transaktionen" )
	public void setTransaktionen(List<Transaktion> aktionen)
	{
		this.aktionen = aktionen;
	}
	public List<Transaktion> getTransaktionen()
	{
		return this.aktionen;
	}
}

