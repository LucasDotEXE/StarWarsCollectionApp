package com.example.starwarscollectablegame.Model.StarwarsDatabase;

import android.os.AsyncTask;

import com.example.starwarscollectablegame.Model.StarwarsDatabase.DaoInterfaces.BaseStarWarsDao;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.SwapiEntry;

public class DatabaseEditHelper<T extends SwapiEntry> {
    private BaseStarWarsDao<T> dao;

    public DatabaseEditHelper(BaseStarWarsDao<T> dao) {
        this.dao = dao;
    }

    public void insert(T swapiEntry) {
        new InsertAsyncTask<T>(this.dao).execute(swapiEntry);
    }

    public void update(T swapiEntry) {
        new UpdateAsyncTask<T>(this.dao).execute(swapiEntry);
    }

    public void delete(T swapiEntry) {
        new DeleteAsyncTask<T>(this.dao).execute(swapiEntry);
    }


    private static class InsertAsyncTask<T> extends AsyncTask<T, Void, Void> {

        private BaseStarWarsDao<T> dao;

        private InsertAsyncTask(BaseStarWarsDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(T... item) {
            this.dao.insert(item[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask<T> extends AsyncTask<T, Void, Void> {

        private BaseStarWarsDao<T> dao;

        private UpdateAsyncTask(BaseStarWarsDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(T... item) {
            this.dao.insert(item[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask<T> extends AsyncTask<T, Void, Void> {

        private BaseStarWarsDao<T> dao;

        private DeleteAsyncTask(BaseStarWarsDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(T... item) {
            this.dao.delete(item[0]);
            return null;
        }
    }
}

