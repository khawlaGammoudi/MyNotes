package com.example.mesnotes;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NoteAdapter extends BaseAdapter {
	List<NoteEntity> data;
	Activity context;

	public NoteAdapter(List<NoteEntity> data, Activity context) {
		this.data = data;
		this.context = context;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		return data.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return data.get(arg0).Id;
	}

	@Override
	public View getView(final int arg0, View arg1, ViewGroup arg2) {
		arg1 = context.getLayoutInflater().inflate(R.layout.row_note, null);
		TextView tv1 = (TextView) arg1.findViewById(R.id.textView1);
		tv1.setText(((NoteEntity) getItem(arg0)).Note);
		TextView tv2 = (TextView) arg1.findViewById(R.id.textView2);
		tv2.setText(((NoteEntity) getItem(arg0)).Title);
		arg1.findViewById(R.id.button1).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View arg3) {
						new AlertDialog.Builder(context)
								.setIcon(android.R.drawable.ic_dialog_alert)
								.setTitle("Suppression")
								.setMessage(
										"Etes vous sure de supprimé la note "
												+ ((NoteEntity) getItem(arg0)).Title
												+ " ?")
								.setPositiveButton("Oui",
										new DialogInterface.OnClickListener() {
											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												new DBAdapter(context)
														.deleteNote((NoteEntity) getItem(arg0));
												data.remove(arg0);
												notifyDataSetChanged();
											}

										}).setNegativeButton("Non", null)
								.show();
					}
				});
		arg1.findViewById(R.id.button2).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View arg3) {
						Intent intent = new Intent(
								android.content.Intent.ACTION_SEND);
						intent.setType("text/plain");
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
						intent.putExtra(Intent.EXTRA_SUBJECT, "Ma note: "
								+ ((NoteEntity) getItem(arg0)).Title);
						intent.putExtra(Intent.EXTRA_TEXT,
								((NoteEntity) getItem(arg0)).Note);
						context.startActivity(intent);
					}
				});

		return arg1;
	}

}
