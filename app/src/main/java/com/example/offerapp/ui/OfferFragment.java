package com.example.offerapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.offerapp.databinding.FragmentOfferBinding;
import com.example.offerapp.ui.recyclerview.OfferComparator;
import com.example.offerapp.ui.recyclerview.OffersLoadStateAdapter;
import com.example.offerapp.ui.recyclerview.RecyclerViewAdapter;
import com.example.offerapp.utilities.NetworkState;
import com.example.offerapp.viewmodels.OfferViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OfferFragment extends Fragment {

    OfferViewModel viewModel;
    private FragmentOfferBinding binding;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentOfferBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        viewModel = new ViewModelProvider(this).get(OfferViewModel.class);

        recyclerViewAdapter = new RecyclerViewAdapter(new OfferComparator());

        initRecyclerView();

        initItemsVisibility();

        getOffers();

        viewModel.getNetworkStateLiveData()
                .observe(getViewLifecycleOwner(), this::changeItemsVisibility);

        return view;
    }

    private void getOffers() {
        // Subscribe to to paging data
        viewModel.offerPagingDataFlowable.subscribe(offerPagingData -> {
            // submit new data to recyclerview adapter
            recyclerViewAdapter.submitData(getLifecycle(), offerPagingData);
        });
    }

    public void initItemsVisibility() {
        binding.error.setVisibility(View.GONE);
        binding.imageView.setVisibility(View.GONE);
        binding.progressDialog.setVisibility(View.VISIBLE);
    }
    public void initRecyclerView() {
        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(
                recyclerViewAdapter.withLoadStateFooter(new OffersLoadStateAdapter()));
    }

    public void changeItemsVisibility(NetworkState networkState) {
        if (networkState.getStatus().equals(NetworkState.Status.FAILED)
                && recyclerViewAdapter.getItemCount() == 0) {
            binding.progressDialog.setVisibility(View.GONE);
            binding.error.setVisibility(View.VISIBLE);
            binding.imageView.setVisibility(View.VISIBLE);
        } else if (networkState.getStatus().equals(NetworkState.Status.LOADING) &&
                recyclerViewAdapter.getItemCount() == 0) {
            binding.progressDialog.setVisibility(View.VISIBLE);
            binding.error.setVisibility(View.GONE);
            binding.imageView.setVisibility(View.GONE);
        } else if (networkState.getStatus().equals(NetworkState.Status.SUCCESS)) {
            binding.progressDialog.setVisibility(View.GONE);
            binding.error.setVisibility(View.GONE);
            binding.imageView.setVisibility(View.GONE);
        } else if (networkState.getStatus().equals(NetworkState.Status.INVALID_SIGNATURE)){
            Toast.makeText(getContext(), networkState.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
