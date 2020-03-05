package com.example.mid_term;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

private ArrayList<ConsolidatedWeather>arrypro;
private Context context;
private View.OnClickListener itemlistener;
    private View.OnClickListener itemListener;

    public RecycleAdapter(ArrayList<ConsolidatedWeather> arrypro, Context context){
    this.arrypro = arrypro;
    this.context = context;


}
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        Picasso.get().load(arrypro.get(position).getImage).into(holder.pkimg);
        holder.pkname.setText(arrypro.get(position).getName());

    }
    public void setClickListener(View.OnClickListener itemListener){
        this.itemListener=itemListener;
    }

    @Override
    public int getItemCount() {
        return arrypro.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView pkimg;
        TextView pkname;

        public ViewHolder(View itemview)
        {
         super(itemview);

         pkimg = itemview.findViewById(R.id.img_w);
         pkname = itemview.findViewById(R.id.txt_w);
         itemview.setTag(this);
            itemview.setOnClickListener(itemListener);
        }


    }
}
