package volunteersofttech.arsa.myrestaurant;

import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    //ประกาศตัวแปร
    private MyManage myManage;
    private EditText userEdittext, passwordEdittext;
    private String userString, passworString;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bind widget  ผู้กตัวแปรกับ Object
        bindwidget();

        //Request Sqllite
        myManage = new MyManage(this);

        //test add vale
        //testaddvalue();

        //delete all SQLite
        deleteallsqlite();

        //Syn JSON to SQLite
        synJSONtoSQLite();


    }//main method "method อันแรกที่ทำงาน oncreate"

    public void clickLogin(View view) {

        userString = userEdittext.getText().toString().trim();
        passworString = passwordEdittext.getText().toString().trim();

        //check space
        if (userString.equals("") || passworString.equals("")) {
            //have space  || = OR ,&& =and
            myAlert("มีช่องว่าง");
        } else {
            //No Space

        }




    }// Clicklogin

    private void myAlert(String strmessage) {
        Toast.makeText(MainActivity.this, strmessage, Toast.LENGTH_SHORT).show();
        // คำสั่งขึ้น Messagebox  Toast
    }


    private void bindwidget() {
        userEdittext = (EditText) findViewById(R.id.editText);
        passwordEdittext = (EditText) findViewById(R.id.editText2);

    }

    private void synJSONtoSQLite() {

        //Connected Http://
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        int intTable = 0;
        while (intTable <=1) {

            //1 Create InputStren
            InputStream inputStream = null;
            String[] urlStrings = {"http://swiftcodingthai.com/19Mar/php_get_user_keng.php",
                    "http://swiftcodingthai.com/19Mar/php_get_food_keng.php"};

            try {

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(urlStrings[intTable]);
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                inputStream = httpEntity.getContent();



            } catch (Exception e) {
                Log.d("Rest", "InputStren ==> " + e.toString());
            }

            //2  Create JSON String
            String strJSON = null;

            try {

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                StringBuilder stringBuilder = new StringBuilder();
                String strline = null;

                while ((strline = bufferedReader.readLine()) !=null) {
                    stringBuilder.append((strline));
                }
                inputStream.close();
                strJSON = stringBuilder.toString();


            } catch (Exception e) {
                Log.d("Rest", "JSON String ==> " + e.toString());
            }

            //3  Update to SQLite

            try {

                JSONArray jsonArray = new JSONArray((strJSON));
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    switch (intTable) {
                        case 0:
                            String strUser = jsonObject.getString("user");
                            String strPassword = jsonObject.getString("password");
                            //String strName = jsonObject.getString(MyManage.column_name)
                            String strName = jsonObject.getString("name");

                            myManage.addValue(1, strUser, strPassword, strName);

                            break;
                        case 1:
                            String strfood = jsonObject.getString(MyManage.column_food);
                            String strPrice = jsonObject.getString(MyManage.column_price);
                            String strSource = jsonObject.getString(MyManage.column_source);

                            myManage.addValue(2, strfood, strPrice, strSource);


                            break;

                    }

                } //for

            } catch (Exception e) {
                Log.d("Rest", "Update SQLite ==> " + e.toString());
            }


            intTable += 1;
        }//white Loop Table ในการหา Table มี 2 ตัวที่ต้องการเปลี่ยนค่า

    }//synJson

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
