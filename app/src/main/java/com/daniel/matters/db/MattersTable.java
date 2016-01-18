package com.daniel.matters.db;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dabraham on 1/13/16.
 */
public class MattersTable {
    public static final String TABLE_NAME = "matters";

    public enum Column {
        id ("INTEGER PRIMARY KEY"),
        description ("TEXT"),
        status ("TEXT"),
        open_date ("TEXT"),
        client_id ("TEXT");

        private final String type;

        Column(String type) {
            this.type = type;
        }

        public String createClause() {
            return String.format("%s %s", name(), type);
        }

        public static List<String> getColumnClauses() {
            List<String> clauses = new ArrayList();

            for (Column value : values()) {
                clauses.add(value.createClause());
            }

            return clauses;
        }
    }

    public static String name() {
        return TABLE_NAME;
    }

    public static String createTable() {
        StringBuilder builder = new StringBuilder("CREATE TABLE ")
                .append(name()).append(" (")
                .append(TextUtils.join(",", Column.getColumnClauses()))
                .append(")");

        return builder.toString();
    }

}
