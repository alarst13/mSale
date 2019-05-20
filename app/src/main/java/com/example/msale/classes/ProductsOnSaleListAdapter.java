package com.example.msale.classes;

import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.msale.R;
import com.example.msale.classes.Products.Product;

import java.util.List;

public class ProductsOnSaleListAdapter extends RecyclerView.Adapter<ProductsOnSaleListAdapter.ProductViewHolder>{

    private List<Product> products;

    public ProductsOnSaleListAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.products_on_sale_layout, viewGroup, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {
        productViewHolder.bindView(products.get(i));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView txt_price,txt_description,txt_off_price;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.products_on_sale_layout_image_view);
            txt_description = itemView.findViewById(R.id.description_txt_products_on_sale);
            txt_price = itemView.findViewById(R.id.price_txt_products_on_sale);
            txt_off_price = itemView.findViewById(R.id.off_price_txt_products_on_sale);
        }

        public void bindView(Product product) {
            txt_description.setText(product.getName());
            if (product.isHaveOff()){
                txt_price.setText(String.valueOf(product.getPrice()));
                txt_off_price.setText(String.valueOf(product.getFinalPrice()));
                txt_price.setPaintFlags(txt_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }else{
                txt_off_price.setText(String.valueOf(product.getFinalPrice()));
            }
        }
    }
}
