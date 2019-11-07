package com.awesomeurch.paymentlayout.page;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import com.awesomeurch.paymentlayout.R;
import com.awesomeurch.paymentlayout.helper.Utils;

public class MainActivity extends AppCompatActivity {
    private ImageView cardImgView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get the card image view
        cardImgView = findViewById(R.id.card_template);

        /*Bitmap cardImgBitmap = Utils.drawableToBitmap(getResources().getDrawable(R.drawable.card_img));
        Bitmap roundedCard = ImageHelper.getRoundedCornerBitmap(cardImgBitmap, 100);*/

        //cardImgView.setImageBitmap(roundedCard);

    }


}
