package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

// Note: Ensure ExpenseData and Expense classes are available in your project.
public class HomeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflating the layout
        View root = inflater.inflate(R.layout.activity_fragment_home, container, false);

        // FIX: Changed R.id.tvLastExpense to R.id.tvCurrentBalance to match the latest XML
        TextView tvCurrentBalance = root.findViewById(R.id.tvCurrentBalance);

        // Check if the TextView was successfully found before trying to set text
        if (tvCurrentBalance != null) {
            // Placeholder logic to set the balance text
            if (!ExpenseData.getDummyExpenses().isEmpty()) {
                // NOTE: This logic assumes get(0) is the correct item to display the balance.
                Expense last = ExpenseData.getDummyExpenses().get(0);
                tvCurrentBalance.setText(last.getAmount() + " " + last.getCurrency());
            } else {
                // Fallback text
                tvCurrentBalance.setText("0 KHR");
            }
        }

        return root;
    }
}
