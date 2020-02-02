package com.saud.app.yaya.Database;

public class TableColumns {

    public static class workout{
        public static  final String TABLE_NAME="workout";
        public static  final String COL_ID="id";
        public static  final String COL_TITLE="title";

    }
    public static class exercise {
        public static  final String TABLE_NAME="exercise";
        public static  final String COL_ID="id";
        public static  final String COL_WORKOUT_ID="workout_id";
        public static  final String COL_TITLE="title";
        public static  final String COL_SERIES="series";
        public static  final String COL_REPETITION="repetition";
        public static  final String COL_RHYTHM="rhythm";
        public static  final String COL_REST_BT_SERIES="rest_bt_series";
        public static  final String COL_REST_BT_EXERCISE="rest_bt_exercise";

    }
}
