package com.strong.exo.MapusingFirebase;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;
import com.strong.exo.R;

import java.util.List;

public class Adapter_map extends RecyclerView.Adapter<Adapter_map.Myviewholder> {

    Context context;
    List<modelclass> list;
    GoogleMap googleMap;


    public Adapter_map(Context context, List<modelclass> list, GoogleMap googleMap) {
        this.context = context;
        this.list = list;
        this.googleMap = googleMap;

    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new Myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position) {
        holder.title.setText("" + list.get(position).getTitle());
        holder.direction.setText("" + list.get(position).getLatitude());
        Picasso.get().load(list.get(position).getImage()).into(holder.imageView);


        holder.getAdapterPosition();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {


            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder {
        TextView title, direction;
        ImageView imageView;

        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.imagemap);
            direction = itemView.findViewById(R.id.direction);
        }

    }


}
