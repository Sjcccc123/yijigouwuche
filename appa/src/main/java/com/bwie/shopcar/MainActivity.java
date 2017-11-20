package com.bwie.shopcar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRc;
    List<ItemBean> list = new ArrayList<> ();
    private CheckBox mCheckAll;
    RcAdapter adapter;
    int count;
    int price;
    /**
     * 结算
     */
    private TextView mNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        initView ();
        //设置布局管理
        mRc.setLayoutManager ( new LinearLayoutManager ( this ) );

        for (int i = 0; i < 10; i++) {
            list.add ( new ItemBean ( 1, false, "item" + i, 11 ) );
        }
        adapter = new RcAdapter ( list, MainActivity.this, new IShopCar () {
            @Override
            public void Check(int num) {
                mCheckAll.setChecked ( false );
                Agou ();//判断购买的商品
                mNum.setText ( "数量: " + count + "   价格:" + price );
            }

            @Override
            public void AllCkeck(int num) {
                mCheckAll.setChecked ( true );
                Agou ();
                mNum.setText ( "数量: " + count + "   价格:" + price );
            }

            @Override
            public void getPrice() {
                Agou ();
                mNum.setText ( "数量: " + count + "   价格:" + price );
            }
        } );
        //设置适配器
        mRc.setAdapter ( adapter );
        //全选按钮
        mCheckAll.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if (mCheckAll.isChecked ()) {
                    for (int i = 0; i < list.size (); i++) {
                        list.get ( i ).setCheck ( true );
                    }

                    adapter.notifyDataSetChanged ();
                } else {
                    for (int i = 0; i < list.size (); i++) {
                        list.get ( i ).setCheck ( false );
                    }
                    adapter.notifyDataSetChanged ();
                }
                Agou ();
                mNum.setText ( "数量: " + count + "   价格:" + price );
            }
        } );

    }

    //判断有几件为勾选状态
    private void Agou() {
        count = 0;
        price = 0;
        for (int i = 0; i < list.size (); i++) {
            if (list.get ( i ).isCheck ()) {
                count += list.get ( i ).getNum ();
                price += list.get ( i ).getPrice () * list.get ( i ).num;
            }
        }
    }

    private void initView() {
        mRc = ( RecyclerView ) findViewById ( R.id.rc );
        mCheckAll = ( CheckBox ) findViewById ( R.id.checkAll );
        mNum = ( TextView ) findViewById ( R.id.num );
    }
}
