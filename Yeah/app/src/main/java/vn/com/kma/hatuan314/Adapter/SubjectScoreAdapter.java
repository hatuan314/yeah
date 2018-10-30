package vn.com.kma.hatuan314.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import vn.com.kma.hatuan314.model.User;
import vn.com.kma.hatuan314.yeah.R;

public class SubjectScoreAdapter extends BaseAdapter {
    Context context;
    ArrayList<User> users;

    public SubjectScoreAdapter(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View v, ViewGroup viewGroup) {
        SubjectScoreAdapter.ViewHolder holder;
        holder = new SubjectScoreAdapter.ViewHolder();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.rating_item, null);
        holder.tvSubjectName = v.findViewById(R.id.tvSubjectName);
        holder.tvScores = v.findViewById(R.id.tvScores);

        User user = users.get(i);
        holder.tvSubjectName.setText(String.valueOf(i+1));
        holder.tvScores.setText(String.valueOf(user.getUser_scores()));
        return v;
    }

    public  class ViewHolder {
        TextView tvSubjectName;
        TextView tvScores;

    }
}
