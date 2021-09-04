package com.cashiar.ui.activityacountmangment.fragments.overview.overviewchild;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cashiar.BuildConfig;
import com.cashiar.R;
import com.cashiar.adapters.BluthoosAdapter;
import com.cashiar.databinding.DialogBluthoosBinding;
import com.cashiar.databinding.FragmentEarnSaleProductBinding;
import com.cashiar.models.AllProductsModel;
import com.cashiar.models.SettingModel;
import com.cashiar.models.UserModel;
import com.cashiar.mvp.fragment_most_sale_product_mvp.EarnSaleFragmentView;
import com.cashiar.mvp.fragment_most_sale_product_mvp.FragmentEarnSalePresenter;
import com.cashiar.mvp.fragment_purchases_mvp.FragmentPurchasesPresenter;
import com.cashiar.mvp.fragment_purchases_mvp.PurchasesFragmentView;
import com.cashiar.preferences.Preferences;
import com.cashiar.printer.PrintPicture;
import com.cashiar.share.Common;
import com.cashiar.ui.activityacountmangment.AccountMangmentActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class FragmentSale extends Fragment implements EarnSaleFragmentView {
    private AccountMangmentActivity activity;
    private FragmentEarnSaleProductBinding binding;
    private Preferences preferences;
    private UserModel userModel;

    private FragmentEarnSalePresenter presenter;
    private ProgressDialog dialog;
    private int type_print;

    private final String write_perm = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private final int write_req = 100;
    private final String bluthoos_perm = Manifest.permission.BLUETOOTH;
    private final String bluthoosadmin_perm = Manifest.permission.BLUETOOTH_ADMIN;

    private final int bluthoos_req = 200;
    private static final int D80MMWIDTH = 576;

    private boolean isPermissionGranted = false;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothDevice mmDevice;
    private BluetoothSocket mmSocket;
    private OutputStream mmOutputStream;
    private InputStream inputStream;
    private boolean stopWorker;
    private byte[] readBuffer;
    private int readBufferPosition;
    private Thread workerThread;
    private String filePath;
    private AlertDialog dialog3;
    public static FragmentSale newInstance() {
        return new FragmentSale();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_earn_sale_product, container, false);
        initView();
        return binding.getRoot();
    }
    private void checkBluthoosPermission() {

        if (ContextCompat.checkSelfPermission(activity, bluthoos_perm) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(activity, bluthoosadmin_perm) != PackageManager.PERMISSION_GRANTED) {


            isPermissionGranted = false;

            ActivityCompat.requestPermissions(activity, new String[]{bluthoos_perm, bluthoosadmin_perm}, bluthoos_req);


        } else {
            isPermissionGranted = true;
            try {
                sendData(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void checkWritePermission() {

        if (ContextCompat.checkSelfPermission(activity, write_perm) != PackageManager.PERMISSION_GRANTED) {


            isPermissionGranted = false;

            ActivityCompat.requestPermissions(activity, new String[]{write_perm}, write_req);


        } else {
            isPermissionGranted = true;
            takeScreenshot(type_print);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == write_req && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            isPermissionGranted = true;
            takeScreenshot(type_print);
        } else if (requestCode == bluthoos_req && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            isPermissionGranted = true;
            try {
                sendData(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void takeScreenshot(int mode) {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now.toString().replaceAll(" ", "") + ".jpeg";

            // create bitmap screen capture
            ScrollView v1 = (ScrollView) activity.getWindow().getDecorView().findViewById(R.id.scrollView);
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = getBitmapFromView(v1, v1.getChildAt(0).getHeight(), v1.getChildAt(0).getWidth());
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

            //setting screenshot in imageview
            filePath = imageFile.getPath();
            Log.e("ddlldld", filePath);
            if (mode == 1) {
                shareImage(new File(filePath));
            } else {
                checkBluthoosPermission();
                // sendData(filePath);
                //printPhoto(FileProvider.getUriForFile(activity, BuildConfig.APPLICATION_ID + ".provider",new File(filePath)));
            }
//   Bitmap ssbitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());

        } catch (Exception e) {
            // Several error may come out with file handling or DOM
            Log.e("ddlldld", e.toString());
        }
    }

    private Bitmap getBitmapFromView(View view, int height, int width) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return bitmap;
    }



    private void shareImage(File file) {
        Uri uri = FileProvider.getUriForFile(activity, BuildConfig.APPLICATION_ID + ".provider", file);


        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setPackage("com.whatsapp");

        intent.putExtra(Intent.EXTRA_STREAM, uri);
        try {
            startActivity(Intent.createChooser(intent, "Share Screenshot"));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(activity, "No App Available", Toast.LENGTH_SHORT).show();
        }
    }

    // activity will find a bluetooth printer device
    void findBT() {

        try {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();


            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBluetooth, 0);
            }

            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
            CreateDialogAlert(activity, pairedDevices);

            //    Toast.makeText(activity, "Bluetooth device found." + mmDevice.getName() + pairedDevices.size(), Toast.LENGTH_LONG).show();
            //     myLabel.setText("Bluetooth device found.");

        } catch (Exception e) {
            Log.e("ldkkd", e.toString());
        }
    }

    public void CreateDialogAlert(Context context, Set<BluetoothDevice> bluetoothDeviceList) {
        List<BluetoothDevice> bluetoothDeviceList1 = new ArrayList<>();
        bluetoothDeviceList1.addAll(bluetoothDeviceList);
        dialog3 = new AlertDialog.Builder(context)
                .create();

        DialogBluthoosBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_bluthoos, null, false);
        BluthoosAdapter bluetoothAdapter = new BluthoosAdapter(context, bluetoothDeviceList1);
        binding.bluthoos.setLayoutManager(new LinearLayoutManager(context));
        binding.bluthoos.setAdapter(bluetoothAdapter);

        dialog3.getWindow().getAttributes().windowAnimations = R.style.Theme_App;
        dialog3.setCanceledOnTouchOutside(false);
        dialog3.setView(binding.getRoot());
        dialog3.show();
    }

    public void openBT(BluetoothDevice bluetoothDevice) throws IOException {
        try {
            dialog3.dismiss();

            mmDevice = bluetoothDevice;
            // Standard SerialPortService ID
            UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
            mmSocket = mmDevice.createRfcommSocketToServiceRecord(uuid);
            mmSocket.connect();
            mmOutputStream = mmSocket.getOutputStream();
            inputStream = mmSocket.getInputStream();

            beginListenForData();

            // myLabel.setText("Bluetooth Opened");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * after opening a connection to bluetooth printer device,
     * we have to listen and check if a data were sent to be printed.
     */
    void sendData(String strPath) throws IOException {
        findBT();


        Bitmap imageBit = BitmapFactory.decodeFile(strPath);
        int height = D80MMWIDTH * imageBit.getHeight() / imageBit.getWidth();

        ByteArrayOutputStream blob = new ByteArrayOutputStream();
        imageBit.compress(Bitmap.CompressFormat.PNG, 0, blob);

        byte[] bitmapdata = blob.toByteArray();
        // byte[] command = Utils.decodeBitmap(imageBit);
        // bitmapdata= ESCUtil.selectBitmap(imageBit,4);
        bitmapdata = PrintPicture.POS_PrintBMP(imageBit, 400, 4);
        //  mmDevice.write(sendData);
        //  binding.image.setImageBitmap(imageBit);


        mmOutputStream.write(bitmapdata);

        // tell the user data were sent
        //  myLabel.setText("Data Sent");


    }

    void beginListenForData() {
        try {
            final Handler handler = new Handler();
            // activity is the ASCII code for a newline character
            final byte delimiter = 10;

            stopWorker = false;
            readBufferPosition = 0;
            readBuffer = new byte[1024];

            workerThread = new Thread(new Runnable() {
                public void run() {
                    while (!Thread.currentThread().isInterrupted()
                            && !stopWorker) {

                        try {

                            int bytesAvailable = inputStream.available();
                            if (bytesAvailable > 0) {
                                byte[] packetBytes = new byte[bytesAvailable];
                                inputStream.read(packetBytes);
                                for (int i = 0; i < bytesAvailable; i++) {
                                    byte b = packetBytes[i];
                                    if (b == delimiter) {
                                        byte[] encodedBytes = new byte[readBufferPosition];
                                        System.arraycopy(readBuffer, 0,
                                                encodedBytes, 0,
                                                encodedBytes.length);
                                        final String data = new String(
                                                encodedBytes, "US-ASCII");
                                        readBufferPosition = 0;

                                    } else {
                                        readBuffer[readBufferPosition++] = b;
                                    }
                                }
                            }

                        } catch (IOException ex) {
                            stopWorker = true;
                            try {
                                closeBT();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }

                    }
                    try {
                        closeBT();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            workerThread.start();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void closeBT() throws IOException {
        try {
            stopWorker = true;
            mmOutputStream.close();
            inputStream.close();
            mmSocket.close();
            // myLabel.setText("Bluetooth Closed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void initView() {

        preferences = Preferences.getInstance();
        activity = (AccountMangmentActivity) getActivity();
        presenter = new FragmentEarnSalePresenter(this, activity);
        userModel = preferences.getUserData(activity);
        presenter.getsale(userModel);
        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   takeScreenshot(2);
                type_print = 2;
                checkWritePermission();

                //takeScreenshot(2);

            }
        });
        binding.btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type_print = 1;
                checkWritePermission();
            }
        });
    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLoad() {
        dialog = Common.createProgressDialog(activity, getString(R.string.wait));
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onFinishload() {
        dialog.dismiss();
    }

    @Override
    public void onpurchase(SettingModel body) {
        binding.setModel(body);
    }

    @Override
    public void mostsale(AllProductsModel body) {

    }


}
