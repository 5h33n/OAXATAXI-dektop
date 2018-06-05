package oaxataxiDesktop;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.SQLException;

import ifg.*;

public class Main {
	 
	public static void main(String...args) throws SQLException, IOException{
		//Login l = new Login();
		String s1;
		URL ficheroUrl = new URL("http://oaxataxi.xyz/comprobacion.txt");
		BufferedReader in = new BufferedReader(new InputStreamReader(ficheroUrl.openStream()));
		s1 = in.readLine();
		if(s1.equals("primero")) {
			Bienvenida b = new Bienvenida();	
			b.main(null);
		}else
		{
			Login l= new Login();
		}
	}
}
