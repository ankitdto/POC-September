package com.example.myapexapp;

import com.datatorrent.api.DefaultInputPort;
import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.common.util.BaseOperator;

public class Recommender extends BaseOperator{
	
	public transient DefaultInputPort<Object> recommenderconfigin = new DefaultInputPort<Object>() {

		@Override
		public void process(Object tuple) {
			// TODO Auto-generated method stub
			
			RecommendationInput r = (RecommendationInput)tuple;
			processTupleRecommender(r);
			recommenderconfigout.emit(r);
			
		}
	}; 
	
	public void processTupleRecommender(RecommendationInput r)
	{
		
		if(r.getItemid() == 1)
		{
			r.setCategory("mobile");
			r.setPrice(20000);
			r.recommendedCategories.add("Mobile Cover");
			r.recommendedCategories.add("Headset");
			r.recommendedCategories.add("Mobile Battery");
		}
		else
		{
			r.setCategory("default");
			r.recommendedCategories.add("TV");
			r.recommendedCategories.add("Fan");
			r.recommendedCategories.add("Table");
		}	
	}

	
	public final transient DefaultOutputPort<Object> recommenderconfigout = new DefaultOutputPort<Object>();
}
