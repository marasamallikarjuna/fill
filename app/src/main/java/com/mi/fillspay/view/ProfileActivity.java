package com.mi.fillspay.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.mi.fillspay.R;
import com.mi.fillspay.utilities.AppUtilities;
import com.mi.fillspay.utilities.FilePath;
import com.mi.fillspay.view_model.ProfilePictureViewModel;

import java.io.File;
import java.io.IOException;

public class ProfileActivity extends BaseActivity implements View.OnClickListener {

    public static final int PICK_IMAGE_REQUEST = 190;
    public static final int OPEN_CAMERA = 191;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 192;
    private ImageView userImageView;
    private Bitmap myBitmap = null;
    Uri outputFileUri;
    ProfilePictureViewModel profilePictureViewModel;
    ImageView update_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initValues();
    }

    private void initValues() {
        userImageView = findViewById(R.id.userImageView);
        update_btn = findViewById(R.id.update_profile_btn);
        update_btn.setOnClickListener(this);
        userImageView.setOnClickListener(this);
        findViewById(R.id.image_choose_button).setOnClickListener(this);
        userImageView.setImageBitmap(AppUtilities.stringToBitmap(_preferencesHelper.getUserProfileImage()));
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_CAMERA);
    }

    private boolean hasPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }
    }

    private void showFileChooser() {

        final TextView tv_title, tv_text1, tv_text2;
        final Button close_btn;

        final BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(this);
        View sheetView = getLayoutInflater().inflate(R.layout.image_chooser_dialog, null);

        tv_title = sheetView.findViewById(R.id.header_id);
        tv_text1 = sheetView.findViewById(R.id.text_one);
        tv_text2 = sheetView.findViewById(R.id.text_two);
        close_btn = sheetView.findViewById(R.id.btn_close);

        tv_title.setText("Open With");
        tv_text1.setText("Camera");
        tv_text2.setText("Gallery");

        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss();
            }
        });

        final boolean result = hasPermissions();

        tv_text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  boolean hasCamera = (PermissionsUtility.checkCameraPermission(ProfileActivity.this) && PermissionsUtility.isDeviceSupportCamera(ProfileActivity.this));

                if (result) {
                    try {
                        mBottomSheetDialog.dismiss();
                        takePicture();
                    } catch (Exception e) {
                        Toast.makeText(ProfileActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            }
        });

        tv_text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (result) {
                    mBottomSheetDialog.dismiss();
                    galleryIntent();
                }
            }
        });

        mBottomSheetDialog.setContentView(sheetView);
        mBottomSheetDialog.show();
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    public void takePicture() {
        // Determine Uri of camera image to save.
        outputFileUri = AppUtilities.getCaptureImageOutputUri(ProfileActivity.this);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(intent, OPEN_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PICK_IMAGE_REQUEST) {
                Uri filePath = data.getData();
                myBitmap = null;
                try {
                    myBitmap = AppUtilities.getThumbnail(ProfileActivity.this, filePath);
                    userImageView.setImageBitmap(myBitmap);
                    if (myBitmap != null) {
                        updateProfilePicture(FilePath.savebitmap(myBitmap, this));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (requestCode == OPEN_CAMERA) {
                try {
                    myBitmap = null;
                    myBitmap = AppUtilities.getThumbnail(ProfileActivity.this, outputFileUri);
                    if (myBitmap != null)
                        myBitmap = AppUtilities.getResizedBitmap(myBitmap, 1000);
                    userImageView.setImageBitmap(myBitmap);
                    if (outputFileUri != null && myBitmap != null) {
                        updateProfilePicture(FilePath.savebitmap(myBitmap, this));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // save file url in bundle as it will be null on scren orientation
        // changes
        outState.putParcelable("pic_uri", outputFileUri);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // get the file url
        outputFileUri = savedInstanceState.getParcelable("pic_uri");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.image_choose_button:
                if (hasPermissions()) {
                    showFileChooser();
                } else {
                    requestPermissions();
                }
                break;

            case R.id.update_profile_btn:
                break;
        }
    }

    public void updateProfilePicture(final File file) {

        profilePictureViewModel = ViewModelProviders.of(this).get(ProfilePictureViewModel.class);

        AppUtilities.showProgress(this);

        profilePictureViewModel.uploadProfileImage(file, "919014250855", _preferencesHelper.getAccessToken(), this).observe(this, profileImageResponse -> {

            if (profileImageResponse != null) {
                if (profileImageResponse.getMessage().equalsIgnoreCase("Success") && profileImageResponse.getStatus().equalsIgnoreCase("OK")) {
                    AppUtilities.stopProgress();
                }
            }
        });
    }
}
