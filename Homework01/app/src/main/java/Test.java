import android.widget.SeekBar;
import android.widget.TextView;

import elizabeththompson.com.homework01.R;

/**
 * Created by elizabeththompson on 9/25/17.
 */

public class GetPassword implements Runnable {

    int passwordLength;

    public GetPassword(int passwordLength) {
        this.passwordLength = passwordLength;
    }

    @Override
    public void run() {
        Util.getPassword(passwordLength);
    }
}

private class Test {

    final SeekBar countSeekbar = (SeekBar) findViewById(R.id.countSeekbar);
    final TextView passwordCount = (TextView) findViewById(R.id.passwordCount);
    countSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            passwordCount.setText(countSeekbar.getProgress());
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {}
    });


    final SeekBar lengthSeekbar = (SeekBar) findViewById(R.id.lengthSeekbar);
    final TextView passwordLength = (TextView) findViewById(R.id.passwordCount);
    lengthSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            passwordLength.setText(lengthSeekbar.getProgress());
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {}
    });
}
