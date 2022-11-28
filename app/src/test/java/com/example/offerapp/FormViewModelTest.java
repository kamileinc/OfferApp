package com.example.offerapp;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.offerapp.viewmodels.FormViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class FormViewModelTest {

    FormViewModel viewModel;

    @Before
    public void init() {
        viewModel = new FormViewModel();
    }

    @Test
    public void whenCheckIsInteger_WithInteger_ReturnTrue() {
        String numberToValidate = "1234";

        assertTrue(viewModel.isInteger(numberToValidate));
    }

    @Test
    public void whenCheckIsInteger_WithString_ReturnFalse() {
        String numberToValidate = "abcd";

        assertFalse(viewModel.isInteger(numberToValidate));
    }

    @Test
    public void whenCheckIsInteger_WithEmptyString_ReturnFalse() {
        String numberToValidate = "";

        assertFalse(viewModel.isInteger(numberToValidate));
    }
}
