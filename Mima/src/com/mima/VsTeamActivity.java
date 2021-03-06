package com.mima;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class VsTeamActivity extends Activity {
	private Context context;
	private WordAPIGetter wordGetter = new WordAPIGetter();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vsteam);
		context = this;
		
		if (!isNetworkConnected()) {
			Log.d("Debug","Hello");
			Toast.makeText(context, "Activate your internet data and try again", Toast.LENGTH_SHORT).show();
			((Activity) context).finish();
		}
		
		Button buttonPlusUnuTeam1 = (Button) findViewById(R.id.button_plusunu_team1);
		final Button buttonMinusUnuTeam1 = (Button) findViewById(R.id.button_minusunu_team1);
		Button buttonPlusUnuTeam2 = (Button) findViewById(R.id.button_plusunu_team2);
		final Button buttonMinusUnuTeam2 = (Button) findViewById(R.id.button_minusunu_team2);
		Button buttonResetScore = (Button) findViewById(R.id.button_reset_score);
		final TextView team1Score = (TextView) findViewById(R.id.text_view_score_team1);
		final TextView team2Score = (TextView) findViewById(R.id.text_view_score_team2);
		
		//buton de adaugat un punct la echipa 1
		buttonPlusUnuTeam1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				team1Score.setText("" + (Integer.parseInt(team1Score.getText().toString()) + 1));
				if (!buttonMinusUnuTeam1.isEnabled()) { 
					buttonMinusUnuTeam1.setEnabled(true);
				}
			}
		});
		
		//buton de scazut un punct din punctajul echipei 1 - folosit pentru greseli
		buttonMinusUnuTeam1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int score = Integer.parseInt(team1Score.getText().toString()) - 1;
				team1Score.setText("" + score);
				if (score == 0) {
					buttonMinusUnuTeam1.setEnabled(false);
				}
			}
		});
		
		//buton de adaugat un punct la echipa 2
		buttonPlusUnuTeam2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				team2Score.setText("" + (Integer.parseInt(team2Score.getText().toString()) + 1));
				if (!buttonMinusUnuTeam2.isEnabled()) { 
					buttonMinusUnuTeam2.setEnabled(true);
				}
			}
		});
		
		//buton de scazut un punct din punctajul echipei 2 - folosit pentru greseli
		buttonMinusUnuTeam2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int score = Integer.parseInt(team2Score.getText().toString()) - 1;
				team2Score.setText("" + score);
				if (score == 0) {
					buttonMinusUnuTeam2.setEnabled(false);
				}
			}
		});
		
		//reseteaza scorurile
		buttonResetScore.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				team1Score.setText("0");
				team2Score.setText("0");
				buttonMinusUnuTeam1.setEnabled(false);
				buttonMinusUnuTeam2.setEnabled(false);
			}
		});
		
		final TextView team1Label = (TextView) findViewById(R.id.text_view_team1);
		final TextView team2Label = (TextView) findViewById(R.id.text_view_team2);
		
		setLongClickListeners(team1Label);
		setLongClickListeners(team2Label);
		
		//get the word for mima
		getWord();
		
		//set reset word button
		ImageButton resetWord = (ImageButton) findViewById(R.id.image_button_new_word);
		resetWord.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getWord();
			}
		});
	}
	
	private void getWord() {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				final TextView mimicWord = (TextView) findViewById(R.id.text_view_word);
				try {
					final String word = wordGetter.getWord();
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							mimicWord.setText(word);
						}
					});

				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}

	private void setLongClickListeners(TextView textView) {
		final TextView aux = textView;
		textView.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				AlertDialog.Builder dialog = new AlertDialog.Builder(context);
				dialog.setMessage("Numele echipei:");
				final EditText input = new EditText(context);
				dialog.setView(input);
				
				dialog.setPositiveButton("Set", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						aux.setText(input.getText());
					}
				});
				
				dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
				
				dialog.create().show();
				return false;
			}
		});
	}
	
	private boolean isNetworkConnected() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		return (cm.getActiveNetworkInfo() != null);
	}
}
