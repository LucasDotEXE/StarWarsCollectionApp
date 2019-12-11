package com.example.starwarscollectablegame.Model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.starwarscollectablegame.Model.StarwarsDatabase.DaoInterfaces.BaseStarWarsDao;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.DaoInterfaces.FilmDao;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.DaoInterfaces.PlanetDao;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarWarsDatabase;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Film;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.Planet;

import java.util.List;

public class StarWarsDataRepository {

    private FilmDao filmDao;
    private LiveData<List<Film>> allFilms;
    private PlanetDao planetDao;

    public StarWarsDataRepository(Application application) {
        StarWarsDatabase database = StarWarsDatabase.getInstance(application);
        this.filmDao = database.filmDao();
        this.allFilms = this.filmDao.getAllFilms();
    }

    public LiveData<List<Film>> getAllFilms() {
        return allFilms;
    }

    public void insertFilm(Film film) {
        new InsertAsyncTask<Film>(this.filmDao).execute(film);
    }

    public void updateFilm(Film film) {
        new UpdateAsyncTask<Film>(this.filmDao).execute(film);
    }

    public void deleteFilm(Film film) {
        new DeleteAsyncTask<Film>(this.filmDao).execute(film);
    }

    public List<Planet> getPlanetByUrl(String url) {
        return  this.planetDao.getPlanetsByUtl(url);
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
