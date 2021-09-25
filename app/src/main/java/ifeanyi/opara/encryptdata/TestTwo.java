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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class TestTwo extends AppCompatActivity {

    KeyGenerator keyGenerator;
    SecretKey secretKey;

    Button encryptBtn, decryptBtn;
    TextView encryptText, decryptText;

    EditText editText;
    ImageView imageView1, imageView2;

    byte[] encrypt;

    Drawable drawable;
    Bitmap bitmap1, bitmap2;
    ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
    byte[] BYTE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_two);

        encryptBtn = findViewById(R.id.encryptBtn);
        decryptBtn = findViewById(R.id.decryptBtn);
        encryptText = findViewById(R.id.encryptText);
        decryptText = findViewById(R.id.decryptText);
        editText = findViewById(R.id.text);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);

        byte[] IV = new byte[12];
        SecureRandom random;
        random = new SecureRandom();
        random.nextBytes(IV);

        try {
            keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            secretKey = keyGenerator.generateKey();
        } catch (Exception e) {
            e.printStackTrace();
        }

        encryptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    drawable = imageView1.getDrawable();
                    bitmap1 = ((BitmapDrawable) drawable).getBitmap();
                    bitmap1.compress(Bitmap.CompressFormat.JPEG, 70, bytearrayoutputstream);
                    BYTE = bytearrayoutputstream.toByteArray();
                    String byteString = BYTE.toString();
                    Log.d("TestTwo", BYTE.toString());

                    encrypt = encrypt(byteString.getBytes(), secretKey, IV);
                    String encryptTxt = new String(encrypt, "UTF-8");
                    encryptText.setText(encryptTxt);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        decryptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String decrypt = decrypt(encrypt, secretKey, IV);
                    decryptText.setText(decrypt);

                    byte[] decryptByte = decrypt.getBytes();
                    bitmap2 = BitmapFactory.decodeByteArray(decryptByte, 0, decrypt.length());
                    imageView2.setImageBitmap(bitmap2);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }

    public static byte[] encrypt(byte[] plaintext, SecretKey key, byte[] IV) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(IV);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] cipherText = cipher.doFinal(plaintext);
        return cipherText;
    }

    public static String decrypt(byte[] cipherText, SecretKey key, byte[] IV) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(IV);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] decryptedText = cipher.doFinal(cipherText);
            return new String(decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}