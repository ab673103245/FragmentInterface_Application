package qianfeng.fragmentinterface_application;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements LeftFragment.UpdateRightTv{

    private RightFragment right_frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 动态加载
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.left_layout,new LeftFragment());
        right_frame = new RightFragment();
        fragmentTransaction.replace(R.id.right_layout, right_frame);
        fragmentTransaction.commit();
    }

    @Override
    public void updateRightTv(String fileName) {
        right_frame.updataTv(fileName); // MainActivity是左边碎片和右边碎片信息交互的桥梁,让MainActivity实现左边碎片的内部接口，
                        // 让MainActivity称为内部接口的子类，再在左边碎片里面引入这个接口变量，让MainActivity称为这个接口变量的子类.就可以使用多态了。
    }
}
