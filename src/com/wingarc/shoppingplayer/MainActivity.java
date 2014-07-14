package com.wingarc.shoppingplayer;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {

	private MyFrame frame;
	private MovieView videoView;
	static String[] menuItems = { "木村拓哉さん使用アレキサンダーマックイーン ストール",
			"モンブランマイスターシュテュックボールペン モーツアルト" };
	static ArrayAdapter<String> adapter;

	TextView textView1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		frame = (MyFrame) findViewById(R.id.commodity_frame);
		videoView = (MovieView) findViewById(R.id.videoView);
		videoView.setVideoURI(Uri.parse("android.resource://"
				+ getPackageName() + "/" + R.raw.green));
		videoView.setMediaController(new MediaController(this));

		videoView.setOnPreparedListener(new OnPreparedListener() {

			@Override
			public void onPrepared(MediaPlayer mp) {
				videoView.ready();
			}
		});

		videoView.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				videoView.timerStop();
			}
		});

		
		videoView.setFrame(frame);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

//		MenuItem item = menu.add(Menu.NONE, MENUID_TIPS, Menu.NONE, "TIPS");
//		item.setIcon(android.R.drawable.ic_menu_info_details);
//		MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
		
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			showTipsDialog();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
	private void showTipsDialog() {
		WebView webview = new WebView(this);
//		webview.loadUrl("http://www.google.co.jp");
		webview.loadUrl("file:///android_asset/list/index.html");
		
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("購入リスト");
		dialog.setView(webview);
		dialog.setPositiveButton("OK", null);
		dialog.show();
	}
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
