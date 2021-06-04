package sg.edu.rp.c346.id20022098.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner chooseSpin;
    EditText etTask;
    Button btnAdd, btnDelete, btnClear;
    ListView lvTask;
    ArrayList<String> Tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chooseSpin = findViewById(R.id.spinner);
        etTask = findViewById(R.id.editTextTask);
        btnAdd = findViewById(R.id.buttonAddTask);
        btnDelete = findViewById(R.id.buttonDeleteTask);
        btnClear = findViewById(R.id.buttonClearTask);
        lvTask = findViewById(R.id.listView);

        Tasks = new ArrayList<String>();

        ArrayAdapter aTask = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Tasks);
        lvTask.setAdapter(aTask);


        chooseSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        etTask.setHint("Type in a new task here!");
                        break;

                    case 1:
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        etTask.setHint("Type in the index of the task to be removed!");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTask = etTask.getText().toString();
                Tasks.add(newTask);
                aTask.notifyDataSetChanged();
                etTask.setText("");
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (Tasks.size() == 0) {
                        Toast.makeText(MainActivity.this, "You don't have any tasks to remove!", Toast.LENGTH_SHORT).show();
                        return;
                    }else{
                        int pos = Integer.parseInt(etTask.getText().toString());
                        if (Tasks.size() <= pos) {
                            Toast.makeText(MainActivity.this, "Wrong Index Number!", Toast.LENGTH_SHORT).show();
                            return;
                        }else{
                            Tasks.remove(pos);
                            aTask.notifyDataSetChanged();
                            etTask.setText("");
                    }
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tasks.clear();
                aTask.notifyDataSetChanged();
            }
        });
    }
}