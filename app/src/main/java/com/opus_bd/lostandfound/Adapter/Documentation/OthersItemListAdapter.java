package com.opus_bd.lostandfound.Adapter.Documentation;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.opus_bd.lostandfound.Activity.OtherItem.BagActivity;
import com.opus_bd.lostandfound.Activity.OtherItem.CategoryListActivity;
import com.opus_bd.lostandfound.Activity.OtherItem.DocumentActivity;
import com.opus_bd.lostandfound.Activity.OtherItem.GarmentsActivity;
import com.opus_bd.lostandfound.Activity.OtherItem.GlassActivity;
import com.opus_bd.lostandfound.Activity.OtherItem.JewelryActivity;
import com.opus_bd.lostandfound.Activity.OtherItem.KeysInformationActivity;
import com.opus_bd.lostandfound.Activity.OtherItem.MobilePhoneActivity;
import com.opus_bd.lostandfound.Activity.OtherItem.OtherItemDetailsActivity;
import com.opus_bd.lostandfound.Activity.OtherItem.PetActivity;
import com.opus_bd.lostandfound.Activity.OtherItem.UmbrellaActivity;
import com.opus_bd.lostandfound.Model.Documentaion.DocumentType;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Utils.Utilities;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OthersItemListAdapter extends RecyclerView.Adapter<OthersItemListAdapter.TransactionViewHolder> {
    private final Context context;
    private List<DocumentType> items;

    public OthersItemListAdapter(List<DocumentType> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public TransactionViewHolder onCreateViewHolder(ViewGroup parent,
                                                    int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_others_item_list, parent, false);
        return new TransactionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TransactionViewHolder holder, int position) {
        DocumentType item = items.get(position);
        holder.set(item);
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvItemName)
        TextView tvreceiveNo;
        @BindView(R.id.ivItemLogo)
        ImageView ivItemLogo;
        @BindView(R.id.llRoot)
        LinearLayout llRoot;
        @BindView(R.id.cvItem)
        CardView cvItem;


        public TransactionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void set(final DocumentType item) {
            //UI setting code

            tvreceiveNo.setText(item.getDocumentTypeName());
          try{
              Glide.with(context).load("http://103.134.88.13:1022/"+item.getImagePath()).into(ivItemLogo);
          }
          catch (Exception e){}


            final int id = item.getId();

            Utilities.showLogcatMessage("id" + id);
            llRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(id==1){
                        Intent intent = new Intent(context, CategoryListActivity.class);
                        context.startActivity(intent);
                    }else if(id==2){
                        Intent intent = new Intent(context, OtherItemDetailsActivity.class);
                        context.startActivity(intent);
                    }else if(id==4){
                        Intent intent = new Intent(context, MobilePhoneActivity.class);
                        context.startActivity(intent);
                    }else if(id==5){
                        Intent intent = new Intent(context, KeysInformationActivity.class);
                        context.startActivity(intent);
                    }else if(id==7){
                        Intent intent = new Intent(context, DocumentActivity.class);
                        context.startActivity(intent);
                    }else if(id==8){
                        Intent intent = new Intent(context, BagActivity.class);
                        context.startActivity(intent);
                    }else if(id==10){
                        Intent intent = new Intent(context, JewelryActivity.class);
                        context.startActivity(intent);
                    }else if(id==11){
                        Intent intent = new Intent(context, GlassActivity.class);
                        context.startActivity(intent);
                    }else if(id==12){
                        Intent intent = new Intent(context, GarmentsActivity.class);
                        context.startActivity(intent);
                    }else if(id==15){
                        Intent intent = new Intent(context, PetActivity.class);
                        context.startActivity(intent);
                    }else if(id==16){
                        Intent intent = new Intent(context, UmbrellaActivity.class);
                        context.startActivity(intent);
                    }else {
                        Intent intent = new Intent(context, CategoryListActivity.class);
                        context.startActivity(intent);
                    }

                }
            });


        }


    }


}
