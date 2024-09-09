package com.org.assignmentpractice;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.org.assignmentpractice.databinding.FragmentUserInfoBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserInfoFragment extends Fragment {
        //FragmentUserInfoBinding binding;
        TextView name ,email, phone ,website , address , cmp_details ;


    AlertDialog.Builder alert;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View userInfo = inflater.inflate(R.layout.fragment_user_info, container, false);
        //txtv = userInfo.findViewById(R.id.txt_view1);
        //Bundle fBundle1 = new Bundle();
       // Bundle fBundle1 = getArguments();
        name = userInfo.findViewById(R.id.info_name_user);
        email = userInfo.findViewById(R.id.info_email_user);
        phone = userInfo.findViewById(R.id.info_phone_user);
        website = userInfo.findViewById(R.id.info_website_cmpy);
        address = userInfo.findViewById(R.id.info_address_cmpy);
        cmp_details = userInfo.findViewById(R.id.info_company_details);

        int id;
      if(getArguments() != null) {
           id = this.getArguments().getInt("uid");
          featchUserDetails(id);
          Toast.makeText(getActivity(), "Fragment ID: " + id, Toast.LENGTH_SHORT).show();
      }
       // txtv.setText(id);


        return userInfo;
    }
    public void featchUserDetails(int infoID){
        String url ="https://jsonplaceholder.typicode.com/users/"+infoID;
        ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Searching Details....");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();
        RequestQueue queue = Volley.newRequestQueue(getActivity());
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
                    cmp_details.setText("Name: "+nameCom
                            +"\nCatchPhars: "+cpCom
                            +"\nbs: "+bsCom);
                    dialog.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                    dialog.dismiss();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();

            }
        });
        queue.add(rq);

    }

}