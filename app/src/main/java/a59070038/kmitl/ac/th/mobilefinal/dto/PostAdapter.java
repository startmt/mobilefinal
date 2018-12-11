package a59070038.kmitl.ac.th.mobilefinal.dto;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import a59070038.kmitl.ac.th.mobilefinal.R;

public class PostAdapter extends ArrayAdapter {
    Context context;
    ArrayList<Post> posts = new ArrayList<Post>();
    public PostAdapter(
            @NonNull Context context,
            int resource,
            @NonNull List<Post> objects
    ){
        super(context, resource, objects);
        this.context = context;
        this.posts = (ArrayList<Post>) objects;
    }

    @NonNull
    public View getView(int position, @NonNull View convertView,
                        @NonNull ViewGroup parent){
        View postItem = LayoutInflater.from(context).inflate(
                R.layout.post_list,
                parent,
                false);
        TextView id = (TextView) postItem.findViewById(R.id.post_id);
        TextView name = (TextView) postItem.findViewById(R.id.post_name);
        TextView email = (TextView) postItem.findViewById(R.id.post_email);
        TextView phone = (TextView) postItem.findViewById(R.id.post_phone);
        TextView website = (TextView) postItem.findViewById(R.id.post_website);

        Post row = posts.get(position);

        id.setText(String.valueOf(row.getId()));
        name.setText(String.valueOf(row.getUsername()));
        email.setText(row.getEmail());
        phone.setText(row.getEmail());
        website.setText(row.getWebsite());
        return postItem;
    }

}
