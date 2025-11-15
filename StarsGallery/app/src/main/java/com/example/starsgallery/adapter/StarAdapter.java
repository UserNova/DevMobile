package com.example.starsgallery.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.starsgallery.R;
import com.example.starsgallery.beans.Star;
import com.example.starsgallery.service.StarService;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class StarAdapter extends RecyclerView.Adapter<StarAdapter.StarViewHolder> implements Filterable {

    private List<Star> stars;        // liste originale
    private List<Star> starsFilter;  // liste filtrée
    private Context context;
    private NewFilter mfilter;

    public StarAdapter(Context context, List<Star> stars) {
        this.context = context;
        this.stars = stars;
        this.starsFilter = new ArrayList<>(stars);
        this.mfilter = new NewFilter(this);
    }

    @NonNull
    @Override
    public StarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.star_item, parent, false);
        return new StarViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StarViewHolder holder, int position) {

        Star s = starsFilter.get(position);

        Glide.with(context)
                .load(s.getImg())
                .into(holder.img);

        holder.name.setText(s.getName());
        holder.rating.setRating(s.getRating());

        // Clique pour ouvrir popup de notation
        holder.itemView.setOnClickListener(v -> openPopup(holder, s));
    }

    @Override
    public int getItemCount() {
        return starsFilter.size();
    }

    @Override
    public Filter getFilter() {
        return mfilter;
    }

    public class StarViewHolder extends RecyclerView.ViewHolder {
        CircleImageView img;
        TextView name;
        RatingBar rating;

        public StarViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgStar);
            name = itemView.findViewById(R.id.tvName);
            rating = itemView.findViewById(R.id.rating);
        }
    }

    private void openPopup(StarViewHolder holder, Star star) {

        View popup = LayoutInflater.from(context).inflate(R.layout.star_edit_item, null, false);

        ImageView img = popup.findViewById(R.id.img);
        RatingBar bar = popup.findViewById(R.id.ratingBar);

        img.setImageDrawable(holder.img.getDrawable());
        bar.setRating(star.getRating());

        new AlertDialog.Builder(context)
                .setTitle("Notez :")
                .setMessage("Donner une note entre 1 et 5 :")
                .setView(popup)
                .setPositiveButton("Valider", (dialog, which) -> {
                    float newRating = bar.getRating();
                    star.setRating(newRating);
                    StarService.getInstance().update(star);
                    notifyItemChanged(holder.getAdapterPosition());
                })
                .setNegativeButton("Annuler", null)
                .show();
    }

    // ---------------- Filtrage ----------------
    public class NewFilter extends Filter {
        RecyclerView.Adapter adapter;

        public NewFilter(RecyclerView.Adapter adapter) {
            this.adapter = adapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Star> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(stars);
            } else {
                String filter = constraint.toString().toLowerCase().trim();
                for (Star s : stars) {
                    if (s.getName().toLowerCase().contains(filter)) {
                        filteredList.add(s);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            starsFilter = (List<Star>) results.values;
            adapter.notifyDataSetChanged();
        }
    }
}
