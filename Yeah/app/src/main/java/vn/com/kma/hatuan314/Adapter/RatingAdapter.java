package vn.com.kma.hatuan314.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.com.kma.hatuan314.model.Rating;
import vn.com.kma.hatuan314.model.User;
import vn.com.kma.hatuan314.yeah.R;

public class RatingAdapter extends BaseAdapter {
    Context context;
    ArrayList<Rating> ratings;

    public RatingAdapter(Context context, ArrayList<Rating> ratings) {
        this.context = context;
        this.ratings = ratings;
    }

    @Override
    public int getCount() {
        return ratings.size();
    }

    @Override
    public Object getItem(int i) {
        return ratings.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View v, ViewGroup viewGroup) {
        RatingAdapter.ViewHolder holder;
        holder = new ViewHolder();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.rating_item, null);
        holder.tvRating_number = v.findViewById(R.id.tvRating_number);
        holder.tvUser_name = v.findViewById(R.id.tvUser_name);
        holder.tvScores = v.findViewById(R.id.tvScores);
        holder.ivAvatar = v.findViewById(R.id.ivAvatar);

        Rating rating = ratings.get(i);
        holder.tvRating_number.setText(String.valueOf(i+1));
        holder.tvUser_name.setText(rating.getUser_name());
        holder.tvScores.setText(String.valueOf(rating.getMath_point()));
        return v;
    }

    public  class ViewHolder {
        TextView tvRating_number;
        TextView tvUser_name;
        TextView tvScores;
        ImageView ivAvatar;

    }
}
