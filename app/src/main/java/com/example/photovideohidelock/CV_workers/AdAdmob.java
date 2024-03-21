//package com.example.photovideohidelock.CV_workers;
//
//import android.app.Activity;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.util.Log;
//import android.widget.RelativeLayout;
//import com.example.photovideohidelock.R;
//import com.google.android.gms.ads.AdListener;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdSize;
//import com.google.android.gms.ads.AdView;
//import com.google.android.gms.ads.LoadAdError;
//import com.google.android.gms.ads.MobileAds;
//import com.google.android.gms.ads.initialization.InitializationStatus;
//import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
//import com.google.android.gms.ads.interstitial.InterstitialAd;
//import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
//
//public class AdAdmob {
//    ProgressDialog ProgressDialog;
//
//    public AdAdmob(Activity activity) {
//        MobileAds.initialize(activity, new OnInitializationCompleteListener() {
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//            }
//        });
//    }
//
//    public void BannerAd(final RelativeLayout relativeLayout, Activity activity) {
//        final AdView adView = new AdView(activity);
//        adView.setAdSize(AdSize.BANNER);
//        adView.setAdUnitId(activity.getString(R.string.bnr_admob));
//        adView.loadAd(new AdRequest.Builder().build());
//        relativeLayout.addView(adView);
//        adView.setAdListener(new AdListener() {
//            public void onAdLoaded() {
//                relativeLayout.setVisibility(0);
//                super.onAdLoaded();
//                Log.e("ddddd", "dddd");
//            }
//
//            public void onAdOpened() {
//                super.onAdOpened();
//                relativeLayout.setVisibility(4);
//                Log.e("ddddd1", "dddd");
//            }
//
//            public void onAdFailedToLoad(LoadAdError loadAdError) {
//                super.onAdFailedToLoad(loadAdError);
//                adView.destroy();
//                relativeLayout.setVisibility(4);
//                Log.e("ddddd2", "dddd" + loadAdError.getMessage());
//            }
//        });
//    }
//
//    public void FullscreenAd(final Activity activity) {
//        Ad_Popup(activity);
//        InterstitialAd.load(activity, activity.getString(R.string.int_admob), new AdRequest.Builder().build(), new InterstitialAdLoadCallback() {
//            public void onAdLoaded(InterstitialAd interstitialAd) {
//                interstitialAd.show(activity);
//                AdAdmob.this.ProgressDialog.dismiss();
//            }
//
//            public void onAdFailedToLoad(LoadAdError loadAdError) {
//                AdAdmob.this.ProgressDialog.dismiss();
//            }
//        });
//    }
//
//    private void Ad_Popup(Context context) {
//        ProgressDialog show = ProgressDialog.show(context, "", "Ad Loading . . .", true);
//        this.ProgressDialog = show;
//        show.setCancelable(true);
//        this.ProgressDialog.show();
//    }
//}
