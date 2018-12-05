package aufgabenblatt_2_RESTFul_API;

import java.net.URI;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import com.sun.net.httpserver.HttpServer;

public class Server 
{
	public static void main(String[] args) throws Exception 
	{
		starteBankServer();
	}
	
	public static void starteBankServer() throws Exception
	{
		URI 			bankserviceURI;
		ResourceConfig 	bankserviceRC;
		HttpServer 		bankserviceServer;
		
		bankserviceURI 		= new URI("http://localhost:54555/");
		bankserviceRC 		= new ResourceConfig(    Services.class);	
		bankserviceServer 	= JdkHttpServerFactory.createHttpServer(bankserviceURI, bankserviceRC);
		
		System.out.println(bankserviceServer.getAddress().getHostString() + " Server läuft...\n");
	}
}
