package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ExpenseListFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_fragment_expense_list, container, false);
        RecyclerView rv = root.findViewById(R.id.recyclerExpenses);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        ExpenseAdapter adapter = new ExpenseAdapter(requireActivity(), ExpenseData.getDummyExpenses());
        rv.setAdapter(adapter);
        return root;
    }
}

