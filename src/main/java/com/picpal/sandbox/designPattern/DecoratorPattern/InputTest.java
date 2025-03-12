package com.picpal.sandbox.designPattern.DecoratorPattern;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputTest {
    public static void main(String[] args) throws Exception {

        int c;
        try{
            String filePath = "C:\\Users\\bwc\\Desktop\\picpal\\sandbox\\src\\main\\java\\com\\picpal\\sandbox\\designPattern\\DecoratorPattern\\test.txt";
            InputStream in = new LowerCaseInputStream(new BufferedInputStream(new FileInputStream(filePath)));

            while ((c = in.read()) >= 0){
                System.out.print((char) c);
            }

            in.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
