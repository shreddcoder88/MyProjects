package com.jebi.jvhor_000.campusmap;


import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.jebi.jvhor_000.campusmap.Adapter.DirectoryAdapter;
import com.jebi.jvhor_000.campusmap.Adapter.FarnumAdapter;
import com.jebi.jvhor_000.campusmap.Adapter.GrapponeAdapter;
import com.jebi.jvhor_000.campusmap.Adapter.LittleHallAdapter;
import com.jebi.jvhor_000.campusmap.Adapter.MacruryAdapter;
import com.jebi.jvhor_000.campusmap.Adapter.McauliffeAdapter;
import com.jebi.jvhor_000.campusmap.Adapter.SweeneyAdapter;

import java.util.ArrayList;



public class MapsActivity extends FragmentActivity implements MainPage.listListener, OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {


    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private static final float DEFAULT_MIN_ZOOM = 16.25f;
    private static final float DEFAULT_MAX_ZOOM = 35f;
    private static final float DEFAULT_ZOOM = 25.0f;
    private static final String TAG = MapsActivity.class.getSimpleName();
    private DrawerLayout drawer;

    private GoogleMap campusMap;

    private static LatLngBounds nhti = new LatLngBounds(new LatLng(43.221504, -71.534645), new LatLng(43.226430, -71.526921));
    private boolean mLocationPermissionGranted;
    private static Location mLastKnownLocation;
    private static CameraPosition mCameraPosition;
    private final LatLng mDefaultLocation = tech;
    private SearchView searchee;

    //Building LatLng
    private static LatLng tech = new LatLng(43.26, -71.5339);
    private static LatLng mcauliffeM = new LatLng(43.224194, -71.532488);
    private static LatLng grapponeM = new LatLng(43.222730, -71.532889);
    private static LatLng littlehallM = new LatLng(43.222887, -71.531689);
    private static LatLng farnumM = new LatLng(43.223338, -71.531386);
    private static LatLng sweeneyM = new LatLng(43.224585, -71.530935);
    private static LatLng libraryM = new LatLng(43.224281, -71.530377);
    private static LatLng macruryM = new LatLng(43.223599, -71.532177);
    private static LatLng northM = new LatLng(43.226160, -71.530688);
    private static LatLng southM = new LatLng(43.220765, -71.532635);
    private static LatLng policeM = new LatLng(43.2257325, -71.5326932);
    private static LatLng cafeteriaM = new LatLng(43.222609, -71.531223);
    private static LatLng officeM = new LatLng(43.223504, -71.530885);
    private static LatLng campusSafetyM = new LatLng(43.222813, -71.530382);
    private static LatLng quadM = new LatLng(43.224016, -71.531379);

    //Parking spot LatLng
    private static LatLng parkingM = new LatLng(43.224689, -71.528922);
    private static LatLng parking1M = new LatLng(43.223571, -71.530360);
    private static LatLng parking2M = new LatLng(43.222793, -71.530848);
    private static LatLng parking3M = new LatLng(43.225511, -71.530210);
    private static LatLng parking4M = new LatLng(43.226375, -71.528638);
    private static LatLng parking5M = new LatLng(43.225521, -71.533875);
    private static LatLng parking6M = new LatLng(43.223466, -71.533209);
    private static LatLng parking7M = new LatLng(43.222620, -71.533256);
    private static LatLng parking8M = new LatLng(43.220039, -71.532988);


    private static Marker[] searchableP, parkingP;
    private static ArrayList<Marker> list;
    private ViewPager viewPager;
    private NavigationView navView;
    private BottomSheetBehavior bottomMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        navView = (NavigationView) findViewById(R.id.navigation_view);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        searchee = (SearchView) findViewById(R.id.search_view);

        searchee.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search(searchee.getQuery());
                searchee.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });




        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);




        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        campusMap = googleMap;

        //add markers
        Marker mMacrury = campusMap.addMarker(new MarkerOptions().position(macruryM).title("Macrury").visible(false));
        Marker mMcauliffe = campusMap.addMarker(new MarkerOptions().position(mcauliffeM).title("Mcauliffe").visible(false));
        Marker mLibrary = campusMap.addMarker(new MarkerOptions().position(libraryM).title("Library").visible(false));
        Marker mLittleHall = campusMap.addMarker(new MarkerOptions().position(littlehallM).title("Little Hall").visible(false));
        Marker mFarnum = campusMap.addMarker(new MarkerOptions().position(farnumM).title("Farnum").visible(false));
        Marker mNorth = campusMap.addMarker(new MarkerOptions().position(northM).title("North").visible(false));
        Marker mSouth = campusMap.addMarker(new MarkerOptions().position(southM).title("South").visible(false));
        Marker mSweeney = campusMap.addMarker(new MarkerOptions().position(sweeneyM).title("Sweeney").visible(false));
        Marker mGrappone = campusMap.addMarker(new MarkerOptions().position(grapponeM).title("Grappone").visible(false));

        Marker mPolice = campusMap.addMarker(new MarkerOptions().position(policeM).title("Police Academy").visible(false));
        Marker mCafeteria = campusMap.addMarker(new MarkerOptions().position(cafeteriaM).title("Cafeteria").visible(false));
        Marker mOffice = campusMap.addMarker(new MarkerOptions().position(officeM).title("Community College Office").visible(false));
        Marker mCampusSafety = campusMap.addMarker(new MarkerOptions().position(campusSafetyM).title("Campus Safety Office").visible(false));
        Marker mQuad = campusMap.addMarker(new MarkerOptions().position(quadM).alpha(0).title("Quad").flat(true).visible(true).infoWindowAnchor(0.7f,0.5f));


        searchableP = new Marker[]{mMcauliffe, mGrappone, mLibrary, mLittleHall, mFarnum, mSweeney, mMacrury, mNorth, mSouth, mCampusSafety, mCafeteria,
                        mPolice, mOffice, mCampusSafety};

        //TODO Parking Markers
        //Parking Markers
        Marker mParking = campusMap.addMarker(new MarkerOptions().position(parkingM).title("Parking").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).visible(false));
        Marker mParking1 = campusMap.addMarker(new MarkerOptions().position(parking1M).title("Parking").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).visible(false));
        Marker mParking2 = campusMap.addMarker(new MarkerOptions().position(parking2M).title("Parking").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).visible(false));
        Marker mParking3 = campusMap.addMarker(new MarkerOptions().position(parking3M).title("Parking").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).visible(false));
        Marker mParking4 = campusMap.addMarker(new MarkerOptions().position(parking4M).title("Parking").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).visible(false));
        Marker mParking5 = campusMap.addMarker(new MarkerOptions().position(parking5M).title("Parking").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).visible(false));
        Marker mParking6 = campusMap.addMarker(new MarkerOptions().position(parking6M).title("Parking").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).visible(false));
        Marker mParking7 = campusMap.addMarker(new MarkerOptions().position(parking7M).title("Parking").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).visible(false));


        parkingP = new Marker[]{mParking, mParking1, mParking2, mParking3, mParking4, mParking5, mParking6, mParking7};
        //campusMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_menu_myplaces)).anchor(5, 5).position(farnumM).snippet("Campus Ground"));



        //Map styling

        campusMap.setMapStyle(new MapStyleOptions(getResources()
                .getString(R.string.style_json)));
        campusMap.setBuildingsEnabled(true);

        // Map zoom preference
        campusMap.setMinZoomPreference(DEFAULT_MIN_ZOOM);
        campusMap.setMaxZoomPreference(DEFAULT_MAX_ZOOM);

        //CameraUpdateFactory.scrollBy(5, 5);
        //campusMap.moveCamera(CameraUpdateFactory.newLatLng(tech));

        //TODO: Make all the polygons work
        // Polygons are initialized here
        final Polygon whole = campusMap.addPolygon((new PolygonOptions()).clickable(false).add(new LatLng(43.226826, -71.535041), new LatLng(43.226826, -71.526675),
                new LatLng(43.221045, -71.526675), new LatLng(43.220882, -71.535041)));
        final Polygon mcauliffeP = campusMap.addPolygon((new PolygonOptions()).clickable(true).add(new LatLng(43.224352, -71.532895), new LatLng(43.224370, -71.532297),
                new LatLng(43.224030, -71.532230), new LatLng(43.223967, -71.532831)));
        final Polygon sweeneyP = campusMap.addPolygon((new PolygonOptions()).clickable(true).add(new LatLng(43.224439, -71.530931), new LatLng(43.224549, -71.531120),
                new LatLng(43.224404, -71.531909), new LatLng(43.224750, -71.532035), new LatLng(43.224847, -71.531256), new LatLng(43.225077, -71.530851), new LatLng(43.224862, -71.530388)));
        final Polygon littleHallP = campusMap.addPolygon((new PolygonOptions()).clickable(true).add(new LatLng(43.222894, -71.532261), new LatLng(43.223046, -71.531205),
                new LatLng(43.222870, -71.531146), new LatLng(43.222705, -71.532186)));
        final Polygon farnumP = campusMap.addPolygon((new PolygonOptions()).clickable(true).add(new LatLng(43.2235784, -71.5314094), new LatLng(43.2233805, -71.5313451),
                new LatLng(43.2234111, -71.5311271), new LatLng(43.2232962, -71.5310886), new LatLng(43.2232286, -71.5315067), new LatLng(43.2235488, -71.5316036)));
        final Polygon libraryP = campusMap.addPolygon((new PolygonOptions()).clickable(true).add(new LatLng(43.224590, -71.530248), new LatLng(43.224472, -71.530209), new LatLng(43.224493, -71.530102),
                new LatLng(43.224264, -71.530027), new LatLng(43.224100, -71.530164), new LatLng(43.224145, -71.530263), new LatLng(43.223941, -71.530437), new LatLng(43.224078, -71.530721),
                new LatLng(43.224355, -71.530525), new LatLng(43.224537, -71.530598)));
        /*final Polygon parkingP = campusMap.addPolygon((new PolygonOptions()).clickable(true).add(new LatLng(43.224024, -71.529889), new LatLng(43.226076, -71.528983), new LatLng(43.225661, -71.527812),
                new LatLng(43.223710, -71.528606)));*/
        final Polygon macruryP = campusMap.addPolygon((new PolygonOptions()).clickable(true).add(new LatLng(43.223799, -71.531864), new LatLng(43.223528, -71.531793), new LatLng(43.223512, -71.531895),
                new LatLng(43.223451, -71.531879), new LatLng(43.223456, -71.531843),new LatLng(43.223246, -71.531780),new LatLng(43.223138, -71.532447),new LatLng(43.223630, -71.532602),new LatLng(43.223683, -71.532247),new LatLng(43.223732, -71.532263),new LatLng(43.223732, -71.532263)));
       //TODO Add a polyline border to quadP; Add Polylines for parking spots
        final Polygon quadP = campusMap.addPolygon((new PolygonOptions()).strokeWidth(0).clickable(false).add(new LatLng(43.224288, -71.531960), new LatLng(43.223613, -71.531742), new LatLng(43.223790, -71.530734),
                new LatLng(43.224315, -71.530928), new LatLng(43.224416, -71.531188)));
        final Polygon grapponeP = campusMap.addPolygon((new PolygonOptions()).clickable(true).add(new LatLng(43.222558, -71.532637), new LatLng(43.222958, -71.532740), new LatLng(43.223106, -71.533044),
                new LatLng(43.222950, -71.533173), new LatLng(43.222869, -71.533009), new LatLng(43.222510, -71.532915)));
        final Polygon policeP = campusMap.addPolygon((new PolygonOptions()).clickable(true).add(new LatLng(43.225978, -71.533206), new LatLng(43.225888, -71.533236), new LatLng(43.225788, -71.532601),
                new LatLng(43.225849, -71.532583), new LatLng(43.225795, -71.532230), new LatLng(43.226000, -71.532166), new LatLng(43.225962, -71.531920), new LatLng(43.225894, -71.531940), new LatLng(43.225846, -71.531653), new LatLng(43.225581, -71.531731), new LatLng(43.225652, -71.532185), new LatLng(43.225577, -71.532208), new LatLng(43.225681, -71.532851),
                new LatLng(43.225721, -71.532839), new LatLng(43.225790, -71.533264), new LatLng(43.225710, -71.533289), new LatLng(43.225812, -71.533917), new LatLng(43.226083, -71.533834), new LatLng(43.225981, -71.533206)));
        final Polygon officeP = campusMap.addPolygon((new PolygonOptions()).add(new LatLng(43.2237179, -71.5308395), new LatLng(43.2233620, -71.5307305), new LatLng(43.2233339, -71.5309327), new LatLng(43.2236876, -71.5310379)));
        final Polygon campusSecurityP = campusMap.addPolygon((new PolygonOptions().add(new LatLng(43.222940, -71.530245), new LatLng(43.222799, -71.530212), new LatLng(43.222788, -71.530281), new LatLng(43.222727, -71.530270), new LatLng(43.222697, -71.530482), new LatLng(43.222902, -71.530534)).clickable(true)));
        final Polygon cafeteriaP = campusMap.addPolygon((new PolygonOptions().clickable(true).add(new LatLng(43.2227062, -71.5315197), new LatLng(43.2228049, -71.5311177), new LatLng(43.2227714, -71.5311006), new LatLng(43.2227903, -71.5309712), new LatLng(43.2226918, -71.5309427), new LatLng(43.2226803, -71.5309913), new LatLng(43.2226332, -71.5309783),
                new LatLng(43.2226366, -71.5309347), new LatLng(43.2225809, -71.5309186), new LatLng(43.2225501, -71.5311261), new LatLng(43.2225227, -71.5311224), new LatLng(43.2224902, -71.5313146), new LatLng(43.2224788, -71.5313581))));
        final Polygon northHallP =campusMap.addPolygon((new PolygonOptions().clickable(true).add(new LatLng(43.225774, -71.531229), new LatLng(43.225872, -71.531317), new LatLng(43.226096, -71.530841), new LatLng(43.226424, -71.531124), new LatLng(43.226484, -71.531000), new LatLng(43.226197, -71.530746),  new LatLng(43.226521, -71.530050),  new LatLng(43.226415, -71.529960), new LatLng(43.226122, -71.530594),
                new LatLng(43.226083, -71.530563))));
        final Polygon southHallP = campusMap.addPolygon((new PolygonOptions().add(new LatLng(43.221033, -71.532699), new LatLng(43.221032, -71.532549), new LatLng(43.220502, -71.532554), new LatLng(43.220505, -71.532704))));


        //Tagging with marker
        whole.setStrokeWidth(5);
        whole.setStrokeColor(Color.argb(0, 255, 255, 255));
        littleHallP.setTag(mLittleHall);
        mcauliffeP.setTag(mMcauliffe);
        farnumP.setTag(mFarnum);
        libraryP.setTag(mLibrary);
        //parkingP.setTag(mParking);
        macruryP.setTag(mMacrury);
        grapponeP.setTag(mGrappone);
        sweeneyP.setTag(mSweeney);
        policeP.setTag(mPolice);
        cafeteriaP.setTag(mCafeteria);
        officeP.setTag(mOffice);
        campusSecurityP.setTag(mCampusSafety);
        southHallP.setTag(mSouth);


        //Customizing Polygon Color
        mcauliffeP.setStrokeColor(Color.BLACK);
        mcauliffeP.setFillColor(Color.rgb(78, 9, 9));
        littleHallP.setStrokeColor(Color.BLACK);
        littleHallP.setFillColor(Color.rgb(78,9,9));
        sweeneyP.setStrokeColor(Color.BLACK);
        sweeneyP.setFillColor(Color.rgb(78,9,9));
        farnumP.setStrokeColor(Color.BLACK);
        farnumP.setFillColor(Color.rgb(78,9,9));
        libraryP.setFillColor(Color.rgb(78,9,9));
        macruryP.setFillColor(Color.rgb(78,9,9));
        grapponeP.setFillColor(Color.rgb(78,9,9));
        campusSecurityP.setFillColor(Color.rgb(78,9,9));
        policeP.setFillColor(Color.rgb(78,9,9));
        cafeteriaP.setFillColor(Color.rgb(78,9,9));
        quadP.setFillColor(Color.rgb(14,123,46));
        officeP.setFillColor(Color.rgb(78,9,9));
        northHallP.setFillColor(Color.rgb(78,9,9));
        southHallP.setFillColor(Color.rgb(78,9,9));

        //parkingP.setStrokeColor(Color.WHITE);
        //parkingP.setFillColor(Color.WHITE);*/

        viewPager = (ViewPager) findViewById(R.id.bottom_sheet);



        bottomMenu = BottomSheetBehavior.from(viewPager);
        bottomMenu.setPeekHeight(100);
        bottomMenu.setHideable(false);


        viewPager.setAdapter(new ViewAdapter(getSupportFragmentManager(), 1));
        //viewPager.setCurrentItem(8);
        //viewPager.beginFakeDrag();



        viewPager.addOnAdapterChangeListener(new ViewPager.OnAdapterChangeListener() {
            @Override
            public void onAdapterChanged(@NonNull ViewPager viewPager, @Nullable PagerAdapter oldAdapter, @Nullable PagerAdapter newAdapter) {

                newAdapter.notifyDataSetChanged();
            }
        });


        campusMap.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() {
            @Override
            public void onPolygonClick(Polygon polygon) {
                Marker marker = (Marker) polygon.getTag();
                polygonClick(marker);
                //setbottomSheetFragment(marker.getTitle());
                marker.setVisible(true);
                setAdapter(marker.getTitle());
            }
        });

        campusMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nhti.getCenter(), 15f));
        campusMap.setLatLngBoundsForCameraTarget(nhti);
        campusMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                //setbottomSheetFragment("Events");
                setAdapter("Events");

                for (Marker p: parkingP){
                    p.setVisible(false);
                }
                for (Marker m : searchableP){
                    m.setVisible(false);
                }

            }
        });

        updateLocationUI();




    }

    private void setAdapter(String marker) {

        //String adapter = marker.getTitle();

        switch (marker){

            case "Sweeney":
                viewPager.setAdapter(new SweeneyAdapter(getSupportFragmentManager(), 2));
                break;
            case "Farnum":
                viewPager.setAdapter(new FarnumAdapter(getSupportFragmentManager(), 2));
                break;
            case "Little Hall":
                viewPager.setAdapter(new LittleHallAdapter(getSupportFragmentManager(), 2));
                break;
            case "Grappone":
                viewPager.setAdapter(new GrapponeAdapter(getSupportFragmentManager(), 1));
                break;
            case "Mcauliffe":
                viewPager.setAdapter(new McauliffeAdapter(getSupportFragmentManager(), 1));
                break;
            case "Macrury":
                viewPager.setAdapter(new MacruryAdapter(getSupportFragmentManager(), 2));
                break;
            case "Library":
                viewPager.setAdapter(new LibraryAdapter(getSupportFragmentManager(), 2));
                break;
            case "Directory":
                viewPager.setAdapter(new DirectoryAdapter(getSupportFragmentManager(), 1));
                campusMap.animateCamera(CameraUpdateFactory.newLatLngZoom(nhti.getCenter(), 15f));
                for (Marker m : searchableP){
                    m.setVisible(true);
                }
                break;
            default:
                viewPager.setAdapter(new ViewAdapter(getSupportFragmentManager(), 1));
                //viewPager.setCurrentItem(8);
                break;

        }
    }

    public void setbottomSheetFragment(String name){


        switch(name) {

            case "Sweeney":
                viewPager.setCurrentItem(0);
                break;
            case "Farnum":
                viewPager.setCurrentItem(1);
                break;
            case "Little Hall":
                viewPager.setCurrentItem(2);
                break;
            case "Mcauliffe":
                viewPager.setCurrentItem(4);
                break;
            case "Library":
                viewPager.setCurrentItem(3);
                break;
            case "Macrury":
                viewPager.setCurrentItem(7);
                break;
            case "Grappone":
                viewPager.setCurrentItem(6);
                break;
            case "Directory":
                viewPager.setCurrentItem(5);
                for (Marker m : searchableP){
                    m.setVisible(true);
                }
                campusMap.animateCamera(CameraUpdateFactory.newLatLngZoom(nhti.getCenter(), 15f));
                break;
            default:
                viewPager.setCurrentItem(8);
                break;

        }

    }

    public void navDrawerClick(MenuItem item){


        String menu = item.getTitle().toString();
        switch (menu){
            case "Directory":
                setAdapter(menu);
                break;
            case "Parking":
                 for (Marker park: parkingP){
                     park.setVisible(true);
                 }
                 bottomMenu.setState(BottomSheetBehavior.STATE_COLLAPSED);
                 break;
            case "Events":
                setAdapter(menu);
                break;
            case "Settings":
                if(campusMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL){
                    campusMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }
                else
                    campusMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case "Exit":
                finish();
                //finishAndRemoveTask();
            default:
                break;
        }
        item.setCheckable(false);
        drawer.closeDrawer(navView);
    }




    private void moveCamera(String building){

        campusMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mcauliffeM, 30));

    }


    public <T> void polygonClick(T name) {

       // Marker marker = (Marker) name;

        for (Marker m: searchableP) {
            if (m.getTitle().equals(name)) {
                campusMap.animateCamera(CameraUpdateFactory.newLatLngZoom(m.getPosition(), 20f));
                m.showInfoWindow();
                setAdapter((String) name);
                return;
            }
        }

       // campusMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 19));
       // marker.showInfoWindow();



    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
        updateLocationUI();
    }

    private void updateLocationUI() {
        if (campusMap == null) {
            return;
        }

    /*
     * Request location permission, so that we can get the location of the
     * device. The result of the permission request is handled by a callback,
     * onRequestPermissionsResult.
     */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
            campusMap.setMyLocationEnabled(true);
            campusMap.getUiSettings().setMyLocationButtonEnabled(true);
        } else if (!mLocationPermissionGranted) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        } else {
            campusMap.setMyLocationEnabled(false);
            campusMap.getUiSettings().setMyLocationButtonEnabled(false);
            mLastKnownLocation = null;
        }
    }


   /* private void getDeviceLocation() {
    *//*
     * Before getting the device location, you must check location
     * permission, as described earlier in the tutorial. Then:
     * Get the best and most recent location of the device, which may be
     * null in rare cases when a location is not available.
     *//*
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            mLastKnownLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        }

        // Set the map's camera position to the current location of the device.
        if (mCameraPosition != null) {
            campusMap.moveCamera(CameraUpdateFactory.newCameraPosition(mCameraPosition));
        } else if (mLastKnownLocation != null) {
            campusMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(mLastKnownLocation.getLatitude(),
                            mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
        } else {
            Log.d(TAG, "Current location is null. Using defaults.");
            campusMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
            campusMap.getUiSettings().setMyLocationButtonEnabled(false);
        }
    }*/


    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void search(CharSequence search) {
        list = new ArrayList();
        final ArrayList<String> searchPicks = new ArrayList<>();
        for (Marker m : searchableP ) {
            if (m.getTitle().toLowerCase().contains(search.toString().toLowerCase())) {
                list.add(m);
                searchPicks.add(m.getTitle());
            }
        }

        String empty = "no results found";

        ArrayAdapter listAdapter = new ArrayAdapter<>(this, R.layout.listview, searchPicks);

        AlertDialog.Builder searchDialog = new AlertDialog.Builder(this);
        /*searchDialog.setSingleChoiceItems(listAdapter, -1, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                polygonClick(list.get(which));
            }
        });*/

        searchDialog.setTitle(String.format(list.isEmpty() ?  empty : "%s? did you mean...", search))
        .setNegativeButton("back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                list.clear();
            }
        })
                .setAdapter(listAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(!searchPicks.isEmpty()) {
                            list.get(which).setVisible(true);
                            polygonClick(list.get(which).getTitle());
                            //setbottomSheetFragment(list.get(which).getTitle());
                        }
                        list.clear();
                    }
                })
        .show();



    }

    @Override
    public void polygonMove(String name) {

        polygonClick(name);
    }


    public void slideMenu(View view) {

        boolean drawerState = drawer.isDrawerOpen(navView);

        if(drawerState){
            drawer.closeDrawer(navView);
        }
        else
            drawer.openDrawer(navView, true);
    }



}









//johnny