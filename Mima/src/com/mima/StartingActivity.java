package com.mima;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class StartingActivity extends Activity {
	private ImageButton allTogether;
	private ImageButton vsTeam;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_starting);
		
		allTogether = (ImageButton) findViewById(R.id.image_button_all_together);
		vsTeam = (ImageButton) findViewById(R.id.image_button_team_vs_team);
		
		allTogether.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				//start activity for all together
			}
		});

		vsTeam.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//start activity for team vs team
				Intent i = new Intent(getApplicationContext(), VsTeamActivity.class);
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.starting, menu);
		return true;
	}

}
