package volunteersofttech.arsa.myrestaurant;

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



    }//main method "method อันแรกที่ทำงาน oncreate"

}//main class
