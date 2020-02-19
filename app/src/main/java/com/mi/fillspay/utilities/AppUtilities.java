package com.mi.fillspay.utilities;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import com.mi.fillspay.R;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class AppUtilities {

    private static ProgressDialog _dialog;

    public static Bitmap getThumbnail(final Context context, Uri uri) throws IOException {
        InputStream input = context.getContentResolver().openInputStream(uri);
        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        onlyBoundsOptions.inDither = true;//optional
        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();
        if ((onlyBoundsOptions.outWidth == -1) || (onlyBoundsOptions.outHeight == -1))
            return null;
        float originalSize = (onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth)
                ? onlyBoundsOptions.outHeight : onlyBoundsOptions.outWidth;
        double ratio = (originalSize > 840) ? (originalSize / 840) : 1.0;
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio);
        bitmapOptions.inDither = true;//optional
        bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
        input = context.getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        input.close();
        return bitmap;
    }


    public static void fullScreenImageDialog(Activity context,String imageString){

        Rect displayRectangle = new Rect();

        Window window =  context.getWindow();

        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);

        final AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.CustomAlertDialog);

        ViewGroup viewGroup = context.findViewById(android.R.id.content);

        View dialogView = LayoutInflater.from(context).inflate(R.layout.fullscreen_image_dialog, viewGroup, false);
        dialogView.setMinimumWidth((int)(displayRectangle.width() * 1f));
        dialogView.setMinimumHeight((int)(displayRectangle.height() * 1f));
        builder.setView(dialogView);

        final AlertDialog alertDialog = builder.create();

        ZoomableImageView billerImage = dialogView.findViewById(R.id.full_imageview);

        billerImage.setImageBitmap(AppUtilities.stringToBitmap(imageString));

        Button buttonOk = dialogView.findViewById(R.id.buttonOk);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    public static void showProgress(final Context context) {
        _dialog = new ProgressDialog(context);
        _dialog.show();
        _dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        _dialog.setContentView(R.layout.progress);
        _dialog.setCancelable(false);
    }

    public static void showLoading(final Context context) {
        _dialog = new ProgressDialog(context);
        _dialog.show();
        _dialog.setContentView(R.layout.progress);
        _dialog.setCancelable(false);
    }

    public static void stopProgress() {
        if (_dialog != null)
            if (_dialog.isShowing()) {
                _dialog.dismiss();
            }
    }

    public static String bitmapToString(final Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    public static Bitmap stringToBitmap(final String imageString) {
        //decode base64 string to image
        if (imageString == null)
            return null;
        byte[] imageBytes = Base64.decode(imageString, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
    }

    public static byte[] bitmapToByte(Bitmap bitmap) {
        //encode image to base64 string
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    private static int getPowerOfTwoForSampleRatio(double ratio) {
        int k = Integer.highestOneBit((int) Math.floor(ratio));
        if (k == 0) return 1;
        else return k;
    }

    public static Uri getPickImageResultUri(final Context mcontext, Intent data) {
        boolean isCamera = true;
        if (data != null) {
            String action = data.getAction();
            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }
        return isCamera ? getCaptureImageOutputUri(mcontext) : data.getData();
    }

    /**
     * Get URI to image received from capture by camera.
     */
    public static Uri getCaptureImageOutputUri(final Context mcontext) {
        Uri outputFileUri = null;
        File getImage = mcontext.getExternalCacheDir();
        if (getImage != null) {
            outputFileUri = FileProvider.getUriForFile(mcontext, mcontext.getApplicationContext().getPackageName() + ".provider", new File(getImage.getPath(), "profile.png"));
        }
        return outputFileUri;
    }

    private static Bitmap rotateImage(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
        img.recycle();
        return rotatedImg;
    }

    public static Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();
        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 0) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return rotateImage(Bitmap.createScaledBitmap(image, width, height, true), 90);
    }
}
