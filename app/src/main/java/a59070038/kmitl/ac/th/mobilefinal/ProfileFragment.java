package a59070038.kmitl.ac.th.mobilefinal;

import android.content.ContentValues;
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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ProfileFragment extends Fragment {
    private String name;
    private String id;
    private int age;
    int _id;
    TextView userId;
    TextView userName;
    TextView userAge;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_setup, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Setup Profile");
        userId = getActivity().findViewById(R.id.profile_user_userId);
        userName = getActivity().findViewById(R.id.profile_user_name);
        userAge = getActivity().findViewById(R.id.profile_user_age);
        setupData();
        saveData();
    }
    public void setupData(){
        SharedPreferences sp = getActivity().getPreferences(Context.MODE_PRIVATE);
        String nameGlobal = sp.getString("name", "name_null");
        if(!nameGlobal.equals("name_null")){
            SQLiteDatabase db = getActivity().openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null);
            Cursor myCur = db.rawQuery("select * from user where name = '" + nameGlobal + "'", null);
            while (myCur.moveToNext()){
                _id = myCur.getInt(0);
                id = myCur.getString(1);
                name = myCur.getString(2);
                age = myCur.getInt(3);
                userId.setText(id);
                userName.setText(name);
                userAge.setText(String.valueOf(age));
            }
            myCur.close();
            db.close();
        }
    }
    public void saveData(){
        Button saveBtn = getView().findViewById(R.id.profile_button_save);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText userId = getView().findViewById(R.id.profile_user_userId);
                EditText userName = getView().findViewById(R.id.profile_user_name);
                EditText userAge = getView().findViewById(R.id.profile_user_age);
                EditText userPassword = getView().findViewById(R.id.profile_user_password);
                EditText userQuote = getView().findViewById(R.id.profile_user_qoute);
                int countSpace = 0;
                String userIdStr = userId.getText().toString();
                String userNameStr = userName.getText().toString();
                String userAgeStr = userAge.getText().toString();
                String userPasswordStr = userPassword.getText().toString();
                String userQouteStr = userQuote.getText().toString();
                Log.d("PROFILE", "CHECKINPUT");
                Log.d("PROFILE", userIdStr);
                Log.d("PROFILE", userNameStr);
                Log.d("PROFILE", userAgeStr);
                Log.d("PROFILE", userQouteStr);
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
                        ContentValues row1 = new ContentValues();
                        row1.put("user_id", userIdStr);
                        row1.put("name", userNameStr);
                        row1.put("age", age);
                        row1.put("password", userPasswordStr);
                        updateDataToDB(row1, _id, userQouteStr);
                    }
                }
            }
        });
    }
    public void updateDataToDB(ContentValues row, int _id, String userQoute){
        SQLiteDatabase db = getActivity().openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null);
        db.update("user", row, "_id = " + _id, null);
        db.close();
        String path = ".";
        File file = new File(path);
        FileWriter writer;
        try {
            writer = new FileWriter(file, false);
            writer.write(userQoute);
            writer.close();

            System.out.println("Write success!");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        SharedPreferences sp = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("quote", userQoute);
        editor.commit();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_view, new HomeFragment())
                .commit();

    }

}
