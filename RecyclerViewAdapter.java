package com.example.drn20;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    private Context mContext;
    private List<Cargo> cargoList;


    public RecyclerViewAdapter(Context mContext, List<Cargo> cargoList){
        this.mContext = mContext;
        this.cargoList = cargoList;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.track_item , parent , false);
        MyViewHolder viewHolder = new MyViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Cargo cargo = cargoList.get(position);
        holder.tv_number.setText(cargoList.get(position).getCargoNo());
        holder.tv_company.setText(cargoList.get(position).getCargoCo());
        holder.tv_des.setText(cargoList.get(position).getCargoDes());
        holder.image1.setImageAlpha(cargoList.get(position).getCargoPic());
        holder.cargoMemo.setText(cargoList.get(position).getCargoMemo());
        holder.trackOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // display menu
                PopupMenu popupMenu = new PopupMenu(mContext , holder.trackOption);
                popupMenu.inflate(R.menu.track_options);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.track_delete:
                                cargoList.remove(position);
                                notifyDataSetChanged();
                            case R.id.track_archive:
                                cargo.setArchived(true);
                            case R.id.memo_edit:
                                Toast.makeText(mContext, "Düzenleme.", Toast.LENGTH_LONG).show();
                            default:
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return cargoList.size();
    }

    public List<Cargo> getList() {
        return cargoList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_number;
        private TextView tv_company;
        private TextView tv_des;
        private TextView trackOption;
        private TextView cargoMemo;
        private ImageView image1;

        private MyViewHolder(View itemView){
            super(itemView);

            tv_number = itemView.findViewById(R.id.cargo_number);
            tv_company = itemView.findViewById(R.id.cargo_company);
            tv_des = itemView.findViewById(R.id.durum_text);
            image1 = itemView.findViewById(R.id.cargo_pic);
            cargoMemo = itemView.findViewById(R.id.cargoMemo);
            trackOption = itemView.findViewById(R.id.trackOption);
        }
    }


}
