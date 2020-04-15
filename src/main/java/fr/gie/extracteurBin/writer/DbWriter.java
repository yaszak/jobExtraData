package fr.gie.extracteurBin.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.gie.extracteurBin.model.LivrableData;
import fr.gie.extracteurBin.repository.LivrableDataRepository;

@Component
public class DbWriter implements ItemWriter<LivrableData>{

	@Autowired
	private LivrableDataRepository livrableDataRepository;
	@Override
	public void write(List<? extends LivrableData> livrableData) throws Exception {


		System.out.println("Données à écrire dans la base de données H2");
		
		livrableDataRepository.saveAll(livrableData);
	}

}
