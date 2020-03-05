package com.example.mid_term;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FirstFragment extends Fragment {

    TextView txtWeatherCondition, txtPerdictbility, txtHumidity, txtMax, txtTemp, txtMin;
    ImageView imgCurrent;
    //    private NavController navController;
    ArrayList<ConsolidatedWeather> parray;
    RecycleAdapter adapter;

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtWeatherCondition = (TextView) getView().findViewById(R.id.txtWeatherCondition);
        txtPerdictbility = (TextView) getView().findViewById(R.id.txtPerdictbility);
        txtHumidity = (TextView) getView().findViewById(R.id.txtHumidity);
        txtMax = (TextView) getView().findViewById(R.id.txtMax);
        txtTemp = (TextView) getView().findViewById(R.id.txtTemp);
        txtMin = (TextView) getView().findViewById(R.id.txtMin);
        imgCurrent = (ImageView) getView().findViewById(R.id.imgCurrent);

        DataServices service = RetrofitClientInstance.getRetrofitInstance().create(DataServices.class);


        Call<weather> call = service.getCompleteWeather();
        call.enqueue(new Callback<weather>() {
            @Override
            public void onResponse(Call<weather> call, Response<weather> response) {
                weather weather = response.body();

                try {
                    parray = new ArrayList<>(weather.getWeather());
                    generateView(parray, view);


                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<weather> call, Throwable t) {

            }
        });
    }

    public void generateView(ArrayList<ConsolidatedWeather> parray, View view) {

        ConsolidatedWeather weather = parray.get(0);

//        parray.get(0).getWeather_state_abbr()

        String uri = "https://www.metaweather.com/static/img/weather/png/64/" + weather.getWeather_state_abbr() + ".png";
        Picasso.get().load(uri).into(imgCurrent);

        txtWeatherCondition.setText(weather.getWeather_state_name());
        txtMin.setText(weather.getMin_temp());
        txtTemp.setText(weather.getThe_temp());
        txtMax.setText(weather.getMax_temp());
        txtHumidity.setText(weather.getHumidity()+"%");
        txtPerdictbility.setText(weather.getPredictability()+"%");

        parray.remove(0);

        adapter = new RecycleAdapter(parray, getActivity().getApplicationContext());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}
