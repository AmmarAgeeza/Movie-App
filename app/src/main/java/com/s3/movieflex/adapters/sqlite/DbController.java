package com.s3.movieflex.adapters.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.s3.movieflex.model.MovieModel;

import java.util.ArrayList;
public class DbController {
    private final DbHelper databaseHelper;
    private SQLiteDatabase database;
    public DbController(Context context) {
        databaseHelper = new DbHelper(context);
    }
    public void open() {
        database = databaseHelper.getWritableDatabase();
    }
    public int addMovie(MovieModel movieModel) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.TAB1_COLO1, movieModel.getId());
        if (movieModel.getTitle() == null)
            values.put(DbHelper.TAB1_COLO2, movieModel.getName());
        else
            values.put(DbHelper.TAB1_COLO2, movieModel.getTitle());
        values.put(DbHelper.TAB1_COLO3, movieModel.getOverview());
        values.put(DbHelper.TAB1_COLO4, movieModel.getPoster_path());
        values.put(DbHelper.TAB1_COLO5, movieModel.getBackdrop_path());
        values.put(DbHelper.TAB1_COLO6, movieModel.getRelease_date());
        values.put(DbHelper.TAB1_COLO7, movieModel.getVote_average());
        database.insert(DbHelper.TABLE1, null, values);
        //addMovieCast(MovieModel.getId(), MovieModel.getCast());
        return movieModel.getId();
    }


    /* private void addMovieCast(long id, ArrayList<Cast> casts) {
         ContentValues contentValues = new ContentValues();
         for (Cast cast : casts) {
             contentValues.put(DbHelper.TAB2_COLO1, id);
             contentValues.put(DbHelper.TAB2_COLO2, cast.getName());
             contentValues.put(DbHelper.TAB2_COLO3, cast.getImgLink());
         }
         database.insert(DbHelper.TABLE2, null, contentValues);
     }*/
    public void delete(int id) {
        //  deleteCast(id);
        database.delete(DbHelper.TABLE1, DbHelper.TAB1_COLO1 + "=" + id, null);
    }

    /* private void deleteCast(int id) {
         database.delete(DbHelper.TABLE2, DbHelper.TAB2_COLO1 + "=" + id, null);
     }
 */
    public boolean selectMovie(int id) {
        Cursor cursor = database.rawQuery("SELECT * from " + DbHelper.TABLE1 +
                " where " + DbHelper.TAB1_COLO1 + " = " + id, null);
        cursor.moveToFirst();
        return !cursor.isBeforeFirst();


    }

    public ArrayList<MovieModel> selectAllMovie() {
        ArrayList<MovieModel> movies = new ArrayList<>();
        // ArrayList<Cast> c1 = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * from " + DbHelper.TABLE1, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            //  c1 = selectCast(cursor.getLong(0));
            movies.add(new MovieModel(cursor.getInt(0), cursor.getString(1), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getString(5), cursor.getFloat(6)));
            cursor.moveToNext();
        }
        return movies;
    }

    /* private ArrayList<Cast> selectCast(int id) {
         ArrayList<Cast> cast = new ArrayList<>();
         Cursor cursor = database.rawQuery("SELECT * from " + DbHelper.TABLE2, null);
         cursor.moveToFirst();
         while (!cursor.isAfterLast()) {
             cast.add(new Cast(cursor.getString(1), cursor.getString(2)));
             cursor.moveToNext();
         }

         return cast;
     }*/
    public void close() {
        databaseHelper.close();
    }
}