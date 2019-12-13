package com.example.sep4android.ui.plant;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.sep4android.MainActivity;
import com.example.sep4android.Model.Plant;
import com.example.sep4android.Model.SensorBoundaries;
import com.example.sep4android.R;
import com.example.sep4android.RDS.PlantReponsitory;
import com.example.sep4android.ViewModel.PlantDetailsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class PlantDetails extends Fragment {
    private static final String CHANNEL_ID = "124D" ;
    private FloatingActionButton editPlantBTN;
    private PlantDetailsViewModel mViewModel;
    private Plant curPlant;
    private Button water;
    private View root;

    NotificationCompat.Builder notification;

    TextView tempMin, tempMax, tempCur, humidityMin, humidityMax, humidityCur, coMin, coMax, coCur,lightMin,lightMax,lightCur;

    public static PlantDetails newInstance(int pos) {
        PlantDetails fragment = new PlantDetails();
        Bundle args = new Bundle();
        args.putInt("plantID", pos);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_plant_info, container, false);
        editPlantBTN = root.findViewById(R.id.floatingActionButton);

        TextView plantName = root.findViewById(R.id.tv_plantName);

        tempMin = root.findViewById(R.id.tv_tempMin);
        tempMax = root.findViewById(R.id.tv_tempMax);
        tempCur = root.findViewById(R.id.tv_tempCurrent);

        humidityMin = root.findViewById(R.id.tv_humidityMin);
        humidityMax = root.findViewById(R.id.tv_humidityMax);
        humidityCur = root.findViewById(R.id.tv_humidityCurrent);

        coMin = root.findViewById(R.id.tv_co2Min);
        coMax = root.findViewById(R.id.tv_co2Max);
        coCur = root.findViewById(R.id.tv_co2Current);

         lightMin = root.findViewById(R.id.tv_lightMin);
         lightMax = root.findViewById(R.id.tv_lightMax);
         lightCur = root.findViewById(R.id.tv_lightCurrent);

         water = root.findViewById(R.id.button_WaterPlant);

        TextView profileName = root.findViewById(R.id.tv_plantType);

        PlantDetailsViewModel mViewModel = ViewModelProviders.of(this).get(PlantDetailsViewModel.class);

        if (PlantReponsitory.getInstance().getPlants() == null) {
            //String email = "naya7777@gmail.com";
            String email = "1";

            PlantReponsitory.getInstance().getPlantFromApi(email);
        }
        mViewModel.getPlants().observe(getActivity(), plantList -> {
            curPlant = plantList.getPlant((getArguments().getInt("plantID")));
            plantName.setText(curPlant.getName());
            tempCur.setText("" + curPlant.getLastTemperatureMeasurement().getMeasurementValue());
            humidityCur.setText("" + curPlant.getLastHumidityMeasurement().getMeasurementValue());
            coCur.setText("" + curPlant.getLastCO2Measurement().getMeasurementValue());
            lightCur.setText("" + curPlant.getLastLightMeasurement().getMeasurementValue());

            profileName.setText(curPlant.getProfile().getName());

            SetMinMax(tempMin, tempMax, curPlant.getProfile().getTemperature());
            SetMinMax(coMin, coMax, curPlant.getProfile().getCo2());
            SetMinMax(humidityMin, humidityMax, curPlant.getProfile().getHumidity());
            SetMinMax(lightMin, lightMax, curPlant.getProfile().getLight());
        });


        water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //notificationCalled();
            }
        });
        GraphView graph = root.findViewById(R.id.graphview);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(getDataPoint());
        graph.addSeries(series);

        FloatButtonOnClick();


        notification = new NotificationCompat.Builder(getContext());

        NotificationCompat.Builder builder2 = new NotificationCompat.Builder(getContext());
        notification.setAutoCancel(true);
        createNotificationChannel();
        colorChange();

        return root;
    }



    public void SetMinMax(TextView min, TextView max, SensorBoundaries v) {
        min.setText(v.getMin().toString());
        max.setText(v.getMax().toString());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        PlantDetailsViewModel mViewModel = ViewModelProviders.of(this).get(PlantDetailsViewModel.class);
    }



        public void FloatButtonOnClick () {
            editPlantBTN.setOnClickListener(v -> {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, new EditPlant());
                fragmentTransaction.commit();
            });
        }

        public DataPoint[] getDataPoint () {
            DataPoint[] dp = new DataPoint[]{
                    new DataPoint(0, 1),
                    new DataPoint(1, 5),
                    new DataPoint(2, 3),
                    new DataPoint(3, 2),
                    new DataPoint(4, 6)
            };
            return (dp);
        }

        public void colorChange () {
            if (
                    Double.parseDouble(tempCur.getText().toString()) < Double.parseDouble(tempMin.getText().toString())
                            || Double.parseDouble(tempCur.getText().toString()) > Double.parseDouble(tempMax.getText().toString())

            ) {

                notificationCalled();
                tempCur.setTextColor(getResources().getColor(R.color.colorWarning));
            }
            if (
                    (Double.parseDouble(coCur.getText().toString()) < Double.parseDouble(coMin.getText().toString())
                            || Double.parseDouble(coCur.getText().toString()) > Double.parseDouble(coMax.getText().toString())

                    )) {

                notificationCalled();

                coCur.setTextColor(getResources().getColor(R.color.colorWarning));
            }
            if (
                    (Double.parseDouble(lightCur.getText().toString()) < Double.parseDouble(lightMin.getText().toString())
                            || Double.parseDouble(lightCur.getText().toString()) > Double.parseDouble(lightMax.getText().toString())

                    )) {

                notificationCalled();
                lightCur.setTextColor(getResources().getColor(R.color.colorWarning));
            }
            if (
                    (Double.parseDouble(humidityCur.getText().toString()) < Double.parseDouble(humidityMin.getText().toString())
                            || Double.parseDouble(humidityCur.getText().toString()) > Double.parseDouble(humidityMax.getText().toString())

                    )) {

                notificationCalled();
                humidityCur.setTextColor(getResources().getColor(R.color.colorWarning));
            }
        }

        public void notificationCalled () {
        /*Notification builder = new NotificationCompat.Builder(getActivity())
                .setContentTitle("P.M.I")
                .setContentText("your plant is dying!")
                .setDefaults(NotificationCompat.DEFAULT_ALL).build();

        builder.flags |=
                Notification.FLAG_AUTO_CANCEL;


        NotificationManager manager = (NotificationManager) getActivity().getSystemService(getContext().NOTIFICATION_SERVICE);
        manager.notify(TAG_SIMPLE_NOTIFICATION,builder);*/
            // Create an explicit intent for an Activity in your app
            notification.setSmallIcon(R.drawable.ic_launcher_foreground);
            notification.setTicker("New notification!");
            notification.setWhen(System.currentTimeMillis());
            notification.setContentTitle("P.M.I");
            notification.setContentText("Your plant is dying! Save it now!");
            notification.setDefaults(Notification.DEFAULT_ALL);

            Intent intent = new Intent(getActivity(), getActivity().getClass());
            PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            notification.setContentIntent(pendingIntent);

            Notification notify = notification.build();
            notify.flags = Notification.FLAG_NO_CLEAR;
            NotificationManager manager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);

            manager.notify((int) (Math.random() * 1500), notify);

        }

        public void diyNotification (View v){
            //
            RemoteViews views = new RemoteViews(getActivity().getPackageName(), R.layout.layout);
            Notification notification = new Notification.Builder(getContext())
                    .setSmallIcon(R.drawable.ic_launcher_foreground).setTicker("Notification ")
                    //
                    .setContent(views).build();
            //
            views.setTextColor(R.id.tv, Color.RED);
            Intent intent = new Intent(getContext(), Notification.class);
            //
            PendingIntent pi = PendingIntent.getActivity(getContext(), 2, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.tv, pi);

            Intent intent2 = new Intent(getActivity(), MainActivity.class);

            PendingIntent pi2 = PendingIntent.getActivity(getContext(), 1, intent2,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.iv, pi2);
            //
            NotificationManager notify = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
            notify.notify(1, notification);

        }

        private void createNotificationChannel() {
// Create the NotificationChannel, but only on API 26+ because
// the NotificationChannel class is new and not in the support library
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence name = getString(R.string.channel_name);
                String description = getString(R.string.channel_description);
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name,
                        importance);
                channel.setDescription(description);
                // Register the channel with the system; you can't change the importance
                // or other notification behaviours after this
                NotificationManager notificationManager =
                        getActivity().getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(channel);
            }
        }


    }



