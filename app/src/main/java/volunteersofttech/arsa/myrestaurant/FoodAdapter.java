package volunteersofttech.arsa.myrestaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by ACER on 3/20/2016.
 */
public class FoodAdapter extends BaseAdapter{

    //ประกาศตัวแปร
    private Context context;
    private String[] urlIconStrings,namefoodStrings, priceStrings;

    public FoodAdapter(Context context, String[] urlIconStrings, String[] namefoodStrings, String[] priceStrings) {
        this.context = context;
        this.urlIconStrings = urlIconStrings;
        this.namefoodStrings = namefoodStrings;
        this.priceStrings = priceStrings;
    }

    @Override
    public int getCount() {
        return urlIconStrings.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //ขอเปิด Service
        View view1 = layoutInflater.inflate(R.layout.food_listview,viewGroup,false);

        //For Image
        ImageView iconImageView = (ImageView) view1.findViewById(R.id.imageView2);
        Picasso.with(context).load(urlIconStrings[i]).resize(120, 120).into(iconImageView);

        //for Text
        TextView foodnameTextView = (TextView) view1.findViewById(R.id.textView2);
        foodnameTextView.setText(namefoodStrings[i]);

        TextView priceTextView = (TextView) view1.findViewById(R.id.textView3);
        priceTextView.setText(priceStrings[i]);


        return view1;
    }
}// Main Class
