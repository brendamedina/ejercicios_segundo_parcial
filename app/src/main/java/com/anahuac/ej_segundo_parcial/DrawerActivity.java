package com.anahuac.ej_segundo_parcial;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.anahuac.ej_segundo_parcial.fragments.FavoriteFragment;
import com.anahuac.ej_segundo_parcial.fragments.HomeFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class DrawerActivity extends AppCompatActivity {
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    boolean isOpen = false;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        setTitle("Drawer Layout");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);

        navigationView = findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        tabLayout = findViewById(R.id.tab_layout);

        showFragment(new HomeFragment());

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                message("TabSelected "+ tab.getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                message("TabUnselected "+ tab.getText());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                message("TabReselected "+ tab.getText());
            }
        });

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                isOpen = true;
                getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                isOpen= false;
                getActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_favorite:
                        message("Pulsaste favorito");
                        showFragment(new FavoriteFragment());
                        break;
                    case R.id.menu_delete:
                        message("Pulsaste eliminar");
                        showFragment(new HomeFragment());
                        break;
                    case R.id.menu_copy:
                        message("Pulsaste copiar");
                        break;
                }
                return true;
            }
        });

    }

    public void showFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container_view, fragment)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            if (isOpen){
                drawerLayout.close();
            } else {
                drawerLayout.open();
            }
        }
        return super.onOptionsItemSelected(item);
    }
    private void message(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}


