package com.post.fx.draw.circle;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author xyp
 * @date 2022/5/27 18:59
 */
public class DrawCircle extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //鼠标左键键开始坐标
        AtomicReference<Double> lStartX = new AtomicReference<>((double) 0);
        AtomicReference<Double> lStartY = new AtomicReference<>((double) 0);
        //鼠标右键开始坐标
        AtomicReference<Double> rStartX = new AtomicReference<>((double) 0);
        AtomicReference<Double> rStartY = new AtomicReference<>((double) 0);
        //鼠标右键结束坐标
        AtomicReference<Double> rEndX = new AtomicReference<>((double) 0);
        AtomicReference<Double> rEndY = new AtomicReference<>((double) 0);

        Group root = new Group();
        Scene scene = new Scene(root);

        //新建矩形
        Rectangle rectangle = new Rectangle();
        Ellipse ellipse = new Ellipse();

        root.getChildren().add(ellipse);
        //鼠标按下事件
        scene.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                ellipse.setRadiusX(0);
                ellipse.setRadiusY(0);
                lStartX.set(event.getX());
                lStartY.set(event.getY());
                System.out.println("开始坐标:" + lStartX.get() + "," + lStartY.get());
            } else if (event.getButton() == MouseButton.SECONDARY) {
                System.out.println("鼠标右键键按下");
                rStartX.set(event.getX());
                rStartY.set(event.getY());
                System.out.println(rStartX.get() + "," + rStartY.get());
            }
        });
//        鼠标拖动事件
        scene.setOnMouseDragged(event -> {
            //判断是不是鼠标左键
            if (event.getButton() == MouseButton.PRIMARY) {
                System.out.println("鼠标左键拖动");
                double absX = Math.abs(event.getX() - lStartX.get());
                double absY = Math.abs(event.getY() - lStartY.get());
                if (event.getX() < lStartX.get()) {
                    ellipse.setCenterX(event.getX()+absX/2);
                } else if(event.getX() > lStartX.get()){
                    ellipse.setCenterX(lStartX.get()+absX/2);
                }
                if (event.getY() < lStartY.get()) {
                    ellipse.setCenterY(event.getY()+absY/2);
                } else if(event.getY() > lStartY.get()){
                    ellipse.setCenterY(lStartY.get()+absY/2);
                }
                ellipse.setRadiusX(absX/2);
                ellipse.setRadiusY(absY/2);
                System.out.println(event.getX() + "," + event.getY());
            }
        });
        //鼠标抬起事件
        scene.setOnMouseReleased(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                System.out.println("鼠标左键抬起");
                double absX = Math.abs(event.getX() - lStartX.get());
                double absY = Math.abs(event.getY() - lStartY.get());
                if (event.getX() < lStartX.get()) {
                    ellipse.setCenterX(event.getX()+absX/2);
                } else if(event.getX() > lStartX.get()){
                    ellipse.setCenterX(lStartX.get()+absX/2);
                }
                if (event.getY() < lStartY.get()) {
                    ellipse.setCenterY(event.getY()+absY/2);
                } else if(event.getY() > lStartY.get()){
                    ellipse.setCenterY(lStartY.get()+absY/2);
                }
                ellipse.setRadiusX(absX/2);
                ellipse.setRadiusY(absY/2);
                System.out.println(event.getX() + "," + event.getY());
            } else if (event.getButton() == MouseButton.SECONDARY) {
                System.out.println("鼠标右键抬起");
                rEndX.set(event.getX());
                rEndY.set(event.getY());
                if (Math.abs(rEndX.get() - rStartX.get()) < 10 && Math.abs(rEndY.get() - rStartY.get()) < 10) {
                    if (ellipse.getRadiusX() != 0 || ellipse.getRadiusY() != 0) {
                        ellipse.setRadiusX(0);
                        ellipse.setRadiusY(0);
                    } else {
                        primaryStage.close();
                    }
                }
                System.out.println(rEndX.get() + "," + rEndY.get());
            }
        }); //创建一个robot对象
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                primaryStage.close();
            }
        });
        root.setOpacity(0.5);
        primaryStage.setScene(scene);
        //置顶 最大化 30%透明度 无边框 取消关闭 隐藏任务栏图标
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setMaximized(true);
        primaryStage.setOpacity(0.5);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }
}
