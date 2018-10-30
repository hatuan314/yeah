package vn.com.kma.hatuan314.yeah;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

import vn.com.kma.hatuan314.Adapter.ThematicAdapter;
import vn.com.kma.hatuan314.model.Thematic;

public class ThematicActivity extends AppCompatActivity {
    RecyclerView rcThematicList;
    ArrayList<Thematic> thematicList;
    ThematicAdapter thematicAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_thematic);
        addControls();
    }

    private void addControls() {
        rcThematicList = findViewById(R.id.rcThematicList);
        thematicList = new ArrayList<>();
        thematicList.add(new Thematic(1,1,"Khảo sát hàm số"));
        thematicList.add(new Thematic(2,1,"Hàm số mũ - Logarit"));
        thematicList.add(new Thematic(3,1,"Nguyên hàm - Tích phân"));
        thematicList.add(new Thematic(4,1,"Số phức"));
        thematicList.add(new Thematic(5,1,"Khối đa diện"));
        thematicList.add(new Thematic(6,1,"Khối xoay tròn"));
        thematicList.add(new Thematic(7,1,"Lượng giác"));
        thematicList.add(new Thematic(8,1,"Tổ hợp xác suất"));
        thematicList.add(new Thematic(9,1,"Cấp số cộng, số nhân và dãy số"));
        thematicList.add(new Thematic(10,1,"Dãy số"));
        thematicList.add(new Thematic(11,1,"Hình học không gian"));
        thematicList.add(new Thematic(12,1,"Hình Oxyz"));

        thematicAdapter = new ThematicAdapter(getApplicationContext(),thematicList);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        rcThematicList.setLayoutManager(manager);
        rcThematicList.setAdapter(thematicAdapter);
    }
}
