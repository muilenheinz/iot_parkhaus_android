
package com.example.iotparkhaus;

import android.os.AsyncTask;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

class AsyncFetchSVGTask extends AsyncTask<String, String, String> {
    /**
     * Before starting background thread
     * Show Progress Bar Dialog
     * */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
//        showDialog(progress_bar_type);
    }

    /**
     * Downloading file in background thread
     * */
    @Override
    protected String doInBackground(String... f_url) {

        try {
            URL url = new URL(f_url[0]);
            URLConnection conection = url.openConnection();
            conection.connect();
            InputStream input = new BufferedInputStream(url.openStream());

            byte[] contents = new byte[1024];
            int bytesRead = 0;
            String strFileContents = "";
            while((bytesRead = input.read(contents)) != -1) {
                strFileContents += new String(contents, 0, bytesRead);
            }

            return strFileContents;

        } catch (Exception e) {
//            Log.e("Error: ", e.getMessage());
        }

        return null;
    }

    /**
     * Updating progress bar
     * */
    protected void onProgressUpdate(String... progress) {
        // setting progress percentage
//        pDialog.setProgress(Integer.parseInt(progress[0]));
    }

    /**
     * After completing background task
     * Dismiss the progress dialog
     * **/
    @Override
    protected void onPostExecute(String file_url) {
        // dismiss the dialog after the file was downloaded
//        dismissDialog(progress_bar_type);
//        Toast.makeText(getApplicationContext(),fileName+" downloaded.", Toast.LENGTH_LONG).show();
    }

}