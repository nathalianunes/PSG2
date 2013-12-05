package communication;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

public class Teste {
	public static void main(String args[]){
		try{
			String url = "http://127.0.0.1:8888/new"; //localhost

			HttpClient httpClient = new DefaultHttpClient();
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		    nameValuePairs.add(new BasicNameValuePair("key","test"));
		    nameValuePairs.add(new BasicNameValuePair("value", total));
		    
		    UrlEncodedFormEntity form;
		    form = new UrlEncodedFormEntity(nameValuePairs);
            form.setContentEncoding(HTTP.UTF_8);
		    
			HttpPost postRequest = new HttpPost(url2);
			postRequest.setEntity(form);
			HttpResponse response = httpClient.execute(postRequest);
		}
		catch(IOException e){
			System.out.println("File not found");
		}
	}
}
