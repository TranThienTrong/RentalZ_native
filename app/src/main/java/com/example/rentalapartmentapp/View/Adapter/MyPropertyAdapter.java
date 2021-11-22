package com.example.rentalapartmentapp.View.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentalapartmentapp.Model.Property;
import com.example.rentalapartmentapp.R;
import com.example.rentalapartmentapp.Services.DatabaseHelper;

import java.util.ArrayList;

public class MyPropertyAdapter extends RecyclerView.Adapter<MyPropertyAdapter.MyViewHolder>{

    private ArrayList<Property> propertyList = new ArrayList<>();
    private DatabaseHelper databaseHelper;
    private Context context;


    public MyPropertyAdapter() {
    }

    public MyPropertyAdapter(Context context, ArrayList<Property> propertyList, DatabaseHelper databaseHelper) {
        this.context = context;
        this.propertyList = propertyList;
        this.databaseHelper = databaseHelper;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_property, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Property property = propertyList.get(position);


        if (property != null) {

            Bitmap bitmapImage = BitmapFactory.decodeByteArray(property.getImage(), 0, property.getImage().length);

            holder.imgpropertyImage.setImageBitmap(bitmapImage);
            holder.tvpropertyTitle.setText(property.getTitle());
            holder.tvpropertyPrice.setText(property.getPrice());
            holder.tvPropertyBedroom.setText(property.getBedroom().name());
            holder.tvPropertyFurniture.setText(property.getFurnitureType().name());
            holder.tvPropertyType.setText(property.getPropertyType().name());
        }
    }

    @Override
    public int getItemCount() {
        return propertyList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgpropertyImage;
        TextView tvpropertyTitle;
        TextView tvpropertyPrice;
        TextView tvPropertyBedroom;
        TextView tvPropertyFurniture;
        TextView tvPropertyType;
        ImageButton imgBtnDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgpropertyImage = itemView.findViewById(R.id.item_image_property);
            tvpropertyTitle = itemView.findViewById(R.id.item_property_title);
            tvpropertyPrice = itemView.findViewById(R.id.item_property_price);
            imgBtnDelete = itemView.findViewById(R.id.item_imgBtn_delete_property);

            tvPropertyBedroom  = itemView.findViewById(R.id.item_property_bedroom);
            tvPropertyFurniture = itemView.findViewById(R.id.item_property_furniture);
            tvPropertyType = itemView.findViewById(R.id.item_property_type);

            imgBtnDelete.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {
            Property property = propertyList.get(getBindingAdapterPosition());
            switch (view.getId()) {
                case R.id.item_imgBtn_delete_property:
                    deleteproperty(property);
                    break;
            }
        }

        private void deleteproperty(final Property property) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            View confirmPopup = LayoutInflater.from(context).inflate(R.layout.dialog_delete, null);
            alertDialogBuilder.setView(confirmPopup);

            Button btnConfirm = confirmPopup.findViewById(R.id.popup_delete_btnYes);
            Button btnCancel = confirmPopup.findViewById(R.id.popup_delete_btnCancel);

            // Popup the Delete Dialog
            final AlertDialog dialog = alertDialogBuilder.create();
            dialog.show();

            btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    databaseHelper.deleteProperty(property);
                    propertyList.remove(propertyList.get(propertyList.indexOf(property)));
                    notifyItemRemoved(getBindingAdapterPosition());
                    dialog.dismiss();
                }
            });

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

        }

    }
}
