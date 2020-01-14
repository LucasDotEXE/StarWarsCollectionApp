package com.example.starwarscollectablegame.Model.Database.PlayerCollectionDatabase;

import android.os.AsyncTask;

import com.example.starwarscollectablegame.Model.Database.PlayerCollectionDatabase.DaoInterfaces.BaseCollectionDao;
import com.example.starwarscollectablegame.Model.Database.PlayerCollectionDatabase.PlayerCollectionDatabaseData.collectionBase;

public class DatabaseEditHelper<T extends collectionBase> {
    private BaseCollectionDao<T> dao;

    public DatabaseEditHelper(BaseCollectionDao<T> dao) {
        this.dao = dao;
    }

    public void insert(T item) {
        new com.example.starwarscollectablegame.Model.Database.PlayerCollectionDatabase.DatabaseEditHelper.InsertAsyncTask<T>(this.dao).execute(item);
    }

    public void update(T item) {
        new com.example.starwarscollectablegame.Model.Database.PlayerCollectionDatabase.DatabaseEditHelper.UpdateAsyncTask<T>(this.dao).execute(item);
    }

    public void delete(T item) {
        new com.example.starwarscollectablegame.Model.Database.PlayerCollectionDatabase.DatabaseEditHelper.DeleteAsyncTask<T>(this.dao).execute(item);
    }

    public void delleteAll() {
        new DeleteAllAsyncTask<T>(this.dao).execute();
    }


    private static class InsertAsyncTask<T extends collectionBase> extends AsyncTask<T, Void, Void> {

        private BaseCollectionDao<T> dao;

        private InsertAsyncTask(BaseCollectionDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(T... item) {
            this.dao.insert(item[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask<T extends collectionBase> extends AsyncTask<T, Void, Void> {

        private BaseCollectionDao<T> dao;

        private UpdateAsyncTask(BaseCollectionDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(T... item) {
            this.dao.update(item[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask<T extends collectionBase> extends AsyncTask<T, Void, Void> {

        private BaseCollectionDao<T> dao;

        private DeleteAsyncTask(BaseCollectionDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(T... item) {
            this.dao.delete(item[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask<T extends collectionBase> extends AsyncTask<Void, Void, Void> {

        private BaseCollectionDao<T> dao;

        private DeleteAllAsyncTask(BaseCollectionDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            this.dao.deleteAll();
            return null;
        }
    }
}
