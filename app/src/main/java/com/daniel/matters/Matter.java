package com.daniel.matters;

/**
 * Created by dabraham on 1/8/16.
 */
public class Matter {

    long id;
    Client client;
    String display_number;
    String description;
    String status;
    String open_date;
    String close_date;
    String pending_date;
    String location;
    String client_reference;
    String reponsible_attorney;
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

    public class Permission {
        long id;
        String url;
        String name;
    }

    public class Client {
        long id;
        String url;
        String name;
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
