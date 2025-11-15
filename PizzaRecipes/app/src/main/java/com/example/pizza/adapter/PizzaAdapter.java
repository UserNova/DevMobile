package com.example.pizza.adapter;

import android.content.Context;
import android.view.*;
import android.widget.*;
import com.example.pizza.R;
import com.example.pizza.classes.Produit;
import java.util.List;

public class PizzaAdapter extends BaseAdapter {
    private final Context ctx;
    private final List<Produit> pizzas;

    public PizzaAdapter(Context ctx, List<Produit> pizzas) {
        this.ctx = ctx;
        this.pizzas = pizzas;
    }

    @Override public int getCount() { return pizzas.size(); }
    @Override public Object getItem(int i) { return pizzas.get(i); }
    @Override public long getItemId(int i) { return pizzas.get(i).getId(); }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(ctx).inflate(R.layout.row_pizza, parent, false);

        ImageView img = convertView.findViewById(R.id.imgPizza);
        TextView tvNom = convertView.findViewById(R.id.tvNom);
        TextView tvMeta = convertView.findViewById(R.id.tvMeta);

        Produit p = pizzas.get(pos);
        img.setImageResource(p.getImageRes());
        tvNom.setText(p.getNom());
        tvMeta.setText(p.getDuree() + " • " + p.getPrix() + " €");

        return convertView;
    }
}