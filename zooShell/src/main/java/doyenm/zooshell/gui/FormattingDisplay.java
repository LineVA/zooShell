//package gui;
//
//import com.google.common.annotations.VisibleForTesting;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Iterator;
//import zoo.paddock.PaddockCoordinates;
//
///**
// *
// * @author doyenm
// */
//public class FormattingDisplay {
//
//    final static int thicknessContour = 1;
//    final static char[] signArray = {'*', '&', '~', '-', '=', '@', '+', '?', '§', 'µ', '£',
//        '$', 'a', 'z', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'q', 's', 'd',
//        'f', 'g', 'h', 'j', 'k', 'l', 'm', 'w', 'x', 'c', 'v', 'b', 'n'};
//
//    public static String singleLine(String str) {
//        return str;
//    }
//
//    private static char[][] d2ArrayCharInitialize(char[][] array) {
//        for (int i = 0; i < array[0].length; i++) {
//            Arrays.fill(array[0], ' ');
//        }
//        return array;
//    }
//
//    private static String d2ArrayToString(char[][] array) {
//        String finalStr = "";
//        for (int i = 0; i < array[0].length - 1; i++) {
//            for (int j = 0; j < array.length; j++) {
//                finalStr += array[j][i];
//            }
//            finalStr += "\n";
//        }
//        for (int j = 0; j < array.length; j++) {
//                finalStr += array[j][array[0].length - 1];
//            }
//        return finalStr;
//    }
//
//    private static char[][] contourMap(char[][] array) {
//        for (int i = 0; i < array[0].length; i++) {
//            array[0][i] = '#';
//            array[array.length - 1][i] = '#';
//        }
//        for (int j = 0; j < array.length; j++) {
//            array[j][0] = '#';
//            array[j][array[0].length - 1] = '#';
//        }
//        return array;
//    }
//
//    private static char[][] paddockMap(PaddockCoordinates coor, char[][] array, char sign) {
//        for (int i = coor.getX() + thicknessContour; i < coor.getX() + coor.getWidth() + thicknessContour; i++) {
//            for (int j = coor.getY() + thicknessContour; j < coor.getY() + coor.getHeight() + thicknessContour; j++) {
//                array[i][j] = sign;
//            }
//        }
//        return array;
//    }
//
//    public static String zooMap(int width, int height, ArrayList<PaddockCoordinates> list) {
//        char[][] array = new char[width + 2][height + 2];
//        array = d2ArrayCharInitialize(array);
//        array = contourMap(array);
//        Iterator it = list.iterator();
//        PaddockCoordinates next;
//        int cpt = 0;
//        while (it.hasNext()) {
//            next = (PaddockCoordinates) it.next();
//            array = paddockMap(next, array, signArray[cpt%signArray.length]);
//            cpt++;
//        }
//        return d2ArrayToString(array);
//    }
//
//    public static String idDisplay(String name) {
//        return " coco";
//    }
//
//    public static String formattingArrayList(ArrayList<String> list) {
//        Iterator it = list.iterator();
//        String str = "";
//        String finalStr = "";
//        while (it.hasNext()) {
//            str = (String) it.next();
//            if (it.hasNext()) {
//                finalStr += str + "\n";
//            } else {
//                finalStr += str;
//            }
//        }
//        return finalStr;
//    }
//}
