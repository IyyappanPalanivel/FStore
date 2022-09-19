package com.tt.fstore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tt.fstore.databinding.UserItemBinding;
import com.tt.fstore.model.CustomerResponse;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {

    Context context;
    List<CustomerResponse.Parameter> customerList;

    public CustomerAdapter(Context context, List<CustomerResponse.Parameter> customerResponseList) {
        this.context = context;
        this.customerList = customerResponseList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UserItemBinding binding = UserItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CustomerResponse.Parameter customer = customerList.get(position);

        holder.binding.firstName.setText(customer.getFirstName()+" "+customer.getLastName());
        holder.binding.email.setText(customer.getPrimaryEmail());
        holder.binding.phone.setText(customer.getPrimaryPhone());
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        UserItemBinding binding;
        public ViewHolder(@NonNull UserItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
