package com.org.assignmentpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchedDetailsActivity extends AppCompatActivity {

    TextView name , email ,phone, website, address , companyDetailsS ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_details);

        name = findViewById(R.id.name_user_search);
        email = findViewById(R.id.email_user_search);
        phone = findViewById(R.id.phone_user_search);
        website = findViewById(R.id.website_cmpy_search);
        address = findViewById(R.id.address_cmpy_search);
        companyDetailsS = findViewById(R.id.company_details_search);
        Intent intent= getIntent();
        int id  = intent.getIntExtra("idUser" , 0);
        featchUserDetails(id);




    }
    public void featchUserDetails(int id){
       // Toast.makeText(this, "Search Id : "+sId, Toast.LENGTH_SHORT).show();
        String url ="https://jsonplaceholder.typicode.com/users"+"/"+id+"";
        Toast.makeText(this, "URL : "+url, Toast.LENGTH_SHORT).show();

        RequestQueue queue = Volley.newRequestQueue(SearchedDetailsActivity.this);
        JsonObjectRequest rq = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                JSONObject addobj = null;
                try {
                    addobj = response.getJSONObject("address");

                JSONObject comobj = response.getJSONObject("company");
                JSONObject geoAddObj = addobj.getJSONObject("geo");

                String st=addobj.getString("street") ,
                        su = addobj.getString("suite") ,
                        ci = addobj.getString("city") ,
                        zip =addobj.getString("zipcode");

                String nameCom=comobj.getString("name") ,
                        cpCom = comobj.getString("catchPhrase") ,
                        bsCom = comobj.getString("bs") ;

                Double geoLat = Double.parseDouble(geoAddObj.getString("lat")),
                        geoLong = Double.valueOf(geoAddObj.getString("lng"));

                AddressModel addressModel ;
                CompanyModel companyModel;
                GeoAddessModel geoAddessModel;
                name.setText(response.getString("name"));
                email.setText(response.getString("email"));
                phone.setText(response.getString("phone"));
                website.setText(response.getString("website"));
                address.setText("Street: "+st
                +"\nSuite: "+su
                +"\nCity: "+ci
                +"\nZipCode: "+zip
                +"\nlat: "+geoLat
                +"\nlan: "+geoLong);
                companyDetailsS.setText("Name: "+nameCom
                +"\nCatchPhars: "+cpCom
                +"\nbs: "+bsCom);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });
        queue.add(rq);
    }
}