package volunteersofttech.arsa.myrestaurant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ACER on 3/19/2016.
 */
public class MyOpenHelper extends SQLiteOpenHelper{

    //ประกาศตัวแปร
    public static final String database_name = "Restaurant.db" ;
    private static final int database_version = 1;
    private  static final  String create_user_table = "create table userTABLE(" +
            "_id integer primary key," +
            "User text," +
            "Password text," +
            "Name text);";

    private  static  final  String create_food_table = "create table foodTABLE(" +
            "_id interger primary key," +
            "Food text," +
            "Price text," +
            "Source text):;

    public MyOpenHelper(Context context) {
        super(context ,database_name,null,database_version);
    } //Constructor  ทำอันนี้ก่อน

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_user_table);
        sqLiteDatabase.execSQL(create_food_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}       //Main class
