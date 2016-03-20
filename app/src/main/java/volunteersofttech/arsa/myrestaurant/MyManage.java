package volunteersofttech.arsa.myrestaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ACER on 3/19/2016.
 */
public class MyManage {

    //ประกาศตัวแปร
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase WriteSqLiteDatabase, readSqLiteDatabase;

    public static final String user_table = "userTABLE";
    public static final String column_id = "_id";
    public static final String column_user = "User";
    public static final String column_password = "Password";
    public static final String column_name = "Name";

    public static final String food_table = "foodTABLE";
    public static final String column_food = "Food";
    public static final String column_price = "Price";
    public static final String column_source = "Source";


    public MyManage(Context context) {
        ;

        //create & connect sqllite
        myOpenHelper = new MyOpenHelper(context);
        WriteSqLiteDatabase = myOpenHelper.getWritableDatabase();
        readSqLiteDatabase = myOpenHelper.getReadableDatabase();


    }//Construtor

    public String[] SearchUser(String strUser) {

        try {
            String[] resultStrings = null;
            Cursor cursor = readSqLiteDatabase.query(user_table,
                    new String[]{column_id, column_user, column_password, column_name},
                    column_user + "=?",
                    new String[]{strUser},
                    null, null, null);
            if (cursor !=null) {
                if (cursor.moveToFirst()) {
                    resultStrings = new String[cursor.getColumnCount()];
                    for (int i = 0; i < 4; i++) {
                        resultStrings[i] = cursor.getString(i);
                    }
                }
            }//if
            cursor.close();
            return resultStrings;


        } catch (Exception e) {
            return null;
        }


        //return new String[0];
    }




    public long addValue(int intTable,
                         String strcolumn2,
                         String strcolumn3,
                         String strcolumn4) {
        ContentValues contentValues = new ContentValues();

        long mylong = 0;

        switch (intTable) {
            case 1:
                //for usertable
                contentValues.put(column_user, strcolumn2);
                contentValues.put(column_password, strcolumn3);
                contentValues.put(column_name, strcolumn4);

                mylong = WriteSqLiteDatabase.insert(user_table, null, contentValues);

                break;
            case 2:
                //for foodtable
                contentValues.put(column_food, strcolumn2);
                contentValues.put(column_price, strcolumn3);
                contentValues.put(column_source, strcolumn4);

                mylong = WriteSqLiteDatabase.insert(food_table, null, contentValues);


                break;

        }

        return mylong;



    }





} //Main Class
