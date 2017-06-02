package com.example.root.myvolleydemo.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.root.myvolleydemo.R;

import java.util.List;

/**
 * Created by root on 6/2/17.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {

    private static final String TAG = "CardAdapter";
    List<String> listAdapterData;
    Context context;

    public CardAdapter(Context context, List<String> data) {
        this.context = context;
        this.listAdapterData = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_bills, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        if (position == 0) {
            holder.viewProgress.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 80f));
            holder.textViewPrice.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 20f));
            holder.viewProgress.setBackground(ContextCompat.getDrawable(context, R.drawable.z_boarder));
        }

        if (position == 1) {
            holder.viewProgress.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 50f));
            holder.textViewPrice.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 50f));
            holder.viewProgress.setBackground(ContextCompat.getDrawable(context, R.drawable.z_boarder));
        }

        if (position == 3) {
            holder.viewProgress.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 30f));
            holder.textViewPrice.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 70f));
            holder.viewProgress.setBackground(ContextCompat.getDrawable(context, R.drawable.z_boarder));
        }
//        holder

    }

    @Override
    public int getItemCount() {
        return listAdapterData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linearLayoutProgress;
        View viewProgress;
        TextView textViewPrice;

        public MyViewHolder(View itemView) {
            super(itemView);
            linearLayoutProgress = (LinearLayout) itemView.findViewById(R.id.linearLayoutProgress);
            viewProgress = itemView.findViewById(R.id.viewProgress);
            textViewPrice = (TextView) itemView.findViewById(R.id.textViewPrice);
//            ButterKnife.bind(this, itemView);
        }
    }
}
