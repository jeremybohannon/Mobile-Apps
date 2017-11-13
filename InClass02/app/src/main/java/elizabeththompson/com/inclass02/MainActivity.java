package elizabeththompson.com.inclass02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void selectShape(View view) {
        TextView selectedShape = (TextView) findViewById(R.id.selectedShape);
        EditText editLength = (EditText) findViewById(R.id.editLength);

        switch(view.getId()) {
            case R.id.triangleImg:
                selectedShape.setText("Triangle");
                editLength.setVisibility(View.VISIBLE);
                break;
            case R.id.squareImg:
                selectedShape.setText("Square");
                editLength.setVisibility(View.INVISIBLE);
                break;
            case R.id.circleImg:
                selectedShape.setText("Circle");
                editLength.setVisibility(View.INVISIBLE);
                break;
            default:
                break;
        }
    }

    public void calculateArea(View view) {
        TextView selectedShape = (TextView) findViewById(R.id.selectedShape);
        String selectedShapeText = selectedShape.getText().toString();

        EditText editWidth = (EditText) findViewById(R.id.editWidth);
        EditText editLength = (EditText) findViewById(R.id.editLength);

        double length1 = Integer.parseInt(editWidth.getText().toString());
        double length2 = Integer.parseInt(editLength.getText().toString());

        TextView editResult = (TextView) findViewById(R.id.result);
        double result = 0;

        switch(selectedShapeText) {
            case "Triangle":
                result = 0.5 * length1 * length2;
                break;
            case "Square":
                result = (length1 * length1);
                break;
            case "Circle":
                result = 3.1416 * length1 * length1;
                break;
            default:
                break;
        }

        editResult.setText("" + result);
        Log.d("debug", "hi");
    }
}
