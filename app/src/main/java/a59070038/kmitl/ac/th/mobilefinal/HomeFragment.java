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
    }
}
