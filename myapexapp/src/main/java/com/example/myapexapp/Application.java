/**
 * Put your copyright and license info here.
 */
package com.example.myapexapp;


import org.apache.apex.malhar.kafka.KafkaSinglePortInputOperator;
import org.apache.apex.malhar.kafka.KafkaSinglePortOutputOperator;
import org.apache.apex.malhar.lib.fs.GenericFileOutputOperator.StringFileOutputOperator;
import org.apache.hadoop.conf.Configuration;

import com.datatorrent.api.annotation.ApplicationAnnotation;
import com.datatorrent.contrib.formatter.CsvFormatter;
//import com.datatorrent.contrib.kafka.KafkaSinglePortByteArrayInputOperator;
import com.datatorrent.contrib.parser.CsvParser;
import com.datatorrent.contrib.parser.JsonParser;
import com.datatorrent.api.StreamingApplication;
import com.datatorrent.api.DAG;
import com.datatorrent.api.DAG.Locality;
import com.datatorrent.lib.db.jdbc.JdbcPOJOInputOperator;
import com.datatorrent.lib.filter.FilterOperator;
import com.datatorrent.lib.formatter.JsonFormatter;
import com.datatorrent.lib.io.ConsoleOutputOperator;

@ApplicationAnnotation(name="MyFirstApplication")
public class Application implements StreamingApplication
{

  @Override
  public void populateDAG(DAG dag, Configuration conf)
  {
    // Sample DAG with 2 operators
    // Replace this code with the DAG you want to build

    //RandomNumberGenerator randomGenerator = dag.addOperator("randomGenerator", RandomNumberGenerator.class);
    /*randomGenerator.setNumTuples(500);

    ConsoleOutputOperator cons = dag.addOperator("console", new ConsoleOutputOperator());

    dag.addStream("randomData", randomGenerator.out, cons.input).setLocality(Locality.CONTAINER_LOCAL);
  */
	  
	  //System.out.println(jsonparser.toString()); 
	  
	  /* old data 
	  KafkaSinglePortInputOperator kafkaInputOperator = dag.addOperator("kafkaInput", KafkaSinglePortInputOperator.class);
	  CsvParser csvParser = dag.addOperator("csvParser", CsvParser.class);
	  //FilterOperator filterOperator = dag.addOperator("filter", new FilterOperator());
	  CsvFormatter formatter = dag.addOperator("formatter", new CsvFormatter());
	  StringFileOutputOperator fileOutput = dag.addOperator("fileOutput", new StringFileOutputOperator());
	  //BytetoString output = dag.addOperator("fileOutput", BytetoString.class);
	  
	  //dag.addStream("PushData", input.outputPort, output.input);
	  
	    dag.addStream("data", kafkaInputOperator.outputPort, csvParser.in);
	    dag.addStream("pojo", csvParser.out, formatter.in);
	    //dag.addStream("filtered", filterOperator.truePort, formatter.in);
	    dag.addStream("string", formatter.out, fileOutput.input);
	  */
	  
	  //--------------------------------------------------------------------------------------------------
	  /*  Old Working Code
	   KafkaSinglePortInputOperator input = dag.addOperator("kafkaInput", KafkaSinglePortInputOperator.class);//addOperator("kafkaInput", KafkaSinglePortInputOperator.class); //addOperator("kafkaInput", KafkaSinglePortInputOperator.class);
	  JsonParser jsonparser = dag.addOperator("jsonParser", JsonParser.class);
	  //jdbcPojoInputOperator = dag.addOperator("JDBC POJO Input", JdbcPOJOInputOperator.class);
	  //FilterOperator filterOperator = dag.addOperator("Filter", FilterOperator.class);
	  Enricher enrichoperator = dag.addOperator("Enricher", Enricher.class);
	  JsonFormatter jsonformatteroperator = dag.addOperator("jsonformatter", JsonFormatter.class);
	  KafkaSinglePortOutputOperator kafkaoutputoperator = dag.addOperator("kafkaOutput", KafkaSinglePortOutputOperator.class);
	  
	  dag.addStream("json-to-pojo", input.outputPort, jsonparser.in);
	  dag.addStream("pojo-to-filter", jsonparser.out,enrichoperator.configin);
	  dag.addStream("Enriched-data", enrichoperator.configout, jsonformatteroperator.in);
	  dag.addStream("POJO-to-JSON", jsonformatteroperator.out, kafkaoutputoperator.inputPort); 
	  */
	  
	  //-------------------------------------------------------------------------------------------------
	  
	  KafkaSinglePortInputOperator input = dag.addOperator("kafkaInput", KafkaSinglePortInputOperator.class);//addOperator("kafkaInput", KafkaSinglePortInputOperator.class); //addOperator("kafkaInput", KafkaSinglePortInputOperator.class);
	  JsonParser jsonparser = dag.addOperator("jsonParser", JsonParser.class);
	  Enrichment enrichoperator = dag.addOperator("Enricher", Enrichment.class);
	  Recommender recommenderoperator = dag.addOperator("Recommender", Recommender.class);
	  EnrichmentOfRecommendation enrichmentOfrecommendationoperator = dag.addOperator("EnrichmentofRecommendation", EnrichmentOfRecommendation.class);
	  JsonFormatter jsonformatteroperator = dag.addOperator("jsonformatter", JsonFormatter.class);
	  KafkaSinglePortOutputOperator kafkaoutputoperator = dag.addOperator("kafkaOutput", KafkaSinglePortOutputOperator.class);
	  
	  dag.addStream("json-to-pojo", input.outputPort, jsonparser.in);
	  dag.addStream("pojo-to-filter", jsonparser.out,enrichoperator.enrichconfigin);
	  dag.addStream("user data", enrichoperator.enrichconfigout, recommenderoperator.recommenderconfigin);
	  dag.addStream("recommended data", recommenderoperator.recommenderconfigout, enrichmentOfrecommendationoperator.enrichRecommendedConfigin);
	  dag.addStream("enriched recommended data", enrichmentOfrecommendationoperator.enrichRecommendedConfigout, jsonformatteroperator.in);
	  dag.addStream("pojo-to-json", jsonformatteroperator.out, kafkaoutputoperator.inputPort);
	  
  }
}
