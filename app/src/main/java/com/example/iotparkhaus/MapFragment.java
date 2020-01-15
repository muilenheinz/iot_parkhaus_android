package com.example.iotparkhaus;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import com.example.iotparkhaus.customDataStructs.parkingGarageOccupation;
import com.example.iotparkhaus.customDataStructs.parkingStats;

import java.util.HashMap;
import java.util.Iterator;


public class MapFragment extends Fragment {

    private MapViewModel mViewModel;
    private View mapView;
    private ImageView img;
    private SVG svg;
    private String svgString;
    private int parkingSpotsCount = 8;
    private int totalFreeSpots, womenFreeSpots, disabledFreeSpots;
    private int testVal = 0;



    public static MapFragment newInstance() {
        return new MapFragment();
    }
    private Button sendMsg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        SocketViewModel socketViewModel = ViewModelProviders.of(requireActivity()).get(SocketViewModel.class);
        socketViewModel.getOccupation().observe(requireActivity(), new Observer<parkingGarageOccupation>() {
            @Override
            public void onChanged(@Nullable parkingGarageOccupation s) {
                System.out.println("changed occupation reached view");
                occupyParkingGarage(s);
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mapView = inflater.inflate(R.layout.map_fragment, container, false);
        getSVG();

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

    private String getSVG() {
        svgString = "<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 637.797 907.087\" width=\"637.797pt\" height=\"907.087pt\">\n" +
                "    <style>\n" +
                "        .space,\n" +
                "        .symbol,\n" +
                "        .number {\n" +
                "        transition: fill 0.3s;\n" +
                "        }\n" +
                "\n" +
                "        .space {\n" +
                "        fill: #1361c2;\n" +
                "        }\n" +
                "\n" +
                "        .symbol,\n" +
                "        .number {\n" +
                "        font-family: Roboto, sans-serif;\n" +
                "        fill: #fff;\n" +
                "        font-style: normal;\n" +
                "        stroke: none;\n" +
                "        }\n" +
                "\n" +
                "        .symbol {\n" +
                "        font-weight: 400;\n" +
                "        font-size: 28px;\n" +
                "        }\n" +
                "\n" +
                "        .number {\n" +
                "        font-weight: 700;\n" +
                "        }\n" +
                "\n" +
                "        .occupied {\n" +
                "        .space {\n" +
                "        fill: rgb(185, 185, 185);\n" +
                "        }\n" +
                "\n" +
                "        .symbol,\n" +
                "        .number {\n" +
                "        fill: #8b8b8b;\n" +
                "        }\n" +
                "        }\n" +
                "    </style>\n" +
                "    <defs>\n" +
                "        <clipPath id=\"_clipPath_kps33JfZoSy5W52p1Pc4vRtUlMFjbvoL\">\n" +
                "            <rect width=\"637.797\" height=\"907.087\"/>\n" +
                "        </clipPath>\n" +
                "    </defs>\n" +
                "    <g clip-path=\"url(#_clipPath_kps33JfZoSy5W52p1Pc4vRtUlMFjbvoL)\">\n" +
                "        <rect x=\"0\" y=\"0\" width=\"637.795\" height=\"907.087\" transform=\"matrix(1,0,0,1,0,0)\" fill=\"rgb(232,232,232)\"/>\n" +
                "        <rect x=\"0\" y=\"311.811\" width=\"637.795\" height=\"283.465\" transform=\"matrix(1,0,0,1,0,0)\" fill=\"rgb(116,116,116)\"/>\n" +
                "        <g class=\"parking parking-008\" id=\"parking-008\">\n" +
                "            <rect class=\"space\" x=\"481.89\" y=\"609.449\" width=\"141.732\" height=\"283.465\" transform=\"matrix(1,0,0,1,0,0)\" fill=\"rgb(185,185,185)\"/>\n" +
                "            <text class=\"symbol\" transform=\"matrix(2.959,0,0,2.959,523.785,761.116)\" style=\"\">\n" +
                "                P\n" +
                "            </text>\n" +
                "            <text class=\"number\" transform=\"matrix(2.959,0,0,2.959,504.118,824.789)\">\n" +
                "                008\n" +
                "            </text>\n" +
                "        </g>\n" +
                "        <g class=\"parking parking-007\" id=\"parking-007\">\n" +
                "            <rect class=\"space\" x=\"325.984\" y=\"609.449\" width=\"141.732\" height=\"283.465\" transform=\"matrix(1,0,0,1,0,0)\" fill=\"rgb(185,185,185)\"/>\n" +
                "            <text class=\"symbol\" transform=\"matrix(2.959,0,0,2.959,367.88,761.116)\">\n" +
                "                P\n" +
                "            </text>\n" +
                "            <text class=\"number\" transform=\"matrix(2.959,0,0,2.959,348.212,824.789)\">\n" +
                "                007\n" +
                "            </text>\n" +
                "        </g>\n" +
                "        <g class=\"parking parking-006\" id=\"parking-006\">\n" +
                "            <rect class=\"space\" x=\"170.079\" y=\"609.449\" width=\"141.732\" height=\"283.465\" transform=\"matrix(1,0,0,1,0,0)\" fill=\"rgb(185,185,185)\"/>\n" +
                "            <text class=\"symbol\" transform=\"matrix(2.959,0,0,2.959,211.974,761.116)\">\n" +
                "                P\n" +
                "            </text>\n" +
                "            <text class=\"number\" transform=\"matrix(2.959,0,0,2.959,192.307,824.789)\">\n" +
                "                006\n" +
                "            </text>\n" +
                "        </g>\n" +
                "        <g class=\"parking parking-005\" id=\"parking-005\">\n" +
                "            <rect class=\"space\" x=\"14.173\" y=\"609.449\" width=\"141.732\" height=\"283.465\" transform=\"matrix(1,0,0,1,0,0)\" fill=\"rgb(185,185,185)\"/>\n" +
                "            <text class=\"number\" transform=\"matrix(2.959,0,0,2.959,36.401,824.789)\">\n" +
                "                005\n" +
                "            </text>\n" +
                "            <path class=\"symbol\" d=\" M 92.641 708.811 C 92.641 705.301 95.491 702.451 99.001 702.451 C 102.511 702.451 105.361 705.301 105.361 708.811 C 105.361 712.321 102.511 715.171 99.001 715.171 C 95.491 715.171 92.641 712.321 92.641 708.811 Z  M 89.461 748.433 L 83.101 748.433 C 83.101 753.68 78.809 757.973 73.562 757.973 C 68.315 757.973 64.022 753.68 64.022 748.433 C 64.022 743.186 68.315 738.893 73.562 738.893 L 73.562 732.533 C 64.785 732.533 57.662 739.656 57.662 748.433 C 57.662 757.21 64.785 764.333 73.562 764.333 C 82.338 764.333 89.461 757.21 89.461 748.433 L 89.461 748.433 Z  M 99.001 737.303 L 93.087 737.303 L 98.397 725.633 C 100.337 721.403 97.221 716.633 92.514 716.633 L 75.978 716.633 C 73.403 716.633 71.081 718.128 70.032 720.449 L 67.901 726.173 L 74.007 727.859 L 76.074 722.993 L 83.101 722.993 L 77.282 736.031 C 75.374 740.261 78.522 745.253 83.165 745.253 L 99.001 745.253 L 99.001 761.153 L 105.361 761.153 L 105.361 743.663 C 105.361 740.165 102.499 737.303 99.001 737.303 L 99.001 737.303 Z \" fill-rule=\"evenodd\" fill=\"rgb(139,139,139)\"/>\n" +
                "        </g>\n" +
                "        <g class=\"parking parking-004\" id=\"parking-004\">\n" +
                "            <rect class=\"space\" x=\"481.89\" y=\"14.173\" width=\"141.732\" height=\"283.465\" transform=\"matrix(1,0,0,1,0,0)\" fill=\"rgb(185,185,185)\"/>\n" +
                "            <text class=\"symbol\" transform=\"matrix(2.959,0,0,2.959,523.785,165.84)\">\n" +
                "                P\n" +
                "            </text>\n" +
                "            <text class=\"number\" transform=\"matrix(2.959,0,0,2.959,504.118,229.514)\">\n" +
                "                004\n" +
                "            </text>\n" +
                "        </g>\n" +
                "        <g class=\"parking parking-003\" id=\"parking-003\">\n" +
                "            <rect class=\"space\" x=\"325.984\" y=\"14.173\" width=\"141.732\" height=\"283.465\" transform=\"matrix(1,0,0,1,0,0)\" fill=\"rgb(185,185,185)\"/>\n" +
                "            <text class=\"symbol\" transform=\"matrix(2.959,0,0,2.959,367.88,165.84)\">\n" +
                "                P\n" +
                "            </text>\n" +
                "            <text class=\"number\" transform=\"matrix(2.959,0,0,2.959,348.212,229.514)\">\n" +
                "                003\n" +
                "            </text>\n" +
                "        </g>\n" +
                "        <g class=\"parking parking-002\" id=\"parking-002\">\n" +
                "            <rect class=\"space\" x=\"170.079\" y=\"14.173\" width=\"141.732\" height=\"283.465\" transform=\"matrix(1,0,0,1,0,0)\" fill=\"rgb(185,185,185)\"/>\n" +
                "            <text class=\"symbol\" transform=\"matrix(2.959,0,0,2.959,211.974,165.84)\">\n" +
                "                P\n" +
                "            </text>\n" +
                "            <text class=\"number\" transform=\"matrix(2.959,0,0,2.959,192.307,229.514)\">\n" +
                "                002\n" +
                "            </text>\n" +
                "        </g>\n" +
                "        <g class=\"parking parking-001\" id=\"parking-001\">\n" +
                "            <rect class=\"space\" x=\"14.173\" y=\"14.173\" width=\"141.732\" height=\"283.465\" transform=\"matrix(1,0,0,1,0,0)\" fill=\"rgb(185,185,185)\"/>\n" +
                "            <text class=\"number\" transform=\"matrix(2.959,0,0,2.959,36.401,229.514)\">\n" +
                "                001\n" +
                "            </text>\n" +
                "            <path class=\"symbol\" d=\" M 72.839 120.107 L 68.34 134.539 C 67.375 137.694 71.507 138.925 72.517 135.964 L 76.542 122.653 L 77.675 122.653 L 70.757 146.038 L 77.216 146.038 L 77.216 163.611 C 77.216 166.794 82.113 166.794 82.113 163.611 L 82.113 146.038 L 83.643 146.038 L 83.643 163.611 C 83.643 166.794 88.387 166.794 88.387 163.611 L 88.387 146.038 L 95.029 146.038 L 87.959 122.653 L 89.244 122.653 L 93.269 135.964 C 94.264 138.995 98.365 137.694 97.447 134.553 L 92.948 120.107 C 92.336 118.474 90.162 115.596 86.52 115.457 L 79.282 115.457 C 75.517 115.596 73.359 118.446 72.839 120.107 Z  M 88.479 109.12 C 88.479 106.269 85.969 103.959 82.878 103.959 C 79.787 103.959 77.277 106.269 77.277 109.12 C 77.277 111.97 79.787 114.281 82.878 114.281 C 85.969 114.281 88.479 111.97 88.479 109.12 Z \" fill-rule=\"evenodd\" fill=\"rgb(139,139,139)\"/>\n" +
                "        </g>\n" +
                "    </g>\n" +
                "</svg>";

        return svgString;
    }

    private void occupyParkingSpot(String spotNumber, Boolean available) {
        //define default color values for available parking spots
        String backgroundColor = "rgb(19, 97, 194)";
        String textColor = "rgb(255, 255, 255)";

        if (!available) {
            backgroundColor = "rgb(185,185,185)";
            textColor = "rgb(139, 139, 139)";
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
        Iterator iterator = _occupation.getOccupation().entrySet().iterator();
        while (iterator.hasNext()) {
            HashMap.Entry occupationRow = (HashMap.Entry) iterator.next();
            occupyParkingSpot(String.format("%03d", occupationRow.getKey()),
                    Boolean.valueOf(occupationRow.getValue().toString()));
        }
        drawParkingGarage();
    }

}
