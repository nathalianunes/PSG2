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

public class Teste {
	public static void main(String args[]){
//		Update u = new Update();
//		u.updateDecubito("teste", "teste");
		try{
			String url = "http://127.0.0.1:8888/getTime"; //localhost

			HttpClient httpClient = new DefaultHttpClient();
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		    nameValuePairs.add(new BasicNameValuePair("obj","ponto"));
		    nameValuePairs.add(new BasicNameValuePair("parte","occiptal"));
		    //nameValuePairs.add(new BasicNameValuePair("value", "teste10"));
		    
		    UrlEncodedFormEntity form;
		    form = new UrlEncodedFormEntity(nameValuePairs);
            form.setContentEncoding(HTTP.UTF_8);
		    
			HttpPost postRequest = new HttpPost(url);
			postRequest.setEntity(form);
			HttpResponse response = httpClient.execute(postRequest);
			ResponseHandler<String> handler = new BasicResponseHandler();
			String body = handler.handleResponse(response);
			System.out.println("Body "+body);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
