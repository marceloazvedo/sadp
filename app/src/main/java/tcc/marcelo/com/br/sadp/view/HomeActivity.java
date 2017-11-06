package tcc.marcelo.com.br.sadp.view;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.model.Paciente;
import tcc.marcelo.com.br.sadp.util.SharedPreferencesUtil;
import tcc.marcelo.com.br.sadp.view.dialog.DeletarPacienteDialog;
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
    private Paciente paciente = null;
    private SharedPreferencesUtil sharedPreferencesUtil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferencesUtil = new SharedPreferencesUtil(this);

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);

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
        View headerLayout = navigationView.getHeaderView(0);

        TextView nomeUsuario = (TextView) headerLayout.findViewById(R.id.nome_usuario);;
        nomeUsuario.setText(sharedPreferencesUtil.getString(SharedPreferencesUtil.NOME_USUARIO));
        TextView tipoUsuario = (TextView) headerLayout.findViewById(R.id.tipo_usuario);
        tipoUsuario.setText(sharedPreferencesUtil.getString(SharedPreferencesUtil.TIPO_USUARIO));
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(paciente != null && !isDrawerLocked){
            MenuItem delete = menu.findItem(R.id.menu_delete);
            delete.setVisible(true);
            delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    DialogFragment confirmarDialog = new DeletarPacienteDialog();
                    Bundle bundle = new Bundle();
                    bundle.putString("paciente", paciente.getId());
                    confirmarDialog.setArguments(bundle);
                    confirmarDialog.show(getFragmentManager(), "missiles");
                    return false;
                }
            });
            MenuItem editar = menu.findItem(R.id.menu_editar);
            editar.setVisible(true);
            editar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    FragmentManagerUtil.trocarFragment(HomeActivity.this, new PacienteFragment());
                    return false;
                }
            });
            MenuItem visualizar = menu.findItem(R.id.menu_visualizar);
            visualizar.setVisible(true);
            visualizar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Bundle bundle = new Bundle();
                    bundle.putString("paciente", paciente.getId());
                    VisualizarPacienteFragment visualizarPacienteFragment = new VisualizarPacienteFragment();
                    visualizarPacienteFragment.setArguments(bundle);
                    FragmentManagerUtil.trocarFragment(HomeActivity.this, visualizarPacienteFragment);
                    return false;
                }
            });
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

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

        preparaEditarPaciente(null);
        supportInvalidateOptionsMenu();

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
        supportInvalidateOptionsMenu();
        toggle.syncState();
    }

    public void preparaEditarPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Paciente getPaciente(){
        return paciente;
    }

}
