package com.example.starwarscollectablegame.Model.Database.PlayerDataDatabse;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class PlayerDataDatabaseEditHelper {

    private PlayerDataDao playerDataDao;

    public PlayerDataDatabaseEditHelper(PlayerDataDao playerDataDao) {
        this.playerDataDao = playerDataDao;
    }

    public void insert(PlayerData playerData) {
        new InsertAsyncTask(playerDataDao).execute(playerData);
    }

    public void update(PlayerData playerData) {
        new UpdateAsyncTask(playerDataDao).execute(playerData);
    }

    public void delete(PlayerData playerData) {
        new DeleteAsyncTask(playerDataDao).execute(playerData);
    }

    public void deleteAll() {
        new DeleteAllAsyncTask(playerDataDao).execute();
    }

    public LiveData<List<PlayerData>> getPlayerByName(String name) {
        try {
            return new getPlayerByNameTask(playerDataDao).execute(name).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static class getPlayerByNameTask extends AsyncTask<String, Void, LiveData<List<PlayerData>>> {

        private PlayerDataDao dao;

        public getPlayerByNameTask(PlayerDataDao dao) {
            this.dao = dao;
        }

        @Override
        protected LiveData<List<PlayerData>> doInBackground(String... strings) {
            return dao.getPlayerByName(strings[0]);
        }
    }


    private static class InsertAsyncTask extends AsyncTask<PlayerData, Void, Void> {

        private PlayerDataDao dao;

        private InsertAsyncTask(PlayerDataDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(PlayerData... item) {
            this.dao.insert(item[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<PlayerData, Void, Void> {

        private PlayerDataDao dao;

        private UpdateAsyncTask(PlayerDataDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(PlayerData... item) {
            this.dao.update(item[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<PlayerData, Void, Void> {

        private PlayerDataDao dao;

        private DeleteAsyncTask(PlayerDataDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(PlayerData... item) {
            this.dao.delete(item[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {

        private PlayerDataDao dao;

        private DeleteAllAsyncTask(PlayerDataDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            this.dao.deleteAll();
            return null;
        }
    }
}
