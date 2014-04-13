package com.bepl4mobile.holiday.android;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends Activity {

	   @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);

	        if (savedInstanceState == null) {
	            getFragmentManager().beginTransaction()
	                    .add(R.id.container, new PlaceholderFragment())
	                    .commit();
	        }
	    }

	    @Override
	    protected void onStart() {
	        super.onStart();
	        new HttpRequestTask().execute();
	    }

	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {

	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }

	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // Handle action bar item clicks here. The action bar will
	        // automatically handle clicks on the Home/Up button, so long
	        // as you specify a parent activity in AndroidManifest.xml.
	        int id = item.getItemId();
	        if (id == R.id.action_refresh) {
	            new HttpRequestTask().execute();
	            return true;
	        }
	        return super.onOptionsItemSelected(item);
	    }

	    /**
	     * A placeholder fragment containing a simple view.
	     */
	    public static class PlaceholderFragment extends Fragment {

	        public PlaceholderFragment() {
	        }

	        @Override
	        public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                                 Bundle savedInstanceState) {
	            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
	            return rootView;
	        }
	    }


	    private class HttpRequestTask extends AsyncTask<Void, Void, Task> {
	        @Override
	        protected Task doInBackground(Void... params) {
	            try {
	            	
	            	HttpAuthentication authHeader = new HttpBasicAuthentication("tlandowski", "pass");
	            	HttpHeaders requestHeaders = new HttpHeaders();
	            	requestHeaders.setAuthorization(authHeader);
	            	HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

	            	
	                final String url = "http://192.168.0.11:8181/holidayService/mobile/bpel4mobile/rest/tasks";
	                RestTemplate restTemplate = new RestTemplate();
	                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	                ResponseEntity<Task[]> greeting = restTemplate.getForEntity(url, Task[].class, requestEntity);
	                return greeting.getBody()[0];
	            } catch (Exception e) {
	                Log.e("MainActivity", e.getMessage(), e);
	            }

	            return null;
	        }

	        @Override
	        protected void onPostExecute(Task greeting) {
	            TextView greetingIdText = (TextView) findViewById(R.id.id_value);
	            TextView greetingContentText = (TextView) findViewById(R.id.content_value);
	            greetingIdText.setText(greeting.getUuid());
	            greetingContentText.setText(greeting.getName());
	        }

	    }

}
