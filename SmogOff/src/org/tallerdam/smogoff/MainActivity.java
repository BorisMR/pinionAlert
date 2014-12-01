package org.tallerdam.smogoff;

import java.util.Arrays;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;

public class MainActivity extends FragmentActivity implements
		OnMapClickListener {

	private final LatLng TEMUCO_LATLNG = new LatLng(-38.73941847, -72.60219722);
	private GoogleMap mapa;
	private int opcVistaGmaps = 0;
	private int opcVistaMacro = 0;
	private int opcVistaVerdes = 0;
	private int opcVistaCiclo = 0;
	
//	Button botonClima = (Button)findViewById(R.id.btnClima);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mapa = ((SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map)).getMap();
		
		mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(TEMUCO_LATLNG, 13));
		mapa.setMyLocationEnabled(true);
		mapa.getUiSettings().setZoomControlsEnabled(true);
		mapa.getUiSettings().setCompassEnabled(true);	
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId())
		{
			case R.id.menu_vista:
				alternarVista();
				break;
			case R.id.menu_centrar:
				CameraUpdate camUpd2 = 
					CameraUpdateFactory.newLatLngZoom(TEMUCO_LATLNG, 13);
				mapa.animateCamera(camUpd2);
				break;
			case R.id.menu_macro:
				alternarMacrozonas();
				break;
			case R.id.menu_verdes:
				alternarAreasVerdes();
				break;
			case R.id.menu_ciclobias:
				alternarCiclobias();
				break;
			case R.id.menu_cerrar:
				super.finish();
				break;
		}
		
		return super.onOptionsItemSelected(item);	
    }
	
	
	private void alternarVista()
	{
		opcVistaGmaps = (opcVistaGmaps + 1);
		
		if(opcVistaGmaps==3){
			opcVistaGmaps=1;
		}
		
		switch(opcVistaGmaps)
		{
			case 1:
				mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
				Toast.makeText(MainActivity.this,"Vista NORMAL",Toast.LENGTH_LONG).show();
				break;
			case 2:
				mapa.setMapType(GoogleMap.MAP_TYPE_HYBRID);
				Toast.makeText(MainActivity.this,"Vista HIBRIDA",Toast.LENGTH_LONG).show();
				break;
		}
	}
	
	private void alternarMacrozonas()
	{
		opcVistaMacro = (opcVistaMacro + 1);
		
		if(opcVistaMacro==3){
			opcVistaMacro=1;
		}
		
		switch(opcVistaMacro)
		{
			case 1:	
			
			List<LatLng> points =Arrays.asList(
				new LatLng(-38.75031820000001,-72.6153374), 
				new LatLng(-38.7489125,-72.6135564),
				new LatLng(-38.7483937,-72.6131916),
				new LatLng(-38.7464357,-72.6112175),
				new LatLng(-38.745950300000004,-72.6130843),
				new LatLng(-38.7459336,-72.6136208),
				new LatLng(-38.7452642,-72.6161313),
				new LatLng(-38.745398099999996, -72.6169038),
				new LatLng(-38.7485778,-72.6181913),
				new LatLng(-38.75031820000001,-72.6153374));
			
				PolygonOptions options = new PolygonOptions();
	
				options.addAll(points);
				options.fillColor(Color.RED); // 50% opacity red, for example
				options.strokeWidth(5);
				options.strokeColor(Color.RED);
				mapa.addPolygon(options);
				options.visible(true);
				
			
				Toast.makeText(MainActivity.this,"Macrozonas Agregadas",Toast.LENGTH_LONG).show();
				break;
			case 2:
				Toast.makeText(MainActivity.this,"Limpiando",Toast.LENGTH_LONG).show();
				break;
		}
	}
	
	private void alternarAreasVerdes()
	{
		opcVistaVerdes = (opcVistaVerdes + 1);
		
		if(opcVistaVerdes==3){
			opcVistaVerdes=1;
		}
		
		switch(opcVistaVerdes)
		{
			case 1:	
			
				mapa.addMarker(new MarkerOptions()
					.position(TEMUCO_LATLNG)
					.title("Zona Verde")
					.snippet("Universidad De La Frontera")
					.icon(BitmapDescriptorFactory
					.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)))
					.setAnchor(0.0f, 1.0f);
				
				mapa.setOnMapClickListener(this);
				
				break;
			case 2:
				mapa.clear();
				break;
		}
	}
	
	private void alternarCiclobias()
	{
		opcVistaCiclo = (opcVistaCiclo + 1);
		
		if(opcVistaCiclo==3){
			opcVistaCiclo=1;
		}
		
		switch(opcVistaCiclo)
		{
			case 1:
				PolylineOptions lineas = new PolylineOptions()
		        .add(new LatLng(-38.7541837,-72.6196718))
		        .add(new LatLng(-38.7538825,-72.6143074))
		        .add(new LatLng(-38.7535478,-72.6115179))
		        .visible(true);
				lineas.width(12);
				lineas.color(Color.BLUE);
				mapa.addPolyline(lineas);
				
				PolylineOptions lineas2 = new PolylineOptions()
		        .add(new LatLng(-38.7534809,-72.6057673))
		        .add(new LatLng(-38.7529119,-72.6042223))
		        .add(new LatLng(-38.7513389,-72.6031494))
		        .add(new LatLng(-38.7487618,-72.6026344))
		        .add(new LatLng(-38.7466867,-72.5996733))
		        .add(new LatLng(-38.745783,-72.592721))
		        .visible(true);
				lineas2.width(12);
				lineas2.color(Color.BLUE);
				mapa.addPolyline(lineas2);
				
				Toast.makeText(MainActivity.this,"Vista Ciclovias",Toast.LENGTH_LONG).show();
				break;
			case 2:
				mapa.clear();
				Toast.makeText(MainActivity.this,"Cerrando Vista Ciclovias",Toast.LENGTH_LONG).show();
				break;
		}
	}
	

	public void moveCamera(View view) {
		mapa.moveCamera(CameraUpdateFactory.newLatLng(TEMUCO_LATLNG));
	}

	public void animateCamera(View view) {
		if (mapa.getMyLocation() != null)
			mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
					mapa.getMyLocation().getLatitude(), mapa.getMyLocation()
							.getLongitude()), 13));
	}

	public void addMarker(View view) {
		mapa.addMarker(new MarkerOptions().position(new LatLng(mapa
				.getCameraPosition().target.latitude,
				mapa.getCameraPosition().target.longitude)));
	}

	@Override
	public void onMapClick(LatLng puntoPulsado) {
		mapa.addMarker(new MarkerOptions().position(puntoPulsado).icon(
				BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
	}

}