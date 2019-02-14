package cn.jaly.utils.image;

import java.awt.*;
import java.io.File;


public class ImageUtilsTest{

	public void test() {
		try {
			String str = ImageUtilsTest.class.getResource("/images/org.jpg").getPath();
			File orgJpg = new File(str);
			str = ImageUtilsTest.class.getResource("/images/org.png").getPath();
			File orgPng = new File(str);
			/*
			ImageUtils.fromFile(orgJpg)
				.quality(0.7f)
				.fixedGivenSize(true)
				.keepRatio(true)
				.size(400, 100)
				.toFile(new File("d:\\image\\400_100_not_ratio.jpg"));
			
			ImageUtils.fromFile(orgJpg)
				.quality(0.7f)
				.fixedGivenSize(true)
				.keepRatio(true)
				.size(200, 200)
				.toFile(new File("d:\\image\\200_200_not_ratio.jpg"));
						
			ImageUtils.fromFile(orgJpg)
				.quality(0.7f)
				.keepRatio(true)
				.size(400, 100)
				.toFile(new File("d:\\image\\400_100_ratio.jpg"));
			
			ImageUtils.fromFile(orgJpg)
				.quality(0.7f)
				.keepRatio(true)
				.size(200, 200)
				.toFile(new File("d:\\image\\200_200_ratio.jpg"));
			
			ImageUtils.fromFile(orgJpg)
				.quality(0.7f)
				.width(700)
				.toFile(new File("d:\\image\\test.jpg"));
			
			ImageUtils.fromFile(orgJpg)
				.scale(1)
				.rotate(150)
				.quality(0.6f)
				.bgcolor(Color.BLUE)
				.toFile(new File("d:\\image\\test.jpg"));
			*/
			ImageUtils.fromFile(orgPng)
				.size(200, 200)
				.rotate(34)		//旋转角度
				.quality(0.6f)
				.fixedGivenSize(true)
				.keepRatio(true)
				.bgcolor(Color.blue)	//透明背景
				.toFile(new File("d:\\image\\test.png"));
			/*
			str = ImageUtilsTest.class.getResource("/images/watermarkater.png").getPath();
			BufferedImage watermarkImage = ImageIO.read(new File(str));
			Watermark watermark = new Watermark(Positions.CENTER,
					watermarkImage, 0.6f);
			ImageUtils.fromFile(orgPng)
				.scale(1)
				.watermark(watermark)
				.toFile(new File("d:\\image\\test.png"));
			
			Watermark watermark2 = new Watermark(Positions.BOTTOM_CENTER,
					watermarkImage, 0.6f);
			ArrayList<Watermark> list = new ArrayList<Watermark>();
			list.add(watermark);
			list.add(watermark2);
			ImageUtils.fromFile(orgPng)
				.scale(1)
				.watermarkArray(list)
				.toFile(new File("d:\\image\\testMul.png"));
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}