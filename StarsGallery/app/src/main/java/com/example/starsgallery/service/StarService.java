package com.example.starsgallery.service;

import com.example.starsgallery.R;
import com.example.starsgallery.beans.Star;
import com.example.starsgallery.dao.IDao;

import java.util.ArrayList;
import java.util.List;

public class StarService implements IDao<Star> {
    private List<Star> stars;
    private static StarService instance;

    private StarService() {
        stars = new ArrayList<>();
        seed();
    }

    public static StarService getInstance() {
        if (instance == null) instance = new StarService();
        return instance;
    }

    private void seed() {
        stars.add(new Star("Lewis Hamilton", R.drawable.hamilton, 4.5f));
        stars.add(new Star("Charles Leclerc", R.drawable.leclerc, 5f));
        stars.add(new Star("Lando Norris", R.drawable.norris, 4.8f));
        stars.add(new Star("Oscar Piastri", R.drawable.piastri, 4.7f));
        stars.add(new Star("George Russell", R.drawable.russell, 4.7f));
        stars.add(new Star("Max Verstappen", R.drawable.verstappen, 4.7f));
    }

    @Override public boolean create(Star o) { return stars.add(o); }

    @Override
    public boolean update(Star o) {
        for (Star s : stars) {
            if (s.getId() == o.getId()) {
                s.setName(o.getName());
                s.setImg(o.getImg());
                s.setRating(o.getRating());
                return true;
            }
        }
        return false;
    }

    @Override public boolean delete(Star o) { return stars.remove(o); }

    @Override
    public Star findById(int id) {
        for (Star s : stars) if (s.getId() == id) return s;
        return null;
    }

    @Override
    public List<Star> findAll() { return stars; }
}
