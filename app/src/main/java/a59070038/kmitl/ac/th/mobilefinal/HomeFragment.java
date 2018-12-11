package a59070038.kmitl.ac.th.mobilefinal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Profile");
        setupDetail();

    }
    public void setupDetail(){
        SharedPreferences sp = getActivity().getPreferences(Context.MODE_PRIVATE);
        String name = sp.getString("name", "name_null");
        String quote = sp.getString("quote", "");
        TextView textName = getView().findViewById(R.id.home_name);
        TextView textQuote = getView().findViewById(R.id.home_quote);
        textName.setText("Hello, " +  name);
        textQuote.setText(quote);
        initButton();
    }
    public void initButton(){
        Button profileBtn = getView().findViewById(R.id.home_profile_setup);
        Button friendBtn = getView().findViewById(R.id.home_friend);
        Button signoutBtn = getView().findViewById(R.id.home_signout);
        profileSetup(profileBtn);

        signout(signoutBtn);
    }
    public void profileSetup(Button profileBtn){
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new ProfileFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
    public void signout(Button sigoutBtn){
        sigoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
    }
}
