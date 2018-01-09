package com.approsoft.rehanpharmacy.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.approsoft.rehanpharmacy.models.ListItem;
import com.approsoft.rehanpharmacy.R;
import com.approsoft.rehanpharmacy.adapters.RecyclerAdapter;
import com.approsoft.rehanpharmacy.listeners.RecyclerTouchListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {

    RecyclerView recyclerMain;
    ArrayList<ListItem> itemsList;

    String[] itemNames = {"Lucia Velasquez" ,
            "Brooke Mckenzie" ,
            "Joanna Sims" ,
            "Marlon Arnold" ,
            "Shaniya Larsen" ,
            "Kamryn Allison" ,
            "Angel Contreras" ,
            "Aaliyah Byrd" ,
            "Kaylee Heath" ,
            "Billy Welch" ,
            "Iris Serrano" ,
            "Amir Moran" ,
            "Maurice Stephenson" ,
            "Daniela Roach" ,
            "Mayra Shannon" ,
            "Ryann Fuentes" ,
            "Livia Bullock" ,
            "Brian Cameron" ,
            "Kameron Owen" ,
            "Asher Craig"};
    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        itemsList = new ArrayList<>();
        for(int i=1; i<=20; i++){
            ListItem item = new ListItem();
            item.setItemName(itemNames[i-1]);
            item.setCompanyName("Company "+ i);
            itemsList.add(item);
        }

        recyclerMain = findViewById(R.id.recyclerMain);
        adapter = new RecyclerAdapter(itemsList, MainActivity.this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        recyclerMain.setLayoutManager(gridLayoutManager);
        recyclerMain.setAdapter(adapter);
        recyclerMain.addOnItemTouchListener(new RecyclerTouchListener(MainActivity.this,
                new RecyclerTouchListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int i) {
                        ListItem item = itemsList.get(i);
                        Intent intent = new Intent(MainActivity.this, ItemDetailsActivity.class);
                        intent.putExtra("itemName", item.getItemName());
                        startActivity(intent);
                    }
                }));
        recyclerMain.smoothScrollToPosition(0);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
            searchView.setOnQueryTextListener(this);

        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        ArrayList<ListItem> newList = new ArrayList<>();
        for (ListItem requestedModel : itemsList) {
            String name = requestedModel.getItemName().toLowerCase();
            if (name.contains(newText))
                newList.add(requestedModel);
        }
        adapter.setFilter(newList);
        return true;
    }
}
