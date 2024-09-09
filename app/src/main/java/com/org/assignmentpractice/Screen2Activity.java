package com.org.assignmentpractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Screen2Activity extends AppCompatActivity {

    LinearLayout linearLayout;
    FrameLayout frameLayout;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);



        linearLayout = findViewById(R.id.sc2_linearLayout);
        frameLayout = findViewById(R.id.frameLayout);
        bottomNavigationView =findViewById(R.id.bottomNavigation_sc2);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id" ,0);
       // Toast.makeText(this, "Screen 2 : "+id, Toast.LENGTH_SHORT).show();
        Bundle bdl = new Bundle();
        bdl.putInt("uid" , id);
        Fragment infoFrag = new UserInfoFragment();
        Fragment postFrag = new PostsFragment();
        Fragment cmntFrg= new UserCommentsFragment();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               // Toast.makeText(Screen2Activity.this, "SCREEN 2 : "+id, Toast.LENGTH_SHORT).show();

                switch (item.getItemId()){
                    case R.id.user_info:
                        infoFrag.setArguments(bdl);
                        Toast.makeText(Screen2Activity.this, "FRG : "+bdl, Toast.LENGTH_SHORT).show();
                        loadFragment( infoFrag , false);
                        break;
                    case R.id.user_post:
                        Toast.makeText(Screen2Activity.this, "Post Fragment : "+bdl, Toast.LENGTH_SHORT).show();
                        postFrag.setArguments(bdl);
                        loadFragment(postFrag, false);
                        break;
                    case R.id.user_Comments:
                        Toast.makeText(Screen2Activity.this, "Comments Fragment : "+bdl, Toast.LENGTH_SHORT).show();
                        cmntFrg.setArguments(bdl);
                        loadFragment(cmntFrg, false);
                        break;
                }

                return true;
            }

        });

       // loadFragment(new UserInfoFragment() , true);
    }

    public void loadFragment(Fragment fragment , boolean isIitialised){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(isIitialised){
            fragmentTransaction.add(R.id.frameLayout , fragment);
        }else {
            fragmentTransaction.replace(R.id.frameLayout, fragment);
        }
        fragmentTransaction.commit();
    }
}