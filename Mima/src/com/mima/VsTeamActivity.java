package com.mima;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class VsTeamActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vsteam);
		
		Button buttonPlusUnuTeam1 = (Button) findViewById(R.id.button_plusunu_team1);
		final Button buttonMinusUnuTeam1 = (Button) findViewById(R.id.button_minusunu_team1);
		Button buttonPlusUnuTeam2 = (Button) findViewById(R.id.button_plusunu_team2);
		final Button buttonMinusUnuTeam2 = (Button) findViewById(R.id.button_minusunu_team2);
		Button buttonResetScore = (Button) findViewById(R.id.button_reset_score);
		final TextView team1Score = (TextView) findViewById(R.id.text_view_score_team1);
		final TextView team2Score = (TextView) findViewById(R.id.text_view_score_team2);
		
		buttonPlusUnuTeam1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				team1Score.setText(Integer.parseInt(team1Score.getText().toString()) + 1);
				if (buttonMinusUnuTeam1.isEnabled()) { 
					buttonMinusUnuTeam1.setEnabled(true);
				}
			}
		});
		
		buttonMinusUnuTeam1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int score = Integer.parseInt(team1Score.getText().toString()) - 1;
				team1Score.setText(score);
				if (score == 0) {
					buttonMinusUnuTeam1.setEnabled(false);
				}
			}
		});
		
		buttonPlusUnuTeam2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				team2Score.setText(Integer.parseInt(team2Score.getText().toString()) + 1);
				if (buttonMinusUnuTeam2.isEnabled()) { 
					buttonMinusUnuTeam2.setEnabled(true);
				}
			}
		});
		
		buttonMinusUnuTeam2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int score = Integer.parseInt(team2Score.getText().toString()) - 1;
				team2Score.setText(score);
				if (score == 0) {
					buttonMinusUnuTeam2.setEnabled(false);
				}
			}
		});
		
		buttonResetScore.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				team1Score.setText("0");
				team2Score.setText("0");
			}
		});
	}

}
