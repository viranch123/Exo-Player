package com.strong.exo.GeoCodingInMap;

import android.content.Context;
import android.location.Address;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLngBounds;
import com.strong.exo.R;

import java.util.List;

public class Adapter_forShowAdress extends RecyclerView.Adapter<Adapter_forShowAdress.Myviewholder> {

    Context context;
    List<Address>list;

    public Adapter_forShowAdress(Context context, List<Address> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View view= LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
        return new Myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position) {
    holder.address.setText(""+list.get(position).getAddressLine(0));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder {
        TextView address;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            address=itemView.findViewById(R.id.title);
        }
    }
}
