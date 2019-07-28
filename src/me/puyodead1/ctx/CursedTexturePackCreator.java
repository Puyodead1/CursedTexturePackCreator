package me.puyodead1.ctx;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;

public class CursedTexturePackCreator {

	private static List<File> files = new ArrayList<File>();
	private static List<String> temp = new ArrayList<String>();

	public static void main(String[] args) {
		final File tpFolder = new File(System.getenv("appdata") + "\\.minecraft\\resourcepacks\\CursedMinecraft");
		if (!tpFolder.exists())
			tpFolder.mkdir();
		final File default_assets = new File(
				System.getenv("appdata") + "\\.minecraft\\resourcepacks\\CursedMinecraft\\default_assets");
		final File path = new File(System.getenv("appdata") + "\\.minecraft\\resourcepacks\\CursedMinecraft\\assets");

		if (!default_assets.exists() || !path.exists()) {
			System.out.println("Missing default assets or base assets!");
			System.exit(1);
		}

		/*
		 * Populate files list
		 */
		for (final File file : default_assets.listFiles())
			processFile(file);

		/*
		 * Make sure the asset directory exists
		 */
		if (!path.exists())
			path.mkdir();

		for (File f : files)
			temp.add(f.getName());

		while (files.size() > 0) {
			/*
			 * Process file1
			 */
			final int randomInt1 = randomIndex();
			File randomFile1 = files.get(randomInt1);
			File randomFileAsset1 = null;
			if (temp.contains(randomFile1.getName()))
				randomFileAsset1 = new File(randomFile1.getAbsolutePath().replace("default_assets", "assets"));
			else
				files.remove(randomInt1);

			/*
			 * Process file2
			 */
			final int randomInt2 = randomIndex();
			File randomFile2 = files.get(randomInt2);
			File randomFileAsset2 = null;
			if (temp.contains(randomFile2.getName()))
				randomFileAsset2 = new File(randomFile2.getAbsolutePath().replace("default_assets", "assets"));
			else
				files.remove(randomInt2);

			String type1 = randomFile1.getAbsolutePath().split("textures")[1].split("\\\\")[1];
			String type2 = randomFile2.getAbsolutePath().split("textures")[1].split("\\\\")[1];

			if (type1.equals(type2)) {
				try {
					FileUtils.copyFile(randomFile1, randomFileAsset2);
					FileUtils.copyFile(randomFile2, randomFileAsset1);
				} catch (IOException e) {
					e.printStackTrace();
				}

				temp.remove(randomInt1);
				files.remove(randomInt1);
				temp.remove(randomInt2);
				files.remove(randomInt2);
			}
		}

		System.out.println("Enjoy....");
	}

	/**
	 * Process a file to check if its a file or directory and adds files to files
	 * array
	 *
	 * @param file
	 */
	private static void processFile(File file) {
		String[] dirsToIgnore = { "colormap", "effect", "environment", "font", "gui", "map", "misc", "models",
				"particle" };
		if (file.isDirectory() && !Arrays.asList(dirsToIgnore).contains(file.getName()))
			for (File f : file.listFiles())
				processFile(f);
		else if (file.getName().endsWith(".png"))
			files.add(file);
		else if (Arrays.asList(dirsToIgnore).contains(file.getName()))
			try {
				FileUtils.copyDirectory(file, new File(file.getAbsolutePath().replace("default_assets", "assets")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	/**
	 * Gets a random index for files array
	 *
	 * @return
	 */
	public static int randomIndex() {
		Random r = new Random();
		return r.nextInt(files.size() - 1);
	}
}
