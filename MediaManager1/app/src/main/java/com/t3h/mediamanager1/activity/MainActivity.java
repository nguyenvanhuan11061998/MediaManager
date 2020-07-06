package com.t3h.mediamanager1.activity;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.IBinder;
import androidx.annotation.NonNull;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.t3h.mediamanager1.App;
import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.Utils.ValidatorUtils;
import com.t3h.mediamanager1.base.BaseActivity;
import com.t3h.mediamanager1.databinding.ActivityMainBinding;
import com.t3h.mediamanager1.login.LoginActivity;
import com.t3h.mediamanager1.media.image.ImageHomeFragment;
import com.t3h.mediamanager1.models.UserModel;
import com.t3h.mediamanager1.media.music.FragmentMusic;
import com.t3h.mediamanager1.fragment.FragmentStart;
import com.t3h.mediamanager1.media.video.FragmentVideo;
import com.t3h.mediamanager1.service.MusicService;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity<ActivityMainBinding> implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.ll_logout)
    LinearLayout logoutLinearLayout;
    @BindView(R.id.tv_user_name)
    TextView userNameTextView;
    @BindView(R.id.tv_mail_or_phone_user)
    TextView mailOrPhoneTextView;
    @BindView(R.id.img_drawer_menu)
    ImageView drawerMenuImageView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    public static final String EXTRA_INDEX_FM = "index_of_fm";
    public static final int REQUEST_MAIN = 1;
    private FragmentMusic fmMusic = new FragmentMusic();
    private FragmentVideo fmVideo = new FragmentVideo();
    private ImageHomeFragment fmImage = new ImageHomeFragment();
    private FragmentStart fmStart;

    private UserModel userModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    public FragmentMusic getFmMusic() {
        return fmMusic;
    }

    public FragmentVideo getFmVideo() {
        return fmVideo;
    }

    public ImageHomeFragment getFmImage() {
        return fmImage;
    }

    public Fragment getCurrentFm;

    private final String[] PERRMISSION = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MusicBinder binder = (MusicService.MusicBinder) service;                                       //đưa service vào application context
            App app = (App)  getApplicationContext();
            app.setService(binder.getService());
            binding.musicPlayView.registerStateMainAct();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    public void showFragment(Fragment fm){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fm != null){
            transaction.replace(R.id.panel, fm);
        } else {
            transaction.add(R.id.panel, fm);
        }
        transaction.commitAllowingStateLoss();
        getCurrentFm = fm;
    }

//======================================================  permission ====================================

    public boolean checkPermission(){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        for (String p : PERRMISSION){
            if (checkSelfPermission(p) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(PERRMISSION,0);
                return false;
            }
        }
        return true;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (checkPermission() == true){
            initAct();
        }else {
            finish();
        }
    }

    @Override
    protected void initAct() {
        if (checkPermission() == false){
            return;
        }

        userModel = ValidatorUtils.loadModel(this);

        userNameTextView.setText(userModel.getUserName());
        if (userModel.getmEmail() != ""){
            mailOrPhoneTextView.setText(userModel.getmEmail());
        } else {
            mailOrPhoneTextView.setText(userModel.getmPhone());
        }
        Intent intent = new Intent(this, MusicService.class);
        bindService(intent,connection,BIND_AUTO_CREATE);

        setSupportActionBar(binding.toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        binding.navView.setNavigationItemSelectedListener(this);

        fmStart = FragmentStart.getInstance();
        showFragment(fmStart);
//        initFrm();


    }

//==================================================================================================

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.nav_image:
                showFragment(fmImage);
                break;
            case R.id.nav_video:
                showFragment(fmVideo);
                break;
            case R.id.nav_music:
                showFragment(fmMusic);
                break;
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        if (getCurrentFm != fmStart){
            showFragment(fmStart);
        }else {
            super.onBackPressed();
        }
    }

    @OnClick({R.id.ll_logout, R.id.img_drawer_menu})
    void onClick(View view){
        switch (view.getId()){
            case R.id.ll_logout:
                goToLogout();
                break;
            case R.id.img_drawer_menu:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
    }

    private void goToLogout() {
        UserModel userModel = new UserModel();
        ValidatorUtils.setUserModel(this,userModel);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

// ============================== play act plaay Model ============================================

}
