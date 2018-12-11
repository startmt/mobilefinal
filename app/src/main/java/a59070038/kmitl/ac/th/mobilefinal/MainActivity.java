package a59070038.kmitl.ac.th.mobilefinal;

import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //SQLLITE Code
        SQLiteDatabase myDB = openOrCreateDatabase("my.db", MODE_PRIVATE,null);
        myDB.execSQL("CREATE TABLE IF NOT EXISTS user(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_id VARCHAR(200)," +
                "name VARCHAR(200), " +
                "age INTEGER, " +
                "password VARCHAR(200))");

        //Init main
        if(savedInstanceState == null){
            Fragment fragment = new LoginFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_view, fragment)
                    .commit();
        }
    }
}
