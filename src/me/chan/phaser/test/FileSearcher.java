package me.chan.phaser.test;

import java.io.File;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class FileSearcher implements Runnable {

	
	private String dirPath;
	private String suffix;
	private List<String> absoluteFiles;
	private Phaser phaser;
	
	public FileSearcher(String dirPath, String suffix, Phaser phaser) {
		this.dirPath = dirPath;
		this.suffix = suffix;
		this.phaser = phaser;
		absoluteFiles = new LinkedList<>();
	}
	
	@Override
	public void run() {
		phaser.arriveAndAwaitAdvance();
		System.out.println(Thread.currentThread().getName() + " starting.");
		File file = new File(dirPath);
		if (file.isDirectory()) {
			processDir(file);
		}
		
		if (isEmptyResult()) 
			return ;
		
		filterResult();
		showInfo();
		phaser.arriveAndDeregister();
		System.out.println("Mission of "+ Thread.currentThread().getName() +" completed!");
	}
	
	
	public void processDir(File dir) {
		File[] files = dir.listFiles();
		if (files != null && files.length > 0) {
			for (File f : files) {
				if (f.isDirectory()) {
					processDir(f);
				} else {
					processFile(f);
				}
			}
		}
	}

	private void processFile (File file) {
		if (file.getName().endsWith(suffix)) {
			absoluteFiles.add(file.getAbsolutePath());
		}
	}
	
	private void filterResult() {
		absoluteFiles.stream().filter((path) -> {
			File file =  new File(path);
			long timestamp = new Date().getTime();
			return timestamp - file.lastModified() 
					< TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS);
						
		}).collect(Collectors.toList());
	}
	
	
	private boolean isEmptyResult() {
		boolean res = true;
		if (absoluteFiles.isEmpty()) {
			System.out.printf("%s: Phase %d: 0 result.\n",
					Thread.currentThread().getName(), phaser.getPhase());
			phaser.arriveAndDeregister();
		} else {
			System.out.printf("%s: Phase %d get %d result.\n",
					Thread.currentThread().getName(), phaser.getPhase(), absoluteFiles.size());
			res = false;
		}
		
		return res;
	}
	
	
	private void showInfo() {
		/*
		for (String abspath : absoluteFiles) {
			System.out.printf("%s: %s\n", Thread.currentThread().getName(), abspath);
		}
		*/
		
		//use lambda expression instead
		absoluteFiles.stream().forEach((abspath) -> {
			System.out.printf("%s: %s\n", Thread.currentThread().getName(), abspath);
		});
		phaser.arriveAndAwaitAdvance();
	}

}
