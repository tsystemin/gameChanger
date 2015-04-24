package in.co.tsystem.gamechanger;

/**
 * Created by diganta.paladhi on 20/04/15.
 */
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class ServerComm {
    public static JSONObject json = null;
    String uri;

    public class RestService {

        public JSONObject doGet(String url) {
            //JSONObject json = null;

            HttpClient httpclient = new DefaultHttpClient();
            // Prepare a request object
            HttpGet httpget = new HttpGet(url);
            // Accept JSON
            httpget.addHeader("accept", "application/json");
            // Execute the request
            HttpResponse response;

            try {
                response = httpclient.execute(httpget);
                // Get the response entity
                // Log.e("myApp", "Issue is here...!");
                HttpEntity entity = response.getEntity();
                // If response entity is not null
                if (entity != null) {
                    // get entity contents and convert it to string
                    InputStream instream = entity.getContent();
                    String result= convertStreamToString(instream);

                    // construct a JSON object with result
                    json=new JSONObject(result);
                    // Closing the input stream will trigger connection release
                    instream.close();
                    //Log.d("JSON2", result);
                }
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // Return the json
            return json;
        }

        private String convertStreamToString(InputStream is) {

            BufferedReader br = null;
            StringBuilder sb = new StringBuilder();

            String line;
            try {

                br = new BufferedReader(new InputStreamReader(is));
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return sb.toString();
        }
    }


    private class myAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            RestService re = new RestService();
            re.doGet(uri);

            return null;
        }
    }

    public JSONObject restRequest (String url){
        uri = url;
        myAsyncTask tsk = new myAsyncTask();
        tsk.execute();
        try {Thread.sleep(1000);} catch (Exception e) {}
        //Log.d("JSON2", json.optString("success"));
        return json;
    }
}
