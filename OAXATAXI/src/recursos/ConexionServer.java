package recursos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class ConexionServer {
	public ConexionServer() {
		
	}
	/**
	 *  Método que utiliza un archivo php alojado en el servidor para, modificando las variables de _POST, 
	 * poder realizar la consulta que se le envía
	 * @param requestCode codigo que identifica el tipo de resultado que se requiere en el archivo php
	 * @throws IOException
	 */
	public String[] Select(String query,int requestCode)  {
		try {
			//Primero definimos el archivo a utilizar y abrimos una conexión http
			URL fichero = new URL("http://oaxataxi.xyz/select.php");
			HttpURLConnection httpURLConnection = (HttpURLConnection) fichero.openConnection();
			httpURLConnection.setRequestMethod("POST");
	        httpURLConnection.setDoOutput(true);
	        httpURLConnection.setDoInput(true);
	        
	        //Con dicha conexión, abrimos un Stream de salida y un bufferwriter para escribie en el archivo
	        OutputStream outputStream = httpURLConnection.getOutputStream();
	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
	        
	        //Con el método encode, emebebemos el código referenciando a _POST, con el valor que espera, y el valor que le damos
	        String post_data = URLEncoder.encode("consulta","UTF-8")+"="+URLEncoder.encode(query,"UTF-8")+"&"

	                        +URLEncoder.encode("code","UTF-8")+"="+URLEncoder.encode(requestCode+"","UTF-8");
	        
	        //Escribimos las modificaciones en el archivo
	        bw.write(post_data);
	        bw.flush();
	        bw.close();
	        outputStream.close();
	                
	        //Con la conexión abierta leemos el contenido del archivo, peor ya no del backend, sino del frontend para obtener la respuesta de la consulta
	        InputStream inputStream = httpURLConnection.getInputStream();
	                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
	                String line=br.readLine();
	                if(requestCode==30||requestCode==40||requestCode==50||requestCode==30) {
		                System.out.println(line);}
	                String [] lista = line.split("<br>");
	                if(requestCode==30||requestCode==40||requestCode==50||requestCode==30) {
		                System.out.println(lista[0]);}
	                lista[0] = lista[0].substring(1);
	                br.close();
	                inputStream.close();
	                httpURLConnection.disconnect();
	                return lista;
		}catch(IOException e) {
			
		}
		return null;
	}
}