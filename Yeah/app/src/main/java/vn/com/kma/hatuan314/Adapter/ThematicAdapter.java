package vn.com.kma.hatuan314.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import vn.com.kma.hatuan314.model.Thematic;
import vn.com.kma.hatuan314.yeah.ExaminationActivity;
import vn.com.kma.hatuan314.yeah.R;

public class ThematicAdapter extends RecyclerView.Adapter<ThematicAdapter.ItemHolder>{
    Context context;
    ArrayList<Thematic> thematicList;

    public ThematicAdapter(Context context, ArrayList<Thematic> thematicList) {
        this.context = context;
        this.thematicList = thematicList;
    }

    //Khởi tạo lại View đã thiết kế ở Layout bên ngoài
    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.thematic_item, null);
        ItemHolder itemHolder = new ItemHolder(view );
        return itemHolder;
    }

    //Hỗ trợ việc set, get thuộc tính gán lên cho Layout
    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        Thematic thematic = thematicList.get(position);
        holder.tvThematicName.setText(thematic.getThematic_name());

    }

    @Override
    public int getItemCount() {
        return thematicList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public TextView tvThematicName;

        public ItemHolder(View itemView) {
            super(itemView);
            tvThematicName = itemView.findViewById(R.id.tvThematicName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ExaminationActivity.class);
                    intent.putExtra("chuyende", thematicList.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}
