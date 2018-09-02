package com.art.dhimas.artapplication.modul.detail;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.art.dhimas.artapplication.BaseActivity;
import com.art.dhimas.artapplication.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.jakewharton.rxbinding.view.RxView;

import rx.functions.Action1;

public class DetailPageActivity extends BaseActivity {
    private ImageView image;
    private TextView titleArt;
    private ProgressBar progressBarImage;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.detail_art;
    }

    @Override
    protected void setContentViewOnChild() {
        toolbarTitle.setText("Detail");
        menuBtn.setVisibility(View.GONE);
        backBtn.setVisibility(View.VISIBLE);
        RxView.clicks(backBtn).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                onBackPressed();
            }
        });

        image = (ImageView) findViewById(R.id.image_art);
        titleArt = (TextView) findViewById(R.id.image_name);
        progressBarImage = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    protected void onCreateAtChild() {
        String title = getIntent().getStringExtra("title");
        String img = getIntent().getStringExtra("image");
        Glide.with(this).load(img)
                .fitCenter()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        progressBarImage.setVisibility(View.GONE);
                        image.setBackground(getDrawable(R.drawable.user));
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        progressBarImage.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(image);

        titleArt.setText(title);
    }
}
