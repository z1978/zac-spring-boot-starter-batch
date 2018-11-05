package com.zac.batch.job.stress.testing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;

import com.zac.batch.BatchConstants;
import com.zac.batch.dto.StressTestingDto;
import com.zac.batch.entity.StressTesting;
import com.zac.batch.util.DateUtil;

@Component(BatchConstants.STRESS_TESTING_JOB_ITEM_PROCESSOR_ID)
public class StressTestingProcessor implements ItemProcessor<StressTestingDto, StressTesting> {

	private static final Logger LOGGER = LoggerFactory.getLogger(StressTestingProcessor.class);

	public StressTesting process(StressTestingDto item) throws Exception {
		

		
		//妥当性チェック
	    BindException e = new BindException(item, "testDate");
	    if(!DateUtil.isValidDate(item.getTestDate(), "yyyy-MM-dd")){
	      e.rejectValue("testDate", "error.data", "testDate日付です");
	      return null;
	    }
//	    ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
//	    if(e.hasErrors()){
//	      throw e;
//	    }
	    

		final String testPoint = item.getTestPoint().toUpperCase();
		final String testDate = item.getTestDate().toUpperCase();

		final StressTesting transformed = new StressTesting(testPoint, testDate);

		LOGGER.info("Converting (" + item + ") into (" + transformed + ")");

		return transformed;
	}
}