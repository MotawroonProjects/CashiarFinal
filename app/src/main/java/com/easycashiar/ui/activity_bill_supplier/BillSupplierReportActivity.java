package com.easycashiar.ui.activity_bill_supplier;

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
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.easycashiar.BuildConfig;
import com.easycashiar.R;
import com.easycashiar.adapters.BluthoosAdapter;
import com.easycashiar.adapters.CustomerBillReportAdapter;
import com.easycashiar.databinding.ActivityCustomerBillBinding;
import com.easycashiar.databinding.ActivitySupplierBillBinding;
import com.easycashiar.databinding.DialogBluthoosBinding;
import com.easycashiar.language.Language;
import com.easycashiar.models.AllBillCustomerReportModel;
import com.easycashiar.models.CustomerBillReportsModel;
import com.easycashiar.models.UserModel;
import com.easycashiar.mvp.acivity_bill_supplier_mvp.ActivityBillSupplierReportPresenter;
import com.easycashiar.mvp.acivity_bill_supplier_mvp.BillSuplierReportActivityView;
import com.easycashiar.preferences.Preferences;
import com.easycashiar.printer.PrintPicture;
import com.easycashiar.share.Common;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import io.paperdb.Paper;

public class BillSupplierReportActivity extends AppCompatActivity implements BillSuplierReportActivityView {
    private ActivitySupplierBillBinding binding;
    private ActivityBillSupplierReportPresenter presenter;
    private String lang;

    private String query="all";
    private Preferences preferences;
    private UserModel userModel;
    private CustomerBillReportAdapter customerBillReportAdapter;
    private List<CustomerBillReportsModel> customerBillReportsModelList;
    private ProgressDialog dialog2;
    private String str = "all", end = "all";
    private int pos;
    private String type;
    private String type1, type2;
    private String name;
    private int type_print;
    private static final int D80MMWIDTH = 576;

    private final String write_perm = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private final int write_req = 100;
    private final String bluthoos_perm = Manifest.permission.BLUETOOTH;
    private final String bluthoosadmin_perm = Manifest.permission.BLUETOOTH_ADMIN;

    private final int bluthoos_req = 200;

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
    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(Language.updateResources(newBase, Paper.book().read("lang", "ar")));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_supplier_bill);
        getDataFromIntent();
        initView();
    }
    private void getDataFromIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            str = intent.getStringExtra("str");
            end = intent.getStringExtra("end");
            name = intent.getStringExtra("name");

        }
    }
    private void checkBluthoosPermission() {

        if (ContextCompat.checkSelfPermission(this, bluthoos_perm) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, bluthoosadmin_perm) != PackageManager.PERMISSION_GRANTED) {


            isPermissionGranted = false;

            ActivityCompat.requestPermissions(this, new String[]{bluthoos_perm, bluthoosadmin_perm}, bluthoos_req);


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

        if (ContextCompat.checkSelfPermission(this, write_perm) != PackageManager.PERMISSION_GRANTED) {


            isPermissionGranted = false;

            ActivityCompat.requestPermissions(this, new String[]{write_perm}, write_req);


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
            RecyclerView v1 = (RecyclerView) getWindow().getDecorView().findViewById(R.id.recView);
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
                //printPhoto(FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider",new File(filePath)));
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
        Uri uri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", file);


        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setPackage("com.whatsapp");

        intent.putExtra(Intent.EXTRA_STREAM, uri);
        try {
            startActivity(Intent.createChooser(intent, "Share Screenshot"));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No App Available", Toast.LENGTH_SHORT).show();
        }
    }

    // this will find a bluetooth printer device
    void findBT() {

        try {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();


            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBluetooth, 0);
            }

            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
            CreateDialogAlert(this, pairedDevices);

            //    Toast.makeText(this, "Bluetooth device found." + mmDevice.getName() + pairedDevices.size(), Toast.LENGTH_LONG).show();
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
        dialog2.setCanceledOnTouchOutside(false);
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
            // This is the ASCII code for a newline character
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        customerBillReportsModelList = new ArrayList<>();
        preferences = Preferences.getInstance();
        userModel = preferences.getUserData(this);
        Paper.init(this);
        lang = Paper.book().read("lang", "ar");
        binding.setLang(lang);
        presenter = new ActivityBillSupplierReportPresenter(this, this);
        customerBillReportAdapter = new CustomerBillReportAdapter(this, customerBillReportsModelList);
        binding.recView.setLayoutManager(new LinearLayoutManager(this));
        binding.recView.setAdapter(customerBillReportAdapter);
        binding.edtSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                query = binding.edtSearch.getText().toString();
                if (!TextUtils.isEmpty(query)) {
                    Common.CloseKeyBoard(BillSupplierReportActivity.this, binding.edtSearch);
                    presenter.getreports(userModel, query, str, end,name);
                    return false;
                }
            }
            return false;
        });
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
        binding.llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.fl.setOnClickListener(view -> openSheet());
        binding.tvday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvday.setText(getResources().getString(R.string.day));
                closeSheet();
                type = "today";
                String date = dateFormat.format(new Date(System.currentTimeMillis()));
                str = date;
                end = date;
                Log.e("ldlldl", str + " " + end);

                if (pos == 0) {
                    type1 =getResources().getString(R.string.day);
                } else {
                    type2 = getResources().getString(R.string.day);
                }
                presenter.getreports(userModel,query,str,end,name);

            }
        });
        binding.tvthismonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tv.setText(getResources().getString(R.string.this_month));
                closeSheet();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date(System.currentTimeMillis()));
                calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
                str = dateFormat.format(calendar.getTime());
                calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                end = dateFormat.format(calendar.getTime());
                Log.e("ldlldl", str + " " + end);
                type = "this_month";

                if (pos == 0) {
                    type1 = getResources().getString(R.string.this_month);
                } else {
                    type2 = getResources().getString(R.string.this_month);
                }
                presenter.getreports(userModel,query,str,end,name);

            }
        });
        binding.tvlsevday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tv.setText(getResources().getString(R.string.last_seven_day));
                closeSheet();
                type = "last7days";
                String date = dateFormat.format(new Date(System.currentTimeMillis()));
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date(System.currentTimeMillis()));
                calendar.add(Calendar.DATE, -6);
                str = dateFormat.format(calendar.getTime());
                end = date;
                Log.e("ldlldl", str + " " + end);

                if (pos == 0) {
                    type1 = getResources().getString(R.string.last_seven_day);
                } else {
                    type2 = getResources().getString(R.string.last_seven_day);
                }
                presenter.getreports(userModel,query,str,end,name);

            }
        });
        binding.tvextentofwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tv.setText(getResources().getString(R.string.extent_of_work));
                closeSheet();
                type = "all";
                str = "all";
                end = "all";
                if (pos == 0) {
                    type1 = getResources().getString(R.string.extent_of_work);
                } else {
                    type2 = getResources().getString(R.string.extent_of_work);
                }
                presenter.getreports(userModel,query,str,end,name);

            }
        });
        binding.tvlmonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tv.setText(getResources().getString(R.string.last_month));
                closeSheet();
                type = "last_month";
                String date = dateFormat.format(new Date(System.currentTimeMillis()));
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date(System.currentTimeMillis()));
                calendar.add(Calendar.DATE, -29);
                str = dateFormat.format(calendar.getTime());
                end = date;
                Log.e("ldlldl", str + " " + end);
                if (pos == 0) {
                    type1 = getResources().getString(R.string.last_month);
                } else {
                    type2 = getResources().getString(R.string.last_month);
                }
                presenter.getreports(userModel,query,str,end,name);

            }
        });
        binding.tvlthrityday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tv.setText(getResources().getString(R.string.last_thirty_day));
                closeSheet();
                type = "last30days";
                String date = dateFormat.format(new Date(System.currentTimeMillis()));
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date(System.currentTimeMillis()));
                calendar.add(Calendar.DATE, -29);
                str = dateFormat.format(calendar.getTime());
                end = date;
                Log.e("ldlldl", str + " " + end);

                if (pos == 0) {
                    type1 = getResources().getString(R.string.last_thirty_day);
                } else {
                    type2 = getResources().getString(R.string.last_thirty_day);
                }
                presenter.getreports(userModel,query,str,end,name);


            }
        });
        binding.tvyday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tv.setText(getResources().getString(R.string.yesterday));
                closeSheet();
                String date = dateFormat.format(new Date(System.currentTimeMillis()));
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date(System.currentTimeMillis()));
                calendar.add(Calendar.DATE, -1);
                str = dateFormat.format(calendar.getTime());
                end = str;
                Log.e("ldlldl", str + " " + end);

                type = "yesterday";
                if (pos == 0) {
                    type1 = getResources().getString(R.string.yesterday);
                } else {
                    type2 = getResources().getString(R.string.yesterday);
                }
                presenter.getreports(userModel,query,str,end,name);


            }
        });
        binding.tvcustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tv.setText(getResources().getString(R.string.custom_history));
                closeSheet();
                type = "custom";
                presenter.show(getFragmentManager(),1);

                if (pos == 0) {
                    type1 = getResources().getString(R.string.custom_history);
                } else {
                    type2 = getResources().getString(R.string.custom_history);
                }

            }
        });
        presenter.getreports(userModel, query, str, end,name);

    }


    @Override
    public void onFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onBackPressed() {
        presenter.backPress();
    }


    @Override
    public void onFinished() {
        finish();
    }


    @Override
    public void onSuccess(AllBillCustomerReportModel model) {

        //Log.e("dlldldl",model.getData().size()+"");
        customerBillReportsModelList.clear();
        customerBillReportAdapter.notifyDataSetChanged();
        customerBillReportsModelList.addAll(model.getData());
        if (customerBillReportsModelList.size() == 0) {
            binding.tvNoData.setVisibility(View.VISIBLE);
        } else {
            binding.tvNoData.setVisibility(View.GONE);
        }
        customerBillReportAdapter.notifyDataSetChanged();

    }


    @Override
    public void onProgressShow() {
        binding.progBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onProgressHide() {
        binding.progBar.setVisibility(View.GONE);

    }
    private void openSheet() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_up);


        binding.flfilter.clearAnimation();
        binding.flfilter.startAnimation(animation);


        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.flfilter.setVisibility(View.VISIBLE);


            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    private void closeSheet() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_down);


        binding.flfilter.clearAnimation();
        binding.flfilter.startAnimation(animation);


        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                binding.flfilter.setVisibility(View.GONE);


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
    @Override
    public void onDateSelected(String date, int type) {
        if(type==1){
            str=date;
            presenter.show(getFragmentManager(),2);
        }
        else {
            end=date;
            presenter.getreports(userModel,query,str,end,name);

        }

    }

}