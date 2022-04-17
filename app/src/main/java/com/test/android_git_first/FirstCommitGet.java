package com.test.android_git_first;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FirstCommitGet {
    String owner;
    String repo;

    public FirstCommitGet(String owner, String repo) {
        this.owner = owner;
        this.repo = repo;
    }

    public String run(Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://api.github.com/repos/"+this.owner+"/"+this.repo+"/commits";

        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Log.d("Response", response.getJSONObject(response.length()-1).toString());
                            String firstCommitUrl = response.getJSONObject(response.length()-1).get("html_url").toString();
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(firstCommitUrl));
                            browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(browserIntent);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                    }
                }
        );
        queue.add(getRequest);


        return url;
    }
}
