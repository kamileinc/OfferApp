package com.example.offerapp.ui.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.offerapp.R;
import com.example.offerapp.data.entity.OfferDto;
import com.example.offerapp.databinding.RecyclerViewItemBinding;

public class RecyclerViewAdapter extends PagingDataAdapter<OfferDto, RecyclerViewAdapter.MyViewHolder> {

    public RecyclerViewAdapter(OfferComparator offerComparator) {
        super(offerComparator);
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RecyclerViewItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        OfferDto offer = getItem(position);

        if (offer != null) {
            holder.offerItemBinding.title.setText(String.valueOf(offer.getTitle()));

            Glide.with(holder.offerItemBinding.thumbnail)
                    .load(offer.getThumbnail().getHiRes())
                    .apply(RequestOptions.centerCropTransform())
                    .placeholder(R.drawable.ic_baseline_photo_24)
                    .dontAnimate()
                    .into(holder.offerItemBinding.thumbnail);
        }
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        RecyclerViewItemBinding offerItemBinding;

        public MyViewHolder(@NonNull RecyclerViewItemBinding offerItemBinding) {
            super(offerItemBinding.getRoot());
            this.offerItemBinding = offerItemBinding;
        }
    }
}
