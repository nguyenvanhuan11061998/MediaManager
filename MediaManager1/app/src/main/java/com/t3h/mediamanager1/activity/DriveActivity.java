package com.t3h.mediamanager1.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.tasks.Task;
import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.base.BaseActivity;
import com.t3h.mediamanager1.databinding.ActivityDriveBinding;

public class DriveActivity extends BaseActivity<ActivityDriveBinding> implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private static final int DRIVE_ACT = 10;
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void initAct() {

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        binding.signInButton.setOnClickListener(this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // Nếu dữ liệu của người dùng trong bộ d dệm hợp lệ, OptionalPendingResult sẽ ở trạng thái "done"
            // và GoogleSignInResult sẽ có ngay mà không cần thực hiện đăng nhập lại.
            GoogleSignInResult result = opr.get();
            handleSignInResult(result.getSignInAccount());

        } else {
            // Nếu người dùng chưa từng đăng nhập trước đó, hoặc phiên làm việc đã hết hạn,
            // thao tác bất đồng bộ này sẽ ngầm đăng nhập người dùng, và thực hiện thao tác cross sign-on.
            return;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_drive;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sign_in_button:
                signIn();

                break;
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, DRIVE_ACT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == DRIVE_ACT) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                handleSignInResult(account);
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }

    }

        private void handleSignInResult(GoogleSignInAccount account) {
            updateUI(account);
        }

        private void updateUI(GoogleSignInAccount account){
            displayInfor(account);
        }

        private void displayInfor(GoogleSignInAccount account){
                binding.signInButton.setVisibility(View.INVISIBLE);

                binding.tvTaikhoan.setVisibility(View.VISIBLE);
                binding.tvAccName.setText(account.getDisplayName());
                binding.tvAccName.setVisibility(View.VISIBLE);
        }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
