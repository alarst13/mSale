package com.example.msale.classes;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.msale.R;
import com.example.msale.activities.ProductActivity;
import com.example.msale.classes.Products.Product;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

public class ProductsOnSaleListAdapter extends RecyclerView.Adapter<ProductsOnSaleListAdapter.ProductViewHolder> {

    private List<Product> products;
    private Activity activity;

    public ProductsOnSaleListAdapter(List<Product> products, Activity activity) {
        this.products = products;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.products_on_sale_layout, viewGroup, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {
        productViewHolder.bindView(products.get(i), activity);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView txt_price, txt_description, txt_off_price;
        private View rootView;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            rootView = itemView;
            imageView = itemView.findViewById(R.id.products_on_sale_layout_image_view);
            txt_description = itemView.findViewById(R.id.description_txt_products_on_sale);
            txt_price = itemView.findViewById(R.id.price_txt_products_on_sale);
            txt_off_price = itemView.findViewById(R.id.off_price_txt_products_on_sale);
        }

        public void bindView(final Product product, final Activity activity) {

            rootView.findViewById(R.id.root_view).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, ProductActivity.class);
                    intent.putExtra("product", product);
                    activity.startActivity(intent);
                }
            });

            txt_description.setText(product.getName());
            if (product.isHaveOff()) {
                txt_price.setText(String.valueOf(product.getPrice()));
                txt_off_price.setText(String.valueOf(product.getFinalPrice()));
                txt_price.setPaintFlags(txt_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                if (product.getPicID() != null) {

                    try {
                        InputStream inputStream = rootView.getContext().getContentResolver().openInputStream(Uri.parse(product.getPicID()));
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        imageView.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }

            } else {
                txt_off_price.setText(String.valueOf(product.getFinalPrice()));
            }
        }
    }
}
