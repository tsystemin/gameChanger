package in.co.tsystem.gamechanger;

/**
 * Created by diganta.paladhi on 05/04/15.
 */
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Iterator;

public class HttpPost {

    public  String  processPost(String uri, JSONObject req)  throws UnsupportedEncodingException
    {
        String text = "";
        String data = "";
        BufferedReader reader=null;

        try {
            Iterator x = req.keys();
            while (x.hasNext()) {
                String key = (String) x.next();
                data += URLEncoder.encode(key, "UTF-8")
                        + "=" + URLEncoder.encode(req.get(key).toString(), "UTF-8");
            }
            // Send data
            // Defined URL  where to send data
            URL url = new URL(uri);

            // Send POST data request
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            wr.flush();

            // Get the server response

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                // Append server response in string
                sb.append(line + "\n");
            }

            text = sb.toString();
            Log.d("RECEIVED", text);

            return text;
        }
        catch(Exception ex)
        {

        }
        finally
        {
            try
            {
                reader.close();
            }
            catch(Exception ex) {}
        }

        return text;
    }

    /*private class myRegAsyncTask extends AsyncTask<Void, Void, Void> {

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
            try {
                GetText();
            } catch (Exception e) {
                Log.d("ASYNC CATCH", "exception");
            }

            return null;
        }
    }*/

}