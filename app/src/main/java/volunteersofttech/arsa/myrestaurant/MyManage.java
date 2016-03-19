package volunteersofttech.arsa.myrestaurant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ACER on 3/19/2016.
 */
public class MyManage {

    //ประกาศตัวแปร
    private  MyOpenHelper myOpenHelper ;
    private SQLiteDatabase WriteSqLiteDatabase,readSqLiteDatabase;


    public  MyManage(Context context) {;

        //create & connect sqllite
        myOpenHelper = new MyOpenHelper(context);
        WriteSqLiteDatabase = myOpenHelper.getWritableDatabase();
        readSqLiteDatabase = myOpenHelper.getReadableDatabase();


    }//Construtor


} //Main Class
