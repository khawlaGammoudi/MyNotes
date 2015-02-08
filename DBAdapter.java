package com.example.mesnotes;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "NTDB";
	private static final String TABLE_NOTE = "note";
	private static final String KEY_ID = "id";
	private static final String KEY_TITLE = "title";
	private static final String KEY_NOTE = "note";

	public DBAdapter(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		String CREATE_NOTIF_TABLE = "CREATE TABLE " + TABLE_NOTE + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT, " + KEY_NOTE + " TEXT)";
		arg0.execSQL(CREATE_NOTIF_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		arg0.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
		onCreate(arg0);
	}

	public void addNote(NoteEntity _note) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TITLE, _note.Title);
		values.put(KEY_NOTE, _note.Note);
		db.insert(TABLE_NOTE, null, values);
		db.close();
	}
	public boolean updateNote(NoteEntity _note) {
		SQLiteDatabase db = this.getWritableDatabase();
	    ContentValues args = new ContentValues();
	    args.put(KEY_TITLE, _note.Title);
	    args.put(KEY_NOTE, _note.Note);
	    return db.update(TABLE_NOTE, args, KEY_ID + "=" + _note.Id, null) > 0;
	  }
	public List<NoteEntity> getAllNote() {
		List<NoteEntity> noteList = new ArrayList<NoteEntity>();
		String selectQuery = "SELECT  * FROM " + TABLE_NOTE;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				NoteEntity note = new NoteEntity(
						Integer.parseInt(cursor.getString(0)),
						cursor.getString(1),
						cursor.getString(2));
				noteList.add(note);
			} while (cursor.moveToNext());
		}
		return noteList;
	}

	public void deleteNote(NoteEntity note) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_NOTE, KEY_ID + " = ?",
				new String[] { String.valueOf(note.Id) });
		db.close();
	}
}
