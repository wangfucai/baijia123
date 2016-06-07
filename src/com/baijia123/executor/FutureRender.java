package com.baijia123.executor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureRender {
	public class ImageData {

	}

	public class ImageInfo {
		public ImageData downloadImage() {
			return null;
		};
	}

	private final ExecutorService executor = Executors.newFixedThreadPool(5);

	void renderPage(CharSequence source) {
		final List<ImageInfo> imageInfos = scanForImagesInfo(source);
		Callable<List<ImageData>> task = new Callable<List<ImageData>>() {

			@Override
			public List<ImageData> call() throws Exception {
				// TODO Auto-generated method stub
				List<ImageData> result = new ArrayList<ImageData>();
				for (ImageInfo imageInfo : imageInfos) {
					result.add(imageInfo.downloadImage());
				}
				return result;
			}

		};
		Future<List<ImageData>> future = executor.submit(task);
		renderText(source);

		try {
			List<ImageData> imageData = future.get();
			for (ImageData data : imageData)
				renderImage(data);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			future.cancel(true);
		} catch (ExecutionException e) {
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
