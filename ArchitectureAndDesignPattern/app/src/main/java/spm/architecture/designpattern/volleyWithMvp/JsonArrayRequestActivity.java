package spm.architecture.designpattern.volleyWithMvp;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import spm.architecture.designpattern.R;
import spm.architecture.designpattern.volleyWithMvp.models.Posts;

public class JsonArrayRequestActivity extends AppCompatActivity implements View.OnClickListener {

	public static final String REQUEST_TAG = "JSON_ARRAY_REQUEST_TAG";
	public static final String JSON_URL = "https://tutorialwing.com/api/tutorialwing_posts.json";

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.volley_json_array_request_activity);

		View view = findViewById(R.id.get_request);
		if (view != null)
			view.setOnClickListener(this);
	}

	private void sendRequest() {
		JsonArrayRequest jsonArrayReq = new JsonArrayRequest(JSON_URL,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						showResponse(new Posts(response));
					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Toast.makeText(JsonArrayRequestActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
					}
				});

		// Increase Timeout to 15 secs.
		jsonArrayReq.setRetryPolicy(new DefaultRetryPolicy(15000,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

		// Adding JsonObject request to request queue
		AppController.getInstance().addToRequestQueue(jsonArrayReq, REQUEST_TAG);
	}

	private void showResponse(Posts posts) {
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.request_response);
		if (recyclerView != null) {
			PostAdapter postAdapter = new PostAdapter(posts, R.layout.volley_single_post);
			recyclerView.setLayoutManager(new LinearLayoutManager(this));
			recyclerView.setAdapter(postAdapter);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.get_request:
				sendRequest();
				break;
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		AppController.getInstance().getRequestQueue().cancelAll(REQUEST_TAG);
	}
}
