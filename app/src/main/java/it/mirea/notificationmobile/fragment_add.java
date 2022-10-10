package it.mirea.notificationmobile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link fragment_add#newInstance} factory method to
// * create an instance of this fragment.
// */
public class fragment_add extends Fragment {


    DatabaseHelper mDatabaseHelper;

    EditText nameTextEdit;
    EditText infoTextEdit;
    EditText dateTextEdit;
    EditText monthTextEdit;
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("fragment_add","created");
        mDatabaseHelper = new DatabaseHelper(this.getContext());
        nameTextEdit = getView().findViewById(R.id.nameTe);
        infoTextEdit = getView().findViewById(R.id.infoTe);
        dateTextEdit = getView().findViewById(R.id.dateTe);
        monthTextEdit = getView().findViewById(R.id.monthTe);
        button = getView().findViewById(R.id.addToDbButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newEntryName = nameTextEdit.getText().toString();
                String newEntryInfo = infoTextEdit.getText().toString();
                String newEntryDate = dateTextEdit.getText().toString();
                String newEntryMonth = monthTextEdit.getText().toString();
                AddData(newEntryName, newEntryInfo, Integer.parseInt(newEntryDate), Integer.parseInt(newEntryMonth));
                nameTextEdit.setText("");
                infoTextEdit.setText("");
                monthTextEdit.setText("");
                dateTextEdit.setText("");
            }
        });
    }


    public void AddData(String name, String description, int day, int month) {
        boolean insertData = mDatabaseHelper.addData(name, description, day, month);

        if (insertData) {
            toastMessage("Data inserted");
        } else {
            toastMessage("Data insert ERR");
        }
    }

    private void toastMessage(String msg) {
        Toast.makeText(this.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}