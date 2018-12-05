package aufgabenblatt_2_RESTFul_API;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;

@XmlRootElement
public class Kunde 
{
	private String 		name, 
						adresse;
	private List<Konto> konten;
	
	public Kunde(){}
	public Kunde(String name, String adresse)
	{
		this.konten = new ArrayList<Konto>();
		this.name    = name;	
		this.adresse = adresse;
	}
	
	@XmlElement
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}
	
	@XmlElement
	public void setAdresse(String adresse)
	{
		this.adresse = adresse;
	}
	public String getAdresse()
	{
		return this.adresse;
	}
	
	@XmlElement( name = "konten")
	public void setKonten(List<Konto> konten)
	{
		this.konten = konten;
	}
	public List<Konto> getKonten()
	{
		return this.konten;
	}
}
