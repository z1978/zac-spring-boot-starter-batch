package com.zac.batch.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import com.zac.batch.util.ZacUtil;

@Component
public class Task1 implements Tasklet {
	final String className = new Object(){}.getClass().getEnclosingClass().getName();
	private static final Logger LOGGER = LoggerFactory.getLogger(Task1.class);

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

		if (isCheckOK()) {
			// 成功
			contribution.setExitStatus(ExitStatus.COMPLETED);
		} else {
			// 失敗
			contribution.setExitStatus(ExitStatus.FAILED);
		}

		LOGGER.debug("----------" + className + " = FINISHED");
		LOGGER.debug("---------- FINISHED");
		return RepeatStatus.FINISHED;
	}

	public boolean isCheckOK() {
		return true;
	}

}
