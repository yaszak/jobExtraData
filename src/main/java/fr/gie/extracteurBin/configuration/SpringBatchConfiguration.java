package fr.gie.extracteurBin.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import fr.gie.extracteurBin.model.LivrableData;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfiguration {

	@Bean
	public Job jobFichierBinaire(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
			ItemReader<LivrableData> itemReader, ItemProcessor<LivrableData, LivrableData> itemProcessor,
			ItemWriter<LivrableData> itemWriter) {

		Step step = stepBuilderFactory.get("Lecture Fichier").<LivrableData, LivrableData>chunk(100).reader(itemReader)
				.processor(itemProcessor).writer(itemWriter).build();

		Job job = jobBuilderFactory.get("Extraction des documents").incrementer(new RunIdIncrementer()).start(step)
				.build();

		return job;

	}

	@Bean
	public FlatFileItemReader<LivrableData> fileItemReader(@Value("${input.file}") Resource inputFile) {

		FlatFileItemReader<LivrableData> fileItemReader = new FlatFileItemReader<>();
		fileItemReader.setResource(inputFile);
		fileItemReader.setName("CSV-READER");
		fileItemReader.setLinesToSkip(1);
		fileItemReader.setLineMapper(lineMapper());
		return fileItemReader;
	}

	private LineMapper<LivrableData> lineMapper() {

		DefaultLineMapper<LivrableData> defaultLineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();

		delimitedLineTokenizer.setDelimiter(",");
		delimitedLineTokenizer.setStrict(false);
		delimitedLineTokenizer.setNames(new String[] { "id", "name", "url", "source" });

		BeanWrapperFieldSetMapper<LivrableData> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
		beanWrapperFieldSetMapper.setTargetType(LivrableData.class);

		defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
		defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);

		return defaultLineMapper;
	}
}
