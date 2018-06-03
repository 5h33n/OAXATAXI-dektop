package oaxataxiDesktop;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import ifg.*;

public class Main {
	 
	public static void main(String...args) throws SQLException, IOException{
		//Login l = new Login();
		String s1,s2;
		String sFichero="src\\comprobacion.txt";
		BufferedReader br = new BufferedReader (new FileReader (sFichero));
		s1 = br.readLine();
		if(s1.equals("primero")) {
			Bienvenida b = new Bienvenida();	
			b.main(null);
		}else
		{
			Login l= new Login();
		}
	}
}
