package a59070038.kmitl.ac.th.mobilefinal;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import a59070038.kmitl.ac.th.mobilefinal.dto.Post;
import a59070038.kmitl.ac.th.mobilefinal.dto.PostAdapter;

public class PostFragment extends Fragment {
    private List<Post> post = new ArrayList<Post>();
    private String url = "https://jsonplaceholder.typicode.com/users";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.post, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ListView postList = getView().findViewById(R.id.post_list);
        PostAdapter postAdapter = new PostAdapter(
                getActivity(),
                R.layout.post_list,
                post
        );
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        try {
            getJson(url);
        }catch (Exception e){

        }
        postList.setAdapter(postAdapter);
        postAdapter.notifyDataSetChanged();

        postList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Fragment commentFragment = new CommentFragment();
//                Bundle args = new Bundle();
//                args.putString("postId", String.valueOf(post.get(position).getId()));
//                commentFragment.setArguments(args);
//                getActivity()
//                        .getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.main_view, commentFragment).addToBackStack(null).commit();
            }
        });
        initbackButton();
    }
    private  void  initbackButton(){
        Button backBtn = getActivity().findViewById(R.id.button_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }
    private void getJson(String url){
        try {
            Log.d("POST", "PULL JSON");
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();

            Log.d("POST", String.valueOf(request));
            Response response = client.newCall(request).execute();
            JSONArray jsonArray = new JSONArray(response.body().string());
            Log.d("POST", String.valueOf(response));
            for(int i = 0; i<jsonArray.length();i++){
                String userIdStr = jsonArray.getJSONObject(i).getString("name");
                int idInt = jsonArray.getJSONObject(i).getInt("id");
                String emailStr = jsonArray.getJSONObject(i).getString("email");
                String phoneStr = jsonArray.getJSONObject(i).getString("phone");
                String websiteStr = jsonArray.getJSONObject(i).getString("website");
                post.add(new Post(idInt,userIdStr, emailStr,phoneStr, websiteStr));
            }
        } catch (IOException e) {
            Log.d("POST", e.getMessage());
            e.printStackTrace();
        } catch (JSONException e) {
            Log.d("POST", e.getMessage());
            e.printStackTrace();
        }
    }
}
