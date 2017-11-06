package tcc.marcelo.com.br.sadp.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.dto.LoginDTO;
import tcc.marcelo.com.br.sadp.dto.UsuarioDTO;
import tcc.marcelo.com.br.sadp.service.IAuthenticationService;
import tcc.marcelo.com.br.sadp.util.SharedPreferencesUtil;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };

    private View focusView;
    private ProgressDialog mProgress;
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    // UI references.
    private EditText mLoginView;
    private EditText mSenhaView;
    private View mProgressView;
    private View mLoginFormView;
    private SharedPreferencesUtil sharedPreferencesUtil;
    private IAuthenticationService authenticationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sadp-service.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        authenticationService = retrofit.create(IAuthenticationService.class);

        setContentView(R.layout.login_activity);
        // Set up the login form.
        sharedPreferencesUtil = new SharedPreferencesUtil(this);
        String token = sharedPreferencesUtil.getTokenApp();
        if (token != null) {
            startHomeActivity();
        }
        mLoginView = (EditText) findViewById(R.id.login);
        populateAutoComplete();

        mSenhaView = (EditText) findViewById(R.id.senha);
        mSenhaView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        getSupportActionBar().hide();
    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mLoginView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        // Reset errors.
        mLoginView.setError(null);
        mSenhaView.setError(null);

        // Store values at the time of the login attempt.
        String login = mLoginView.getText().toString();
        String senha = mSenhaView.getText().toString();

        boolean continuar = true;
        focusView = null;

        if (TextUtils.isEmpty(senha)) {
            mLoginView.setError(getString(R.string.error_field_required));
            focusView = mSenhaView;
            continuar = false;
        }
        if (TextUtils.isEmpty(login)) {
            mLoginView.setError(getString(R.string.error_field_required));
            focusView = mLoginView;
            continuar = false;
        }

        if(continuar){
            mProgress = ProgressDialog.show(this, "", "Aguarde");
            Call<UsuarioDTO> call = authenticationService.login(new LoginDTO(login, senha));
            call.enqueue(new Callback<UsuarioDTO>() {
                @Override
                public void onResponse(Call<UsuarioDTO> call, Response<UsuarioDTO> response) {
                    UsuarioDTO usuario = response.body();
                    if(usuario.getCodigo().equals("000")){
                        Toast.makeText(LoginActivity.this, "Seja bem vindo " + usuario.getNome(), Toast.LENGTH_SHORT).show();
                        sharedPreferencesUtil.addString(SharedPreferencesUtil.TOKEN, usuario.getToken());
                        sharedPreferencesUtil.addString(SharedPreferencesUtil.NOME_USUARIO, usuario.getNome());
                        sharedPreferencesUtil.addString(SharedPreferencesUtil.TIPO_USUARIO, usuario.getTipoUsuario());
                        startHomeActivity();
                    } else {
                        // Check for a valid password, if the user entered one.
                        if (usuario.getCodigo().equals("003")) {
                            mSenhaView.setError(getString(R.string.error_invalid_password));
                            focusView = mSenhaView;
                        }

                        // Check for a valid email address.
                        if (usuario.getCodigo().equals("002")) {
                            mLoginView.setError(getString(R.string.erro_login_invalido));
                            focusView = mLoginView;
                        }
                        mProgress.dismiss();
                        focusView.requestFocus();
                    }
                }
                @Override
                public void onFailure(Call<UsuarioDTO> call, Throwable t) {
                    mProgress.dismiss();
                }
            });
        } else {
            focusView.requestFocus();
        }

    }

    private boolean isLoginValid(String login) {
        //TODO: Replace this with your own logic
        return login.length() > 7;
    }

    private boolean isSenhaValid(String senha) {
        //TODO: Replace this with your own logic
        return senha.length() > 4;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    private void startHomeActivity() {
        Intent startMain = new Intent(this, HomeActivity.class);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
        finish();
    }
}

