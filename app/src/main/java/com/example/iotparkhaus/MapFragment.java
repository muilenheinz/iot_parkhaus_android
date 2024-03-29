package com.example.iotparkhaus;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import com.example.iotparkhaus.customDataStructs.constants;
import com.example.iotparkhaus.customDataStructs.parkingGarageOccupation;
import java.util.Map;


public class MapFragment extends Fragment {

    private MapViewModel mViewModel;
    private View mapView;
    private ImageView img;
    private SVG svg;
    private String svgString;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        SocketViewModel socketViewModel = ViewModelProviders.of(requireActivity()).get(SocketViewModel.class);
        socketViewModel.getOccupation().observe(requireActivity(), new Observer<parkingGarageOccupation>() {
            @Override
            public void onChanged(@Nullable parkingGarageOccupation s) {
                occupyParkingGarage(s);
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mapView = inflater.inflate(R.layout.map_fragment, container, false);

        try {
            svgString = new AsyncFetchSVGTask().execute(constants.getSvgUrl()).get();
        } catch(Exception e ) {
            e.printStackTrace();
        }

        return mapView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MapViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void drawParkingGarage() {
        img = mapView.findViewById(R.id.pakingGarageMap);
        try {
            svg = SVG.getFromString(svgString);
            if(svg.getDocumentWidth() != - 1F) {
                svg.setDocumentHeight(600f);
                svg.setDocumentWidth(600f);
                Bitmap bitmap = Bitmap.createBitmap(700, 700, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                canvas.drawARGB(0, 255, 255, 255);
                svg.renderToCanvas(canvas);
                img.setBackground(new BitmapDrawable(getResources(), bitmap));
            }
        } catch (SVGParseException e) {
            e.printStackTrace();
        }
    }

    private void occupyParkingSpot(String spotNumber, Boolean available) {
        //define default color values for available parking spots
        String backgroundColor = constants.getBackgroundColorSpotAvilable();
        String textColor = constants.getTextColorSpotAvilable();

        if (!available) {
            backgroundColor = constants.getBackgroundColorSpotOccupied();
            textColor = constants.getTextColorSpotOccupied();
        }

        replaceAttributeInSVG(spotNumber,"space", "fill", backgroundColor);
        replaceAttributeInSVG(spotNumber,"symbol", "fill", textColor);
        replaceAttributeInSVG(spotNumber,"number", "fill", textColor);
    }

    private void replaceAttributeInSVG(String concernedSpot, String className, String attributeName, String newAttributeValue) {
        int beginOfSVGParent = svgString.indexOf("parking-" + concernedSpot);
        int beginOfSubTag = svgString.indexOf(className, beginOfSVGParent);
        int endOfSubTag = svgString.indexOf(">", beginOfSubTag);
        String styleIdentifier = "style=\"";
        int beginOfStyleProp = svgString.indexOf(styleIdentifier, beginOfSubTag);
        int endOfStyleProp = svgString.indexOf("\"", beginOfStyleProp + styleIdentifier.length());

        //element has style prop
        if (beginOfStyleProp < endOfSubTag && beginOfStyleProp != -1) {
            //style prop of element has a property of attributeName
            if (svgString.indexOf(attributeName, beginOfStyleProp) < endOfStyleProp
                && svgString.indexOf(attributeName, beginOfStyleProp) != -1) {
                    svgString = svgString.substring(0, svgString.indexOf(attributeName, beginOfStyleProp) + 5) +
                        newAttributeValue  + svgString.substring(endOfStyleProp);
            } else {
                //style prop of element has no property of attributeName, so add it
                svgString = svgString.substring(0, beginOfStyleProp + styleIdentifier.length())
                        + attributeName + ":" + newAttributeValue +
                        svgString.substring(beginOfStyleProp + styleIdentifier.length());
            }
        } else {
            //Attribute does not occur in element, so simply add it
            svgString = svgString.substring(0, beginOfSubTag + className.length() + 1) + " style=\"" + attributeName + ":" + newAttributeValue + "\"" +
                    svgString.substring(beginOfSubTag + className.length() + 1);
        }
    }

    private void occupyParkingGarage(parkingGarageOccupation _occupation) {
        for (Map.Entry<Integer, Boolean> integerBooleanEntry : _occupation.getOccupation().entrySet()) {
            occupyParkingSpot(String.format("%03d", integerBooleanEntry.getKey()),
                    Boolean.valueOf(integerBooleanEntry.getValue().toString()));
        }
        drawParkingGarage();
    }
}
