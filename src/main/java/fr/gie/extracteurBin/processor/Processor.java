package fr.gie.extracteurBin.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import fr.gie.extracteurBin.model.LivrableData;

@Component
public class Processor implements ItemProcessor<LivrableData, LivrableData> {

	
	private static final Map<String, String> URL_NAMES = new HashMap<>();
	
	 public Processor() {
		
		URL_NAMES.put("test.com", "test-active.com");
		URL_NAMES.put("test2.com", "test_archive.com");
		URL_NAMES.put("test3.com", "test_bnd.com");
		URL_NAMES.put("test4.com", "test_bnd.com");
	}
	@Override
	public LivrableData process(LivrableData item) throws Exception {
		
		String oldUrl = item.getUrl();
		String newUrl = URL_NAMES.get(oldUrl);
		item.setUrl(newUrl);
		
		return item;
	}

}
