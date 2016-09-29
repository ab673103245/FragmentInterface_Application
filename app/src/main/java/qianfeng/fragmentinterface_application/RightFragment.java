package qianfeng.fragmentinterface_application;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2016/9/14 0014.
 */
public class RightFragment extends Fragment {


    private TextView tv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.right_layout, container, false);
        tv = (TextView) view.findViewById(R.id.tv);
        return view;
    }

    public void updataTv(String fileName)
    {
        AssetManager assets = getActivity().getAssets(); // 这个assets已经锁定到assets文件夹那里
//        assets.open(fileName)


        StringBuffer buffer = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(assets.open(fileName)));
            String str = "";
            while((str=br.readLine())!=null)
            {
                buffer.append(str);
            }
            tv.setText(buffer.toString()); // 根据左边的Fragment所点击的item的position，来获取点击了的文件名，再传这个文件名到这里打开assets里面的文件。

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
