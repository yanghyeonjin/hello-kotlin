package com.yanghyeonjin.hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.yanghyeonjin.hellokotlin.databinding.ActivityAdMobBinding

class AdMobActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdMobBinding

    companion object {
        const val TAG: String = "로그"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdMobBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        MobileAds.initialize(this) {}

        // 광고 로드
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)

        binding.adView.adListener = object: AdListener() {
            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.e(TAG, "AdMobActivity - onAdLoaded() called")
            }

            override fun onAdFailedToLoad(adError : LoadAdError) {
                // Code to be executed when an ad request fails.
                Log.e(TAG, "AdMobActivity - onAdFailedToLoad() called")
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                Log.e(TAG, "AdMobActivity - onAdOpened() called")
            }

            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
                Log.e(TAG, "AdMobActivity - onAdClicked() called")
            }

            override fun onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                Log.e(TAG, "AdMobActivity - onAdLeftApplication() called")
            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
                Log.e(TAG, "AdMobActivity - onAdClosed() called")
            }
        }
    }
}