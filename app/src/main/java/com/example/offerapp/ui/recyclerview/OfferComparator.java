package com.example.offerapp.ui.recyclerview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.offerapp.data.entity.OfferDto;

import java.util.Objects;

public class OfferComparator extends DiffUtil.ItemCallback<OfferDto> {
    @Override
    public boolean areItemsTheSame(@NonNull OfferDto oldItem, @NonNull OfferDto newItem) {
        return Objects.equals(oldItem.getOfferId(), newItem.getOfferId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull OfferDto oldItem, @NonNull OfferDto newItem) {
        return Objects.equals(oldItem.getOfferId(), newItem.getOfferId());
    }
}
