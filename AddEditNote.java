package com.example.mesnotes;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class AddEditNote extends Activity {
	EditText et1, et2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_edit_note);

		et1 = (EditText) findViewById(R.id.editText1);
		et2 = (EditText) findViewById(R.id.editText2);
		if (Accueil.SELECTED != null) {
			et1.setText(Accueil.SELECTED.Title);
			et2.setText(Accueil.SELECTED.Note);
		}
		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (verif()) {
					if (Accueil.SELECTED != null) {
						NoteEntity data = new NoteEntity(Accueil.SELECTED.Id,
								et1.getText().toString(), et2.getText()
										.toString());
						new DBAdapter(AddEditNote.this).updateNote(data);
					} else {
						NoteEntity data = new NoteEntity(0, et1.getText()
								.toString(), et2.getText().toString());
						new DBAdapter(AddEditNote.this).addNote(data);
					}
					finish();
				}
			}
		});
	}

	private boolean verif() {
		boolean x = true;
		if (et1.getText().toString().length() == 0) {
			et1.setError("Saisir un titre!");
			x = false;
		}
		if (et2.getText().toString().length() == 0) {
			et2.setError("Saisir le note!");
			x = false;
		}
		return x;
	}
}
