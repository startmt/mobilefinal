package a59070038.kmitl.ac.th.mobilefinal;

import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public class LoginFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Login");
        SharedPreferences sp = getActivity().getPreferences(Context.MODE_PRIVATE);
        String checkLoginsp = sp.getString("id", "id_null");
        if(!checkLoginsp.equals("id_null")){
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_view, new HomeFragment())
                    .commit();
        }
        else {
            initbutton();
        }
    }
    public void initbutton(){
        Button loginBtn = getView().findViewById(R.id.button_login);
        TextView register = getView().findViewById(R.id.login_text_register);
        login(loginBtn);
        registerbtn(register);
    }
    public void login(Button loginBtn){
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText userId = getView().findViewById(R.id.login_user_id);
                EditText userPassword = getView().findViewById(R.id.login_user_password);
                String userIdStr =userId.getText().toString();
                String userPasswordStr =userPassword.getText().toString();
                if (userIdStr.equals("") ||
                        userPasswordStr.equals("")){
                    Toast.makeText(getActivity(), "Please fill out this form", Toast.LENGTH_SHORT).show();
                }
                else {
                    SQLiteDatabase db = getActivity().openOrCreateDatabase("my.db", MODE_PRIVATE, null);
                    Cursor myCur = db.rawQuery("select * from user where user_id = '" + userIdStr + "'", null);
                    String dbPassword = null;
                    String dbName = null;
                    while (myCur.moveToNext()){
                        Log.d("LOGIN", "GET PASSWORD");
                        dbName = myCur.getString(2);
                        dbPassword = myCur.getString(4);
                    }
                    if(!dbPassword.equals(userPasswordStr) || dbPassword.equals(null)){
                        Toast.makeText(getActivity(), "Invalid user or password", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        SharedPreferences sp = getActivity().getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("name", dbName);
                        editor.putString("id", userIdStr);
                        editor.commit();
                        getActivity()
                                .getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.main_view, new HomeFragment())
                                .commit();
                    }
                    myCur.close();
                    db.close();
                }
            }
        });
    }
    public void registerbtn(TextView register){
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("LOGIN", "Go to register page");
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
