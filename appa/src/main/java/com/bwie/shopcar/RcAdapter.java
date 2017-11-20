package com.bwie.shopcar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;


public class RcAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<ItemBean> list;
    Context context;
    IShopCar iShopCar;
    int num;

    public RcAdapter(List<ItemBean> list, Context context, IShopCar iShopCar) {
        this.list = list;
        this.context = context;
        this.iShopCar = iShopCar;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate ( context, R.layout.item, null );
        return new ViewHolder ( view );
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //得到数据
        final ItemBean itemBean = list.get ( position );
        final ViewHolder viewHolder = ( ViewHolder ) holder;
        viewHolder.box.setChecked ( itemBean.isCheck () );
        viewHolder.name.setText ( itemBean.getName () );
        viewHolder.price.setText ( itemBean.getPrice () + "" );
        viewHolder.num.setText ( itemBean.getNum () + "" );
        viewHolder.jia.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                itemBean.num++;
                //判断当前商品是否选中
                if (itemBean.isCheck ()) {
                    iShopCar.getPrice ();
                }
                notifyDataSetChanged ();//刷新适配器
            }
        } );
        viewHolder.jian.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if (itemBean.num > 0) {
                    itemBean.num--;
                    //判断当前商品是否选中
                    if (itemBean.isCheck ()) {
                        iShopCar.getPrice ();
                    }
                    notifyDataSetChanged ();//刷新适配器
                }
            }
        } );
        //点击监听
        viewHolder.box.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if (viewHolder.box.isChecked ()) {
                    itemBean.setCheck ( true );
                    if (dellAll ()) {
                        //  Toast.makeText(context,"2",Toast.LENGTH_LONG).show();
                        iShopCar.AllCkeck ( num );
                    } else {
                        //   Toast.makeText(context,"1",Toast.LENGTH_LONG).show();
                        iShopCar.Check ( num );
                    }
                } else {
                    itemBean.setCheck ( false );
                    iShopCar.Check ( num );

                }
            }
        } );
    }

    //按钮是否全选
    public boolean dellAll() {
        for (int i = 0; i < list.size (); i++) {
            if (!list.get ( i ).isCheck ()) {
                return false;
            }

        }
        return true;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox box;
        TextView name;
        TextView price;
        TextView jia;
        TextView jian;
        TextView num;

        public ViewHolder(View itemView) {
            super ( itemView );
            box = itemView.findViewById ( R.id.check );
            name = itemView.findViewById ( R.id.name );
            price = itemView.findViewById ( R.id.price );
            jian = itemView.findViewById ( R.id.jian );
            jia = itemView.findViewById ( R.id.jia );
            num = itemView.findViewById ( R.id.num );
        }
    }

    @Override
    public int getItemCount() {
        return list.size ();
    }
}
