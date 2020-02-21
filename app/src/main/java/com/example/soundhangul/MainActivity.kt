package com.example.soundhangul

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import com.kakao.util.helper.Utility.getPackageInfo
import kotlinx.android.synthetic.main.activity_main.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mContext = applicationContext
        var key = getKeyHash(mContext)
        Log.d("Key", "HashKey:"+key)

        consonant.setOnClickListener{
            val nextConsonant = Intent(this@MainActivity, PracticeConsonant::class.java)
            startActivity(nextConsonant)
        }

        vowel.setOnClickListener{
            val nextVowel = Intent(this@MainActivity, PracticeVowel::class.java)
            startActivity(nextVowel)
        }

        soundcard.setOnClickListener {
            val nextReadSound = Intent(this@MainActivity, SoundCard::class.java)
            startActivity(nextReadSound)
        }

        makeWord.setOnClickListener {
            val nextMakeWord = Intent(this@MainActivity, MakeWord::class.java)
            startActivity(nextMakeWord)
        }
        
    }

    fun getKeyHash(mcon:Context) : String? {
        val packageInfo = getPackageInfo(mcon, PackageManager.GET_SIGNATURES)
        if (packageInfo == null)
            return null
        for (signature in packageInfo.signatures)
        {
            try
            {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                return Base64.encodeToString(md.digest(), Base64.NO_WRAP)
            }
            catch (e: NoSuchAlgorithmException) {
                Log.w("main", "Unable to get MessageDigest. signature=" + signature, e)
            }
        }
        return null
    }
}
