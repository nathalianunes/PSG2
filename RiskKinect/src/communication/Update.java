package communication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import controller.Decubito;

public class Update {
	private String[] dorsal;
	private String[] ventral;
	private String[] lateralDireito;
	private String[] lateralEsquerdo;
	
	public Update(){
		dorsal = new String[13];
		
		dorsal[0] = "occiptal";
		dorsal[1] = "escapularDir";
		dorsal[2] = "escapularEsq";
		dorsal[3] = "apofisesEspinhosas";
		dorsal[4] = "cristaIliacaDir";
		dorsal[5] = "cristaIliacaEsq";
		dorsal[6] = "sacro";
		dorsal[7] = "joelhoDir";
		dorsal[8] = "joelhoEsq";
		dorsal[9] = "tendaoDir";
		dorsal[10] = "tendaoEsq";
		dorsal[11] = "calcanharDir";
		dorsal[12] = "calcanharEsq";
		
		ventral = new String[4];
		ventral[0] = "pavilhaoAuricularDir";
		ventral[1] = "pavilhaoAuricularEsq";
		ventral[2] = "ombroDir";
		ventral[3] = "ombroEsq";
		
		lateralDireito = new String[5];
		lateralDireito[0] = "pavilhaoAuricularDir";
		lateralDireito[1] = "ombroDir";
		lateralDireito[2] = "cotoveloDir";
		lateralDireito[3] = "trocanterDir";
		lateralDireito[4] = "maleoloDir";
		
		lateralEsquerdo = new String[5];
		lateralEsquerdo[0] = "pavilhaoAuricularEsq";
		lateralEsquerdo[1] = "ombroEsq";
		lateralEsquerdo[2] = "cotoveloEsq";
		lateralEsquerdo[3] = "trocanterEsq";
		lateralEsquerdo[4] = "maleoloEsq";
	}
	
	public boolean updatePonto(String nome, double tempo){
		try{
			String url = "http://127.0.0.1:8888/updatePonto"; //localhost

			HttpClient httpClient = new DefaultHttpClient();
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			
			double time = this.getTime("ponto", nome);
			
			Double tempoTotal = time+tempo;
			
		    nameValuePairs.add(new BasicNameValuePair("nome", nome));
		    nameValuePairs.add(new BasicNameValuePair("tempo", tempoTotal.toString()));
		    
		    UrlEncodedFormEntity form;
		    form = new UrlEncodedFormEntity(nameValuePairs);
            form.setContentEncoding(HTTP.UTF_8);
		    
			HttpPost postRequest = new HttpPost(url);
			postRequest.setEntity(form);
			HttpResponse response = httpClient.execute(postRequest);
		}
		catch(IOException e){
			System.out.println(e.getCause()+" Erro");
			return false;
		}
		return true;
	}
	
	private void updateDecubitoPontos(String decubito, double tempo){
		double time;
		Double tempoTotal;
		if(decubito.equals(Decubito.DORSAL.getNome())){
			for(int i=0; i<dorsal.length; i++){
				time = getTime("ponto", dorsal[i]);
				tempoTotal = time+tempo;
				updatePonto(dorsal[i], tempoTotal);
			}
		}
		else{
			if (decubito.equals(Decubito.LATERAL_DIR.getNome())){
				for(int i=0; i<lateralDireito.length; i++){
					time = getTime("ponto", lateralDireito[i]);
					tempoTotal = time+tempo;
					updatePonto(lateralDireito[i], tempoTotal);
				}
			}
			else{
				if (decubito.equals(Decubito.LATERAL_ESQ.getNome())){
					for(int i=0; i<lateralEsquerdo.length; i++){
						time = getTime("ponto", lateralEsquerdo[i]);
						tempoTotal = time+tempo;
						updatePonto(lateralEsquerdo[i], tempoTotal);
					}
				}
				else{
					for(int i=0; i<ventral.length; i++){
						time = getTime("ponto", ventral[i]);
						tempoTotal = time+tempo;
						updatePonto(ventral[i], tempoTotal);
					}
				}
			}
		}
	}
	
	public boolean updateDecubito(String nome, double tempo){
		try{
			String url = "http://127.0.0.1:8888/updateDecubito"; //localhost

			HttpClient httpClient = new DefaultHttpClient();
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

			double time = this.getTime("decubito", nome);
			Double tempoTotal = time+tempo;
			
		    nameValuePairs.add(new BasicNameValuePair("nome", nome));
		    nameValuePairs.add(new BasicNameValuePair("tempo", tempoTotal.toString()));
		    
		    UrlEncodedFormEntity form;
		    form = new UrlEncodedFormEntity(nameValuePairs);
            form.setContentEncoding(HTTP.UTF_8);
		    
			HttpPost postRequest = new HttpPost(url);
			postRequest.setEntity(form);
			HttpResponse response = httpClient.execute(postRequest);
			
			updateDecubitoPontos(nome, tempo);
		}
		catch(IOException e){
			System.out.println(e.getCause()+" Erro");
			return false;
		}
		return true;
	}
	
	private double getTime(String obj, String parte){
		HttpClient httpClient = new DefaultHttpClient();
		try{
			String url = "http://127.0.0.1:8888/getTime"; //localhost

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		    nameValuePairs.add(new BasicNameValuePair("obj", obj));
		    nameValuePairs.add(new BasicNameValuePair("parte",parte));
		    
		    UrlEncodedFormEntity form;
		    form = new UrlEncodedFormEntity(nameValuePairs);
            form.setContentEncoding(HTTP.UTF_8);
		    
			HttpPost postRequest = new HttpPost(url);
			postRequest.setEntity(form);
			HttpResponse response = httpClient.execute(postRequest);
			ResponseHandler<String> handler = new BasicResponseHandler();
			String body = handler.handleResponse(response);
			
			return Double.parseDouble(body);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
			httpClient.getConnectionManager().shutdown(); 
		}
		return 0.0;
	}
}
