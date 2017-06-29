package com.example.kingslee.bakingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kingslee.bakingapp.R;
import com.example.kingslee.bakingapp.datatype.Step;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by KingsLee on 6/29/2017.
 */

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepAdapterViewHolder> {

    private ArrayList<Step> mStep;
    private final StepAdapterOnClickHandler mClickHandler;

    public interface StepAdapterOnClickHandler {
        void onClick(Step step);
    }

    public StepAdapter (StepAdapterOnClickHandler clickHandler) { mClickHandler = clickHandler;}

    public class StepAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @InjectView(R.id.tv_step_desc)
        public TextView mStepDesc;
        @InjectView(R.id.iv_step_thumbnail)
        public ImageView mStepThumbnail;


        public StepAdapterViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Step step = mStep.get(getAdapterPosition());
            mClickHandler.onClick(step);
        }
    }

    @Override
    public StepAdapter.StepAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttactToParentImmediately = false;

        View view = inflater.inflate(R.layout.step_item, parent, shouldAttactToParentImmediately);
        return new StepAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepAdapter.StepAdapterViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        Step daStep = mStep.get(position);
        holder.mStepDesc.setText(daStep.getDesc());
        Picasso.with(context)
                .load(daStep.getThumbnail())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.mStepThumbnail);
    }

    @Override
    public int getItemCount() {
        if (null != mStep) {
            return mStep.size();
        }
        return 0;
    }

    public void setData(ArrayList<Step> stepData){
        mStep = stepData;
        notifyDataSetChanged();
    }
}
