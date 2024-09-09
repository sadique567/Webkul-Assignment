package com.org.assignmentpractice;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ArrayList<UserDetailsModel> items;
    RecyclerView recyclerView;
    UserDetailsAdaptor adaptor;
    EditText searchPanelTxt ;
    Button searchBtn;

    AlertDialog.Builder alert;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setElevation(0.0f);
       recyclerView = findViewById(R.id.userdetails_recyclerview);
        searchPanelTxt = findViewById(R.id.search_edittxt);
        searchBtn = findViewById(R.id.search_btn);
        alert = new AlertDialog.Builder(this);

        items =new ArrayList<UserDetailsModel>();
        getDetails();
        adaptor = new UserDetailsAdaptor(getApplicationContext() , items);
        //adaptor.setClickListener((ItemClickListener) this);


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , SearchedDetailsActivity.class);

                int sid = Integer.parseInt(searchPanelTxt.getText().toString());
                intent.putExtra("idUser" , sid);
                startActivity(intent);
            }
        });
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Screen ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this , Screen2Activity.class);
                startActivity(intent);
            }
        });


    }



    // use this for featching Details.......
public void getDetails(){
    String url ="https://jsonplaceholder.typicode.com/users";
    ProgressDialog dialog =new ProgressDialog(MainActivity.this);
    dialog.setMessage("Featching Your Details......");
    dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    dialog.show();
    RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
    StringRequest request =new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                //Toast.makeText(MainActivity.this, "Try Block Called", Toast.LENGTH_SHORT).show();
                JSONArray arr = new JSONArray(response);

                for(int i=0; i<arr.length(); i++) {
                    JSONObject obj = arr.getJSONObject(i);
                    JSONObject addobj = obj.getJSONObject("address");
                    JSONObject comobj = obj.getJSONObject("company");
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
                    items.add(
                            new UserDetailsModel(
                                    obj.getString("name"),
                                    obj.getString("email"),
                                    obj.getString("phone"),
                                    obj.getString("website"),
                                    addressModel = new AddressModel(st , su , ci , zip  , geoAddessModel = new GeoAddessModel(geoLat , geoLong)),
                                    companyModel = new CompanyModel(nameCom , cpCom , bsCom),
                                    obj.getInt("id")
                                                ));
                }
                //recyclerView =(RecyclerView) findViewById(R.id.userdetails_recyclerview);
                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this , 1));
                recyclerView.setAdapter(adaptor);

                dialog.dismiss();

            }catch (Exception e){
                e.printStackTrace();
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "Something went Wrong....", Toast.LENGTH_SHORT).show();
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        dialog.dismiss();
            Toast.makeText(MainActivity.this, "Fail to get Responce ...."+error, Toast.LENGTH_SHORT).show();

        }
    });
    queue.add(request);
}
}