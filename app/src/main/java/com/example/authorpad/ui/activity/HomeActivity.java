package com.example.authorpad.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.authorpad.R;
import com.example.authorpad.app.UserSessionManager;
import com.example.authorpad.model.Story;
import com.example.authorpad.ui.fragment.FeedbackFragment;
import com.example.authorpad.ui.fragment.HomeFragment;
import com.example.authorpad.ui.fragment.MyStoryFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        navigationView = findViewById(R.id.home_drawer);
        drawerLayout = findViewById(R.id.drawer_layout);
        floatingActionButton = findViewById(R.id.home_fab);

        checkLogin();

        navigateToFragment(1);

        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, StoryActivity.class);
            startActivity(intent);
        });

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            switch (id) {
                case R.id.drawer_home:
                    navigateToFragment(1);
                    break;
                case R.id.drawer_story:
                    navigateToFragment(2);
                    break;
                case R.id.drawer_feedback:
                    navigateToFragment(3);
                    break;
                case R.id.drawer_logout:
                    AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                    builder.setTitle("Logout?");
                    builder.setPositiveButton("Yes", (dialog, which) -> {
                        UserSessionManager.getInstance(HomeActivity.this).setLogout();
                        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    });
                    builder.setNegativeButton("No", ((dialog, which) -> {
                        dialog.cancel();
                    }));

                    AlertDialog dialog = builder.create();
                    dialog.show();
                    break;
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    private void navigateToFragment(int i) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (i) {
            case 1:
                // ini ke home
                fragmentTransaction.replace(R.id.home_fr, new HomeFragment());
                fragmentTransaction.commit();
                break;
            case 2:
                // ini ke my story
                fragmentTransaction.replace(R.id.home_fr, new MyStoryFragment());
                fragmentTransaction.commit();
                break;
            case 3:
                // ini ke feedback
                fragmentTransaction.replace(R.id.home_fr, new FeedbackFragment());
                fragmentTransaction.commit();
                break;
            default:
                return;
        }
    }

    private void checkLogin() {
        if(! UserSessionManager.getInstance(this).isLoggedin()) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}