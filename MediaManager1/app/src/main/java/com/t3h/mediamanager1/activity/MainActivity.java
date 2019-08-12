package com.t3h.mediamanager1.activity;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.IBinder;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import com.t3h.mediamanager1.App;
import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.base.BaseActivity;
import com.t3h.mediamanager1.databinding.ActivityMainBinding;
import com.t3h.mediamanager1.fragment.FragmentMusic;
import com.t3h.mediamanager1.fragment.FragmentImage;
import com.t3h.mediamanager1.fragment.FragmentStart;
import com.t3h.mediamanager1.fragment.FragmentVideo;
import com.t3h.mediamanager1.service.MusicService;


public class MainActivity extends BaseActivity<ActivityMainBinding> implements NavigationView.OnNavigationItemSelectedListener {

    public static final String EXTRA_INDEX_FM = "index_of_fm";
    public static final int REQUEST_MAIN = 1;
    private FragmentMusic fmMusic = new FragmentMusic();
    private FragmentVideo fmVideo = new FragmentVideo();
    private FragmentImage fmImage = new FragmentImage();
    private FragmentStart fmStart = new FragmentStart();

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

    public FragmentImage getFmImage() {
        return fmImage;
    }

    public FragmentStart getFmStart() {
        return fmStart;
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

    private void initFrm() {
        FragmentTransaction transaction  = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.panel,fmMusic);
        transaction.add(R.id.panel,fmVideo);
        transaction.add(R.id.panel,fmImage);
        transaction.add(R.id.panel,fmStart);
        transaction.commitAllowingStateLoss();

    }


    public void showFragment(Fragment fm){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.panel,fm);
        transaction.commitAllowingStateLoss();
        getCurrentFm = fm;
    }

//======================================================  permission ====================================

    public boolean checkPermission(){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        for (String p : PERRMISSION){
            int accept = checkSelfPermission(p);
            if (accept ==PackageManager.PERMISSION_DENIED){
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
        Intent intent = new Intent(this, MusicService.class);
        bindService(intent,connection,BIND_AUTO_CREATE);

        setSupportActionBar(binding.toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        binding.navView.setNavigationItemSelectedListener(this);

        initFrm();

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
        if (getCurrentFm != getFmStart()){
            showFragment(fmStart);
        }else {
            super.onBackPressed();
        }
    }


// ============================== play act plaay Model ============================================

}
