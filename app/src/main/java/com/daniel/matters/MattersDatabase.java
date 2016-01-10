package com.daniel.matters;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by dabraham on 1/10/16.
 */
@Database(name = MattersDatabase.NAME, version = MattersDatabase.VERSION)
public class MattersDatabase {

    public static final String NAME = "MattersDatabase";

    public static final int VERSION = 1;
}
