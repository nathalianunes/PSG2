package communication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

public class Create {
	public boolean createPonto(String nome, String tempo){
		try{
			String url = "http://127.0.0.1:8888/newPonto"; //localhost

			HttpClient httpClient = new DefaultHttpClient();
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		    nameValuePairs.add(new BasicNameValuePair("nome","teste10"));
		    nameValuePairs.add(new BasicNameValuePair("tempo", "23525"));
		    
		    //nameValuePairs.add(new BasicNameValuePair("nome", nome));
		    //nameValuePairs.add(new BasicNameValuePair("tempo", tempo));
		    
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
	
	public boolean newDecubito(String nome, String tempo){
		try{
			String url = "http://127.0.0.1:8888/newDecubito"; //localhost

			HttpClient httpClient = new DefaultHttpClient();
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		    nameValuePairs.add(new BasicNameValuePair("nome","test1"));
		    nameValuePairs.add(new BasicNameValuePair("tempo", "23525"));
		    
		    //nameValuePairs.add(new BasicNameValuePair("nome", nome));
		    //nameValuePairs.add(new BasicNameValuePair("tempo", tempo));
		    
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
}
