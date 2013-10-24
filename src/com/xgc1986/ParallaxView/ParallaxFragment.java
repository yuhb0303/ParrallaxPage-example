package com.xgc1986.ParallaxView;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by xgc1986 on 9/9/13.
 */
public class ParallaxFragment extends Fragment {

    private RelativeLayout mRoot;
    private CatsAdapter mCatsAdapter;
    private ParallaxFragment that;

    public ParallaxFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        that = this;
        View v = inflater.inflate(R.layout.fragment_cat, container, false);
        ImageView image = (ImageView) v.findViewById(R.id.image);
        image.setImageResource(getArguments().getInt("image"));
        image.setScaleType(ImageView.ScaleType.MATRIX);

        TextView text = (TextView)v.findViewById(R.id.name);
        text.setText(getArguments().getString("name"));

        text.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/sunshine.ttf"));

        TextView blog = (TextView) v.findViewById(R.id.blog);
        blog.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/news-regular.ttf"));

        blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://indexoutofrange.com";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        TextView more = (TextView)v.findViewById(R.id.more);

        more.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mCatsAdapter != null) {
                    mCatsAdapter.remove(that);
                    mCatsAdapter.notifyDataSetChanged();

                    if (mCatsAdapter.getCount() == 0) {

                    } else {

                    }
                }
                return true;
            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCatsAdapter != null) {
                    int select = (int) (Math.random() * 4);

                    int[] resD = {R.drawable.nina, R.drawable.niju, R.drawable.yuki, R.drawable.kero};
                    String[] resS = {"Nina", "Niju", "Yuki", "Kero"};

                    ParallaxFragment newP = new ParallaxFragment();
                    Bundle b = new Bundle();
                    b.putInt("image", resD[select]);
                    b.putString("name", resS[select]);
                    newP.setArguments(b);
                    mCatsAdapter.add(newP);
                }
            }
        });
        return v;
    }

    public void setAdapter(CatsAdapter catsAdapter) {
        mCatsAdapter = catsAdapter;
    }
}
