package com.daniel.matters.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.daniel.matters.Matter;
import com.daniel.matters.MattersApplication;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by dabraham on 1/14/16.
 */
public class MatterDb {

    private static BriteDatabase singleton = null;

    private static void createBriteDbInstance() {
        synchronized (BriteDatabase.class) {
            if (singleton == null) {
                singleton = SqlBrite.create().wrapDatabaseHelper(new MatterDbHelper(MattersApplication.getContext()));
            }
        }
    }

    public static void insertMatter(Matter matter) {
        createBriteDbInstance();
        singleton.insert(MattersTable.TABLE_NAME, matter.createContentValues(), SQLiteDatabase.CONFLICT_REPLACE);
        insertClient(matter.getClient());
    }

    public static void insertClient(Matter.Client client) {
        createBriteDbInstance();
        singleton.insert(ClientTable.TABLE_NAME, client.createContentValues(), SQLiteDatabase.CONFLICT_REPLACE);
    }

    public static void insertMatters(List<Matter> matters) {
        createBriteDbInstance();

        for (Matter matter : matters) {
            singleton.insert(MattersTable.TABLE_NAME, matter.createContentValues(), SQLiteDatabase.CONFLICT_REPLACE);
            singleton.insert(ClientTable.TABLE_NAME, matter.getClient().createContentValues(), SQLiteDatabase.CONFLICT_REPLACE);
        }
    }

    public static Observable<List<Matter>> queryMatters() {
        createBriteDbInstance();
        String query = "SELECT " + createMatterProjection()
                + " FROM " + MattersTable.TABLE_NAME
                + " INNER JOIN " + ClientTable.TABLE_NAME + " ON "
                + matterQueryJoinOn() ;

        return singleton.createQuery(MattersTable.TABLE_NAME, query)
                .mapToList(new Func1< Cursor, Matter>() {
                    @Override
                    public Matter call(Cursor cursor) {
                        return new Matter(cursor);
                    }
                }).asObservable();
    }

    private static String createMatterProjection() {
        return (MattersTable.TABLE_NAME + "." + MattersTable.Column.id.name())
                + " AS " + MattersTable.Column.id.name() + ","
                + MattersTable.TABLE_NAME + "." + MattersTable.Column.description.name() + ","
                + MattersTable.TABLE_NAME + "." + MattersTable.Column.status.name() + ","
                + MattersTable.TABLE_NAME + "." + MattersTable.Column.open_date.name() + ","
                + MattersTable.TABLE_NAME + "." + MattersTable.Column.client_id.name() + ","
                + ClientTable.TABLE_NAME + "." + ClientTable.Column.name.name() + ","
                + ClientTable.TABLE_NAME + "." + ClientTable.Column.url.name() ;

    }

    private static String matterQueryJoinOn() {
     return MattersTable.TABLE_NAME + "." + MattersTable.Column.client_id + "="
             + ClientTable.TABLE_NAME + "." + ClientTable.Column.id;
    }


}
