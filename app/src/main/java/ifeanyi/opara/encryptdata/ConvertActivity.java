package ifeanyi.opara.encryptdata;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class ConvertActivity extends AppCompatActivity {

//    Button button;
//    ImageView imageview;
//    Drawable drawable;
//    Bitmap bitmap1, bitmap2;
//    ByteArrayOutputStream bytearrayoutputstream;
//    byte[] BYTE;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_convert);
//        button = findViewById(R.id.button1);
//        imageview = findViewById(R.id.imageView1);
//        bytearrayoutputstream = new ByteArrayOutputStream();
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawable = imageview.getDrawable();
//                bitmap1 = ((BitmapDrawable) drawable).getBitmap();
//                bitmap1.compress(Bitmap.CompressFormat.JPEG, 70, bytearrayoutputstream);
//                BYTE = bytearrayoutputstream.toByteArray();
//                Log.d("ConvertActivity",  BYTE.toString());
////                bitmap2 = BitmapFactory.decodeByteArray(BYTE, 0, BYTE.length);
////                imageview.setImageBitmap(bitmap2);
//            }
//        });
//    }
}