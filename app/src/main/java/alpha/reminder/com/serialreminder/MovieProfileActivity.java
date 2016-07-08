package alpha.reminder.com.serialreminder;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Eugeniy Krukun on 08.07.2016.
 */

public class MovieProfileActivity extends Activity implements AppBarLayout.OnOffsetChangedListener {
    private String title, type, year;
    private Bitmap poster;
    private ImageView posterImage;
    private TextView yearView, typeView;
    private CardView cardView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_profile);

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(this);

        cardView = (CardView) findViewById(R.id.info_card);

        Intent intent = getIntent();

        title = intent.getStringExtra("title");
        year = intent.getStringExtra("year");
        type = intent.getStringExtra("type");

        poster = BitmapFactory.decodeByteArray(intent.getByteArrayExtra("poster"), 0, intent.getByteArrayExtra("poster").length);
        posterImage = (ImageView) findViewById(R.id.film_poster);
        posterImage.setImageBitmap(poster);

        yearView = (TextView) findViewById(R.id.year_text);
        yearView.setText("Year:" + year);

        typeView = (TextView) findViewById(R.id.type_text);
        typeView.setText("Type:" + type);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(title);
        collapsingToolbarLayout.setExpandedTitleMarginBottom(140);

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        verticalOffset = Math.abs(verticalOffset);
        int difference = appBarLayout.getTotalScrollRange() - 150;
        if (verticalOffset > difference) {
          cardView.setVisibility(View.INVISIBLE);
        } else {
            cardView.setVisibility(View.VISIBLE);
        }
    }
}
