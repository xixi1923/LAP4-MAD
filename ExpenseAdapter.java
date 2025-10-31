package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {
    private final List<Expense> expenses;
    private final Context context;

    public ExpenseAdapter(Context context, List<Expense> expenses) {
        this.context = context;
        this.expenses = expenses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_expense, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Expense e = expenses.get(position);
        holder.tvAmountCurrency.setText(String.format("%s %s", formatAmount(e.getAmount()), e.getCurrency()));
        holder.tvDate.setText(e.getFormattedDate());
        holder.tvCategory.setText(e.getCategory());
        holder.tvRemark.setText(e.getRemark());

        // Click to open DetailExpenseActivity
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailExpenseActivity.class);
            intent.putExtra("expenseId", e.getId());
            holder.itemView.getContext().startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return expenses.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvAmountCurrency, tvDate, tvCategory, tvRemark;

        ViewHolder(View itemView) {
            super(itemView);
            tvAmountCurrency = itemView.findViewById(R.id.tvAmountCurrency);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvRemark = itemView.findViewById(R.id.tvRemark);
        }
    }

    private String formatAmount(double amount) {
        // If amount is whole number -> no decimals
        if (amount == Math.floor(amount)) {
            return String.format("%d", (long) amount);
        } else {
            return String.format("%.2f", amount);
        }
    }
}
