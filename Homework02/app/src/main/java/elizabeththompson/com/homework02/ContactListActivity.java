package elizabeththompson.com.homework02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ContactListActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        listView = (ListView) findViewById(R.id.contactListView);

        final ContactListItem[] items = new ContactListItem[40];

        for (int i = 0; i < items.length; i++) {
            items[i] = new ContactListItem("John", "Doe", "(000) 000-0000");
        }

        CustomAdapter customAdapter = new CustomAdapter(this, R.id.firstName, items);
        listView.setAdapter(customAdapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
//                Toast.makeText(getBaseContext(), items[i].getText(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
