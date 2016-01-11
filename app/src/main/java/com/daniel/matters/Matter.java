package com.daniel.matters;

import android.os.Parcel;
import android.os.Parcelable;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by dabraham on 1/8/16.
 */
@Table(database = MattersDatabase.class)
public class Matter extends BaseModel implements Parcelable {

    // TODO: add back in fields that were not working with retrofit

    @PrimaryKey()
    long id;
    @ForeignKey(saveForeignKeyModel = true)
    Client client;
    String display_number;
    @Column
    String description;
    @Column
    String status;
    @Column
    String open_date;
    String close_date;
    String pending_date;
    String location;
    String client_reference;
    String responsible_attorney;
    String originating_attorney;
    String practice_area;
    boolean billable;
    String maildrop_address;
    String created_at;
    String updated_at;
    //String[] custom_field_values;
    String billing_method;
    long group_id;
    Permission permission;
    //ActivityRate activity_rates;

    public Matter() {

    }

    protected Matter(Parcel in) {
        id = in.readLong();
        client = in.readParcelable(Client.class.getClassLoader());
        display_number = in.readString();
        description = in.readString();
        status = in.readString();
        open_date = in.readString();
        close_date = in.readString();
        pending_date = in.readString();
        location = in.readString();
        client_reference = in.readString();
        responsible_attorney = in.readString();
        originating_attorney = in.readString();
        practice_area = in.readString();
        billable = in.readByte() != 0;
        maildrop_address = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
        billing_method = in.readString();
        group_id = in.readLong();
        permission = in.readParcelable(Permission.class.getClassLoader());
    }

    public static final Creator<Matter> CREATOR = new Creator<Matter>() {
        @Override
        public Matter createFromParcel(Parcel in) {
            return new Matter(in);
        }

        @Override
        public Matter[] newArray(int size) {
            return new Matter[size];
        }
    };

    public Long getId() {
        return id;
    }

    public String getClientName() {
        if (client != null) {
            return client.name;
        } else {
            return "";
        }
    }

    public String getDescription() {
        return description;
    }

    public String getOpenDate() {
        return open_date;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeParcelable(client, 0);
        dest.writeString(display_number);
        dest.writeString(description);
        dest.writeString(status);
        dest.writeString(open_date);
        dest.writeString(close_date);
        dest.writeString(pending_date);
        dest.writeString(location);
        dest.writeString(client_reference);
        dest.writeString(responsible_attorney);
        dest.writeString(originating_attorney);
        dest.writeString(practice_area);
        dest.writeInt(billable ? 1 : 0);
        dest.writeString(maildrop_address);
        dest.writeString(created_at);
        dest.writeString(updated_at);
        dest.writeString(billing_method);
        dest.writeLong(group_id);
        dest.writeParcelable(permission, 0);
    }


    public static class Permission implements Parcelable {
        long id;
        String url;
        String name;

        protected Permission(Parcel in) {
            id = in.readLong();
            url = in.readString();
            name = in.readString();
        }

        public static final Creator<Permission> CREATOR = new Creator<Permission>() {
            @Override
            public Permission createFromParcel(Parcel in) {
                return new Permission(in);
            }

            @Override
            public Permission[] newArray(int size) {
                return new Permission[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeLong(id);
            dest.writeString(url);
            dest.writeString(name);
        }
    }

    @ModelContainer
    @Table(database = MattersDatabase.class)
    public static class Client extends BaseModel implements Parcelable {
        @PrimaryKey
        long id;
        String url;
        @Column
        String name;

        public Client() {

        }

        protected Client(Parcel in) {
            id = in.readLong();
            url = in.readString();
            name = in.readString();
        }

        public static final Creator<Client> CREATOR = new Creator<Client>() {
            @Override
            public Client createFromParcel(Parcel in) {
                return new Client(in);
            }

            @Override
            public Client[] newArray(int size) {
                return new Client[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeLong(id);
            dest.writeString(url);
            dest.writeString(name);
        }
    }

//    public class ActivityRate {
//        long id;
//        String created_at;
//        String upadated_at;
//        User user;
//        Group group;
//
//        public class User {
//            long id;
//            String url;
//            String name;
//            String email;
//        }
//
//        public class Group {
//            float rate;
//            boolean flat_rate;
//            String activity_description;
//        }
//    }

}
