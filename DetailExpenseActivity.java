package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class DetailExpenseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_detail);

        TextView tvRemark = findViewById(R.id.tvRemarkDetail);
        TextView tvAmount = findViewById(R.id.tvAmountDetail);
        TextView tvCurrency = findViewById(R.id.tvCurrencyDetail);
        TextView tvCategory = findViewById(R.id.tvCategoryDetail);
        TextView tvDate = findViewById(R.id.tvDateDetail);
        String id = getIntent().getStringExtra("expenseId");
        Expense e = ExpenseData.getExpenseById(id);
        if (e != null) {
            tvRemark.setText(e.getRemark());
            tvAmount.setText(String.valueOf(e.getAmount()));
            tvCurrency.setText(e.getCurrency());
            tvCategory.setText(e.getCategory());
            tvDate.setText(e.getFormattedDate());
        } else {
            tvRemark.setText("Not found");
        }
        Button btnBack = findViewById(R.id.btnBackDetail);

        btnBack.setOnClickListener(v -> {
            // Close this activity and go back
            finish();
        });

    }
}
