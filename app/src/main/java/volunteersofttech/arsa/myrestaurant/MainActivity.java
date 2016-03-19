package volunteersofttech.arsa.myrestaurant;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //ประกาศตัวแปร
    private MyManage myManage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Request Sqllite
        myManage = new MyManage(this);

        //test add vale
        //testaddvalue();

        //delete all SQLite
        deleteallsqlite();




    }//main method "method อันแรกที่ทำงาน oncreate"

    private void deleteallsqlite() {
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE,null  );
        sqLiteDatabase.delete(MyManage.user_table, null, null);
        sqLiteDatabase.delete(MyManage.food_table, null, null);
    }


    private void testaddvalue() {
        myManage.addValue(1, "user", "pass", "name");
        myManage.addValue(2, "food", "price", "source");
    }

}//main class
