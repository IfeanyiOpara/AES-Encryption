package ifeanyi.opara.encryptdata

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.ByteArrayOutputStream
import java.io.UTFDataFormatException
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class MainActivity : AppCompatActivity() {

    private lateinit var keyGenerator : KeyGenerator
    private lateinit var secretKey: SecretKey

    private lateinit var encrypt : ByteArray
    private lateinit var decrypt : ByteArray
    private lateinit var byte : ByteArray
    private lateinit var bitmap1: Bitmap
    private lateinit var bitmap2: Bitmap

    private lateinit var drawable: Drawable

    private var byteArrayOutputStream : ByteArrayOutputStream = ByteArrayOutputStream()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val IV : ByteArray = ByteArray(12)
        val random : SecureRandom = SecureRandom()
        random.nextBytes(IV)

        try {
            keyGenerator = KeyGenerator.getInstance("AES")
            keyGenerator.init(256)
            secretKey = keyGenerator.generateKey()
        } catch (e: Exception){
            e.printStackTrace()
        }

        encryptBtn.setOnClickListener{
            try {
                drawable = imageView1.drawable
                bitmap1 = (drawable as BitmapDrawable).bitmap
                bitmap1.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream)
                byte = byteArrayOutputStream.toByteArray()
                val byteString: String = byte.toString()
                Log.d("TestTwo", byte.toString())

                encrypt = encrypt(text.text.toString().toByteArray(), secretKey, IV)
                val encryptTxt : String = encrypt.toString()
                encryptText.text = encryptTxt
            } catch (e : java.lang.Exception){
                e.printStackTrace()
            }
        }

        decryptBtn.setOnClickListener {
            try {
                decrypt = decrypt(encrypt, secretKey, IV)!!.toByteArray()
                Log.d("ConvertActivity2", decrypt.toString())
                decryptText.text = decrypt.toString()
                bitmap2 = BitmapFactory.decodeByteArray(decrypt, 0, decrypt.size)
                imageView2.setImageBitmap(bitmap2)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    fun encrypt(plaintext: ByteArray, key: SecretKey, IV: ByteArray) : ByteArray{
        val cipher = Cipher.getInstance("AES")
        val keySpec = SecretKeySpec(key.encoded, "AES")
        val ivSpec = IvParameterSpec(IV)
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec)
        val cipherText = cipher.doFinal(plaintext)
        return cipherText
    }

        fun decrypt(cipherText: ByteArray?, key: SecretKey?, IV: ByteArray?): String? {
            try {
                val cipher = Cipher.getInstance("AES")
                val keySpec = SecretKeySpec(
                    key!!.encoded, "AES"
                )
                val ivSpec = IvParameterSpec(IV)
                cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec)
                val decryptedText = cipher.doFinal(cipherText)
                return String(decryptedText)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }


}