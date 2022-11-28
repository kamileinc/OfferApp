package com.example.offerapp.viewmodels;

import static com.example.offerapp.utilities.Constants.EMPTY_ERROR;
import static com.example.offerapp.utilities.Constants.NOT_INTEGER_ERROR;
import static java.lang.Integer.parseInt;

import androidx.lifecycle.ViewModel;

public class FormViewModel extends ViewModel {


    public String validateText(String textToValidate) {
        if (textToValidate.isEmpty()) return EMPTY_ERROR;

        return null;
    }

    public String validateNumber(String numberToValidate) {
        if (numberToValidate.isEmpty()) return EMPTY_ERROR;

        if (!isInteger(numberToValidate)) return NOT_INTEGER_ERROR;

        return null;
    }

    public boolean isInteger(String numberToValidate) {
        try {
            parseInt(numberToValidate);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
