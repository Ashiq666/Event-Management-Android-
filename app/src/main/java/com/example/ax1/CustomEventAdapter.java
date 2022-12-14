package com.example.ax1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CustomEventAdapter extends ArrayAdapter<Event> {

    private final Context context;
    private final ArrayList<Event> values;

    public CustomEventAdapter(@NonNull Context context, @NonNull ArrayList<Event> items) {
        super(context, -1, items);
        this.context = context;
        this.values = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.layout_event_row, parent, false);

        TextView eventName = rowView.findViewById(R.id.tvEventName);
        TextView eventPlaceName = rowView.findViewById(R.id.tvEventPlaceName);
        TextView eventCapacity = rowView.findViewById(R.id.tvEventBudget);
        //TextView eventType = rowView.findViewById(R.id.tvEventType);

        Event e = values.get(position);
        eventName.setText(e.name);
        eventPlaceName.setText(e.place);
       eventCapacity.setText("Budget " + values.get(position).capacity);
        //eventType.setText(e.eventType);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, new_event.class);
                i.putExtra("key", values.get(position).key);
                context.startActivity(i);
            }
        });

        return rowView;

    }
}
