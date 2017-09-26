package com.example.myapexapp;

import com.datatorrent.api.DefaultInputPort;
import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.common.util.BaseOperator;

public class EnrichmentOfRecommendation extends BaseOperator{
	
	public transient DefaultInputPort<Object> enrichRecommendedConfigin = new DefaultInputPort<Object>() {
		
		@Override
		public void process(Object tuple) {
			// TODO Auto-generated method stub
			RecommendationInput r = (RecommendationInput) tuple;
			processEnrichmentOfRecommentationTuple(r);
			enrichRecommendedConfigout.emit(r);
			
		}

		
	};
	
	public void processEnrichmentOfRecommentationTuple(RecommendationInput r) {
		// TODO Auto-generated method stub
		
		
		Product p = new Product(7,"Mobile Cover",200);
		Product p1 = new Product(17,"Headset",1000);
		Product p2 = new Product(16,"Mobile Battery",700);
		
		Product p3 = new Product(78,"Television",25000);
		Product p4 = new Product(23,"Fan",2000);
		Product p5 = new Product(37,"Table",1000);
		
		
		
		if(r.getItemid() == 1 && r.getCategory().equals("mobile"))
		{
			r.recommendedProducts.add(p);
			r.recommendedProducts.add(p1);
			r.recommendedProducts.add(p2);
		}
		else
		{
			r.recommendedProducts.add(p3);
			r.recommendedProducts.add(p4);
			r.recommendedProducts.add(p5);
		}
		
	}
	
	public final transient DefaultOutputPort<Object> enrichRecommendedConfigout = new DefaultOutputPort<Object>();
	

}
