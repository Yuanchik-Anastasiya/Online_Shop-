package ru.yuanchik.example.onlineshop.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import ru.yuanchik.example.onlineshop.R;
import ru.yuanchik.example.onlineshop.adapter.PopularAdapter;
import ru.yuanchik.example.onlineshop.databinding.ActivityMainBinding;
import ru.yuanchik.example.onlineshop.domain.PopularDomain;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getWindow().setDecorFitsSystemWindows(false);

            WindowInsetsController controller = getWindow().getInsetsController();
            if (controller != null) {
                controller.hide(WindowInsets.Type.navigationBars());
                controller.setSystemBarsBehavior(
                        WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                );
            }
        }

        statusBarColor();
        initRecyclerView();
    }

    private void statusBarColor() {
        Window window = MainActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.purple_Dark));
    }

    private void initRecyclerView() {
        ArrayList<PopularDomain> items = new ArrayList<>();
        items.add(new PopularDomain("T-shirt black","item_1",15,4,500,"Immerse yourself in a world of vibrant visuals and immersive sound with the monitor.\n" +
                "Its cutting-edge monitor technology brings every scene to life with striking clarity and rich colors.\n" +
                "With seamless integration and a sleek, modern design, the monitor Pro is not just a monitor, but a centerpiece for your entertainment space."));
        items.add(new PopularDomain("Smart Watch","item_2",10,4.5,450,"Immerse yourself in a world of vibrant visuals and immersive sound with the monitor.\n" +
                "Its cutting-edge monitor technology brings every scene to life with striking clarity and rich colors.\n" +
                "With seamless integration and a sleek, modern design, the monitor Pro is not just a monitor, but a centerpiece for your entertainment space."));
        items.add(new PopularDomain("Phone","item_3",3,4.9,800,"Immerse yourself in a world of vibrant visuals and immersive sound with the monitor.\n" +
                "Its cutting-edge monitor technology brings every scene to life with striking clarity and rich colors.\n" +
                "With seamless integration and a sleek, modern design, the monitor Pro is not just a monitor, but a centerpiece for your entertainment space."));

        binding.PopularView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        binding.PopularView.setAdapter(new PopularAdapter(items));
    }
}