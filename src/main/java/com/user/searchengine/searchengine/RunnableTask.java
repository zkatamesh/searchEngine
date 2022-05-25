package com.user.searchengine.searchengine;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class RunnableTask {
    public static HashMap<String, File> searchResults;
//

    public static HashMap<String /*KeyPhrase*/, File> createSearchResults() {
        File img = new File("./src/main/resources");
        HashMap<String, File> images = new HashMap<String, File>();
        String name = "";
        String newName = "";
        HashSet<String> exts = new HashSet<String>(Arrays.asList(".png", ".jpg", ".jpeg", ".gif", ".webp"));
        for (File folder : img.listFiles()) {
            //System.out.println(folder.getName() + folder.getClass().getSimpleName());
            /*if(folder.isDirectory()) {
                images.put(folder.getName(), folder);
            }*/
            boolean isImage = false;

            if (folder.isDirectory()) {
                continue;
            }

            for (String ext : exts) {
                if (folder.getName().toLowerCase().contains(ext)) {
                    isImage = true;
                }
            }

            if (!isImage) {
                continue;
            }

            try {
                if (ImageIO.read(folder) != null) ;
                name = folder.getName();
                int index = name.indexOf(".");
                newName = name.substring(0, index);
                images.put(newName.toLowerCase(), folder);
            } catch (IOException e) {
                e.printStackTrace(); //might not be needed
                System.out.println(folder);
            }

        }
        setSearchResults(images);
        return images;


    }

//    public static HashMap<String /*KeyPhrase*/, File> createSearchResults() throws FileNotFoundException {
//        File img = new File("./src/main/resources");
//        HashMap<String, File> images = new HashMap<String, File>();
//        String name = "";
//        String newName = "";
//        for (File folder : img.listFiles()) {
//            FileInputStream inputStream = new FileInputStream(folder); //new
//            Image image = new Image(inputStream); //new
//            name = folder.getName();
//            images.put(name.toLowerCase(), folder);
//        }
//        setSearchResults(images);
//        return images;
//    }

    public static HashMap<String, File> getSearchResults() {
        return searchResults;
    }

    public static void setSearchResults(HashMap<String, File> a) {
        searchResults = a;
    }

   /* public static HashSet<Link> search(String keyPhrase) {

    }*/

    /*public static void displayImage(String keyPhrase) {
        HashMap<String, File> results = createSearchResults();
        if(results.containsKey(keyPhrase)) {
            try {
                Image image = ImageIO.read(RunnableTask.class.getResource("/" + keyPhrase + ".jpg"));
                JFrame frame = new JFrame() {
                    @Override
                    public void paint(Graphics g) {
                        super.paint(g);
                        g.drawImage(image, 0, 0, this);
                    }
                };
                frame.setSize(image.getWidth(frame), image.getHeight(frame));
                frame.setVisible(true);
            } catch (Exception ignored) {

            }
        }
    } */

    public static void displayImage(String keyPhrase) {
        HashMap<String, File> results = createSearchResults();
        if (results.containsKey(keyPhrase)) {
            try {
                File image = new File(String.valueOf(RunnableTask.class.getResource("/" + keyPhrase + ".jpg")));
                InputStream stream = new FileInputStream(image);
                Image imageToPrint = new Image(stream);
                ImageView imageView = new ImageView();
                imageView.setImage(imageToPrint);
                imageView.setX(10);
                imageView.setY(10);
                imageView.setFitWidth(575);
                imageView.setPreserveRatio(true);
                Group root = new Group(imageView);
                //Scene scene = new Scene(root, 595, 370);

            } catch (Exception ignored) {

            }
        }
    }

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        String keyPhrase = keyboard.next().toLowerCase();
        //displayResults(search(keyPhrase));
        //HashMap<String, File> results = createSearchResults();
        //System.out.println(results); //empty for some reason
        displayImage(keyPhrase);
        //HashMap<String, Image> a = createSearchResults();
    }
}
