package com.example.anew.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.anew.R;
import com.example.anew.adapters.LearningLeadersAdapter;
import com.example.anew.model.LearningLeaders;
import com.example.anew.services.LearningLeadersApiInterface;
import com.example.anew.retrofit.LearningLeadersRetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LearningLeadersFragment extends Fragment {


    RecyclerView learningRecycler;
    LearningLeadersAdapter learningLeadersAdapter;
    LearningLeadersApiInterface learningLeadersApiInterface;


    public LearningLeadersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_learning_leaders, container, false);

        learningRecycler = view.findViewById(R.id.leaders_recycler);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        learningLeadersApiInterface = LearningLeadersRetrofitClient.getRetrofitClient().create(LearningLeadersApiInterface.class);

        Call<List<LearningLeaders>> call = learningLeadersApiInterface.getAllLearningLeaders();

        call.enqueue(new Callback<List<LearningLeaders>>() {
            @Override
            public void onResponse(Call<List<LearningLeaders>> call, Response<List<LearningLeaders>> response) {

                List<LearningLeaders> learningLeadersList = response.body();

                getAllLearningLeaders(learningLeadersList);
            }

            @Override
            public void onFailure(Call<List<LearningLeaders>> call, Throwable t) {

                Toast.makeText(getContext(), "No Response from Server", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getAllLearningLeaders(List<LearningLeaders> learningLeadersList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        learningRecycler.setLayoutManager(layoutManager);
        learningLeadersAdapter = new LearningLeadersAdapter(getContext(), learningLeadersList);
        learningRecycler.setAdapter(learningLeadersAdapter);
        learningLeadersAdapter.notifyDataSetChanged();
    }
}