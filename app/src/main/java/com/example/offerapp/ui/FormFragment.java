package com.example.offerapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.offerapp.databinding.FragmentFormBinding;
import com.example.offerapp.viewmodels.FormViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FormFragment extends Fragment {

    private FormViewModel viewModel;
    private FragmentFormBinding binding;
    String applicationIdText;
    String userIdText;
    String securityTokenText;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentFormBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        viewModel = new ViewModelProvider(this).get(FormViewModel.class);

        binding.button.setOnClickListener(view1 -> {
            extractFormData();
            setErrorsIfAny();
            if (noErrors()) {
                navigateToOfferFragment(view);
            }
        });

        return view;
    }

    private void extractFormData() {
        applicationIdText = binding.applicationId.getText().toString();
        userIdText = binding.userId.getText().toString();
        securityTokenText = binding.securityToken.getText().toString();
    }

    private void setErrorsIfAny() {
        binding.applicationId.setError(viewModel.validateNumber(applicationIdText));
        binding.userId.setError(viewModel.validateText(userIdText));
        binding.securityToken.setError(viewModel.validateText(securityTokenText));
    }

    private boolean noErrors() {
        return binding.applicationId.getError() == null
                && binding.userId.getError() == null
                && binding.securityToken.getError() == null;
    }

    private void navigateToOfferFragment(View view) {
        @NonNull NavDirections action = FormFragmentDirections
                .actionFormFragmentToOfferFragment(
                        Integer.parseInt(applicationIdText), userIdText, securityTokenText);
        Navigation.findNavController(view).navigate(action);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
