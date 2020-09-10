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

import com.example.anew.adapters.SkillIQLeadersAdapter;

import com.example.anew.model.SkillIQLeaders;

import com.example.anew.services.SkillIQLeadersApiInterface;
import com.example.anew.retrofit.SkillIQLeadersRetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillIQLeadersFragment extends Fragment {

    RecyclerView skillRecycler;
    SkillIQLeadersAdapter skillIQLeadersAdapter;
    SkillIQLeadersApiInterface skillIQLeadersApiInterface;


    public SkillIQLeadersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_skill_i_q_leaders, container, false);

        skillRecycler = view.findViewById(R.id.skill_recycler);


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        skillIQLeadersApiInterface = SkillIQLeadersRetrofitClient.getRetrofitClient().create(SkillIQLeadersApiInterface.class);

        Call<List<SkillIQLeaders>> call = skillIQLeadersApiInterface.getAllSkillIQLeaders();

        call.enqueue(new Callback<List<SkillIQLeaders>>() {
            @Override
            public void onResponse(Call<List<SkillIQLeaders>> call, Response<List<SkillIQLeaders>> response) {

                List<SkillIQLeaders> skillIQLeadersList = response.body();

                getAllSkillIQLeaders(skillIQLeadersList);
            }

            @Override
            public void onFailure(Call<List<SkillIQLeaders>> call, Throwable t) {

                Toast.makeText(getContext(), "No Response from Server", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getAllSkillIQLeaders(List<SkillIQLeaders> skillIQLeadersList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        skillRecycler.setLayoutManager(layoutManager);
        skillIQLeadersAdapter = new SkillIQLeadersAdapter(getContext(), skillIQLeadersList);
        skillRecycler.setAdapter(skillIQLeadersAdapter);
        skillIQLeadersAdapter.notifyDataSetChanged();
    }
}