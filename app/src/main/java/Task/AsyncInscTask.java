package Task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class AsyncInscTask extends AsyncTask<String, Void, Void> {
    Context mContext;
    String urlParameters;



    public AsyncInscTask(Context ctx, String urlParameters) {
        mContext = ctx;
        this.urlParameters = urlParameters;
    }

    @Override
    protected Void doInBackground(String... params) {

        String urlString = params[0];
//newuser
        try {
            byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
            //int postDataLength = postData.length;
            URL url = new URL(urlString);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);

            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

            writer.write(urlParameters);
            writer.flush();
//
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                Log.i("REQINF", line);
            }
            writer.close();
            reader.close();

        } catch (Exception e) {
            Log.i("REQERR", e.getMessage());
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
       // Toast.makeText(mContext, "POST HTTP DONE!", Toast.LENGTH_LONG).show();
    }
}