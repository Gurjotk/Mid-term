package com.example.mid_term;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FirstFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private static final String ARG_PARAM1 = "param1";
   // private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    //private String mParam1;
 //   private String mParam2;

    private NavController nav;
    ArrayList<ConsolidatedWeather> parray;
    RecycleAdapter adapter;
    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nav= Navigation.findNavController(getActivity(),R.id.nav_host_fragment_container);
        // btn_go= view.findViewById(R.id.firstFragment);
        //btn_go.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //  public void onClick(View v) {
        //    nav.navigate(R.id.secondFragment);

        //  }
        // });
        DataServices service = RetrofitClientInstance.getRetrofitInstance().create(DataServices.class);
        Call<weather> call=service.getAllWeather();
        call.enqueue(new Callback<weather>() {
            @Override
            public void onResponse(Call<weather> call, Response<weather> response) {
                weather weather = response.body();
                try {
                    parray = new ArrayList<>(weather.getConsolidatedWeather());
                    generateView(parray,view);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<weather> call, Throwable t) {

            }
        });
    }

    private void generateView(ArrayList<ConsolidatedWeather> parray, View view) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


}
