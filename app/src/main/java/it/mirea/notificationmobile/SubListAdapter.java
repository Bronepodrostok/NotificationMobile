package it.mirea.notificationmobile;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


public class SubListAdapter extends ArrayAdapter<Subscription> {

    private static final String TAG = "PersonListAdapter";

    private Context mContext;
    int mResource;
    DatabaseHelper mDatabaseHelper;
    ListView mListView;

    public SubListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Subscription> objects, ListView lv) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        mListView = lv;
        mDatabaseHelper = new DatabaseHelper(this.getContext());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent,false);

        TextView nameTv = (TextView) convertView.findViewById(R.id.nameView);
        TextView infoTv = (TextView) convertView.findViewById(R.id.infoView);
        TextView dateTv = (TextView) convertView.findViewById(R.id.dateView);
        TextView monthTv = (TextView) convertView.findViewById(R.id.monthView);
        ImageButton button = (ImageButton) convertView.findViewById(R.id.removeButton);

        nameTv.setText(getItem(position).getName());
        String description = getItem(position).getDescription();
        if (description.equals("")) {
            infoTv.setVisibility(View.GONE);
        } else {
            infoTv.setText(getItem(position).getDescription());
        }
        monthTv.setText(String.valueOf(getItem(position).getMonth()));
        dateTv.setText(String.valueOf(getItem(position).getDay()).concat("."));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"Deleting on pos" + getItem(position).getID());
                mDatabaseHelper.deleteFromDb(getItem(position).getID());
                Log.d("SubListAdapter", "populateListView: dispalying data in listView");

                Cursor data = mDatabaseHelper.getData();
                ArrayList<Subscription> list = new ArrayList<>();
                while (data.moveToNext()) {
                    Subscription sub = new Subscription(data.getInt(0), data.getString(1), data.getString(2), data.getInt(3), data.getInt(4));
                    list.add(sub);
                }
                SubListAdapter adapter = new SubListAdapter(getContext(), R.layout.list_item, list, mListView);
                mListView.setAdapter(adapter);
            }
        });

        return convertView;
    }
}
