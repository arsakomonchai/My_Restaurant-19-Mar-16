package volunteersofttech.arsa.myrestaurant;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    // ประกาศตัวแปร
    private TextView showofficerTextView;
    private Spinner deskSpinner;
    private ListView foodListView;

    private String officerString,deskString,foodString, amountString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        //bind widget
        bindwidget();

        //Show View
        showview();

        //create Desk Spinner
        createDeskSpinner();

        //Create Food ListView
        createFoodlistview();

    }//main method

    private void createFoodlistview() {

        //Read All Sqlite
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + MyManage.food_table, null);
        cursor.moveToFirst();
        int intCount = cursor.getCount();

        String[] iconStrings = new String[intCount];
        String[] foodStrings = new String[intCount];
        String[] priceStrings = new String[intCount];

        for (int i = 0; i < intCount; i++) {

            iconStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_source));
            foodStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_food));
            priceStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_price));

            cursor.moveToNext();


        }//for
        cursor.close();

        FoodAdapter foodAdapter = new FoodAdapter(OrderActivity.this, iconStrings, foodStrings, priceStrings);
        foodListView.setAdapter(foodAdapter);

    }//food listview

    private void createDeskSpinner() {

        final String[] deskStrings = {"โต๊ะ 1", "โต๊ะ 2", "โต๊ะ 3", "โต๊ะ 4", "โต๊ะ 5", "โต๊ะ 6", "โต๊ะ 7", "โต๊ะ 7", "โต๊ะ 8", "โต๊ะ 9", "โต๊ะ 10"};
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,deskStrings);
        deskSpinner.setAdapter(stringArrayAdapter);

        deskSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                deskString = deskStrings[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { // ไม่มีการเลือกจะเข้าที่ไหน
                deskString = deskStrings[0];

            }
        });

    }//deskspinner


    private void showview() {

        //Receive Value From Intent
        officerString = getIntent().getStringExtra("Officer");

        showofficerTextView.setText(officerString);

    }//showview

    private void bindwidget() {
        showofficerTextView = (TextView) findViewById(R.id.textView);
        deskSpinner = (Spinner) findViewById(R.id.spinner);
        foodListView = (ListView) findViewById(R.id.listView);


    }//bindwidget

}//main class
