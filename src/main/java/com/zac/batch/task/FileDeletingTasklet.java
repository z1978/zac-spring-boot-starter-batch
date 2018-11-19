package com.zac.batch.task;

import java.io.File;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

public class FileDeletingTasklet implements Tasklet, InitializingBean {

	private Resource directory;

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(directory, "directory must be set");
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

		File dir = directory.getFile();

		File[] files = dir.listFiles();
		for (File f : files) {
			System.out.println(f.getName());
			boolean deleted = f.delete();
			if (!deleted) {
				throw new UnexpectedJobExecutionException("Could not delete file " + f.getPath());
			} else {
				System.out.println(f.getPath() + " is deleted!");
			}
		}
		return RepeatStatus.FINISHED;
	}

	public Resource getDirectory() {
		return directory;
	}

	public void setDirectory(Resource directory) {
		this.directory = directory;
	}

}
