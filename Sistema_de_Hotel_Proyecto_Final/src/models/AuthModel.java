package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import redis.clients.jedis.Jedis;
import views.AuthView;
import views.HomeView;


public class AuthModel {
	
	public AuthModel() {
		
	}
	public boolean conectado(String u, String p) throws IOException  {
		
		Jedis jedis = new Jedis("splendid-hound-18755.upstash.io", 6379, true);
		jedis.auth("AUlDAAIjcDEzNTBiYmZkMTg2OTY0N2YyYmE4NDVhY2VjM2NhMjk0N3AxMA");

		List<String> listaRedis = jedis.lrange("cuenta", 0, 3);
		
		  if (!jedis.isConnected()) {
	        System.err.println("ERROR: No se pudo conectar con la base de datos");
	        return false;
	    }
		

	    try{
	    	
	       for (String linea : listaRedis) {
			
	            System.out.println(linea);
	            String[] datos = linea.split(",");
	            String pass = "";
	            String user = "";
	            
	            if (datos.length < 3) {
	            user = datos[0].trim();
	            pass = datos[1].trim();
	            }
	            if (user.equals(u) && pass.equals(p)) {
	            	HomeView entrada = new HomeView();
	            	entrada.home();
	                return true;
	            }
	       }
	    }
	    finally{
	    	
	    }
	    return false;
	}
}
