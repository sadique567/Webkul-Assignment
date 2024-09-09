package com.org.assignmentpractice;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;


public class UserCommentsFragment extends Fragment {

   RecyclerView recyclerView;
   ArrayList<CommentsModel> cmntList;
   CommentsAdaptor commentsAdaptor;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View commentsData =  inflater.inflate(R.layout.fragment_user_comments, container, false);
        recyclerView = commentsData.findViewById(R.id.cmnt_recycler);
        cmntList = new ArrayList<>();

        int uid1;
        if(getArguments() != null){
            uid1 = this.getArguments().getInt("uid");
            Toast.makeText(getActivity(), "Cmnt : "+uid1, Toast.LENGTH_SHORT).show();
            getComments(uid1);
        }
        commentsAdaptor = new CommentsAdaptor(getActivity() , cmntList);
    return commentsData;
    }
    public void getComments(int idC){
        String url = "https://jsonplaceholder.typicode.com/posts/"+idC+"/comments";
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray arr = new JSONArray(response);
                    for (int i=0 ; i< arr.length(); i++){
                        JSONObject obj = arr.getJSONObject(i);
                        cmntList.add(
                                new CommentsModel(
                                        obj.getString("name"),
                                        obj.getString("email"),
                                        obj.getString("body")
                                )
                        );
                        recyclerView.setLayoutManager(new GridLayoutManager(getActivity() ,1));
                        recyclerView.setAdapter(commentsAdaptor);




                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }
}