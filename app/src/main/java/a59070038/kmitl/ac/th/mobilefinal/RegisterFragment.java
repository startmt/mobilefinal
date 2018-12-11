package a59070038.kmitl.ac.th.mobilefinal;

import android.content.ContentValues;
import android.content.Context;
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
import android.widget.Toast;

public class RegisterFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.register,
                container,
                false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Register");
        register();
    }
    public void register(){
        Button registerBtn = getView().findViewById(R.id.button_register);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateInput();
            }
        });
    }
    public void validateInput(){
        EditText userId = getView().findViewById(R.id.regis_user_userId);
        EditText userName = getView().findViewById(R.id.regis_user_name);
        EditText userAge = getView().findViewById(R.id.regis_user_age);
        EditText userPassword = getView().findViewById(R.id.regis_user_password);
        int countSpace = 0;
        String userIdStr = userId.getText().toString();
        String userNameStr = userName.getText().toString();
        String userAgeStr = userAge.getText().toString();
        String userPasswordStr = userPassword.getText().toString();
        Log.d("REGISTER", "CHECKINPUT");
        Log.d("REGISTER", userIdStr);
        Log.d("REGISTER", userNameStr);
        Log.d("REGISTER", userAgeStr);
        Log.d("REGISTER", userPasswordStr);
        for (char x : userNameStr.toCharArray()){
            if(x == ' '){
                countSpace += 1;
            }
        }
        if(userIdStr.equals("") ||
                userNameStr.equals("") ||
                userAgeStr.equals("") ||
                userPasswordStr.equals("")){
            Toast.makeText(getActivity(), "กรุณากรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show();
        }
        else {
            int age = Integer.parseInt(userAgeStr);
            if(userIdStr.length() < 6 ||
                    userIdStr.length() > 12 ||
                    countSpace != 1 ||
                    age < 10 ||
                    age > 80 ||
                    userPasswordStr.length() < 6){
                Toast.makeText(getActivity(), "กรุณากรอกข้อมูลให้ถูกต้อง", Toast.LENGTH_SHORT).show();
            }
            else {
                setDataToDB(userIdStr, userNameStr, age, userPasswordStr);
            }
        }
    }
    public void setDataToDB(String userId, String userName, int age, String userPassword){
        ContentValues row1 = new ContentValues();
        row1.put("user_id", userId);
        row1.put("name", userName);
        row1.put("age", age);
        row1.put("password", userPassword);
        SQLiteDatabase db = getActivity().openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null);
        db.insert("user", null, row1);
        db.close();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_view, new LoginFragment()).commit();
    }
    }
