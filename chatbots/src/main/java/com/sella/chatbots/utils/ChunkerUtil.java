package com.sella.chatbots.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.Span;

public class ChunkerUtil {
	
	public static void main(String args[]){
		PROCESSMSG("Hi, How are you?.. I need August month transactions");
	}

	public static String PROCESSMSG(String input){
		try {
			
			
			POSModel model = new POSModelLoader()
			.load(new File("D://TestWorkspace/NLPTest/en-pos-maxent.bin"));
	PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
	POSTaggerME tagger = new POSTaggerME(model);

//	String input = "Hi. How are you? This is Mike.";
	ObjectStream<String> lineStream = new PlainTextByLineStream(new StringReader(input));

	perfMon.start();
	String line;
	String whitespaceTokenizerLine[] = null;

	String[] tags = null;
	while ((line = lineStream.read()) != null) {
		whitespaceTokenizerLine = WhitespaceTokenizer.INSTANCE.tokenize(line);
		tags = tagger.tag(whitespaceTokenizerLine);

		POSSample sample = new POSSample(whitespaceTokenizerLine, tags);
		System.out.println(sample.toString());
			perfMon.incrementCounter();
	}
	perfMon.stopAndPrintFinalResult();

	// chunker
	InputStream is;
		is = new FileInputStream("D://TestWorkspace/NLPTest/en-chunker.bin");

	ChunkerModel cModel = new ChunkerModel(is);

	ChunkerME chunkerME = new ChunkerME(cModel);
	String result[] = chunkerME.chunk(whitespaceTokenizerLine, tags);
	for(String s:result){
		System.out.println(s);
	}
	for (int i =0;i < result.length;i++){
		//if(result[i].equalsIgnoreCase("I-NP") || result[i].equalsIgnoreCase("B-PP")){
		if(result[i].equalsIgnoreCase("I-NP")){
			System.out.println(whitespaceTokenizerLine[i]);
		}
	}
	Span[] span = chunkerME.chunkAsSpans(whitespaceTokenizerLine, tags);
//	for (Span s : span)
//		System.out.println(s.toString());

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(input.toLowerCase().contains("transfer")){
			input = "Please enter your payee";
		}
		return input;
	}
}
