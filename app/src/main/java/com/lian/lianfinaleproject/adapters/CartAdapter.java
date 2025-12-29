package com.lian.lianfinaleproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lian.lianfinaleproject.R;
import com.lian.lianfinaleproject.model.Cart;
import com.lian.lianfinaleproject.model.Item;
import com.lian.lianfinaleproject.utils.ImageUtil;

public class CartAdapter  extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private Context context;
    private Cart cart;

    public CartAdapter(Context context, Cart cart) {
        this.context = context;
        this.cart = cart;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        if (cart != null && cart.getItemList() != null && position < cart.getItemList().size()) {
            Item item_cart = cart.getItemList().get(position);
            holder.bind(item_cart);
        }
    }

    @Override
    public int getItemCount() {
        return (cart != null && cart.getItemList() != null) ? cart.getItemList().size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
      public   ImageView product_image;
      public   TextView product_name, product_category, product_amount, product_company;
        //  ImageButton ibPlus, ibMinus;

      public   CheckBox cbbuy_or_not;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            product_image = itemView.findViewById(R.id.itemCartPic);
            product_name = itemView.findViewById(R.id.item_item_cart_name);
            product_category = itemView.findViewById(R.id.item_item_cart_category);
            product_amount = itemView.findViewById(R.id.item_item_cart_amount);
            product_company = itemView.findViewById(R.id.item_item_cart_company);
            cbbuy_or_not = itemView.findViewById(R.id.cbbuy_or_not);

        }




        public void bind(final Item item) {

            int amount = item.getAmount();

          //  if (amount > 0) {


                product_image.setImageBitmap(ImageUtil.convertFrom64base(item.getPic()));
                product_name.setText(item.getName());
                product_category.setText(item.getType());
                product_amount.setText(String.valueOf(amount));
                product_company.setText(item.getCompany());


                cbbuy_or_not.setChecked(false);


        //    }

        }
    }
}
