package com.example.myapexapp;

import org.apache.apex.malhar.lib.fs.GenericFileOutputOperator.StringFileOutputOperator;

import com.datatorrent.api.DefaultInputPort;

public class BytetoString extends StringFileOutputOperator{
	@Override
	protected void processTuple(String tuple) {
		// TODO Auto-generated method stub
		super.processTuple(tuple);
	}

	public transient DefaultInputPort<byte[]> input1 = new DefaultInputPort<byte[]>() {

		@Override
		public void process(byte[] bytes) {
			// TODO Auto-generated method stub
			String s = bytes.toString(); 
			processTuple(s);
		}
	};
 		
	/*public transient DefaultInputPort<byte[]> input1 = new DefaultInputPort<byte[]>() {

		@Override
		public void process(byte[] arg0) {
			// TODO Auto-generated method stub
			//arg0  = 
			//processTuple(d)
		}
	};*/

}
