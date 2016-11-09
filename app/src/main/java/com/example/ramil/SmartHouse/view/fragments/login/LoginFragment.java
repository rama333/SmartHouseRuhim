package com.example.ramil.SmartHouse.view.fragments.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ramil.SmartHouse.R;


/**
 * Created by Ramil on 05.08.2016.
 */
public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.login_fragment, viewGroup, false);

//        RelativeLayout root = (RelativeLayout) view.findViewById(R.id.login);
       /* SVG svg1 = SVGParser.getSVGFromResource(getResources(),
                R.raw.login);
        Drawable pictureDrawable1 = svg1.createPictureDrawable();
        //root.setBackgroundDrawable(pictureDrawable);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        //imageView.setImageDrawable(pictureDrawable);
        //imageView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);


        SVGImageView svgImageView = new SVGImageView(getActivity());
        svgImageView.setImageDrawable(pictureDrawable1);
        svgImageView.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));


        SVG svg = SVGParser.getSVGFromResource(getResources(), R.raw.login);
        Drawable pictureDrawable = svg.createPictureDrawable();
        Bitmap bitmap = Bitmap.createBitmap(pictureDrawable.getIntrinsicWidth(), pictureDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);

        LinearLayout backgroundLayout = (LinearLayout) view.findViewById(R.id.login);
        bitmapDrawable.setTileModeX(Shader.TileMode.REPEAT);
        backgroundLayout.setBackgroundDrawable(bitmapDrawable);


       // root.addView(svgImageView);
*/

        return view;
    }
}
