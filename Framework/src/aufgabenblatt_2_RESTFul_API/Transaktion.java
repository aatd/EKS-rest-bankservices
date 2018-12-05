package aufgabenblatt_2_RESTFul_API;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Transaktion 
{
	private int betrag;
	private TransaktionsTyp typ;
	
	public Transaktion() {}
	
	public Transaktion(TransaktionsTyp typ, int betrag)
	{
		this.betrag = betrag;
		this.typ    = typ;
	}
	
	@XmlElement
	public void setBetrag(int betrag)
	{
		this.betrag = betrag;
	}
	public int getBetrag()
	{
		return this.betrag;
	}

	@XmlElement( name = "transaktionsTyp" )
	public void setTyp(TransaktionsTyp typ)
	{
		this.typ = typ;
	}
	public TransaktionsTyp getTyp()
	{
		return this.typ;
	}
}
