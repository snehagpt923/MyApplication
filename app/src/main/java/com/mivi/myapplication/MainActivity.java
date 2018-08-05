package com.mivi.myapplication;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.mivi.myapplication.common.CommonMethods;
import com.mivi.myapplication.databinding.ActivityMainBinding;
import com.mivi.myapplication.model.Collection;
import com.mivi.myapplication.model.Included;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        context = MainActivity.this;

        Collection collectionEntity = new Gson().fromJson(CommonMethods.loadJSONFromAsset(context),
                Collection.class);
        if (collectionEntity != null) {
            List<Included> list = collectionEntity.getIncluded();
            if (list != null && list.size() > 0) {
                Included subscription = list.get(1);
                if (!TextUtils.isEmpty(subscription.getType()) &&
                        subscription.getType().equalsIgnoreCase("subscriptions")) {
                    binding.tvSubscription.setText("Subscription Id: " + subscription.getId());
                    binding.dataBalance.setText("Data Balance: " + subscription.getAttributes().getIncludedDataBalance());
                }

                Included product = list.get(2);
                if (!TextUtils.isEmpty(product.getType()) &&
                        product.getType().equalsIgnoreCase("products")) {

                    binding.tvProductName.setText("Product Name: " + product.getAttributes().getName());
                    binding.tvProductPrice.setText("Product Price: " + product.getAttributes().getPrice());
                }
            }


        }
    }
}
