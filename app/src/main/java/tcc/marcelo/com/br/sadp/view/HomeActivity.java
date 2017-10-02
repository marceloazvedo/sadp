package tcc.marcelo.com.br.sadp.view;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.view.dialog.FecharAppDialog;
import tcc.marcelo.com.br.sadp.view.dialog.LogoutDialog;
import tcc.marcelo.com.br.sadp.util.FragmentManagerUtil;

/**
 * Created by Marcelo S. Azevedo on 26/09/2017.
 */
public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar = null;
    private DrawerLayout drawer = null;
    private ActionBarDrawerToggle toggle = null;
    private boolean isDrawerLocked = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManagerUtil.popBackStack(HomeActivity.this);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManagerUtil.trocarFragment(this, new HomeFragment());
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Log.i("INFO", "Drawer Locked: " + isDrawerLocked);
        if(isDrawerLocked){
            FragmentManagerUtil.popBackStack(this);
        } else {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                DialogFragment confirmarDialog = new FecharAppDialog();
                confirmarDialog.show(getFragmentManager(), "missiles");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Log.i("INFO", "ID selecionado: " + id);

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //}

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            FragmentManagerUtil.trocarFragment(this, new HomeFragment());
        } else if (id == R.id.nav_pacientes) {
            FragmentManagerUtil.trocarFragment(this, new ListaPacientesFragment());
        } else if (id == R.id.nav_perfil) {
            FragmentManagerUtil.trocarFragment(this, new PerfilFragment());
        } else if (id == R.id.nav_sair) {
            DialogFragment confirmarDialog = new LogoutDialog();
            confirmarDialog.show(getFragmentManager(), "missiles");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setDrawerState(boolean enable){
        isDrawerLocked = !enable;
        if(enable){
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            toggle.onDrawerStateChanged(DrawerLayout.LOCK_MODE_UNLOCKED);
            toggle.setDrawerIndicatorEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        } else {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            toggle.onDrawerStateChanged(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            toggle.setDrawerIndicatorEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toggle.syncState();

    }

}
