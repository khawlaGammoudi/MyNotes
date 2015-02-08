package com.example.mesnotes;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Accueil extends Activity {
	public static NoteEntity SELECTED = null;
	ListView list;
	List<NoteEntity> data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accueil);
		list = (ListView) findViewById(R.id.listView1);
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				SELECTED = data.get(arg2);
				startActivity(new Intent(getApplicationContext(),
						AddEditNote.class));
			}
		});

		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				SELECTED = null;
				startActivity(new Intent(getApplicationContext(),
						AddEditNote.class));
			}
		});
	}

	private void loadData() {
		data = new DBAdapter(this).getAllNote();
		list.setAdapter(new NoteAdapter(new DBAdapter(this).getAllNote(), this));
	}

	@Override
	protected void onResume() {
		super.onResume();
		loadData();
	}
}
