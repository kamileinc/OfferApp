package com.example.offerapp.ui.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.paging.LoadState;
import androidx.paging.LoadStateAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.offerapp.R;
import com.example.offerapp.databinding.LoadStateItemBinding;

import org.jetbrains.annotations.NotNull;

public class OffersLoadStateAdapter extends LoadStateAdapter<OffersLoadStateAdapter.LoadStateViewHolder> {

    @NotNull
    @Override
    public LoadStateViewHolder onCreateViewHolder(@NotNull ViewGroup parent,
                                                  @NotNull LoadState loadState) {
        return new LoadStateViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NotNull LoadStateViewHolder holder,
                                 @NotNull LoadState loadState) {
        holder.bind(loadState);
    }
    public static class LoadStateViewHolder extends RecyclerView.ViewHolder {
        private final ProgressBar mProgressBar;


        LoadStateViewHolder(@NonNull ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.load_state_item, parent, false));
            LoadStateItemBinding binding = LoadStateItemBinding.bind(itemView);
            mProgressBar = binding.progressBar;
        }

        public void bind(LoadState loadState) {
            mProgressBar.setVisibility(loadState instanceof LoadState.Loading
                    ? View.VISIBLE : View.GONE);
        }
    }
}
