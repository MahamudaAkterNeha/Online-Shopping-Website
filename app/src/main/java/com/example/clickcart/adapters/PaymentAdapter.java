package com.example.clickcart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clickcart.R;
import com.example.clickcart.models.AddressModel;
import com.example.clickcart.models.PaymentModel;

import java.util.List;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.ViewHolder> {

    Context context;
    List<PaymentModel> paymentModelList;
    SelectedPayment selectedPayment;

    private RadioButton selectedRadioBtn;

    public PaymentAdapter(Context context, List<PaymentModel> paymentModelList, SelectedPayment selectedPayment) {
        this.context = context;
        this.paymentModelList = paymentModelList;
        this.selectedPayment = selectedPayment;
    }

    @NonNull
    @Override
    public  ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentAdapter.ViewHolder holder, int position) {

        holder.payment.setText(paymentModelList.get(holder.getAdapterPosition()).getUserPaymentDetails());
        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(PaymentModel payment:paymentModelList){
                    payment.setSelected(false);
                }
                paymentModelList.get(holder.getAdapterPosition()).setSelected(true);
                if(selectedRadioBtn!=null){
                    selectedRadioBtn.setChecked(false);
                }
                selectedRadioBtn = (RadioButton) v;
                selectedRadioBtn.setChecked(true);
                selectedPayment.setPayment(paymentModelList.get(holder.getAdapterPosition()).getUserPaymentDetails());
            }
        });

    }

    @Override
    public int getItemCount() {
        return paymentModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView payment;
        RadioButton radioButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            payment = itemView.findViewById(R.id.payment_add);
            radioButton = itemView.findViewById(R.id.select_payment);
        }
    }

    public  interface SelectedPayment{
        void setPayment(String payment);
    }
}
