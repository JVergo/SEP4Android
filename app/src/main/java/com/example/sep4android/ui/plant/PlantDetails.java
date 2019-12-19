package com.example.sep4android.ui.plant;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.sep4android.MainActivity;
import com.example.sep4android.Model.Plant;
import com.example.sep4android.Model.PlantData;
import com.example.sep4android.Model.SensorBoundaries;
import com.example.sep4android.R;
import com.example.sep4android.RDS.PlantReponsitory;
import com.example.sep4android.RDS.UserRepository;
import com.example.sep4android.Model.historicData;
import com.example.sep4android.ViewModel.PlantDetailsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Objects;

public class PlantDetails extends Fragment {
    private static final String CHANNEL_ID = "124" ;
    NotificationManagerCompat notificationManager;
    NotificationCompat.Builder notificationBuilder;

    private FloatingActionButton editPlantBTN;
    private PlantDetailsViewModel mViewModel;
    private Plant curPlant;
    private View root;



    TextView tempMin, tempMax, tempCur, humidityMin, humidityMax, humidityCur, coMin, coMax, coCur, lightMin, lightMax, lightCur;

    private AlertDialog dialog;
    private AlertDialog.Builder builder;

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
        TextView sensorid = root.findViewById(R.id.tv_sensorId);

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


        TextView profileName = root.findViewById(R.id.tv_plantType);

        PlantDetailsViewModel mViewModel = ViewModelProviders.of(this).get(PlantDetailsViewModel.class);

        if (PlantReponsitory.getInstance().getPlants() == null) {
            String email = UserRepository.getInstance().getUserEmail();
            PlantReponsitory.getInstance().getPlantFromApi(email);
        }
        mViewModel.getPlants().observe(Objects.requireNonNull(getActivity()), plantList -> {
            if(getArguments() != null)
            {
                curPlant = plantList.getPlant((getArguments().getInt("plantID")));
                plantName.setText(curPlant.getName());
                sensorid.setText(curPlant.getDeviceId());

                tempCur.setText(String.format("%s", curPlant.getLastTemperatureMeasurement().getMeasurementValue()));
                humidityCur.setText(String.format("%s", curPlant.getLastHumidityMeasurement().getMeasurementValue()));
                coCur.setText(String.format("%s", curPlant.getLastCO2Measurement().getMeasurementValue()));
                lightCur.setText(String.format("%s", curPlant.getLastLightMeasurement().getMeasurementValue()));

                profileName.setText(curPlant.getProfile().getName());

                SetMinMax(tempMin, tempMax, curPlant.getProfile().getTemperatureBoundaries());
                SetMinMax(coMin, coMax, curPlant.getProfile().getCo2Boundaries());
                SetMinMax(humidityMin, humidityMax, curPlant.getProfile().getHumidityBoundaries());
                SetMinMax(lightMin, lightMax, curPlant.getProfile().getLightBoundaries());


                PlantReponsitory.getInstance().GetHistoricDataFromAPI(curPlant.getId(), this);
            }
        });

        FloatButtonOnClick();

        Button delete = root.findViewById(R.id.imgBtnDelete);
        delete.setOnClickListener(view -> {

            builder = new AlertDialog.Builder(getContext());
            builder.setMessage("Are you sure you want to delete this Plant ?");
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", (dialogInterface, i) -> {
                //delete api
                PlantReponsitory.getInstance().deletePlant(curPlant.getId());
                PlantReponsitory.getInstance().UpdatePalnts(UserRepository.getInstance().getUserEmail());

                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, new PlantFragment());
                fragmentTransaction.commit();

            }).setNegativeButton("No", (dialogInterface, i) -> dialogInterface.cancel());
            dialog = builder.create();
            dialog.show();

        });

        createNotificationChannel();
        colorChange();

        return root;
    }

    /**
     * Used to set the values of the min and max text fealds with the SensorBoundaries values
     * @param min The min textView to be set
     * @param max The max textView to be set
     * @param v The SensorBoundaries that is use to set the values of the text feald
     */
    public void SetMinMax(TextView min, TextView max, SensorBoundaries v) {
        min.setText(v.getMin().toString());
        max.setText(v.getMax().toString());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        PlantDetailsViewModel mViewModel = ViewModelProviders.of(this).get(PlantDetailsViewModel.class);
    }

    public void FloatButtonOnClick(){
        editPlantBTN.setOnClickListener(v -> {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, EditPlant.newInstance(getArguments().getInt("plantID"), curPlant.getPlantProfileId()));
            fragmentTransaction.commit();
            });
        }

    public void CreateGraph(historicData hd) {
        GraphView graph = root.findViewById(R.id.tempGraphview);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(getDataPoint(hd.getTemperature()));
        graph.addSeries(series);

        graph = root.findViewById(R.id.humidityGraphview);
        series = new LineGraphSeries<>(getDataPoint(hd.getHumidity()));
        graph.addSeries(series);

        graph = root.findViewById(R.id.co2Graphview);
        series = new LineGraphSeries<>(getDataPoint(hd.getCo2()));
        graph.addSeries(series);

        graph = root.findViewById(R.id.lightGraphview);
        series = new LineGraphSeries<>(getDataPoint(hd.getLight()));
        graph.addSeries(series);
    }

    public DataPoint[] getDataPoint(PlantData[] hd) {
        for (int i = 0; i < hd.length; i++) {
            if (hd[i] == null) {
                hd[i] = new PlantData(0.0);
            }
        }
        DataPoint[] dp;
        dp = new DataPoint[]{
                new DataPoint(0, hd[0].getMeasurementValue()),
                new DataPoint(1, hd[1].getMeasurementValue()),
                new DataPoint(2, hd[2].getMeasurementValue()),
                new DataPoint(3, hd[3].getMeasurementValue()),
                new DataPoint(4, hd[4].getMeasurementValue()),
                new DataPoint(5, hd[5].getMeasurementValue()),
                new DataPoint(6, hd[6].getMeasurementValue())
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

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private PendingIntent getPendingIntent() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return PendingIntent.getActivity(getContext(), 0, intent, 0);
    }

    public  void notificationCalled(){
        notificationManager = NotificationManagerCompat.from(getContext());
        notificationBuilder = new NotificationCompat.Builder(getContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("P.M.I")
                .setContentIntent(getPendingIntent())
                .setContentText(curPlant.getName() + " is dying! D:")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        notifyIt();
    }

    public void notifyIt() {
        notificationManager.notify(1, notificationBuilder.build());
    }


}