package com.example.mr_shareone.materialdesign;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.mr_shareone.materialdesign.bean.Fruit;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private FruitAdapter fruitAdapter;
    private Context context;
    private SwipeRefreshLayout swipeRefreshLayout;
    GridLayoutManager layoutManager;
    private static int a = 0;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);fab.setOnClickListener(this);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeColors(R.color.colorWhite);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.menu);
        }

        navigationView.setCheckedItem(R.id.nav_call);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        fruitAdapter = new FruitAdapter(context,initFruitData("水果"));
        fruitAdapter.setOnclick(new FruitAdapter.Onclick() {
            @Override
            public void itemOnclicked(Fruit fruit) {
                Intent intent = new Intent(MainActivity.this,CollapsingToolbarLayoutActivity.class);
                intent.putExtra("fruit",fruit);
                startActivity(intent);
            }
        });
        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(fruitAdapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.backup:
                Toast.makeText(getApplicationContext(), "backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(getApplicationContext(), "delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(getApplicationContext(), "settings", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
            default:
                break;

        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
//                Toast.makeText(this,"Fab isclicke",Toast.LENGTH_SHORT).show();
                Snackbar.make(v,"Data deleted",Snackbar.LENGTH_SHORT)
                        .setAction("undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,"Data restored",Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
            default:
                break;
        }
    }

    public List<Fruit> initFruitData(String s){
        List<Fruit> fruits = new ArrayList<Fruit>();
        for(int i=0;i<10;i++){
            Fruit fruit = new Fruit(s+i,R.drawable.head);
            fruits.add(fruit);
        }
        return fruits;
    }

    public void refreshFruits(){
        fruitAdapter.setFruits(initFruitData(a+""));
        a++;
        if(swipeRefreshLayout.isRefreshing()){

            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
