package com.fusheng.kingweather.picture;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fusheng.kingweather.R;
import com.fusheng.kingweather.base.WhetherApp;
import com.fusheng.kingweather.network.RequestUrl;
import com.fusheng.kingweather.util.ImageLoader;
import com.fusheng.kingweather.util.ToolUtil;

import java.util.List;

/**
 * author LiXiaoWei
 * date  2018/6/12.
 * desc:
 */

public class PictureAdapter extends BaseQuickAdapter<CarInfo, BaseViewHolder> {
    public PictureAdapter(int layoutResId, @Nullable List<CarInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CarInfo item) {
        ImageLoader.showImage(WhetherApp.getWhetherApp(), String.format("%s%s", RequestUrl.BASE_IMAGE_URL, item.getUrl()), (ImageView) helper.getView(R.id.item_car_icon));
        String carModel = String.format("%s %s %s %s %s", ToolUtil.removeNull(item.getBrandName()), ToolUtil.removeNull(item.getLineName()), ToolUtil.removeNull(item.getModelName()),
                ToolUtil.removeNull(item.getDisplacement()), TextUtils.isEmpty(item.getProduceYear()) ? "" : item.getProduceYear() + "年款");
        helper.setText(R.id.item_car_num, item.getCode())
                .setText(R.id.item_owner_name, item.getName())
                .setText(R.id.item_car_model, carModel)
                .addOnClickListener(R.id.item_ll_manage);
        helper.getView(R.id.item_car_model).setVisibility(ToolUtil.isStringNull(carModel.trim()) ? View.GONE : View.VISIBLE);
    }
}
