package qianfeng.fragmentinterface_application;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/14 0014.
 * 1.定义一个接口
 * 2.创建一个接口变量
 * 3.初始化接口变量
 * 4.让MainActivity来实现这个接口中的方法
 * 在Fragment和Activity建立关联的时候，就立刻判断MainActivity是不是该接口的子类，如果是，就将这个MainActivity传回给这个LeftFragment，
 * 其实就是多态的使用，UpdateRightTv updateRightTv = new MainActivity(); 再调用这个new MainActivity()里面重写过的接口中的方法，就是这样而已。
 * 接口就是为了让变量更广泛使用而已，让它可以接收的变量范围更大而已，只要是实现了这个接口的类，我都把你当作是这个接口的子类，这样，
 * 用一个接口类型的变量，就可以接收这个接口的所有子类。
 *
 *
 *  * 接口回调，五个步骤：
 * 1.定义一个接口
 * 2.定义一个接口变量
 * 3.初始化接口变量
 * 4.调用接口中的方法
 * 5.实现接口
 */
public class LeftFragment extends Fragment {

    private List<String> list;

    private UpdateRightTv updateRightTv;


    public interface UpdateRightTv
    {
        public void updateRightTv(String fileName);
    }

    // 当Fragment和Activity建立连接的时候，就判断MainActivity是不是这个接口的子类
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof UpdateRightTv)
        {
            updateRightTv = (UpdateRightTv) activity; // 子类转换为父类，要强转
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<>();
        list.add("day1.txt");
        list.add("day2.txt");
        list.add("day3.txt");
        list.add("day4.txt");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.left_layout, container, false);
        ListView lv = (ListView) view.findViewById(R.id.lv);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),list.get(position),Toast.LENGTH_SHORT).show();
                // 在这个点击事件里面，把文件名fileName传递到MainActivity中，
                // 再在MainActivity中找到右边碎片的对象，然后用右边碎片的对象来写一个更新这个右边内容的方法
                if(updateRightTv != null)
                {
                    updateRightTv.updateRightTv(list.get(position)); // 这个updateRightTv实际上是由MainActivity充当的，现在updateRightTv就是MainActivity
                }


            }
        });

        return view;
    }



}
