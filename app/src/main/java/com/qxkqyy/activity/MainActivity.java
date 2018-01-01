package com.qxkqyy.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.donkingliang.banner.CustomBanner;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qxkqyy.R;
import com.qxkqyy.adapter.AppointmentAdapter;
import com.qxkqyy.bean.AppointmentModel;
import com.qxkqyy.utils.RealmHelper;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/**
 * Created by puyihao on 2018/1/1.
 */

public class MainActivity extends AppCompatActivity {
    private CustomBanner mBanner;
    private AppointmentAdapter mAdapter;
    private RecyclerView mRvAppointment;
    private List<AppointmentModel> mAppointmentModelList;
    private int ADD_APPOINTMENT = 100;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Fresco.initialize((Context)this);
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        initWidget();
        initData();
    }

    protected void onStart() {
        super.onStart();
        initBanner();
    }

    private void initWidget(){
        View account = this.findViewById(R.id.account);
        account.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                startActivity(new Intent(getApplicationContext(), Login2Activity.class));
                overridePendingTransition(R.anim.leftout, R.anim.leftin);
            }
        }));
        View account2 = this.findViewById(R.id.account2);
        account2.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                startActivity(new Intent(getApplicationContext(), AccountActivity.class));
                overridePendingTransition(R.anim.leftout, R.anim.leftin);
            }
        }));
        View drlist = this.findViewById(R.id.doctorList);
        drlist.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                startActivity(new Intent(getApplicationContext(), ListActivity.class));
                overridePendingTransition(R.anim.upout, R.anim.upin);
            }
        }));
        View chat = this.findViewById(R.id.chat);
        chat.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                SharedPreferences preferences = getSharedPreferences("user", 0);
                String logged = preferences.getString("logged", "0");
                if(Intrinsics.areEqual(logged, "1")) {
                    startActivity(new Intent(getApplicationContext(), BoardActivity.class));
                    overridePendingTransition(R.anim.upout, R.anim.upin);
                } else {
                    Toast.makeText(getApplicationContext(), (CharSequence)"请登录后再留言！", Toast.LENGTH_SHORT).show();
                }

            }
        }));
        View newtask = this.findViewById(R.id.newTask);
        newtask.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                SharedPreferences preferences = getSharedPreferences("user", 0);
                String logged = preferences.getString("logged", "0");
                if(Intrinsics.areEqual(logged, "1")) {
                    startActivityForResult(new Intent(getApplicationContext(), NewActivity.class),ADD_APPOINTMENT);
                    overridePendingTransition(R.anim.upout, R.anim.upin);
                } else {
                    Toast.makeText(getApplicationContext(), (CharSequence)"请登录后再新建预约！", Toast.LENGTH_SHORT).show();
                }

            }
        }));

        mRvAppointment = (RecyclerView) findViewById(R.id.rv_content);
        mRvAppointment.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRvAppointment.setItemAnimator(new DefaultItemAnimator());
    }

    private void initData(){
        mAppointmentModelList = RealmHelper.getRealm(getApplicationContext()).getAppointment();
        mAdapter = new AppointmentAdapter(getApplicationContext(),mAppointmentModelList);
        mRvAppointment.setAdapter(mAdapter);
    }

    public void initBanner(){
        View banner = this.findViewById(R.id.banner);
        if(banner == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.donkingliang.banner.CustomBanner<kotlin.String>");
        } else {
            this.mBanner = (CustomBanner)banner;
            List images = (List)(new ArrayList());
            images.add("https://1970633640.github.io/webapp/banner1.png");
            images.add("https://1970633640.github.io/webapp/banner2.jpg");
            this.setBean(images);
            View account = this.findViewById(R.id.account);
            View account2 = this.findViewById(R.id.account2);
            View my1 = this.findViewById(R.id.my1);
            SharedPreferences preferences = this.getSharedPreferences("user", 0);
            String logged = preferences.getString("logged", "0");
            if(Intrinsics.areEqual(logged, "0")) {
                account.setVisibility(View.VISIBLE);
                account2.setVisibility(View.GONE);
                my1.setVisibility(View.GONE);
                mRvAppointment.setVisibility(View.GONE);
            }

            if(Intrinsics.areEqual(logged, "1")) {
                account2.setVisibility(View.VISIBLE);
                my1.setVisibility(View.VISIBLE);
                mRvAppointment.setVisibility(View.VISIBLE);
                account.setVisibility(View.GONE);
            }

            CustomBanner banner1 = this.mBanner;
            if(this.mBanner != null) {
                banner1.setOnPageClickListener((CustomBanner.OnPageClickListener)(new CustomBanner.OnPageClickListener() {
                    // $FF: synthetic method
                    // $FF: bridge method
                    public void onPageClick(int var1, Object var2) {
                        this.onPageClick(var1, (String)var2);
                    }

                    public final void onPageClick(int i, @NotNull String t) {
                        Intrinsics.checkParameterIsNotNull(t, "t");
                        startActivity(new Intent(getApplicationContext(), WebActivity.class));
                    }
                }));
            }
        }

    }

    private final void setBean(List beans) {
        CustomBanner customBanner = this.mBanner;
        if(this.mBanner != null) {
            customBanner = customBanner.setPages((CustomBanner.ViewCreator)(new CustomBanner.ViewCreator() {
                @NotNull
                public View createView(@NotNull Context context, int position) {
                    Intrinsics.checkParameterIsNotNull(context, "context");
                    SimpleDraweeView imageView = new SimpleDraweeView(context);
                    ((GenericDraweeHierarchy)imageView.getHierarchy()).setPlaceholderImage(R.drawable.picture, ScalingUtils.ScaleType.CENTER_INSIDE);
                    return (View)imageView;
                }

                public void updateUI(@NotNull Context context, @NotNull View view, int position, @NotNull String entity) {
                    Intrinsics.checkParameterIsNotNull(context, "context");
                    Intrinsics.checkParameterIsNotNull(view, "view");
                    Intrinsics.checkParameterIsNotNull(entity, "entity");
                    Uri uri1 = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.drawable.picture);
                    Uri uri = Uri.parse(entity);
                    SimpleDraweeView draweeView = (SimpleDraweeView)view;
                    draweeView.setImageURI(uri);
                }

                // $FF: synthetic method
                // $FF: bridge method
                public void updateUI(Context var1, View var2, int var3, Object var4) {
                    this.updateUI(var1, var2, var3, (String)var4);
                }
            }), beans);
            if(customBanner != null) {
                customBanner.setScrollDuration(100);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAppointmentModelList.clear();
        mAppointmentModelList.addAll(RealmHelper.getRealm(getApplicationContext()).getAppointment());
        mAdapter.notifyDataSetChanged();
        mRvAppointment.scrollToPosition(0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_APPOINTMENT){
            onResume();
        }
    }
}
