package com.baijia123.executor;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import com.baijia123.executor.FutureRender.ImageData;
import com.baijia123.executor.FutureRender.ImageInfo;


public class Renderer {
	
	public class ImageData {

	}

	public class ImageInfo {
		public ImageData downloadImage() {
			return null;
		};
	}
	
	private final ExecutorService executor;
	
	Renderer(ExecutorService executor){
		this.executor = executor;
	}
	
	void renderPage(CharSequence source){
		List<ImageInfo> info = scanForImagesInfo(source);
		CompletionService<ImageData> completionService = new ExecutorCompletionService<ImageData>(executor);
		for(final ImageInfo imageInfo : info){
			completionService.submit(new Callable<ImageData>(){

				@Override
				public ImageData call() throws Exception {
					// TODO Auto-generated method stub
					return imageInfo.downloadImage();
				}	
			});
		}
		
		renderText(source);
		try{
			for(int t = 0, n = info.size(); t < n; t++){
				Future<ImageData> f= completionService.take();
				ImageData imageData = f.get();
				renderImage(imageData);
			}
		}catch(InterruptedException e){
			Thread.currentThread().interrupt();
		}catch(ExecutionException e){
			throw launderThrowable(e.getCause());
		}
	}
	
	public List<ImageInfo> scanForImagesInfo(CharSequence source) {
		return Collections.emptyList();
	}

	public void renderText(CharSequence source) {
	}

	public void renderImage(ImageData data) {
	}

	public RuntimeException launderThrowable(Throwable t) {
		if (t instanceof RuntimeException)
			return (RuntimeException) t;
		else if (t instanceof Error)
			throw (Error) t;
		else
			throw new IllegalStateException("Not unchecked", t);
	}
	
}
