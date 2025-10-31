package com.example.myapplication;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.google.android.material.button.MaterialButton;
import java.util.Calendar;
import java.util.Date;

public class AddExpenseFragment extends Fragment {

    private EditText etAmount, etDate, etRemark, etCurrency;
    private AutoCompleteTextView etCategory;
    private MaterialButton btnSubmit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_expense, container, false);

        etAmount = root.findViewById(R.id.etAmount);
        etDate = root.findViewById(R.id.etDate);
        etRemark = root.findViewById(R.id.etRemark);
        etCurrency = root.findViewById(R.id.etCurrency);
        etCategory = root.findViewById(R.id.etCategory);
        btnSubmit = root.findViewById(R.id.btnSubmit);

        // Category dropdown
        String[] categories = {"Food", "Transport", "Shopping", "Bills", "Health", "Other"};
        android.widget.ArrayAdapter<String> adapter =
                new android.widget.ArrayAdapter<>(requireContext(),
                        android.R.layout.simple_dropdown_item_1line, categories);
        etCategory.setAdapter(adapter);

        // Date picker
        etDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(requireContext(),
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        String formattedDate = selectedYear + "-" +
                                String.format("%02d", (selectedMonth + 1)) + "-" +
                                String.format("%02d", selectedDay);
                        etDate.setText(formattedDate);
                    }, year, month, day);
            dialog.show();
        });

        // Submit button
        btnSubmit.setOnClickListener(v -> {
            String remark = etRemark.getText().toString().trim();
            String amountStr = etAmount.getText().toString().trim();
            String currency = etCurrency.getText().toString().trim();
            String category = etCategory.getText().toString().trim();
            String dateStr = etDate.getText().toString().trim();

            if (TextUtils.isEmpty(remark) || TextUtils.isEmpty(amountStr)
                    || TextUtils.isEmpty(currency) || TextUtils.isEmpty(category)
                    || TextUtils.isEmpty(dateStr)) {
                Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            double amount;
            try {
                amount = Double.parseDouble(amountStr);
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "Invalid amount", Toast.LENGTH_SHORT).show();
                return;
            }

            Expense newExpense = new Expense(remark, amount, currency, category, new Date());
            ExpenseData.addExpense(newExpense);
            Toast.makeText(getContext(), "Expense added!", Toast.LENGTH_SHORT).show();

            // Clear fields
            etAmount.setText("");
            etDate.setText("");
            etRemark.setText("");
            etCurrency.setText("KHR");
            etCategory.setText("");
        });

        return root;
    }
}
